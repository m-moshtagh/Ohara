# Constructors

A constructor, is a special type of method that creates a new object.

## Features

the name of the constructor matches the name of the class, and there’s no return type.

```java
public class Chick {
    public void Chick() {}
}
```

## Purpose

The purpose of a constructor is to initialize fields, although you can put any code in there.

## Rules

* A class can contain many overloaded constructors, provided the signature for each is distinct.
* The compiler inserts a default no-argument constructor if no constructors are declared.
* If a constructor calls this(), then it must be the first line of the constructor.
* Java does not allow cyclic constructor calls This is not a constructor:

## Default Constructors

The compiler will supply a “do nothing” default constructor If no constructor is declared manually. There are some scenarios that do require you to declare a constructor.

> This is compile time phase, The default constructor doesn't exist in .java file but will be available in .class files.

Declaring a private constructor useful when a class has only static methods or the developer wants to have full control of all calls to create new instances of the class.

## Call other constructors using `this()`

```java
public class Hamster {
    private String color;
    private int weight;
        public Hamster(int weight, String color) { // First constructor
        this.weight = weight;
        this.color = color;
    }
    public Hamster(int weight) { // Second constructor
        this(weight, "brown");
    }
}
```

We can only use this() on the first statement which means we can only use it once.

Compiler can also, detect cycle calls from Constructos and provides error messages.

```Java
public class Gopher {
    public Gopher() {
        this(5); // DOES NOT COMPILE
    }
    public Gopher(int dugHoles) {
        this(); // DOES NOT COMPILE
    }
}
```

## Parent constructor using `super()`

The first statement of every constructor is a call to a parent constructor using `super()` or another constructor in the class using `this()`.

> Rules for `this()` also applies on `super()`. on the first line either super or this can only exist. By default compiler uses `super()` if we don't mention it.
