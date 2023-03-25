# Design Patterns revisited in Modern Java by Venkat Subramaniam

## Iterator

### Break inside Streams

```java
public class Sample {
    public static void main(String[] args) {
        var names = List.of("Dory", "Gill", "Bruce", "Nemo", "Darla", "Marlin", "Jacques");
        //external iterators - we control the iteration

        //for(int i = 0; i <= names.size(); ... //verbose
        int count = 0;
        for (var name : names) {
            if (name.length() == 4) {
                System.out.println(name.toUpperCase());
                count++;

                //continue

                if (count == 2) {
                    break;
                }
            }
        }

        System.out.println("------");
        //internal iterator
        names.stream()
                .filter(name -> name.length() == 4)
                .map(String::toString)
                .limit(2)
                //.takeWhile(predicate)
                .forEach(System.out::println);

        //limit and takeWhile are the functional equivalent
        //of break from the imperative style.
    }
}
```

### Pure Functions & Internal iterator

````java
public class Sample {
    public static void main(String[] args) {
        var names = List.of("Dory", "Gill", "Bruce", "Nemo", "Darla", "Marlin", "Jacques");

        var result1 = new ArrayList<String>();

        for (var name : names) {
            if (name.length() == 4) {
                result1.add(name.toUpperCase());
            }
        }

        System.out.println(result1);

        var result2 = new ArrayList<String>();
        names.stream()
                .filter(name -> name.length() == 4)
                //.map(name -> performImpureOperation(name)) //avoid
                .map(String::toUpperCase)
                .forEach(name -> result2.add(name)); //BAD IDEA
        //.toList(); is a better option
        //The functional pipeline is *not* pure
        //We are doing shared mutability
        //The result may be unpredictable if we 
        //every change this code to run in parallel
        //by adding .parallel() or
        //by changing .stream() to .parallelStream()


        System.out.println(result2);

        //I quit saying "code works"
        //I often now say "the code behaves"
    }
}

//Functional pipeline offers internal iterators
//is less complex
//easy to modify
//easy to understand
//but....

//it is very important that we make the functional
//pipeline pure. Avoid shared mutable variables.
//

//What is a pure function:
//A pure function is idempotent, it returns the same
//result for the same input and does not have any side effects
//1. It does not change any state that is visible outside
//2. It does not depend on anything outside that 
// may change

//Functional programming relies on lazy evaluation
//for efficiency.
//Lazy evaluation and parallel execution rely on
//immutability and purify of functions for correctness.

//FP emphasizes immutability and purity, not because
//it is fashionable, but because it is essential to
//its survival.
````

## Strategy

When we want to vary a small part of an algorithm while keeping the rest of the algorithm the same we use this pattern.
In the past, how did we use strategy:

> We created an interface and then a bunch of classes Then wire them together often use factories. Now lambdas are
> straight strategy

### Initial code

```java
public class Sample {
    public static int totalValues(List<Integer> numbers) {
        int total = 0;

        for (var number : numbers) {
            total += number;
        }

        return total;
    }

    public static void main(String[] args) {
        var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(totalValues(numbers));
    }
}
```

We modify it to have another functionality:

```java
public class Sample {
    public static int totalValues(List<Integer> numbers) {
        int total = 0;

        for (var number : numbers) {
            total += number;
        }

        return total;
    }

    public static int totalEvenValues(List<Integer> numbers) {
        int total = 0;

        for (var number : numbers) {
            if (number % 2 == 0) {
                total += number;
            }
        }

        return total;
    }


    public static void main(String[] args) {
        var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(totalValues(numbers));
        System.out.println(totalEvenValues(numbers));
    }
}
```

We also add another needed change:

```java
public class Sample {
    public static int totalValues(List<Integer> numbers) {
        int total = 0;

        for (var number : numbers) {
            total += number;
        }

        return total;
    }

    public static int totalEvenValues(List<Integer> numbers) {
        int total = 0;

        for (var number : numbers) {
            if (number % 2 == 0) {
                total += number;
            }
        }

        return total;
    }

    public static int totalOddValues(List<Integer> numbers) {
        int total = 0;

        for (var number : numbers) {
            if (number % 2 != 0) {
                total += number;
            }
        }

        return total;
    }


    public static void main(String[] args) {
        var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(totalValues(numbers));
        System.out.println(totalEvenValues(numbers));
        System.out.println(totalOddValues(numbers));
    }
}
```

> Strategies are often a single method or function. So, functional interfaces and lambdas work really well

Now we can do this:

```java
public class Sample {
    public static int totalValues(List<Integer> numbers, Predicate<Integer> selector) {
        int total = 0;

        for (var number : numbers) {
            if (selector.test(number)) {
                total += number;
            }
        }

        return total;
    }

    public static void main(String[] args) {
        var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(totalValues(numbers, ignore -> true));
    }
}
```

Now we use it like this:

