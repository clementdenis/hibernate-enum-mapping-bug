package com.example.hibernateenummappingbug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class HibernateEnumMappingBugApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateEnumMappingBugApplication.class, args);
	}

	@Profile("withgenerics")
	@Configuration
	@EntityScan(basePackageClasses = com.example.hibernateenummappingbug.entity.generics.TestEntity.class)
	static class GenericsConfiguration {}

	@Profile("nogenerics")
	@Configuration
	@EntityScan(basePackageClasses = com.example.hibernateenummappingbug.entity.nogenerics.TestEntity.class)
	static class NoGenericsConfiguration {}

	@Profile("enumonentity")
	@Configuration
	@EntityScan(basePackageClasses = com.example.hibernateenummappingbug.entity.enumonentity.TestEntity.class)
	static class EnumOnEntityConfiguration {}

	@Profile("forcecolumndefinition")
	@Configuration
	@EntityScan(basePackageClasses = com.example.hibernateenummappingbug.entity.forcecolumndefinition.TestEntity.class)
	static class ForceColumnDefinitionConfiguration {}

	@Profile("rawgenerics")
	@Configuration
	@EntityScan(basePackageClasses = com.example.hibernateenummappingbug.entity.rawgenerics.TestEntity.class)
	static class RawConfiguration {}


}
