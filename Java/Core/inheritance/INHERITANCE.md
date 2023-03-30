# Inheritance in Java

Inheritance is the process by which a subclass automatically includes certain members of the class, including primitives, objects, or methods defined in the parent class.

## Features

* We can reuse primitives, objects and methods defined in superclasses inside subclasss.
* protected members of superclass are available in subclasses.
* Declaring a class `final` will make sure it won't get inherited.
* final, abstract, sealed, non-sealed and static can be applied.
* Java supports Single inheritance.
* All classes in Java extend Object class by default. This is simply handled by compiler.
* Top level classes can only have package, public access modifiers. private and protected can be applied to inner classes.

> Part of what makes multiple inheritance complicated is determining which parent to inherit values from in case of a conflict. For example, if you have an object or method defined in all of the parents, which one does the child inherit? There is no natural ordering for parents in this example, which is why Java avoids these issues by disallowing multiple inheritance altogether.

## This keyword

The this reference refers to the current instance of the class and can be used to access any member of the class, including inherited members. It can be used in any instance method, constructor, or instance initializer block. It cannot be used when there is no implicit instance of the class, such as in a static method or static initializer block.

## Super keyword

Super is a keyword which points to the super class. For example if we declare a same variable in both super and sub classes. Two different variables are availble in subclass one is for super class and one is for itself. we can point to the super class property using `super` keyword.
