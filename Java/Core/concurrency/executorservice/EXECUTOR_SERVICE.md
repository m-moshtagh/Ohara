# ExecutorService in Java

The Java ExecutorService interface, `java.util.concurrent.ExecutorService`, represents an asynchronous execution
mechanism which is capable of executing tasks concurrently in the background. In this Java ExecutorService tutorial I
will explain how to create a ExecutorService, how to submit tasks for execution to it, how to see the results of those
tasks, and how to shut down the ExecutorService again when you need to.

## ExecutorService Interface

We can simply initialize ExecutorService interface with `Executors.newFixedThreadPool()`and provide the number of ready
threads we want. However, Java has two built in implementations of this interface:

* ThreadPoolExecutor
* ScheduledThreadPoolExecutor

> We can use factory methods of Executors class to create a thread pool.

## Task delegation

There are a few different ways to delegate tasks for execution to an ExecutorService

### Execute Runnable

The Java `ExecutorService execute(Runnable)` method takes a `java.lang.Runnable` object, and executes it asynchronously.

```java
public class demo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute((Runnable) () -> System.out.println("Asynchronous task"));

        executorService.shutdown();
    }
}
```

> There is no way of obtaining the result of the executed Runnable, if necessary. You will have to use a Callable for
> that (explained in the following sections).

### Submit Runnable

The Java `ExecutorService submit(Runnable)` method also takes a Runnable implementation, but returns a Future object.
This Future object can be used to check if the Runnable has finished executing.

```java
public class demo {
    public static void main(String[] args) {
        Future future = executorService.submit((Runnable) () -> System.out.println("Asynchronous task"));

        future.get();  //returns null if the task has finished correctly.
    }
}
```

### Submit Callable

The Runnable interface is very similar to the Callable interface. The Runnable interface represents a task that can be
executed concurrently by a thread or an ExecutorService. The Callable can only be executed by an ExecutorService. Both
interfaces only has a single method. There is one small difference between the Callable and Runnable interface though.
The difference between the Runnable and Callable interface is more easily visible when you see the interface
declarations.

```java
public class demo {
    public static void main(String[] args) {
        Future future = executorService.submit(new Callable() {
            public Object call() {
                System.out.println("Asynchronous Callable");
                return "Callable Result";
            }
        });

        System.out.println("future.get() = " + future.get());
    }
}
```

### invokeAny

The invokeAny() method takes a collection of Callable objects, or interfaces of Callable. Invoking this method does
not return a Future, but returns the result of one of the Callable objects. You have no guarantee about which of the
Callable's results you get. Just one of the ones that finish.

If one Callable finishes, so that a result is returned from `invokeAny()`, then the rest of the Callable instances are
cancelled.

```java
public class demo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            public String call() {
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            public String call() {
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            public String call() {
                return "Task 3";
            }
        });

        String result = executorService.invokeAny(callables);

        System.out.println("result = " + result);

        executorService.shutdown();
    }
}
```

### invokeAll

The `invokeAll() `method invokes all the Callable objects you pass to it in the collection passed as parameter. The
`invokeAll()` returns a list of Future objects via which you can obtain the results of the executions of each Callable.

Keep in mind that a task might finish due to an exception, so it may not have "succeeded". There is no way on a Future
to tell the difference.

```java
public class demo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            public String call() {
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            public String call() {
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            public String call() {
                return "Task 3";
            }
        });

        List<Future<String>> futures = executorService.invokeAll(callables);

        for (Future<String> future : futures) {
            System.out.println("future.get = " + future.get());
        }

        executorService.shutdown();
    }
}
```

## Cancel Task

You can cancel a task (Runnable or Callable) submitted to a Java ExecutorService by calling the `cancel()` method on the
Future returned when the task is submitted. Cancelling the task is only possible if the task has not yet started
executing. Here is an example of cancelling a task by calling the `Future.cancel()` method:

## Shutdown

To terminate the threads inside the ExecutorService you call its `shutdown()` method. The ExecutorService will not shut
down immediately, but it will no longer accept new tasks, and once all threads have finished current tasks, the
ExecutorService shuts down. All tasks submitted to the ExecutorService before `shutdown()` is called, are executed.

## ShutdownNow

If you want to shut down the ExecutorService immediately, you can call the `shutdownNow()` method. This will attempt to
stop all executing tasks right away, and skips all submitted but non-processed tasks. There are no guarantees given
about the executing tasks. Perhaps they stop, perhaps to execute until the end. It is a best effort attempt.

## awaitTermination

The ExecutorService `awaitTermination()` method will block the thread calling it until either the ExecutorService has
shutdown completely, or until a given time out occurs. The `awaitTermination()` method is typically called after calling
`shutdown()` or `shutdownNow()`.