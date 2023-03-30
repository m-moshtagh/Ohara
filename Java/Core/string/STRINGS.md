# Strings

## Concept

String is an immutable class representing String literals in java. They will instantiate inside heap and are managed in Spring pool.

## Equality check

We should always use `equals()` method to check the equality of string literals. The `==` operator checks the reference equality. We can also use `equalsIgnoreCase()`

> In case of StringBuilders we need to use `toString()` on them then compare with a string. or use String `contetEquals()` method.

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

### Search for subString

We can search a larger string to see if it contains a substring or not. We can make use of `startsWith()`, `endsWith()` and `contains()`.

### Replacing values

Method replace() does a simple search and replace on the string. We can pass single characters and charSequence.

### Removing whitespace

We can remove whitespace(\n, \t, \r) from a String using `trim()` and `strip()` methods.

> Strip() supports unicode.

We can use `stripLeading()` or `stripTrailing()` methods if we want only one position to be cleared.

### indentation

We can use `indent()` and `stripIndent()` methods to manage indentation in our conatted strings and text blocks. We can add or remove indentation by passing integer values to indent function.

> both methods normalize linebreaks to \n no matter what OS you are using. and stipIndent also adds a line break if missing.

```Java
public class Demo {
    public static void main(String[] args) {
        var block = """
                a
                 b
                c""";
        var concat = " a\n"
                + "  b\n"
                + " c";
        System.out.println(block.length()); // 6
        System.out.println(concat.length()); // 9
        System.out.println(block.indent(1).length()); // 10
        System.out.println(concat.indent(-1).length()); // 7
        System.out.println(concat.indent(-4).length()); // 6
        System.out.println(concat.stripIndent().length()); // 6
    }
}
```

### Checking for Empty or Blank Strings

We can check for empty or blank Strings using isBlank() or isEmpty().

```Java
System.out.println(" ".isEmpty()); // false
System.out.println("".isEmpty()); // true
System.out.println(" ".isBlank()); // true
System.out.println("".isBlank()); // true
```

### Translating Escapes

We add a backlash to escape the backlash. The translateEscapes() method takes these literals and turns them into the equivalent escaped character.

```Java
var str = "1\\t2";
System.out.println(str); // 1\t2
System.out.println(str.translateEscapes()); // 1 2
```

### Formatting Values

There are methods to format String values using formatting flags. Two of the methods take the format string as a parameter, and the other uses an instance for that value.
The method parameters are used to construct a formatted String in a single method call, rather than via a lot of format and concatenation operations. They return a reference to the instance they are called on so that operations can be chained together.

method signatures:

```Java
public static String format(String format, Object args...)
public static String format(Locale loc, String format, Object args...)
public String formatted(Object args...)
```

```Java
var name = "Kate";
var orderId = 5;
// All print: Hello Kate, order 5 is ready
System.out.println("Hello "+name+", order "+orderId+" is ready");
System.out.println(String.format("Hello %s, order %d is ready",
name, orderId));
System.out.println("Hello %s, order %d is ready"
.formatted(name, orderId));
```

> %s -> String, %d -> integer, %f -> float and %n line break

Passing non-compatible parameter causes runtimeException.

In case of floating points we can specify the length of the value and the precition.

```Java
var pi = 3.14159265359;
System.out.format("[%f]",pi); // [3.141593]
System.out.format("[%12.8f]",pi); // [ 3.14159265]
System.out.format("[%012f]",pi); // [00003.141593]
System.out.format("[%12.2f]",pi); // [ 3.14]
System.out.format("[%.3f]",pi); // [3.142]
```

> 12 before `.` shows that the value should have 12 characters, if the value is not enough it will add leading spaces to it, by putting `0` it will replace the spaces with `0`.

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

```Java
var sb = new StringBuilder("animals");
String sub = sb.substring(sb.indexOf("a"), sb.indexOf("al"));
int len = sb.length();
char ch = sb.charAt(6);
System.out.println(sub + " " + len + " " + ch);
```

### Appending values

This adds the parameter to the StringBuilder and returns a reference to the current StringBuilder.

```Java
public StringBuilder append(String str)
```

```Java
var sb = new StringBuilder().append(1).append('c');
sb.append("- ").append(true);
```

### Insert Data

The insert() method adds characters to the StringBuilder at the requested index and returns a reference to the current StringBuilder.

### Deleting Contents

It's the opposite insert() method.

signatures:

```Java
public StringBuilder delete(int startIndex, int endIndex)
public StringBuilder deleteCharAt(int index)
```

### Replacing Portions

This method works differently from String class, We pass indices instead of Strings.

Signature:

```Java
public StringBuilder replace(int startIndex, int endIndex, String newString)
```

### Reverse

We can use `reverse()` to reverse the String on the StringBuilder object.
