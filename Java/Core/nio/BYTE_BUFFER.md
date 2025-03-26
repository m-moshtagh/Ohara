# Byte buffer

A ByteBuffer is a fundamental data structure in Java's New I/O (NIO) package that provides a container for working with
sequences of bytes. It's essentially a fixed-size block of memory that you can use to efficiently read from and write to
various I/O sources like files, network sockets, or in-memory data.

## Basic Concepts

* Capacity: Total size of the buffer
* Position: Current read/write index
* Limit: First index that should not be read/written
* Mark: Remembered position for later return

## Navigation

### mark

Sets a bookmark at the current position

```java
ByteBuffer buf = ByteBuffer.allocate(10);
buf.put((byte)1);
buf.put((byte)2);
buf.mark();  // Remember this position (position=2)
buf.put((byte)3);
buf.put((byte)4);

// Later we can return to the marked position
```

### reset

Returns the position to the previously marked position

```java
buf.reset();  // Position goes back to 2
byte next = buf.get();  // Will read byte 3 again
```

> Throws InvalidMarkException if no mark is set or mark is invalidated

### limit

Sets the limit (index where reading/writing should stop)

```java
ByteBuffer buf = ByteBuffer.allocate(10);
// Write data
buf.put(new byte[]{1,2,3,4,5});
buf.flip();  // Sets limit=position (5) and position=0
```

### clear

Prepares buffer for new writing by, Setting position to 0, Setting limit to capacity and Discarding the mark.
> Doesn't erase data! Just resets pointers:

```java
ByteBuffer buf = ByteBuffer.allocate(10);
buf.put((byte)1);
buf.put((byte)2);
buf.clear();
buf.put((byte)3);  // Overwrites position 0
```

### rewind

Prepares to re-read the buffer by, Setting position to 0, Keeping limit unchanged and discarding the mark.

## common pitfalls

* Forgetting to `flip()`: Trying to read without flipping after writing
* Assuming `clear()` erases data: It just resets pointers
* Invalid mark usage: Mark is cleared by operations that change position/limit
* Mixing absolute and relative operations

```java
buf.put(10, (byte)1);  // Absolute put
buf.put((byte)2);      // Relative put - continues from position
```