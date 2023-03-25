# Containerize Spring Boot applications

## Methods

We can create Docker images using 

* integrated plugin
* JID plugin
* Dockerfile maven plugin

### Integrated spring boot plugin

In this method we don't need to write docker file which makes this method not recommended.
`mvn spring-boot:build-image`

### JIB plugin from Google

In this method we need a Dockerfile
`mvn compile com.google.cloud.tools:jib-maven-plugin:2.3.0:dockerBuild`

## Build Optimized Docker image

First we create our dockerfile

```Dockerfile
FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/application.jar"]
```

Then we configure JIB inside POM file

```xml

<plugin>
    <groupId>com.google.cloud.tools</groupId>
    <artifactId>jib-maven-plugin</artifactId>
    <version>2.5.2</version>
</plugin>
```
