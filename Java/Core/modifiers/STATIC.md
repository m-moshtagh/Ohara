# Static Modifier

`static` is one of non-access modifiers that can be applied to inner classes, methods and attributes.

## Usage

`static` modifier can't be used inside:

* Outer class
* inside local block
* constructor
* static local variable

## static methods

static methods(class methods) are the type of methods that can be called without creating an instance from class. We can
directly call static methods by class name.

* accessing encapsulated static fields
* stateless
* utility
* avoid overhead of object creation

```java
public class Example {
    public static void main(String[] args) {
        Integer.parseInt();
    }
}
```

> static methods are also accessible via class instances.

### behaviour

We already know that methods of classes are stored in method area, static methods are no different however, the `this`
object is not passed to them implicitly hence, any functionality of this is not accessible in static methods.

static methods are also part of early binding against non-static methods which are late binding. simply to put
non-static methods behaviour can be changed during runtime because of polymorphism and inheritance however This is not
true in case of static methods.

static methods can be overloaded but not overridden.

> because of early binding compiler can inline static methods.

## static fields

by declaring a field static, we only reserve one memory space for that field which will be shared among all instances of
the class.

> final static fields can be inlined by compiler.

## static initialization block

static initialization block only runs once after classloading and initializing static fields, before initializing
non-static fields.


