# Strings

## Concept

String is an immutable class representing String literals in java. They will instantiate inside heap and are managed in Spring pool.

## Equality check

We should always use equals() method to check the equality of string literals. The `==` operator checks the reference
equality.

## Frequent methods

### Concatination

We can concat operands with '+' and when either one is a String concatination occurs.

> The expressions are evaluated from left to right. for example 1 + 2 + 'c' -> 3c

### length

We can get the length by `str.length()` method.

> This method uses normal counting

### charAt

We can query a character in String by the index with charAt() method.

### indexOf

We can find the first index of the first occurence of a character by indexOf() method.
We can pass int number of a character, a character and a whole String.
We can also pass an integer as second parameter to search from that index.

```Java
var name = "animals";
System.out.println(name.indexOf("al"));
System.out.println(name.indexOf('a', 4));
```

> When no character is found -1 would return.

### subString

This method also looks for characters by index and return that piece of String.
We need to pass a first index parameter and a second optional parameter for end index.

> The endIndex parameter will not be included in the returned String.

```Java
var name = "animals";
System.out.println(name.substring(3)); // mals
System.out.println(name.substring(name.indexOf('m'))); // mals
System.out.println(name.substring(3, 4)); // m
System.out.println(name.substring(3, 7)); // mals
System.out.println(name.substring(3, 3)); // empty string
System.out.println(name.substring(3, 2)); // exception
System.out.println(name.substring(3, 8)); // exception
```

### Adjusting Case

We can adjust Cases with `toLowerCase()` and `toUpperCase()`, which return a new string since they are immutable.

## String Pool

Whenever we create a String literal JVM will put it inside String pool in Heap memory. in CompileTime So it can reuse it. Strings are immutable so, if we change it another String will be created in pool instead of mutate the current one. However, this pool enables us to reuse exact strings if they are the same. In some cases like when we use toString() or initialize String in runtime with new String() the value will not register in pool but, we can do it by using intern() method.

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

### String Transformations to Other Primitives

We can use parse builtIn methods to convert other primitives to String and also use valueOf functions to transform a string representation of a literal to the actual datatype.

## StringBuffer & StringBuilder

Whenever we need to make multiple operations on String valus, since String class is immutable we can use StringBuffer & StringBuilder.
These two classes use a fixed array then whenever the limit is going to exceed, it reconstructs the array.

> If we have an estimation of the final size of the string we are going to build we can create a fixed array in order to avoid reconstruction overhead.

It is also efficient to return compact String after operations on StringBuilder or StringBuffer is finished. We can use toString() or trimToString() methods.
