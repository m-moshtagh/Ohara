# Inversion Of Control Pattern

This pattern is not part of GOF patterns. It's responsible for relegating control of dependencies to the spring
Contrainer instead of user creating objects. Dependency Injection is part of IoC.

## How it works?

Central Container maintains and constructs all object references and, hands them to other objects when needed at runtime
or startup.
Spring application context is the IoC container. This container leverages the configuration on creating beans and manage
dedependency injections.
