# Service Registry

If a microservice wants to communicate, it needs to look up the name of the service it wants So then it can call it.

## Setup

We add Eureka server dependency inside POM file and use `@EnableEurekaServer` configuration.

> It's recommended to use dependency management inside pom or build.gradle to set spring cloud version specific to
> spring boot version. We can check the compatibility
> at [spring cloud documentation](https://spring.io/projects/spring-cloud)
