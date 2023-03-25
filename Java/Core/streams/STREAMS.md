# Streams

Streams concept is a powerful feature of java that allow us to process collection of data in a declarative way.

## Programming paradigms

* imperative programming: we specify sections of code that how things should be done.
* declarative programming: we specify sections of code that what should be done like SQL.
* functional programming: we classify our project using functions. it's kinda like declarative programming.
* object-oriented programming: we classify our project with objects and classes.
* event driven programming: we classify our project based on events that take place in our project.

## Creation

* Collections
* Arrays
* Arbitrary number of Objects
* infinite / finite streams

We have two kinds if stream functions:

* intermediate: These functions return Stream() or other objects, so we can chain other functions to it.
* terminal: These functions like collect() and foreach() only consume something and don't return anything so the stream
  operations are ended after calling one of these methods.

## Mapping Streams

Quite often we need to change the values in our stream. for this reason we use map() or flatmap() method.

* map(): When we have a stream of an object and need to extract something from it, we use map() method.
* flatmap(): When we have a Stream of lists(Stream<List<Integer>>) and we want to flatten the collection and
  extract values from it, we use flatmap().

## Filtering streams

filter() methods accept a Predicate which can apply a condition to stream and return the stream. we can chain multiple
filter methods.

## Slicing streams

Stream has some really useful methods for slicing:

* limit(): with this we can limit the stream to a number of items
* skip(): we can skip number of items with this method

* we can create pagination using limit() and skip() methods.

* takeWhile(Predicate): we get elements till the condition is met after that it is stopped.
* dropWhile(Predicate): we can skip elements till the condition is met, after that we get the elements.

* Difference with filter() method is that filter() iterates the whole stream and returns it.

## Sorting streams

We can use stream().sorted() method for sorting a stream. the element should implement comparable, or we need to pass
a Comparator.

* Comparator is a functional interface, so we can use lambda expression.
* We can also use Comparator.comparing() method instead of String.compareTo() method.
* We can also reverse the order using reversed() method.

## Getting unique values only

we can use distinct() method for this purpose.

## Peek()

We can use peek() method to iterate over The stream. This is useful for troubleshooting we can use it after each
map() or filter() methods and then see the values. the difference from foreach() is that foreach() is a terminal
operation.

## Simple Reducers

These methods simply reduce a Stream of objects into objects. These are terminal operations.

* count()
* anyMatch(Predicate)
* allMatch(Predicate)
* noneMatch(Predicate)
* findFirst()
* findAny()
* max(Comparator)
* min(Comparator)
  Some of these return an Optional which is really helpful for preventing null values.

## Reduce

We also have general purpose reduce() method which accepts a BinaryOperator, or we can give an initial value and a
BinaryOperator.
The second constructor doesn't return an optional.

Optional: is a type which may or may not have a value. it has method get(), orElse() method which we can provide a value
if the value might be null.

## Collectors

Stream interface has a collect() method that takes a Collector interface which can help us convert stream into any
data structure.

* Collectors.toList()
* Collectors.toSet()
* Collectors.toMap(Function key, Function value)

* for values if we want to write something like this to return the current object: type -> type, we can use
  Function.identity()

* Collectors.summingInt() -> gives us summarization of int values. also, available for Double and Long.
* Collectors.summarizingInt() -> gives us sum, min, average, max of int values. also, available for Double and Long
* Collectors.joining(String delimiter) -> concatenates String values.

* We can also group by one field or enum. this takes a Function which we can pass the field we want to group by. by
  default,
  it returns a map, but we can pass another argument Collectors.
* if we want to group by and show them with one of the fields for example movie title we should use Collectors.mapping()
  and pass
  field name.
* we can also collect based on a condition using collectors.partitioningBy().
* If we are working with primitive types it's better to use primitiveTypeStreams like IntStream.

## Usages

> Simple filter example

```java
import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        var strings = Arrays.asList("a1", "b2", "c3", "d4");
        strings.stream().filter(s -> s.startsWith("c")).map(String::toUpperCase).sorted().forEach(System.out::println);
    }
}
```

> Create immutable Stream

```java
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args) {
        Stream.builder().add("a1").add("a2").add("a3").build().findFirst().ifPresent(System.out::println);
        Stream.of("a1", "a2", "a3").forEach(System.out::println);
    }
}
```

> Stream instead of for loop

```java
import java.util.stream.IntStream;

public class Demo {
    public static void main(String[] args) {
        IntStream.range(0, 4).forEach(System.out::println);
        // rangeClosed() for including closed value in the stream
    }
}
```

> Stream map for processing

```java
import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        Arrays.stream(new int[]{1, 2, 3}).map(n -> 2 * (n + 1)).average().ifPresent(System.out::println);
    }
}
```

> map dataTypes

```java
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args) {
        Stream.of("a1", "a2", "a3").map(s -> s.substring(1)).mapToInt(Integer::parseInt).max().ifPresent(System.out::println);
        Stream.of(1.0, 2.0, 3.0).mapToInt(Double::intValue).mapToObj(i -> "a" + i).forEach(System.out::println);
    }
}
```

> anyMatch

```java
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args) {
        var result = Stream.of("d2", "a2", "b1", "b3", "c")
                .map(String::toUpperCase)
                .anyMatch(s -> s.startsWith("A"));
        assertThat(result).isTrue();
    }
}
```

> sort

```java
public class Demo {
    public static void main(String[] args) {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .sorted((s1, s2) -> s1.compareTo(s2))
                .filter(s -> s.startsWith("a"))
                .map(String::toUpperCase)
                .forEach(s -> System.out.println("forEach: " + s));
    }
}
```

> Group By

```java
public class Demo {
    public static void main(String[] args) {
        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));
    }
}
```

> Generate Random Numbers

```java
import java.util.Random;

public class Demo {
    public static void main(String[] args) {
        // Generates 100 number from -1000 to 1000
        new Random().ints(100, -1000, 1000).limit(10).sorted()
                .forEach(System.out::println);
    }
}
```

> merge List of String into one String

```java
public class Demo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "bcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string ->
                !string.isEmpty()).toList();
        System.out.println("Filtered List: " + filtered);
        String mergedString = strings.stream().filter(string ->
                !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("Merged String: " + mergedString);
    }
}
```

> Retrieve statistics of a process

```java
public class Demo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = numbers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("Highest number in List : " + stats.getMax());
        System.out.println("Lowest number in List : " + stats.getMin());
        System.out.println("Sum of all numbers : " + stats.getSum());
        System.out.println("Average of all numbers : " + stats.getAverage());
    }
}
```
