This sample project demonstrates a very specific Hibernate bug, triggered in the following configuration:
- Use a Postgresql data source
- Have a parameterized @MappedSuperclass
- Have an enum type field on this @MappedSuperclass
- Have a regular @Entity subclass of the parameterized @MappedSuperclass
- Generate the DDL (hibernate.hbm2ddl.auto=create/create-drop/update)

=> When all these conditions are met, the following exception is thrown:
```
Caused by: org.hibernate.MappingException: Error creating SQL create commands for table : with_generics
	at org.hibernate.tool.schema.internal.StandardTableExporter.getSqlCreateStrings(StandardTableExporter.java:174)
	at org.hibernate.tool.schema.internal.StandardTableExporter.getSqlCreateStrings(StandardTableExporter.java:33)
	at org.hibernate.tool.schema.internal.SchemaCreatorImpl.createFromMetadata(SchemaCreatorImpl.java:364)
	at org.hibernate.tool.schema.internal.SchemaCreatorImpl.performCreation(SchemaCreatorImpl.java:176)
	at org.hibernate.tool.schema.internal.SchemaCreatorImpl.doCreation(SchemaCreatorImpl.java:144)
	at org.hibernate.tool.schema.internal.SchemaCreatorImpl.doCreation(SchemaCreatorImpl.java:128)
	at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.performDatabaseAction(SchemaManagementToolCoordinator.java:254)
	at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.lambda$process$5(SchemaManagementToolCoordinator.java:143)
	at java.base/java.util.HashMap.forEach(HashMap.java:1421)
	at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.process(SchemaManagementToolCoordinator.java:140)
	at org.hibernate.internal.SessionFactoryImpl.<init>(SessionFactoryImpl.java:334)
	at org.hibernate.boot.internal.SessionFactoryBuilderImpl.build(SessionFactoryBuilderImpl.java:415)
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1425)
	... 108 more
Caused by: org.hibernate.HibernateException: Unable to resolve JDBC type code for column `with_generics.my_enum`
	at org.hibernate.mapping.Column.getSqlType(Column.java:289)
	at org.hibernate.tool.schema.internal.StandardTableExporter.getSqlCreateStrings(StandardTableExporter.java:99)
	... 123 more
Caused by: org.hibernate.MappingException: Could not determine type for column my_enum of type org.hibernate.type.CustomType: org.hibernate.MappingException
	at org.hibernate.mapping.Column.getSqlTypeCode(Column.java:221)
	at org.hibernate.mapping.Column.getSqlTypeName(Column.java:261)
	at org.hibernate.mapping.Column.getSqlType(Column.java:285)
	... 124 more
Caused by: org.hibernate.MappingException: SQLType code's does not match. mapped as 12 but is -3
	at org.hibernate.mapping.Column.getSqlTypeCode(Column.java:210)
	... 126 more
```

Debugging this a bit, it seems like the SQL type for enum field `my_enum` is resolved at some point to a varbinary (-3) column type
which clashes with previously resolved varchar column type (12).

The bug does NOT occur when:
- The @MappedSuperclass is not parameterized
- The enum field is declared on the @Entity class directly instead of the @MappedSuperclass one
- When using another database like H2 or HSQL (I did not test others)

The bug still occurs when:
- The @Entity does not declare the type parameter (raw usage of generics) on its parent class
- The enum field is not annotated or annotated with @Enumerated(EnumType.ORDINAL) => the exception become "mapped as 5 but is -3"
- Using the pgjdbc-ng Postgres driver

A simple workaround is to set the column definition explicitly with @Column(columnDefinition = "varchar(255)") 
(or similar declaration if the enum should be saved as ordinal value).

All of these cases are exposed in test ContextLoadTest:
- contextLoads() methods succeed
- enumTypingBugTriggered() methods fail with the specific bug