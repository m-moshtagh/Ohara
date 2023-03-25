# Collection Framework

## Hierarchy of collection API:

* Iterable
    * Collection
        * List
            * ArrayList
            * LinkedList
        * Queue
            * PriorityQueue
        * Set
            * HashSet

The iterable interface which is on the peak of our hierarchy, is not actually a part of Collection framework and is
part of java.lang package. This interface represents that we can iterate over elements of the object. (using foreach)
There is a common way to use Iterable interface with hasNext() and next() methods using while loop but when we implement
this interface we can also use foreach which is so simple.

Iterable interface force us to implement the iterator() method. and the Iterator interface makes us to implement
next() and hasNext() methods. We can create an inner class that implements Iterator interface and then use it in the
Iterable interface method.

## The collection Interface

This interface is a container of other objects. add(), remove() and contains() are its main methods

## The List interface

List is sequence of collections. when we want order in collection and index is important for us ( collection doesn't
support indexes), we can use this interface.
listOf() method helps us create a fixed immutable list. (if we use copyOf() method we make an immutable type too)

## Queue

Queue interface is used whenever we need to use or execute something in the order they enter the list. Two most used
classes are ArrayDeque and Priority queue.
ArrayDeque is double ended queue which lets elements enter queue both sides.
PriorityQueue is like the queue OS uses to execute tasks in our system.

## Set

Set When we need to store unique values we use Set. set stores elements base on hash of the object so the order
doesn't matter. we can also use addAll() for union and removeAll() for different and retainAll() for intersection.

## Map

Map Whenever we want to access an object using a keyword we use Map interface which is a hashtable.
we can use entrySet() if we want to access all the elements in map. keySet() to retrieve all keys and values() to
retrieve all values.

### Examples

#### Sort & remove redundant values

```java
import java.util.Arrays;
import java.util.TreeSet;

public class Demo {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(customSort({5, 6, 2, 2, 4, 5})));
    }

    public static Integer[] customSort(Integer[] array) {
        return new TreeSet<Integer>(List.of(array)).toArray(new Integer[0]);
    }
}
```