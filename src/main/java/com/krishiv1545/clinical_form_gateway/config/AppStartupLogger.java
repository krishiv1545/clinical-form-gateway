package com.krishiv1545.clinical_form_gateway.config;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;


// Component is just a 'bean'
// in regular Java, we would have to create an instance of this class and call the method
// but in Spring, it will automatically create an instance of this class and call the method
// Spring will manage the lifecycle of this bean, and it will call the onStartup method after the application has started
// 'bean' == object created and managed by Spring, that's it
@Component
public class AppStartupLogger {

    @PostConstruct
    public void onStartup() {

        String green = "\u001B[32m";
        String reset = "\u001B[0m";

        System.out.println(green + "Spring Boot has started managing beans" + reset);
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