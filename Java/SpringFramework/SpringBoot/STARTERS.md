# Spring Boot Starters

Spring boot was delivered to ease us with the programming of microservices.
Spring boot is pre-configured and provides dependency group for different jobs.

## Web Starters

First, let's look at developing the REST service; we can use libraries like Spring MVC, Tomcat and Jackson – a lot of
dependencies for a single application. Spring Boot starters can help to reduce the number of manually added dependencies
just by adding one dependency. So instead of manually specifying the dependencies just add one starter as in the
following example:

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

## Test Starters

For testing, we usually use the following set of libraries: Spring Test, JUnit, Hamcrest, and Mockito. We can include
all
of these libraries manually, but Spring Boot starter can be used to automatically include these libraries in the
following way:

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

## The Data JPA Starters

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

## Mail Starters

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```