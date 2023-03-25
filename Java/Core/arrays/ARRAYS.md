# Arrays

Arrays are collection of Items with same data type with fixed length.

## 1D Array

We can define single dimension array in two ways:

```java
public class Demo {
    public static void main(String[] args) {
        int[] arrayOne = {1, 2, 3, 4};
        int arrayTwo[] = {1, 2, 3, 4};
    }
}
```

## 2D Array

```java
public class Demo {
    public static void main(String[] args) {
        int[][] multiplication = new int[11][11];

        for (var i = 1; i <= 10; i++) {
            for (var j = 1; j <= 10; j++) {
                multiplication[i][j] = i * j;
                System.out.println(multiplication[i][j]);
            }
            System.out.println();
        }
    }
}
```
