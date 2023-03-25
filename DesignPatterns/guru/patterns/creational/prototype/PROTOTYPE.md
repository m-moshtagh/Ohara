# Prototype Design Pattern

Prototype is a creational design pattern that lets you copy existing objects without making your code dependent on their
classes.

## Problem

If we try to copy an existing object, we need to go through all the fields of the original object and copy their values
over to the new object. But the problem is that some variables are private and not visible from outside. We also get
dependent to the class.

## Solution

The pattern declares a common interface for all objects that support cloning. This interface allows us to clone the
object without coupling your code to the class of that object. Object that supports cloning is called prototype.
The prototype interface declares the clone method. The concrete prototype class implements cloning linked objects.
We need a constructor with Class type parameter. constructor must copy the values of current object into new one.

> For subclasses, we need to call `super()` too.

further detail in [refactoring.guru](https://refactoring.guru/design-patterns/prototype)

## Implementation

```java
public interface Cloneable {
    Object clone();
}
```

```java
public class Prototype1 implements Cloneable {
    private int x;

    public Prototype(Prototype prototype) {
        this.x = prototype.x;
    }

    public Object clone() {
        return new Prototype(this);
    }
}
```

```java
public class Prototype2 extends Prototype1 {
    private int y;

    public Prototype2(Prototype2 prototype) {
        super(prototype);
        this.y = prototype.y;
    }

    public Object clone() {
        return new Prototype2(this);
    }
}
```
