# Race Condition

Race Condition is a situation where multiple threads try to access variable, data or resources in a way where the final
result may depend on how threads access is scheduled.

Race condition occurs when:

* Two or more threads read and write the same resource concurrently.
* The threads access the variables using:
    * Check then act
    * Read modify write (Where modified value depends on the previously read value)
* The thread access to resource is not atomic.

## Read Modify Write Example

```java
public class Counter {
    private long count;

    public void increment() {
        this.count++;
    }

    public long get() {
        return this.count;
    }
}
```

```java
public class RaceConditionDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(RaceConditionDemo.class);

    public static void main(String[] args) {
        var counter = new Counter();
        var thread1 = new Thread(getRunnable(counter, "thread1 final count"));
        var thread2 = new Thread(getRunnable(counter, "thread2 final count"));
        thread1.start();
        thread2.start();
    }

    public static Runnable getRunnable(Counter counter, String message) {
        return () -> {
            for (var i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
            LOGGER.debug("{}: {}", message, counter.get());
        };
    }
}
```

```text
16:07:50.567 [Thread-2] DEBUG c.d.race condition.RaceConditionDemo - thread2 final count: 1082437
16:07:50.566 [Thread-1] DEBUG c.d.race condition.RaceConditionDemo - thread1 final count: 1082437
```

## Fix

We can fix the condition by:

* synchronizing the increment method in counter. hens, each thread at time can write the object and the other can be
  aware of the new value.
* By making the count attribute AtomicLong.

```java
public class Counter {
    private final AtomicLong count = new AtomicLong(0);

    public void increment() {
        this.count.incrementAndGet();
    }

    public long get() {
        return this.count.get();
    }
}
```

```text
16:28:35.299 [Thread-1] DEBUG c.d.race condition.RaceConditionDemo - thread1 final count: 1471893
16:28:35.304 [Thread-2] DEBUG c.d.race condition.RaceConditionDemo - thread2 final count: 2000000
```

```java
public class Counter {
    private long count;

    public void increment() {
        synchronized (this) {
            this.count++;
        }
    }

    public long get() {
        return this.count;
    }
}
```

```text
16:32:12.803 [Thread-2] DEBUG c.d.race condition.RaceConditionDemo - thread2 final count: 1526455
16:32:12.810 [Thread-1] DEBUG c.d.race condition.RaceConditionDemo - thread1 final count: 2000000
```

> Race condition happens when two threads are racing to read and write the value inside memory. however if we have one
> thread updating and one reading at same time we may encounter visibility problem but no race condition.
