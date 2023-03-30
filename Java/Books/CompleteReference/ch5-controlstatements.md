# Control Statements

## Java’s Selection Statements

Java supports two of them `if` & `switch`. These statements allow you to control the flow of your program’s execution based upon conditions known only during run time.

- The switch differs from the if in that switch can only test for equality, whereas if can evaluate any type of Boolean expression. That is, the switch looks only for a match between the value of the expression and one of its case constants.
- No two case constants in the same switch can have identical values. Of course, a switch statement and an enclosing outer switch can have case constants in common.
- A switch statement is usually more efficient than a set of nested ifs.

> The last point is particularly interesting because it gives insight into how the Java compiler works. When it compiles a switch statement, the Java compiler will inspect each of the case constants and create a “jump table” that it will use for selecting the path of execution depending on the value of the expression. Therefore, if you need to select among a large group of values, a switch statement will run much faster than the equivalent logic coded using a sequence of if-elses. The compiler can do this because it knows that the case constants are all the same type and simply must be compared for equality with the switch expression. The compiler has no such knowledge of a long list of if expressions.

## Iteration Statements

Java’s iteration statements are for, while, and do-while.

A while body can be empty

```Java
public class NoBody {
    public static void main(String[] args) {
        int i = 100, j = 200;

        // find midpoint between i and j
        while(++i < --j);
        System.out.println("midpoint is " + i);
    }
}
```

### For each on multidimentional Arrays

```Java
public class ForEach3 {
    public static void main(String[] args) {
        int sum = 0;
        int[][] nums = new int[3][5];
        for(int[] x : nums) {
            for(int y : x)
                sum+=y;
        }
    }
    System.out.print(sun);
}
```

### Return

> One last point: In the preceding program, the if(t) statement is necessary. Without it, the Java compiler would flag an “unreachable code” error because the compiler would know that the last println( ) statement would never be executed. To prevent this error, the if statement is used here to trick the compiler for the sake of this demonstration.

### break & continue on lable

```Java
public class BreakLoop {
  public static void main(String[] args) {
  outer:
    for (var i = 0; i < 3; i++) {
      System.out.println("pass" + i + ": ");
      for (var j = 0; j < 100; j++) {
        if (j == 10)
          break outer;
        System.out.println(j + " ");
      }
      System.out.println("This won't print!");
    }
    System.out.println("Loops complete");
  }
}
```
