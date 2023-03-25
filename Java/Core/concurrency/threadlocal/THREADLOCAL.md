# ThreadLocal class

This class is designed to hold a single value per thread. For example if we have two threads that set two values in
ThreadLocal class they can only access the value that they have set inside ThreadLocal. The values of two threads will
not overwrite each other. 

## Code

```java
public class ThreadLocalDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadLocalDemo.class);

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        var thread = new Thread(() -> {
            threadLocal.set("Hello threadLocal");
            inheritableThreadLocal.set("Hello inheritableThreadLocal");
            LOGGER.debug((threadLocal.get()));
            LOGGER.debug((inheritableThreadLocal.get()));
            new Thread(() -> {
                LOGGER.debug((threadLocal.get()));
                LOGGER.debug((inheritableThreadLocal.get()));
            }).start();
        });

        thread.start();
        thread.join();
    }
}
```