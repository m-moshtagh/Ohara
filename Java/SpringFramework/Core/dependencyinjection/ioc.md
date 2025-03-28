# Inversion Of Control

## Concept

(IoC) is a programming technique in which object coupling is bound at run time by an assembler object and is typically
not known at compile time using static analysis.
IoC relies on dependency injection because a mechanism is needed in order to activate the components providing the
specific functionality.

## How To Implement

1. using a factory pattern
2. using a service locator pattern
3. using a dependency injection of any given below type:
    1. a constructor injection
    2. a setter injection
    3. an interface injection

## IOC in Spring framework

The org.springframework.beans and org.springframework.context packages provide the basis for the Spring Framework’s IoC
container. The BeanFactory interface provides an advanced configuration mechanism capable of managing objects of any
nature. The `ApplicationContext` interface builds on top of the `BeanFactory` (it is a sub-interface) and adds other
functionality such as easier integration with Spring’s AOP features, message resource handling (for use in
internationalization), event propagation, and application-layer specific contexts such as the `WebApplicationContext`
for use in web applications.

The `BeanFactory` is the actual representation of the Spring IoC container that is responsible for containing and
otherwise managing the aforementioned beans. The `BeanFactory` interface is the central IoC container interface in
Spring. The most commonly used BeanFactory implementation is the XmlBeanFactory class. Other commonly used class
is`XmlWebApplicationContext`. Depending on the bean definition, the factory will return either an independent instance
of contained object(the Prototype design pattern), or a single shared instance (a superior alternative to the Singleton
design pattern, in which the instance is a singleton in the scope of the factory)
