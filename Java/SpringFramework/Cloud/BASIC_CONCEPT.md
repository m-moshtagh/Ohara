# Cloud native applications

## 5 Elements of cloud native application

* Application Design -> Towards microservice architecture
* API exposure -> Internal and external access is via standardized methods.
* Operational integration -> Aggregating log and monitoring information to enable application management
* DevOps -> Automation across application lifecycle
* Testing -> Changing the role and use of quality assurance (QA)

## 12 Factors for a cloud application

With these factors we can say we have a cloud native application.

### One Code repository per application for multiple deployments

This means we have one codebase which can be deployed in several environments like development, production, test and
staging. Which means the difference between them is just configuration.

### Dependency isolation and dependency

This means we need to manage our dependencies using a dependency management system like Jfrog or nexus.

### Store the configuration in the environment

We need to centralize the configurations of the code base so each instance and environment can use the one it needs.

### External services as attachable and detachable services

Resources like Database, message service should be as external services to use them.

### Assembly, publication and execution

To manage microservice lifecycle separately from beginning to end.

### Stateless processes

Processes inside cloud native application should be stateless and stateful processes should be transferred to workflow
engine.

### Use ports to expose services

We need to easily expose application with a port.

### Concurrency and independent processes

They should work concurrently

### Quick starts and graceful stops

Because of tomcat runtime engine They should have rapid start up and downtime.

### Less gap between development and production

The only gap should be in configurations.

### Handle logs and monitoring flows

We should monitor applications with adequate tools.

### Administration processes.

The processes should be fully administrative.

## Packaging Cloud native applications

We can package them as 'All in one' or 'each service with its own codebase'. The second one is recommended.
