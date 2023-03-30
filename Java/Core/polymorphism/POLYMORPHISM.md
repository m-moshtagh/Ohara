# Polymorphism

the property of an object to take on many different forms. A java object may be accessed using:

* A reference with the same type as the object
* A reference that is a superclass of the object
* A reference that defines an interface the object implements or inherits

> a cast is not required if the object is being reassigned to a supertype or interface of the object.

```Java
public class Primate {
    public boolean hasHair() {
        return true;
    }
}
public interface HasTail {
    public abstract boolean isTailStriped();
}
public class Lemur extends Primate implements HasTail {
    public boolean isTailStriped() {
       return false;
    }
    public int age = 10;
    public static void main(String[] args) {
        Lemur lemur = new Lemur();
        System.out.println(lemur.age);
        HasTail hasTail = lemur;
        System.out.println(hasTail.isTailStriped());
        Primate primate = lemur;
        System.out.println(primate.hasHair());
    } 
}
```

* Here only one object Lemur is being created
* Once the object has been assigned to a new reference type, only the methods and variables available to that reference type are callable on the object without an explicit cast.

## Polymorphism Rules

1. The type of the object determines which properties exist within the object in memory.
2. The type of the reference to the object determines which methods and variables are accessible to the Java program.

> In real world scenarios it's recommended to use a common Interface between bunch of classes as reference type. like Collections Framework.

## Casting Objects

Once we change the reference to a superclass type, we can still access the properties of the subtype by casting it.

```Java
Lemur lemur = new Lemur();
Primate primate = lemur; // Implicit Cast to supertype
Lemur lemur2 = (Lemur)primate; // Explicit Cast to subtype
Lemur lemur3 = primate; // DOES NOT COMPILE (missing cast)
```

### Casting Rules

1. Casting a reference from a subtype to a supertype doesn’t require an explicit cast.
2. Casting a reference from a supertype to a subtype requires an explicit cast.
3. At runtime, an invalid cast of a reference to an incompatible type results in a ClassCastException being thrown
4. The compiler disallows casts to unrelated types.

> In case of interfaces There's a limitation, which compiler can not enforce the rules when casting to an invalid interface at compile time. However throws exception at runtime.
> Compiler only forces a rule in case of interfaces and it's if the subclass can't implement the interface for example it's declared as a final class then compiler prevents it in compile time.

## Overriding a Method

Polymorphism’s ability to replace methods at runtime via overriding is one of the most
important properties of Java.

One key feature of inheritance is that we can extend a behavior in subclasses or if we have a common one, instead of implement and maintain it in all subclasses, just have a single point of change inside parent class.
In Java, overriding a method occurs when a subclass declares a new implementation for an inherited method with the same signature and compatible return type.

> A method signature is composed of the name of the method and method parameters. It does not include the return type, access modifiers, optional specifiers, or any declared exceptions.

When overriding a method if we want to mention superclass implementation we can use `super` and if we don't we get stackOverFlow because compiler will mention current implementation.

### Rules to follow for overriding a method

* The method in the child class must have the same signature as the method in the parent class.
* The method in the child class must be at least as accessible as the method in the parent class.
* The method in the child class may not declare a checked exception that is new or broader than the class of any exception declared in the parent class method.
* If the method returns a value, it must be the same or a subtype of the method in the parent class, known as covariant return types.

> Covariance type is that given an inherited return type A and an overriding return type B, can you assign an instance of B to a reference variable for A without a cast? If so, then they are covariant. This rule applies to primitive types and object types alike.

We can use `@Override` annotation to inform compiler we are overriding a function so it can check for violations.

### Method hiding

Static methods are not inherited and can not be overriden. However if we declare everything the same way as parent class we can hide the parent version of that static method. Method hiding unlike overriding is not part of polymorphism

> It is method hiding if the two methods are marked static and method overriding if they are not marked static. If one is marked static and the other is not, the class will not compile.

### Variable hiding

Variables can not be overriden like methods and if we declare a variable with same name in subclass we are creating a totaly separate one from the parent variable.

```Java
class Carnivore {
    protected boolean hasFur = false;
}
public class Meerkat extends Carnivore {
    protected boolean hasFur = true;
    public static void main(String[] args) {
        Meerkat m = new Meerkat();
        Carnivore c = m;
        System.out.println(m.hasFur); // true
        System.out.println(c.hasFur); // false
    }
}
```

#### No hiding(Bad practice)

Although Java allows you to hide variables and static methods, it is considered an extremely poor coding practice. As you saw in the previous example, the value of the variable or method can change depending on what reference is used, making your code very confusing, difficult to follow, and challenging for others to maintain. This is further compounded when you start modifying the value of the variable in both the parent and child methods, since it may not be clear which variable you’re updating. When you’re defining a new variable or static method in a child class, it is considered good coding practice to select a name that is not already used by an inherited member. Redeclaring private methods and variables is considered less problematic, though, because the child class does not have access to the variable in the parent class to begin with.

### Final methods

When we declare a method final, it forbids subclasses to bot override or hide that method.

> This applies on overriden methods not private ones which do not pass on to the subclasses.

## Abstract Classes

When designing a model, we sometimes want to create an entity that cannot be instantiated directly. We want other developers to be able to create instances of the subclasses, In other words, we want to force all objects of abstract class to have a particular type at runtime.

An abstract class is a class declared with the abstract modifier that cannot be instantiated directly and may contain abstract methods.

## Rules

* Only instance methods can be marked abstract within a class, not variables, constructors, or static methods.
* An abstract method can only be declared in an abstract class.
* A non abstract class that extends an abstract class must implement all inherited abstract methods.
* Overriding an abstract method follows the existing rules for overriding methods that you learned about earlier in the chapter.

An abstract class is most commonly used when you want another class to inherit properties of a particular class, but you want the subclass to fill in some of the implementation details.

> An abstract class can be initialized, but only as part of the instantiation of a nonabstract subclass.
> The primary difference between a constructor in an abstract class and a non-abstract class is that a constructor in an abstract class can be called only when it is being initialized by a non-abstract subclass.

We can not declare a method with static, private, final modifiers simoltunesly with abstract.
