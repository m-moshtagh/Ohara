# Adapter Pattern

This pattern is used when two different interfaces share a common operation. Adapter is a wrapper class that is created
to hold an instance of one interface and implement the other. This is used when we have legacy code and, we don't want
to break things by creating a whole new object. Also, when we have common operations on one interface and, we want to
reduce The code amount we write.

## Code

```java
public interface Dog {
    void bark();

    void eat();

}
```

```java
public interface Cat {
    void meoew();

    void eat();
}
```

```java
public class Neku implements Cat {
    @Override
    public void meoew() {
        System.out.println("meow!");
    }

    @Override
    public void eat() {
        System.out.println("eating");
    }
}
```

```java
public class DogAdapter implements Dog {
    private Cat cat;

    public DogAdapter(Cat cat) {
        this.cat = cat;
    }

    @Override
    public void bark() {
        System.out.println("bark");
    }

    @Override
    public void eat() {
        cat.eat();
    }
}
```