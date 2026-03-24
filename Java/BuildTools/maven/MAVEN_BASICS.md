# Basic Concepts

***

## Maven Coordinates

Maven coordinates, identify an artifact in order to find in a maven repository.
It consist of *groupid*, *artifactId* and *version*.

> groupId and Version can be inherited from a parent POM.

### SNAPSHOT

snapshot suffix is an important qulifier for maven behavior. It tells maven it's a developement build.
Maven checks local repository first then, the remote repositories.
By default, maven will check remote repositories once per day and it's configurable.

## Repositories

Maven repositories are locations where project artifacts are stored.

We have three types:

* **Local**: Repo on local system. `.m2` directory
* **Central**: Public repository hosted by maven community
* **Remote**: Others which can be public or private

> For resolving dependencies maven, first checks the local repository then depending on the configuration it checks central or other remote repos.

## Maven Wagon

It's a unified maven api. It's a transport abstraction for artifact and repository handling code.
Allows for different providers to be used for communications with maven repositories.

Available providers are:

* File
* HTTP
* FTP
* SSH
* WebDAV
* SCM

## Maven POM

Project object model describes a maven project. It has a XML schema defining rules that comply with maven-4.0.0.xsd

> POMs can also, inherit properties from a parent POM.

## Dependencies

Dependency Management is one of the best features of maven. It's an artifact which our project depends upon. Transive dependency is dependency of an artifact in our project.

**Dependency Mediation** Determins what version to use when multiple versions of the same dependency are encountered. it chooses the nearest Definition in dependency tree.

> A -> B
> A -> D 2.0 -- This one is going to be used.
> B -> D 1.5

We can also, exclude dependencies or make them optional which excludes them by default for downstream projects.

### Scope

* **Compile**: Default. Available on all classpaths of project. Also, propogated to downstream projects.
* **Provided**: Like compile, but expected to be provided by JDK or Container at runtime.
* **Runtime**: Not required for compile, but needed for runtime. On runtime and test classpaths, not compile.
* **Test**: Only available on test Classpath, not transitive.
* **System**: similar to provided, but JAR is added to system explicitly.(via file path)
* **Import**: imports dependency of POM.

### Dependency plugin

All dependencies are managed by this plugin.
important goals are:

* dependency:tree
* dependency:go-offline
* dependency:purge-local-repository
* dependency:sources

## Build lifecycles

A lifecycle is a pre-defined group of build steps called phases. Each phase can be bound to one or more plugin goals.

> Every work done by maven is bound to a plugin.

### pre-defined lifecycles

* **Clean**: removes all build artifacts
* **default**: builds and deploys the project
  * *validate*
  * *compile*
  * *test*
  * *package*
  * *verify*
  * *install*
  * *deploy*
* **site**: creates a website for your project

> All the above have phases to go on with the goal.
