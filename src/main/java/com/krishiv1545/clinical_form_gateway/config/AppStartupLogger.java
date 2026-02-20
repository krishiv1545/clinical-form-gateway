package com.krishiv1545.clinical_form_gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;


// Component is just a 'bean'
// in regular Java, we would have to create an instance of this class and call the method
// but in Spring, it will automatically create an instance of this class and call the method
// Spring will manage the lifecycle of this bean, and it will call the onStartup method after the application has started
// 'bean' == object created and managed by Spring, that's it
@Component
public class AppStartupLogger {

    // DEPENDENCY INJECTION
    @Autowired
    private ApplicationContext context;

    @PostConstruct
    public void onStartup() {

        String green = "\u001B[32m";
        String reset = "\u001B[0m";

        System.out.println(green + "Spring Boot has started managing beans" + reset);
        System.out.println(green + "Total Beans: " + context.getBeanDefinitionCount() + reset);
        // we created 1 bean (AppStartupLogger)
        // but Spring Boot creates a lot of beans for us
        // this is auto-configuration, which is one of the main features of Spring Boot

        boolean hasDataSource = context.containsBean("dataSource");
        boolean hasEntityManager = context.containsBean("entityManagerFactory");
        boolean hasSecurityFilterChain = context.containsBean("springSecurityFilterChain");
        boolean hasDispatcherServlet = context.containsBean("dispatcherServlet");

        System.out.println("Has DataSource: " + hasDataSource);
        System.out.println("Has EntityManagerFactory: " + hasEntityManager);
        System.out.println("Has SecurityFilterChain: " + hasSecurityFilterChain);
        System.out.println("Has DispatcherServlet: " + hasDispatcherServlet);
    }
}
// DEPDENDENCY INJECTION W/ EXAMPLE

// class A {
//     B b = new B();
// }

// in regular Java, we would have to create an instance of B
// thi is tightly coupled, because A is directly dependent on B, and if we want to change the implementation of B, we would have to change the code in A as well
// but in Spring, it will automatically create an instance of B

// class A {
//     @Autowired
//     B b;
// }

// in Spring, we can use @Autowired to inject the dependency of B into A
// Dependency Injection is a design pattern that allows us to inject the dependencies of a class into it
// rather than having the class create its own dependencies.
// This promotes loose coupling (AKA modularity) and makes our code more testable and maintainable.

// APPLICATION CONTEXT
// eg. Map<Class, Object> container;
// its just a container that holds all the beans (objects) that Spring manages
// we can add beans to the container, and we can retrieve them by their class type.

// 1. Spring Boot starts up and creates an application context (container) to manage beans.
// 2. Spring Boot scans the classpath for classes annotated with @Component (or other stereotype annotations like @Service, @Repository, etc.) 
// and creates instances of those classes as beans in the application context.
// it scans the base package 'com.krishiv1545.clinical_form_gateway'
// 3. Spring Boot then looks for methods annotated with @PostConstruct 
// and calls them after the beans have been createdand the application context is fully initialized.

// CONTEXT REFRESH (steps 1-3 above)
// 1. App starts
// 2. context.refresh();
// 3. Spring creates DefaultListableBeanFactory (no beans exist yet)
// 4. @ComponentScan runs
//    @Configuration classes parsed
//    Auto-configuration classes are imported
//    XML configs loaded (if any)
//    Spring builds Map<String, BeanDefinition> (still, no beans exist yet, just definitions (metadata))
// 5. Invoke BeanFactoryPostProcessors (eg. @Configuration classes are processed, and @Bean methods are parsed, and BeanDefinitions are created for those beans)
// 6. Register BeanPostProcessors (eg. @Autowired injector is processed, and BeanPostProcessors are created to handle dependency injection, @PostConstruct handler is processed, and BeanPostProcessors are created to handle @PostConstruct methods)
// (5, 6). BeanFactoryPostProcessor modifies definitions.
//         BeanPostProcessor modifies instances.
// 7. Instantiate singletons (eg. AppStartupLogger is created, and its dependencies are injected, and its @PostConstruct method is called)
// For each bean:-
// createBean()
//     ↓
// instantiate
//     ↓
// populate properties (DI happens here)
//     ↓
// apply BeanPostProcessors (before init)
//     ↓
// call @PostConstruct
//     ↓
// apply BeanPostProcessors (after init)
//     ↓
// store in singleton cache
// AppStartupLogger is initialized and @PostConstruct is called (387 such beans, at the time of writing this comment)

// (this is biased for my project)
// 8. TomcatServletWebServerFactory is created (because we have spring-boot-starter-web dependency)
// 9. Tomcat is started, and it starts listening for HTTP requests (its just another bean in the application context)