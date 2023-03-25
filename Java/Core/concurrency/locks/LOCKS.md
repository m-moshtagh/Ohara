# Java Lock interface

The Java Lock interface, `java.util.concurrent.locks.Lock`, represents a concurrent lock which can be used to guard
against race conditions inside critical sections. Thus, the Java Lock interface provides a more flexible alternative to
a Java synchronized block.

## Difference from Synchronized blocks

* A synchronized block makes no guarantees about the sequence in which threads waiting to entering it are granted
  access.
* You cannot pass any parameters to the entry of a synchronized block. Thus, having a timeout trying to get access to a
  synchronized block is not possible.
* The synchronized block must be fully contained within a single method. A Lock can have its calls to `lock()` and
  `unlock()` in separate methods.

## How to use

First a Lock is created. Then it's `lock()` method is called. Now the Lock instance is locked. Any other thread calling
`lock()` will be blocked until the thread that locked the lock calls `unlock()`. Finally, `unlock()` is called, and the
Lock is now unlocked so other threads can lock it.

> Obviously all threads must share the same Lock instance. If each thread creates its own Lock instance, then they will
> be locking on different locks, and thus not be blocking each other from access. I will show you later in this Java
> Lock tutorial an example of how a shared Lock instance looks.

```java
public class CounterLock {

    private long count = 0;

    private Lock lock = new ReentrantLock();

    public void inc() {
        try {
            lock.lock();
            this.count++;
        } finally {
            lock.unlock();
        }
    }

    public long getCount() {
        try {
            lock.lock();
            return this.count;
        } finally {
            lock.unlock();
        }
    }
}
```

## Reentrant Locks

A lock is called reentrant if the thread that holds the lock can lock it again. A non-reentrant lock is a lock which
cannot be locked again if locked, not even by the thread that holds the lock. Non-reentrant locks may result in
reentrant lockout which is a situation similar to a deadlock.

## Fairness

The ReentrantLock behaviour is unfair by default. However, you can tell it to operate in fair mode via its constructor.
The ReentrantLock class has a constructor that takes a boolean parameter specifying whether the ReentrantLock should
provide fairness or not to waiting threads. Here is an example of creating a ReentrantLock instance using fair mode

> ReentrantLock lock = new ReentrantLock(true); // in order to solve starvation

## More functions

### Lock

The `lock()` method locks the Lock instance if possible. If the Lock instance is already locked, the thread
calling `lock()`is blocked until the Lock is unlocked.

### lockInterruptibly

The `lockInterruptibly()` method locks the Lock unless the thread calling the method has been interrupted. Additionally,
if a thread is blocked waiting to lock the Lock via this method, and it is interrupted, it exits this method calls.

### tryLock

The tryLock() method attempts to lock the Lock instance immediately. It returns true if the locking succeeds, false if
Lock is already locked. This method never blocks.

### trylock(long timeout, TimeUnit timeUnit)

The `tryLock(long timeout, TimeUnit timeUnit)` works like the `tryLock()` method, except it waits up the given timeout
before giving up trying to lock the Lock.

### unlock

The `unlock()` method unlocks the Lock instance. Typically, a Lock implementation will only allow the thread that has
locked the Lock to call this method. Other threads calling this method may result in an unchecked exception (
RuntimeException).

### getHoldCount

The Java ReentrantLock `getHoldCount()` method returns the number of times a given thread has locked this Lock instance.
A thread can lock a Lock more than once due to Lock reentrant.

### getQueueLength

The ReentrantLock `getQueueLength()` method returns the number of threads waiting to lock the Lock.

### hasQueuedThread

The ReentrantLock `hasQueuedThread(Thread thread)` method takes a Thread as parameter and return true if that Thread is
queued up waiting to lock the Lock, and false if not.

### isFair

The ReentrantLock `isFair()` method returns true if this Lock guarantees fairness among threads waiting to lock it, and
false if not.

### isHeldByCurrentThread

The ReentrantLock `isHeldByCurrentThread()` method returns true if the Lock is held (locked) by the thread calling
`isHeldByCurrentThread()`, and false if not.

### isLocked

The ReentrantLock `isLocked()` method returns true if the Lock is currently locked, and false if not. 