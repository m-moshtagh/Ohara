# Lambda Expressions

Lambda expression is actually anonymous implementation of a single method interface(functional interface) which can be stored as an object which can be passed through methods as arguments.

## Transmutation

First we Used anonymous functions as below:

```java
import java.util.Comparator;

public class LambdaDemo {
    Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    };
}
```

Now with lambda we can rewrite this like:

```java
import java.util.Comparator;

public class LambdaDemo {
    Comparator<String> comparator = (String o1, String o2) -> {
        return o1.compareTo(o2);
    };
}
```

Since we have one line return statement we can omit `return` keyword and curly braces. Input arguments types can be
inferred from interface declaration.

```java
import java.util.Comparator;

public class LambdaDemo {
    Comparator<String> comparator = (o1, o2) -> o1.compareTo(o2);
}
```

Because we are providing the implementation from pre implemented `compareTo` method, we can use method reference.

```java
import java.util.Comparator;

public class LambdaDemo {
    Comparator<String> comparator = String::compareTo;
}
```

## Variable Capture

If we try to pass variables inside our lambdas They should be either final or static.

> Still we can pass regular variables to lambda if we don't change it after lambda usage.
