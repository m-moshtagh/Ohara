# Maven for spring boot

***

## Spring boot POM & BOM

When we create a sping boot project we are going to inherit from a spring boot parent. It's in a remote repository and we import it in our POM.

The Hierarchy:

Spring boot project => Spring boot starter parent => Spring boot dependencies => Spring boot build => Maven  supe POM

Spring team define version of compatible components that we can use in our project. We can also define version tag of them in our project to override it.

## Spring boot starters

Spring staters bring in all the dependencies we need to fulfill a cause. For example when adding spring starter web it brings spring starter json, spriing starter tomcat and ...

We can always determine the dependencies using: `mvn dependencies:tree`

## Spring boot Fat JAR

Spring boot builds our project and gives us a FAT or Uber jar which self deployable. it contains everything we need in a single JAR.

## Running Spring boot from maven

we can also run spring boot app using spring boot plugin `mvn spring-boot:run`
