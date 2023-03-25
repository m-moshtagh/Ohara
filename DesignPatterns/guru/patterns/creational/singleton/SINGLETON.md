# Singleton Design Pattern

This principal ensures that a class has only one instance, while providing global access point to this instance.

## Problem

Most of the time we create multiple instances from a stateless class. We can reuse this object by applying singleton
principle. We also need to provide a global access point to this object so, we can not change it.

## Solution

First we need to make the default constructor private so, it's not accessible from outside and create a new object with
it. Then we create a static method which returns the object.

```java
public class SingletonDemo {
    private static SingletonDemo singletonDemo;

    private SingletonDemo() {
    }

    public static SingletonDemo getInstance() {
        if (singletonDemo == null)
            singletonDemo = new SingletonDemo();
        return singletonDemo;
    }
}
```