```java
public class Sample {
    public static int totalValues(List<Integer> numbers,
                                  Predicate<Integer> selector) {
        int total = 0;

        for (var number : numbers) {
            if (selector.test(number)) {
                total += number;
            }
        }

        return total;
    }

    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }

    public static void main(String[] args) {
        var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(totalValues(numbers, ignore -> true));
        System.out.println(totalValues(numbers,
                number -> number % 2 == 0));

        System.out.println(totalValues(numbers, Sample::isOdd));
    }
}
```

We can also change it to:

```java
public class Sample {
    public static int totalValues(List<Integer> numbers,
                                  Predicate<Integer> selector) {

        return numbers.stream()
                .filter(selector)
                .mapToInt(e -> e)
                .sum();
    }

    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }

    public static void main(String[] args) {
        var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(totalValues(numbers, ignore -> true));
        System.out.println(totalValues(numbers,
                number -> number % 2 == 0));

        System.out.println(totalValues(numbers, Sample::isOdd));
    }
}
```

## Lazy evaluation

```java
interface Person {
    Pet getPet();

    default void play() {
        System.out.println("playing with " + getPet());
    }
}

class DogPerson implements Person {
    private Dog dog = new Dog();

    public Pet getPet() {
        return dog;
    }
}

class CatLover implements Person {
    private Cat cat = new Cat();

    public Pet getPet() {
        return cat;
    }
}

public class Sample {
    public static void call(Person person) {
        person.play();
    }

    public static void main(String[] args) {
        call(new DogPerson());
        call(new CatLover());
    }
}
```

Short-circuiting is when like below the compiler will ignore the second statement in if the first one fails

```java
public class Sample {
    public static int compute(int number) {
        //imagine takes some time to compute
        System.out.println("compute called...");
        return number * 100;
    }

    public static void main(String[] args) {
        int value = 4;

        if (value > 4 && compute(value) > 100) {
            System.out.println("path 1");
        } else {
            System.out.println("path 2");
        }
    }
}
```

Eager evaluation: In this case The value of temp is always evaluated, whether it is used or not.

```java
public class Sample {
    public static int compute(int number) {
        //imagine takes some time to compute
        System.out.println("compute called...");
        return number * 100;
    }

    public static void main(String[] args) {
        int value = 4;

        int temp = compute(value);

        if (value > 4 && temp > 100) {
            System.out.println("path 1 with " + temp);
        } else {
            System.out.println("path 2");
        }
    }
}
```

> David Wheeler:
> In CS we can solve almost any problem by introducing
> one more level of indirection
> In procedural code, pointers given the power of indirection
> In OO code, overriding functions given the power of indirection
> In FP, lambdas give the power of indirection

> myFunction1(Type value) - eager
> myFunction2(Supplier<Type> supplier) - lazy

When do we pass value vs. a functional interface to a method?
One consideration is lazy evaluation

```java
class Lazy<T> {
    private T instance;
    private Supplier<T> supplier;

    public Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public T get() {
        if (instance == null) {
            instance = supplier.get();
        }

        return instance;
    }
}

public class Sample {
    public static int compute(int number) {
        //imagine takes some time to compute
        System.out.println("compute called...");
        return number * 100;
    }

    public static void main(String[] args) {
        int value = 4;

        Lazy<Integer> temp = new Lazy(() -> compute(value));
        //lazy evaluation

        if (value > 4 && temp.get() > 100) {
            System.out.println("path 1 with " + temp.get());
        } else {
            System.out.println("path 2");
        }
    }
}
```

## Develop fluent code

```java
class Mailer {
    public void from(String addr) {
        System.out.println("from...");
    }

    public void to(String addr) {
        System.out.println("to...");
    }

    public void subject(String line) {
        System.out.println("subject...");
    }

    public void body(String content) {
        System.out.println("body...");
    }

    public void send() {
        System.out.println("sending...");
    }
}

public class Sample {
    public static void main(String[] args) {
        Mailer mailer = new Mailer();
        mailer.from("builder@agiledeveloper.com");
        mailer.to("venkats@agiledeveloper.com");
        mailer.subject("Your code sucks");
        mailer.body("...details...");
        mailer.send();
    }
}
```

We can apply cascade method pattern

```java
class Mailer {
    public Mailer from(String addr) {
        System.out.println("from...");
        return this;
    }

    public Mailer to(String addr) {
        System.out.println("to...");
        return this;
    }

    public Mailer subject(String line) {
        System.out.println("subject...");
        return this;
    }

    public Mailer body(String content) {
        System.out.println("body...");
        return this;
    }

    public void send() {
        System.out.println("sending...");
    }
}

public class Sample {
    public static void main(String[] args) {
        new Mailer()
                .from("builder@agiledeveloper.com")
                .to("venkats@agiledeveloper.com")
                .subject("Your code sucks")
                .body("...details...")
                .send();
    }
}
```

We can also handle the object allocation using a consumer

