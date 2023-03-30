# Immutable Class

The immutable objects pattern is an object-oriented design pattern in which an object cannot be modified after it is created. Immutable objects are helpful when writing secure code because you don’t have to worry about the values changing. They also simplify code when dealing with concurrency since immutable objects can be easily shared between multiple threads.

## Declare an immutable class

1. Mark the class as final or make all of the constructors private.
2. Mark all the instance variables private and final.
3. Don’t define any setter methods.
4. Don’t allow referenced mutable objects to be modified.
5. Use a constructor to set all properties of the object, making a copy if needed.

### Examples

```Java
import java.util.*;
public final class Animal { // Not an immutable object declaration
    private final ArrayList<String> favoriteFoods;
    public Animal() {
        this.favoriteFoods = new ArrayList<String>();
        this.favoriteFoods.add("Apples");
    }
    public List<String> getFavoriteFoods() {
        return favoriteFoods;
    } 
}
```

> We don't follow fourth rule we can modify our object.

We can fix it by wrapping manipulations in methods.

```Java
import java.util.*;
public final class Animal { // An immutable object declaration
    private final List<String> favoriteFoods;
    public Animal() {
        this.favoriteFoods = new ArrayList<String>();
        this.favoriteFoods.add("Apples");
    }
    public int getFavoriteFoodsCount() {
        return favoriteFoods.size();
    }
    public String getFavoriteFoodsItem(int index) {
        return favoriteFoods.get(index);
    } 
}
```

> Now accessors can not modify the returned object. However we can also, give a copy of the object whenever requested. This can be expensive if it's called multiple times by the caller.

```Java
public ArrayList<String> getFavoriteFoods() {
    return new ArrayList<String>(this.favoriteFoods);
}
```

#### Defensive Copy

```Java
import java.util.*;
public final class Animal { // Not an immutable object declaration
    private final ArrayList<String> favoriteFoods;
    public Animal(ArrayList<String> favoriteFoods) {
        if (favoriteFoods == null || favoriteFoods.size() == 0)
        throw new RuntimeException("favoriteFoods is required");
        this.favoriteFoods = favoriteFoods;
    }
    public int getFavoriteFoodsCount() {
        return favoriteFoods.size();
    }
    public String getFavoriteFoodsItem(int index) {
        return favoriteFoods.get(index);
    }
}
```

This way if we call we can modify the object

```Java
var favorites = new ArrayList<String>();
favorites.add("Apples");
var zebra = new Animal(favorites); // Caller still has access to favorites
System.out.println(zebra.getFavoriteFoodsItem(0)); // [Apples]
favorites.clear();
favorites.add("Chocolate Chip Cookies");
System.out.println(zebra.getFavoriteFoodsItem(0)); // [Chocolate Chip Cookies]
```

We can fix the problem by copying the object in constructor. This is defensive because we try to fix the problem before it's existed.

```Java
public Animal(List<String> favoriteFoods) {
    if (favoriteFoods == null || favoriteFoods.size() == 0)
        throw new RuntimeException("favoriteFoods is required");
    this.favoriteFoods = new ArrayList<String>(favoriteFoods);
}
```
