# Pattern Matching

Introduced in Java 16

## Usage

We can simply bind a variable to the type Which is used after instanceOf keyword which will be allocated if the pattern
is true. However, This is the feature that if condition of instanceOf clause is negative, the reference is available
outside the if block.

```java
public class Demo {
    boolean equals(Object obj) {
        return obj instanceof Myclass that && this.field.equals(that.field);
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