# Scheduling & Retry

We want to schedule a job that tries to run a task in a timeline. We have different solutions for this, and we want to
discuss some of it here.

## Timer class

Timer is a java.util class which can schedule a task using TimerTask class which is a Runnable.

```java
package reflection.retry;

import java.util.Random;
import java.util.TimerTask;

public class Timer {
    private static final Random random = new Random();

    public static void getConnection() {
        var timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long l = random.nextLong(4);
                if (l == 0) {
                    System.out.println("Connection Established");
                    timer.cancel();
                } else
                    System.out.println("Connection Failed");
            }
        }, 10, 5000);
    }

    public static void main(String[] args) {
        Timer.getConnection();
    }
}

```

## ScheduledExecutorService

We have a factory for scheduled ExecutorServices.
This is similar to Timer class with the difference that in timer we cancel() the job, here we shut down the
executorService.

```java
package reflection.retry;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {
    public static void main(String[] args) {
        try (var executor = Executors.newScheduledThreadPool(1)) {
            executor.scheduleAtFixedRate(() -> {
                var random = new Random().nextLong(4);
                if (random == 0) {
                    System.out.println("connection established");
                    executor.shutdown();
                } else {
                    System.out.println("connection lost");
                }
            }, 1, 2, TimeUnit.SECONDS);
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

```

## RetriableTask(custom implementation)

In order to have full control we can create a custom class which implements Callable interface.

```java
package reflection.retry;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;

public class RetriableTask<T> implements Callable<T> {
    private static final int DEFAULT_MAX_RETRIES = 10;
    private static final long DEFAULT_RETRY_DELAY_MILLIS = 2000L;
    private Callable<T> task;
    private int retriesLeft;
    private long timeToWait;

    public RetriableTask(Callable<T> task) {
        this(task, DEFAULT_MAX_RETRIES, DEFAULT_RETRY_DELAY_MILLIS);
    }

    public RetriableTask(Callable<T> task, int retriesLeft, long timeToWait) {
        this.task = task;
        this.retriesLeft = retriesLeft;
        this.timeToWait = timeToWait;
    }

    public static void main(String[] args) {
        RetriableTask<Long> task = new RetriableTask<>(() -> {
            var random = new Random().nextLong(4);
            if (random == 0) {
                System.out.println("connection established");
            } else {
                System.out.println("connection lost");
                throw new Exception();
            }
            return random;
        });
        try {
            task.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public T call() throws Exception {
        while (true) {
            try {
                return task.call();
            } catch (InterruptedException | CancellationException e) {
                throw e;
            } catch (Exception e) {
                retriesLeft--;
                if (retriesLeft == 0) {
                    throw new Exception("Retry exhausted after " + retriesLeft + " retries", e);
                }
                Thread.sleep(timeToWait);
            }
        }
    }
}

```