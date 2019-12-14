package com.jabirinc.jabirincpetclinic;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 *          ==== What to test in integration testing ====
 * While doing integration testing in spring boot applications, we shall keep in mind that:
 *
 *      - An integration test is supposed to test whether different modules are bounded correctly and if they
 *      work as expected.
 *      - The integration tests shall not utilize the actual production dependencies (e.g. database/network) and
 *      they can mimic the certain behaviors.
 *      - The application shall run in ApplicationContext and run tests in it.
 *      - Spring boot provides @SpringBootTest annotation which starts the embedded server, creates a web environment
 *      and then enables @Test methods to do integration testing. Use it’s webEnvironment attribute for it.
 *      - It also creates the ApplicationContext used in our tests.
 *      - It is good to use h2 in-memory DB for mimicking the database. Though it is not mandatory and we can use
 *      mockito to mock the database interactions.
 *      - It is recommended to use test specific configurations using @TestConfiguration annotation.
 *
 *
 * @ExtendWith(SpringExtension.class) – [ Junit 5 ]
 * SpringExtension integrates the Spring TestContext Framework into JUnit 5’s Jupiter programming model.
 * The SpringExtension class is essentially the entry-point to start using the Spring Test framework.
 *
 * @SpringBootTest
 * This annotation helps in writing integration tests. It starts the embedded server and fully initializes
 * the application context. We can inject the dependencies in test class using @Autowired annotation.
 * By default, it scans and loads the whole application context and all spring boot related auto-configurations.
 *
 * Under the hood, @SpringBootTest tries to mimic the processes added by Spring Boot framework for creating the
 * context e.g. it decides what to scan based on package structures, loads external configurations from predefined
 * locations, optionally runs auto-configuration starters and so on.
 *
 * We can also provide test specific beans configuration using nested @Configuration class or
 * explicit @TestConfiguration classes.
 *
 * It also provides support for different webEnvironment modes and running web server listening on a
 * defined or random port. It also registers a TestRestTemplate and/or WebTestClient bean for use in web tests.
 *
 * Note:
 * In a typical Spring integration test, you'd annotate the test class with @ContextConfiguration to specify
 * defines class-level metadata that is used to determine how to load and configure an ApplicationContext.
 * We can specify @SpringBootTest annotation on a test class that runs Spring Boot based tests.
 * @SpringBootTest provides the following features over and above the regular Spring TestContext provided
 * by @ContextConfiguration(classes=…​) annotation in spring-test.
 *
 *      - Automatically searches for a @SpringBootConfiguration when nested @Configuration class is not used,
 *      and no explicit classes are specified.
 *      - Allows custom environment properties to be defined using the properties attribute.
 *      - Provides support for different webEnvironment modes, including the ability to start a fully running
 *      web server listening on a defined or random port.
 *      - Registers a TestRestTemplate and/or WebTestClient bean for use in web tests that are using a fully
 *      running web server.
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JabirincPetClinicApplicationTests {

    @Test
    public void contextLoads() {
    }
}
