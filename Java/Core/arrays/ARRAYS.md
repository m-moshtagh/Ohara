# Arrays

Arrays are collection of Items with same data type with fixed length which are refered to by a single name followed by index of the Item.
Arrays in Java are Reference Types which are stored in heap memory.

> Arrays of reference types are initialized with null value if not specified however arrays of primitives are created with default value.

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

### Zero sized array vs Null Array

When we define an array like `int[] array = new int[0];` trying to access the array we get `ArrayIndexOutOfBoundException` however, when we assig null to an array we get `NullPointerException`.

When we are validating array arguments pass through methods we might have a problem if we only check the nullPointerException.

```Java
public class Demo {
    public static void main(String[] args) {
        int[] emptyArray = new int[0];
        int[] nullArray = null;
        badCheck(emptyArray); 
        badCheck(nullArray); 
        goodCheck(emptyArray); 
        goodCheck(nullArray); 

    }

    public static boolean badCheck(int[] array) {
        return array != null ? true : false;
    }
    public static boolean goodCheck(int[] array) {
        return (array != null && array.length != 0) ? true : false;
    }
}
```

Imagine we call a function which returns an array. We have a special syntax which we can access indices of the returned array.
for example if the readData() method returns an array, we can use `var item = readData() [0];` to assign first item to a variable.

> Max size of an array can be `Integer.MAX_VALUE -2` which will be estimated to 2GB.
> Best way to prevent is to always create an array with fix size.

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
