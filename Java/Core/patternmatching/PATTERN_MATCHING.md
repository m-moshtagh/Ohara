# Pattern Matching

Introduced in Java 16

## Usage

We can simply bind a variable to the type Which is used after instanceOf keyword which will be allocated if the pattern is true. However, This is the feature that if condition of instanceOf clause is negative, the reference is available outside the if block.

> This way we can avoid ClassCastException.
> We can also prevent reassign by making the pattern variable final to avoid reassigning bad data to the variable.

```java
public class Demo {
    boolean equals(Object obj) {
        return obj instanceof final Myclass that && this.field.equals(that.field);
    }

    boolean equals(Object obj) {
        if (!(obj instanceof MyClass that)) {
            throw new Exception();
        }
        return this.field.equals(that.field); // accessible
    }

    boolean equals(Object obj) {
        if (obj instanceof String s && s.length() > 8) {
        } //true
        if (obj instanceof String s || s.length() > 8) {
        } // false
    }
}
```

### SubType

The type of the pattern variable must be a subtype of the variable on the left side of the expression. It also cannot be the same type

```java
Integer value = 123;
if(value instanceof Integer) {}
if(value instanceof Integer data) {} // DOES NOT COMPILE
```

> The compiler has some limitations on enforcing pattern matching types when we mix
classes and interfaces

```java
Number value = 123;
if(value instanceof List) {}
if(value instanceof List data) {} // COMPILES
```

### Flow Scoping

Flow Scoping means that the pattern variable is only available when the instanceOf operator is true.
