package com.jabirinc.jabirincpetclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot @SpringBootApplication Annotation
 * Spring Boot @SpringBootApplication annotation is used to mark a configuration class that declares one or
 * more @Bean methods and also triggers auto-configuration and component scanning. It’s same as declaring a
 * class with @Configuration, @EnableAutoConfiguration and @ComponentScan annotations.
 *
 *      --- Understanding Spring Boot Web MVC Auto Configuration ---
 * WebMvcAutoConfiguration is responsible for Auto-configuration for Web MVC. Following is its snippet
 * (spring-boot-2.2.2.RELEASE):
 * @Configuration(proxyBeanMethods=false)
 *  @ConditionalOnWebApplication(type=SERVLET)
 *  @ConditionalOnClass(value={javax.servlet.Servlet.class,org.springframework.web.servlet.DispatcherServlet.class,org.springframework.web.servlet.config.annotation.WebMvcConfigurer.class})
 *  @ConditionalOnMissingBean(value=org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport.class)
 *  @AutoConfigureOrder(value=-2147483638)
 *  @AutoConfigureAfter(value={DispatcherServletAutoConfiguration.class,TaskExecutionAutoConfiguration.class,ValidationAutoConfiguration.class})
 * public class WebMvcAutoConfiguration extends Object {
 *      Nested Classes
 *      static class 	WebMvcAutoConfiguration.EnableWebMvcConfiguration
 *      static class 	WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter
 * }
 *
 * As seen in above snippet, the following conditions must be met for this auto configuration to work:
 *
 *  1 - Servlet, DispatcherServlet and WebMvcConfigurer classes must be present on the classpath. If the dependency
 *  of spring-boot-starter-web has been included in the project then all of these classes will be present.
 *  2 - The application context being used must be web application context. When we start spring boot application
 *  via SpringApplication class, AnnotationConfigEmbeddedWebApplicationContext is initialized as ApplicationContext
 *  only if ConfigurableWebApplicationContext is on the classpath (included when used spring-boot-starter-web),
 *  otherwise AnnotationConfigApplicationContext is initialized.
 *  3 - WebMvcConfigurationSupport must not already be registered as bean. In a typical web MVC application,
 *  this class is registered when we use @EnableWebMvc.
 *
 *      Where to find Boot MVC Configurations?
 * It's good to know where Spring Boot does MVC related beans registration and configuration so that we can
 * investigate and find answers if they are not available in the documentations. Following are two important classes:
 *
 *  WebMvcAutoConfigurationAdapter: is a nested configuration class in WebMvcAutoConfiguration which extends
 *      WebMvcConfigurerAdapter and has Boot specific necessary beans registration for Web MVC.
 *
 *  EnableWebMvcConfiguration: The other nested configuration class we should know is EnableWebMvcConfiguration.
 *      This class extends DelegatingWebMvcConfiguration which is capable of discovering more WebMvcConfigurer
 *      (needed for application side configuration as stated in the last section).
 *
 *      Adding additional Spring Web MVC Components
 * If we need to add additional MVC configuration (interceptors, formatters, view controllers etc.) we have two ways
 * 1 - Annotate the class (interceptors, formatters ...) as component and it will be automatically included
 * 2 - we can add our own @Configuration class of type WebMvcConfigurerAdapter in SpringBootApplication main class as
 * follows, for example to add A custom Formatter class TradeAmountFormatter
 *
 *      @SpringBootApplication
 *      public class BootMain {
 *          public static void main(String[] args) {
 *              SpringApplication.run(BootMain.class, args);
 *          }
 *
 *          @Configuration
 *          static class MyConfig extends WebMvcConfigurerAdapter {
 *              @Override
 *              public void addFormatters(FormatterRegistry registry) {
 *                  registry.addFormatter(new TradeAmountFormatter());
 *              }
 *          }
 *      }
 *
 *
 *
 * SpringBoot by default scan packages where this class is defined and below.
 * If one has a package defined outside/above this class package, we need to
 * tell and specify where SpringBoot do component scan.
 * For example if we have a package "com.jabirinc.outside", include this
 * @ComponentScan(basePackages = {"com.jabirinc.outside", "com.jabirinc.jabirincpetclinic"})
 */

@SpringBootApplication
public class JabirincPetClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(JabirincPetClinicApplication.class, args);
    }

}
