# Dependency Injection

One of the common best practices that is used to achieve loose coupling is Dependency injection.

## loose coupling

Loose coupling describes an adaptable relationship between a client and a service, where significant changes to the
internals of the service have minimal impact on the client.
It is generally achieved by placing a rigid contract between client and service so that either may evolve independently,
so long as they fulfill the obligations laid down by the contract.

## Creating Dependency

* Direct initialization -> new Class
* Factory method -> Factory.create()
* Dependency Injection -> @Inject Dependency dependency;

## Types of Dependency Injection

* Setter Injection
* Constructor injection
* Method injection
* Field injection

> In order to use dependency injection we need to first ask Three questions:
> 1. What to Inject?(choose correct implementation)
> 2. Where to Inject?(Which Field)
> 3. How to Inject?(Setter, Constructor, Method)

In order to inject dependencies, we can:

* By Type
* By ID

> We can announce these by using a configuration file or annotations.

## Fixed wiring vs Autowiring

in fixed wiring we explicitly announce the dependency which implementation we want to use. hence, we'll have more
control over dependency injection. however this may make code repetitive and also maintenance harder if dependencies are
going to change often.

In Contrast, using autowiring we choose the dependency automatically. Frameworks like spring use this approach.

> Hollywood principle(Don't call us, we will call you)

## Container

Container is a runtime environment which provides services to objects and classes inside it.

## lookup vs Inject

using lookup, we announce the implementation in a config file and container can use the context to get the
implementation by looking it up.
However, in injection we use wiring.