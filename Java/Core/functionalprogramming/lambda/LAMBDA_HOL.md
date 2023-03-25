# Lambda HOL by Stuart marks & Jose Paumard

## Functional Interface

Functional interface is an interface which has only one abstract method. These interfaces also, can be annotated with
`@FunctionalInterface`.(_not necessary_)

Java natively doesn't support functional programming but using this concept we can code in a functional style. We can
provide implementation using Lambda expression. Below is like providing a simple hello implementation to one of Java
built-in functional interfaces `Consumer`.

```java
import java.util.function.Consumer;

public class Demo {
    public static void main(String[] args) {
        Consumer<String> consumer = (String text) -> System.out::println;
        consumer.accept("Hello Lambda");
    }
}
```