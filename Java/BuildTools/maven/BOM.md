# Bill of Mateial

It's actually like a recipe for building widgets.

***

## Spring Parent POM

Very similar to Maven BOM, spring already provides us a parent POM which provide us a set of curated dependencies. Also common set of properties is inherited.

Differences FROM BOM:

* Doesn't set common dependencies
* Doesn't set common plugins
* Doens't set common plugin configuration

Level of inheritance in our projects:

> Spring Parent POM => BOM => Service POM

We can see the effective POM in our Intellij. We can find several properties and tags that come from parent, if we declare them in our BOM. They get overwitten. like `<licenses>`

## Common poperties

We can define common properties like java.version and things like that in `<properties>`. With this we don't have to define these properties inside any service POMS.

> It's always better to first check if the property exist in parent POM then override it instead of defining a new property.

## DependencyManagement

We can define DependencyManagement Tag and tell what version of dependencies we want in our project. Then in service POM When declaring a dependency coordinate we don't specify version tag.

We can also declare the scope of a specific dependency in BOM.

For common dependencies, We define them inside BOM.

> We can also define common build plugins.
