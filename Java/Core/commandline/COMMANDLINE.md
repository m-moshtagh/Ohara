# CommandLine with Java

## Pass parameter to main method

We can pass parameters to main method's args[] argument via passing values to java command while executing java command.

```text
java program-name foo bar
```

```java
public class Demo {
    public static void main(String[] args) {
        System.out.println(args[0] + " " + args[1]);
    }
}
```
