# Architecture of Cloud native application

## Basic view

Each of our microservices need to be registered inside a separate microservice named service registry like Eureka.

In order to manage our configuration files like yaml or properties files we need a config server, to manage them in an
easy way.

All these microservices produce their own logs and, we need to aggregate them in order to analyze a process which uses
multiple services. Sleuth, zipkin and ELK stack is used here.

In Domain Driven Design, each domain has its own microservices as business or config server and discovery isolated from
other domains. Thus, we need an API gateway microservice in order to expose each Domain to others. Spring Cloud Gateway
is used here.
Filtering and routing requests is done here.
> This Gateway is not the same as Edge layer API which consumers use.

We Have message brokers as isolated services which we need to get integrated with.

We need to implement Fault tolerance, rate limit, bulkhead pattern.

## Spring Cloud Components

* Distributed Configuration -> Spring cloud configuration, Spring cloud zookeeper, spring consul config
* Distributed messaging -> Spring stream with kafka, rabbitmq
* Service Discovery -> Spring Cloud Eureka, Spring Cloud Consul, Spring Cloud Zookeeper
* Logging -> Spring Cloud Zipkin, Spring Cloud Sleuth
* Spring Service Communication -> Spring Hystrix, Spring Ribbon, Spring Feign, Spring Zuul
