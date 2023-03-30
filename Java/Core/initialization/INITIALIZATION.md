# Initialization

Order of initialization refers to how members of a class are assigned values.

## Initializing Classes

First, we initialize the class, which involves invoking all static members in the class hierarchy, starting with the highest superclass and working downward. This is sometimes referred to as loading the class. The Java Virtual Machine (JVM) controls when the class is initialized, although you can assume the class is loaded before it is used. The class may be initialized when the program first starts, when a static member of the class is referenced, or shortly before an instance of the class is created

The order of the initialization for a class follows:

* If there is a superclass Y of X, then initialize class Y first.
* Process all static variable declarations in the order in which they appear in the class.
* Process all static initializers in the order in which they appear in the class.

## Initializing final variables

There's a simple rule: **by the time the constructor completes, all final instance variables must be assigned a value exactly once.**
So the final instance members should be initialized inline, in constructor or instance initializers blocks.

## Initializing Instances

1. Initialize class X if it has not been previously initialized.
2. If there is a superclass Y of X, then initialize the instance of Y first.
3. Process all instance variable declarations in the order in which they appear in the class.
4. Process all instance initializers in the order in which they appear in the class.
5. Initialize the constructor, including any overloaded constructors referenced with this().
