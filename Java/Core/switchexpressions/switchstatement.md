# Switch Enhancement

This feature added to Java in version 13. We can now return expressions alongside statements in switch block and
store the result in a variable.

> Using switch expressions we need to be cautious that We have to cover all possible values, since we are storing it inside a variabnle. we can cover this by adding a default branch or if we are using enums cover all possible values.

```java
String getWeather(Season value) {
    return switch(value) {
    case WINTER -> "Cold";
    case SPRING -> "Rainy";
    case SUMMER -> "Hot";
    case FALL -> "Warm";
    };
}
```

This works however if we add another value to the enum, suddenly this switch will fail, but if we add a default branch we can handle it better.

## Switch statements

To use this feature we use `->` instead of `:` in front of case keyword.
If we have multiple expressions for a case, we can use `yield` keyword in order to break the switch. This is equilavant to return inside a switch expression.

> You can’t return incompatible or random data types.

## Pattern matching

Instead of using `instanceof` keyword now we can use Wrapper classes on switch statements. We can also combine them with other conditions.
