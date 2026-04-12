# Testing with Maven

***

We simply use surefire plugin for unit tests and failsafe plugin for integration tests.

## Surefire

When executing test goal, maven surefire plugin checks all the classes with specific prefixes that have `test` in their names and run their methods. and generates a report for us.

> When we add dependencies that are only used in test classes we set scope of them to test so, they are not included in packaging.

### Version of libraries

We need to be careful that the version of the surefire plugin that comes with maven does support libraries like junit5 or we need to explicitly announce a version of the plugin inside POM.xml.

## Failsafe

failsafe plugin is used for running integration tests.
This plugin is not included by default. We need to configure it and bind it to a goal like verify. It checks for classes with naming convention of `IT`

## JaCoCo

This plugin generates a report from our test converage.

## SpotBugs

This plugin checks for spitfalls in our code.

## Skipping tests

Inside intellij we can use toggle skip test button.
We can also have a `<skipTest>` and `<skipITs>` true in properties.
We can use `-DskipTests` and `-DskipITs`.
