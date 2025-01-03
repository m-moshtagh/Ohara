# Object Oriented programming

Object-Oriented is one of the programming paradigms that has been around for a long time which was provided to fix the
issues related to structural style of programming.

Steps:

1. Object-Oriented Analysis(OOA)
2. Object-Oriented Design(OOD)
3. Object-Oriented Programming(OOP)

## Object-Oriented Language

A language which supports These three criteria can be called an Object-Oriented language

* Encapsulation
* Inheritance
* Polymorphism

## Abstraction

In object-oriented programming, **abstraction** is the process of reducing an object to its essence, so that only the
necessary elements are represented. Abstraction defines an object in terms of its properties _(attributes)_,
behaviors _(functionality)_, and interface(means of communicating with other objects).

> Abstraction is a concept related to OOA & OOD.

## Encapsulation

Encapsulation is the process of hiding unnecessary details from the User of a component in order to make things simpler.

> Encapsulation is not security protection!

In order to apply encapsulation to code we have these functionalities:

* Access modifiers / Visibility Modifiers
* Mutators & Accessors

creating mutators(setters) & accessors(getters) allow us to:

* prevent invalid state(validation)
* provide synchronized access
* read only
* prevent unnecessary side effects
* Lazy Fetch

## Inheritance

Inheritance is the process by which a subclass automatically includes certain members of the class, including
primitives, objects, or methods defined in the parent class.

* We can reuse primitives, objects and methods defined in superclasses inside subclasses.
* protected members of superclass are available in subclasses.
* Declaring a class `final` will make sure it won't get inherited.
* final, abstract, sealed, non-sealed and static can be applied.
* Java supports Single inheritance.
* All classes in Java extend Object class by default. This is simply handled by compiler.
* Top level classes can only have package, public access modifiers. private and protected can be applied to inner
  classes.

> Part of what makes multiple inheritance complicated is determining which parent to inherit values from in case of a
> conflict. For example, if you have an object or method defined in all the parents, which one does the child
> inherit?
> There is no natural ordering for parents in this example, which is why Java avoids these issues by disallowing
> multiple
> inheritance altogether.

### This vs Super

The `this` reference refers to the current instance of the class and can be used to access any member of the class,
including inherited members. It can be used in any instance method, constructor, or instance initializer block. It
cannot be used when there is no implicit instance of the class, such as in a static method or static initializer block.

Super is a keyword which points to the super class. For example if we declare a same variable in both super and
subclasses. Two different variables are available in subclass one is for super class and one is for itself. we can point
to the super class property using `super` keyword.

### Inheritance vs Composition

In order to use another class Features in the class we want. We can make use of inheritance and composition.
to use them with composition we can simply initialize the target class and use its features. But with inheritance we
need to extend from that class.

> By extending a class we also gain some features through universal polymorphism.

## Polymorphism

The word "polymorphism" has its root in two Greek words: "poly" (means many) and “morphos” (means form).
In programming, polymorphism is the ability of an entity (e.g. variable, class, method, object, code, parameter,etc.)
to take on different meanings in different contexts.

There are two types of polymorphism:

* Ad hoc
* Universal

### Ad Hoc Polymorphism

If the types for which a piece of code works are (finite) and all those types (must be known) when the code is written,
it is known as ad hoc polymorphism.
Ad hoc polymorphism is also known as apparent polymorphism because it is not a polymorphism in a true sense.
Some computer science purists do not consider ad hoc polymorphism a polymorphism at all.

There two types of ad hoc polymorphism:

* Overloading
* Coercion

> Coercion is used in different contexts: assignments, method parameters, etc.

#### Overloading

Overloading methods is a compile time operation(early binding) which has 3 resolutions and if not solvable by compiler
ambiguity will occur.

1. performs without permitting boxing or unboxing conversion, or the use of variable arity(...) method invocation
2. performs overload resolution while allowing boxing and unboxing but still precludes the use of variable arity method
   invocation.
3. Allows overloading to be combined with variable arity methods, boxing, and unboxing.
4. Ambiguity: Compile-time error "reference to m is ambiguous"

### Examples

overloading -> `m(int a, int b) , m(long a, long b)`
Coercion -> `int num = 707  -> double d = num`

### Universal Polymorphism

If a piece of code is written in such a way that it works for (infinite) number of types
(will also work for new types not known at the time the code is written), it is called universal polymorphism.
In universal polymorphism, the same code works on many types, whereas in ad hoc polymorphism,
different implementations of code are provided for different types giving an apparent impression of polymorphism.

There two types of Universal polymorphism:

* inclusion: all types are related by a supertype-subtype relationship
* parametric: all types are related or unrelated

#### Overriding (Dynamic Method Dispatch)

When you override a method, you must provide exactly the same signature as the super class. But, after Java 5:
The return type of override method can be the subtype of original version(covariant return type)

> No weaker access modifier in overriding

> static methods cannot be overridden, but they can be hidden.

By declaring a method `final` we prevent it to be overridden.

> Overriding is Run-time and depends on Object

### Examples

Inclusion -> `Person p = new Employee()`
Parametric => `List<T> , List<P extends Person>`