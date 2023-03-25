# Strings

## Concept

String is an immutable class representing String literals in java. They will instantiate inside heap and are managed
in Spring pool.

## Equality check

We should always use equals() method to check the equality of string literals. The `==` operator checks the reference
equality.

## String Pool

Whenever we create a String literal JVM will put it inside String pool in Heap memory. So it can reuse it. Strings are
immutable so, if we change it another String will be created in pool instead of mutate the current one. However, this
pool enables us to reuse exact strings if they are the same. In some cases like when we use toString() the value will
not register in pool but, we can do it by using intern() method.

```java
public class StringDemo {
    public static void main(String[] args) {
        String one = "hello";
        String two = "hello"; // both references one literal in pool
        one = "Bye"; // new literal in pool will be created and one will point to it.

        String castNum = 75L.toString().intern();
        String num = "75"; // now if we compare references using == it will pass but without intern() it won't
    }
}
```
