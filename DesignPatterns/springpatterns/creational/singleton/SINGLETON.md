# Singleton pattern

This pattern enforce a class to have only one instance. Classes which are stateless can benefit from this pattern. All
we have to do is to hold the instance inside the class itself, make the constructor private and, write a getInstance
method to return the instance. 

```java
public class ServiceProvider {
    private static ServiceProvider serviceProvider = null;

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        if (serviceProvider == null)
            synchronized (ServiceProvider.class) {
                serviceProvider = new ServiceProvider();
            }
        return serviceProvider;
    }
}
```

