# SOLID Principles

***

## Why SOLID?

OOP is a great paradigm to make software products but There should be some principles
to lead the design into much more flexibility and quality code which is easy to maintain.

These 5 principles aim at dependency management in OOP software design.

## Problems without SOLID

* Code fragility: when other parts of program break when we change another piece of program.
* Code Rigidity: we need to change many places when we want to change a piece of work.

The main thing we always need to consider is the technical debt which we should pay for maintaining the application. The
more we apply these rules the easier the maintaining will get. Other things which will helps us are design patterns,
TDD.

### Single Responsibility (SRP)

* The class, module or function is only supposed to do one job.
* The class, module or function should only change for one reason.

#### Benefits

* makes code more testable
* makes code easy to understand and maintainable.
* reduces inter-dependency between various software components(coupling).

#### Solutions

* if/else and switch statement expressions should be extracted in separate methods. because each case give us a single
  reason to change.
* On monster methods Which do more than one thing to do and are dependant to multiple objects we have to split them into
  multiple methods.
* God classes should be separated to multiple specialized classes.
* When we instantiate an interface inside the method, the methods is tightly coupled with that interface constructor. So
  if we change it, this piece of method will break, and we'll need to change it too. However, we can pass the
  abstraction as argument.

#### Code

##### If/else switch

```java
public class Demo {
    public String foo(int debt) {
        if (debt == 0)
            return extractedMethodForIf();
        return extractedMethodForElse();
    }
}
```

##### Monster method

```java
import com.fasterxml.jackson.core.JsonFactory;

public class Demo {
    public String payDebt() {
        var fooBank = new fooBank();
        var balance = fooBank.getAccountBalance();
        var debt = fooBank.getAccount().getDebt();
        JsonFactory.builder().build();
        return toJson("debt");
    }
}
```

> This should be separated into different methods.

##### God Classes

```java
public class Util {
    public void calculateHash() {
    }

    public void convertDate() {
    }

    public void toJson() {
    }

    public void prettyFormat() {
    }

    public void log() {
    }
}
```

> This class needs to be split to specialized utilClasses like DateUtil, LogUtil and,...

### Open Closed

* Class should be open for extension but closed for modification. Means for new features we must not modify existing
  code.
* We need to be able to extend a class behavior, without modifying it.
    * Having Private variables and using getters and setters when we only need them.
* Use abstract base classes.

> In order to achieve this principle, All we have to do is to bring a layer of abstraction to our code in order
> to have simple extension. Inheritance, Polymorphism and Strategy pattern help achieve this principle.

> All we have to do is to create abstract class or interface and each time create a class that implements a new feature
> then use factory pattern with runtime polymorphism to use the specific class we want.

> If we expose public API we need to apply this pattern so, if we change the code inside our framework the client code
> doesn't break.

### Liskov Substitution

* Objects in a program should be replaceable by their subtypes without altering the correctness of program.
* Violations will often fail the "is a" test.
* A square is a rectangle however, a rectangle is not a square.

#### Violations

We need to make sure that while using _**is a**_ relationship the derived class is fully replaceable. For example,
We have bird class with fly() method. Ostrich is a bird but in biology. In programming, it's not a bird.
We create rectangle class which is parent of Square class. In square define that height and width are equal. Now if
we try to instantiate square from rectangle we still have setters for width and length so, this is not applicable too.
Another way is that when we ever encountered an interface implementation which throws not supported exception
implementing the interface this is a violation of LISKOV principle.

> Using instance of and then casting the object to call a specific method is also a violation.

#### Refactor Violations

* Eliminate incorrect relations between objects.
* Use "Tell, don't ask" principle to eliminate type checking and casting.

#### Code Examples

##### Bird Ostrich

```java
public class Bird {
    public void fly() {
    }
}
```

```java
public class Ostrich extends Bird {
    public void fly() {
        throw new Exception();
    }
}
```

> We can just separate the above classes. Square case is solved like this.

##### Partial Implemented interfaces

```java
public interface Account {
    void localTransfer();

    void internationalTransfer();
}

public class SchoolAccount implements Account {
    void localTransfer() {
        System.out.println("transferred");
    }

    void internationalTransfer() {
        throw new Exception();
    }
}
```

> To solve this we can just break Account interface into two detailed interfaces: LocalTransfer and
> InternationalTransfer

##### Fixing Instance of violation

```java
public class Usage {
    public static void main(String[] args) {
        for (var task : listOfTask) {
            if (null instanceof Task) {
                var bf = (BugFix) task;
                bf.BugDescription();
            }
            task.progress();
        }
    }
}
```

> We can simply override the progress method in BugFix class:

```java
public class BugFix extends Task {
    @Override
    void progress() {
        this.bugDEscribtion();
        super.progress();
    }
}
```

### Interface Segregation

* Make fine-grained interfaces that are client specific.
* Many client specific interfaces are better than one general purpose interface.
* Keep components focused and minimize dependencies between them.
* Avoid God interfaces. like, Single responsibility principle.

> The brief work this principle wants is to not create fat interfaces and, avoid creating methods that target class of
> interface might not implement them.

> We can implement multiple interfaces in Java so, we can have multipurpose interfaces easily.

#### Solutions

* For our own code we can simply make more interfaces.
* For legacy code we can use Adapter design pattern.

### Dependency Inversion

* Abstraction should not depend upon details.
* Details should depend upon the abstraction.
* Important that higher level and lower level objects depend on the same abstract interaction.
* This is not the same as Dependency Injection - which is how objects obtain dependant objects.

> What Dependency Inversion Principle says is that instead of a high-level module depending on a low-level module,
> both should depend on an abstraction.

#### Dependency Injection

This is a technique that allows the creation of dependent objects outside a class and provides those objects to a
class. We can achieve this by defining the dependency as a local member and initialize it using the constructor.
However, in large scale managing these instances will be a big problem.

#### Dependency Inversion

This is a design principle in which the control of object creation, configuration and lifecycle is passed to a container
framework.