```java
class Mailer {
    public static void send(Consumer<Mailer> block) {
        var mailer = new Mailer();
        block.accept(mailer);
        System.out.println("sending...");
    }

    public Mailer from(String addr) {
        System.out.println("from...");
        return this;
    }

    public Mailer to(String addr) {
        System.out.println("to...");
        return this;
    }

    public Mailer subject(String line) {
        System.out.println("subject...");
        return this;
    }

    public Mailer body(String content) {
        System.out.println("body...");
        return this;
    }
}

public class Sample {
    public static void main(String[] args) {
        Mailer.send(mailer ->
                mailer
                        .from("builder@agiledeveloper.com")
                        .to("venkats@agiledeveloper.com")
                        .subject("Your code sucks")
                        .body("...details..."));
    }
}
```

## Decorator

In legacy, we build decorators by creating classes implementing the same interface and bind the new objects by
constructor.
In functional way we can use Function interface and use andThen() to combine functionalities.

```java
public class Sample {
  public static void print(int number, 
    String message,
    Function<Integer, Integer> func) {
    
    System.out.println(number + " " + 
      message + ": " + func.apply(number));
  }

  public static void main(String[] args) {
    Function<Integer, Integer> inc = value -> value + 1;
    Function<Integer, Integer> doubled = value -> value * 2;

    print(5, "incremented" , inc);
    print(5, "doubled" , doubled);

    
    print(5, "incremented and doubled" , 
      inc.andThen(doubled));
      
    /*
      inc ---->|inc|---->
      doubled ---->|doubled|---->

      combined
      ---->|---->|inc|--->|doubled|---->|---->
    */
  }
}
```

```java
class Camera {
  private Function<Color, Color> filter;

  public Camera(Function<Color, Color>... filters) {
    filter = Stream.of(filters)
      .reduce(Function.identity(), Function::andThen);
  }

  public Color snap(Color input) {
    return filter.apply(input);
  }
}

public class Sample {
  public static void print(Camera camera) {
    System.out.println(camera.snap(new Color(125, 125, 125)));
  }

  public static void main(String[] args) {
    print(new Camera());

    print(new Camera(Color::brighter));
    print(new Camera(Color::darker));

    print(new Camera(Color::brighter, Color::darker));
  }
}

```

## ARM (automatic resource management) with Execute around method pattern

```java
class Resource {
    public Resource() {
        System.out.println("created...");
    }

    public Resource op1() {
        System.out.println("op1...");
        return this;
    }

    public Resource op2() {
        System.out.println("op2...");
        return this;
    }

//    public void finalize() {  has since been deprecated
//        System.out.println("release external resource");
//    }
}

public class Sample {
    public static void main(String[] args) {
        Resource resource = new Resource();
        resource.op1();
        resource.op2();
        //finalize not called
    }
}
```

We can create a close method

```java
class Resource {
    public Resource() {
        System.out.println("created...");
    }

    public Resource op1() {
        System.out.println("op1...");
        return this;
    }

    public Resource op2() {
        System.out.println("op2...");
        return this;
    }

    public void close() {
        System.out.println("release external resource");
    }
}

public class Sample {
    public static void main(String[] args) {
        Resource resource = new Resource();
        resource.op1();
        resource.op2();
        resource.close();
        //what if there was an exception
    }
}
```

using finally block

```java
class Resource {
    public Resource() {
        System.out.println("created...");
    }

    public Resource op1() {
        System.out.println("op1...");
        return this;
    }

    public Resource op2() {
        System.out.println("op2...");
        return this;
    }

    public void close() {
        System.out.println("release external resource");
    }
}

public class Sample {
    public static void main(String[] args) {
        Resource resource = new Resource();
        try {
            resource.op1();
            resource.op2();
        } finally {
            resource.close();
        }

        //code is verbose
        //easy to forget to call try and finally
    }
}
```

Using ARM

```java
class Resource implements AutoCloseable {
    public Resource() {
        System.out.println("created...");
    }

    public Resource op1() {
        System.out.println("op1...");
        return this;
    }

    public Resource op2() {
        System.out.println("op2...");
        return this;
    }

    public void close() {
        System.out.println("release external resource");
    }
}

public class Sample {
    public static void main(String[] args) {
        //ARM—Automatic Resource Management

        try (Resource resource = new Resource()) {
            resource.op1();
            resource.op2();
        }
    }
}
```

Best solution is to use EAM

```java
class Resource {
  private Resource() { System.out.println("created..."); }

  public static void use(Consumer<Resource> block) {
    Resource resource = new Resource(); //before

    try {
      block.accept(resource);
    } finally {
      resource.close(); //after
    }
  }

  public Resource op1() {
    System.out.println("op1...");
    return this;
  }

  public Resource op2() {
    System.out.println("op2...");
    return this;
  }

  private void close() {
    System.out.println("release external resource");
  }
}

//Execute Around Method: with a before and after part.
//This is more of a civilized AOP

public class Sample {
  public static void main(String[] args) {
    Resource.use(resource ->
      resource.op1()
        .op2());
  }
}
```
