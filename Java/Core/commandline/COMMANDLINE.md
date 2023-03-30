# CommandLine with Java

## Pass parameter to main method

We can pass parameters to main method's args[] argument via passing values to java command while executing java command.

```text
java program-name foo bar
```

```java
public class MainArguments {
  public static void main(String[] args) {
    for(var i = 0; i <= args.length - 1; i++) 
      System.out.printf("The Argument number %d is %s.\n", i + 1, args[i]);
  }
}
```
