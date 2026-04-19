# Multi Module Projects

***

## Parent POM

For building a multimodule project, We create a POM file at the root of the project which has the packaging = POM and all modules inherit from it.

> Modules themselves can have children too.

We need to define modules in the parent section.

```xml
<modules>
    <module>entities</module>
</modules>
```

We also need to define a parent section in each module.
An *artifactId* is also needed.

## Maven Reactor

The Reactor is what builds each module of maven project. It collects the modules to build, Then run select build lifecycle against each module. The reactor will determine the build order of the modules. By default it'll build modules sequentially.

Optionally can use threads to build modules in parallel.

Factors for build order:

* Project dependencies: modules used by other modules in the project will get built first.
* Plugin declaration: IE if module is a plugin used by other modules.
* Order of modules declared in `modules` section of POM.

> We shouldn't overuse modules they slow down project. if a module consist of One class or interface it's a code smell.

## Dependency To Other modules

All we need to do is to add the grroupId and artifactId of modules we want in dependency section.

## Version via maven property

We can define a revision tag inside propeties tag. and reference project cordinate version via that tag. For modules we can use `project.version` to specify project version.

## BOM(Bill of Material)

Meaning effective receipe of components required to produce a widget. In real, we define dependencyManagement section inside POM, fully qualified dependencies are listed there. Then The Dependencies inside dependencies section will inherit from there. This is used to standardized versions.

Any dependencies inside dependencyManagement don't become transitive dependencies for the artifact.

> Spring boot defines it in a remote parent POM. Spring cloud defines an standalone POM then imported into the dependenciesManagement.
