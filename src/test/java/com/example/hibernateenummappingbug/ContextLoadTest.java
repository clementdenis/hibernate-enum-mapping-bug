package com.example.hibernateenummappingbug;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
public class ContextLoadTest {

    @Nested
    class H2 {
        @Nested
        @ActiveProfiles({"nogenerics"})
        class noGenerics {
            @Test
            void contextLoads() {
            }
        }

        @Nested
        @ActiveProfiles({"withgenerics"})
        class withGenerics {
            @Test
            void contextLoads() {
            }
        }
    }

    @Nested
    @SpringBootTest(properties = "spring.datasource.url=jdbc:hsqldb:mem:test")
    class HSQL {
        @Nested
        @ActiveProfiles({"withgenerics"})
        class withGenerics {
            @Test
            void contextLoads() {
            }
        }
    }

    /**
     *
     */
    @Nested
    class Postgres {

        @Nested
        @ActiveProfiles({"postgres", "nogenerics"})
        class noGenerics {
            @Test
            void contextLoads() {
            }
        }

        @Nested
        @ActiveProfiles({"postgres", "withgenerics"})
        class withGenerics {
            @Test
            void enumTypingBugTriggered() {
            }
        }

        @Nested
        @ActiveProfiles({"postgres", "enumonentity"})
        class enumOnEntity {
            @Test
            void contextLoads() {
            }
        }

        @Nested
        @ActiveProfiles({"postgres", "forcecolumndefinition"})
        class forceColumnDefinition {
            @Test
            void contextLoads() {
            }
        }

        @Nested
        @ActiveProfiles({"postgres", "rawgenerics"})
        class rawGenerics {
            @Test
            void enumTypingBugTriggered() {
            }
        }
    }

    /**
     * Tries with pgjdbc-ng drive instead of standard one
     */
    @Nested
    class PgJdbcNgDrive {
        @Nested
        @ActiveProfiles({"pgjdbcng", "nogenerics"})
        class noGenerics {
            @Test
            void contextLoads() {
            }
        }

        @Nested
        @ActiveProfiles({"pgjdbcng", "withgenerics"})
        class withGenerics {
            @Test
            void enumTypingBugTriggered() {
            }
        }
    }

}
