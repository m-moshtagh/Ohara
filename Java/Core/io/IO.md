# Java Input & Output API:

IO refers to input output. so whenever we need to do some input and output operations we use Java IO API.
Java handles these operations using streams. input streams are for reading or getting data and, output streams are
for writing or sending data. Java has two kinds of streams:

## Char streams

These classes are usually for handling text based files or data. The naming convention of these classes usually ends
with reader and writer.

## Byte Streams

These classes are usually for handling any files except text based files. The naming convention of these classes usually
ends with stream.

> Java IO is implemented in decorator design pattern, so we can add features depending on classes.
> Java IO API is synchronized.

### OutputStreams:

We use these classes to write into different media:

* File
* Telnet
* ByteArray
  ...

> OutputStream class is abstract, so we mostly use same methods trying to work with its implementations.

Its fundamental method is: write(int n) which takes an int argument from 0 to 255 and writes corresponding byte to
output stream.

> WE can also Buffer our streams by chaining them with BufferedOutputStream.
> WE should always flush() because, if we set buffer to 1024 byte and at the end we don't have that large data to
> send to client, server will wait for response but client has not received the whole message yet, so we need to flush
> before closing all streams to avoid data loss.

> closing a stream will release any resource it was working with.
> It's better to use dispose pattern for above purpose.(closing resource in finally block, check if it was null just
> ignore it. This is often used for streams, JDBC connections, Sockets and Channels.) Try with resource simplified this
> task.
> We can pass true to constructor in order to append to the file.

### InputStreams:

To Read data from particular media we use concrete subclasses of this type.
read() method reads a single byte of a data from inputStream source and returns an int from 0 to 255. end of stream is
signified by returning -1.

> Reading and writing is slow, so if we have anything important to run it's better to put them in their own thread.
> It's always better to use read(byte[] input, int offset, int length) method because reading 1byte is heavy task.
> we need to always close our inputStream to ensure that our job is completed correctly.
> We can also skip() in any special situation.
> We also have available() method to return the amount of bytes that are available.

InputStream class also has three methods for backing up and reread data that we've already read.

* mark()
* reset()
* markSupported()

We can mark a position in stream and further by resetting the stream we can reread the data. Some InputStreams don't
support it we can check with markSupported() method. There's also a limit on how far we can get back and its specified
using readAheadLimit in mark method.

> marking a second location deletes the first one.

### FilterStreams

Filters can be chained to OutputStream and, modify data as it's read or written. like encrypting or compressing it.
OutputStreams & InputStreams are rawTypes. to determine the types we have to explicitly code ourselves but, Java has
provided some type filters streams which come to our aid this way.
Filters come two versions:

* filter streams
* readers & writers

#### Chaining Filters

Streams are connected using their constructors.

> `FileInputStream fin = new FileInputStream("data.txt");`
> `BufferedInputStream bin = new BufferedInputStream(fin);`

> The above code lets us use both of them but in order to prevent this we can write it this way:

> `InputStream in = new FileInputStream("data.txt");`
> `in = new BufferedInputStream(in);`

> this way won't let us use buffered stream's additional methods.

### Buffered Streams:

The BufferedOutputStream stores written data in a buffer( a protected byte array field named buf) until the buffer
is full or stream is flushed. Then it writes the data onto the underlying output stream all at once. A single writes
of many bytes is much faster than many small writes added to the same thing.
The BufferedInputStream also has a byte array named buf which its read() method first looks in this buffer and if it
was empty it will go for the underlying source.

> the default size of buffer is 2048 bytes for inputStream & 512 bytes for OutputStream. The ideal size is
> dependant to what stream we are buffering. for network connection we want something larger.

### PrintStreams

We all use this OutputStream because of println() method. This Class tries to convert its arguments to String.
We need to avoid using this on network because, this is platform dependant because of end line arguments of different
platforms. also, printStream uses platforms default encoding so the client may have problem. Third problem is that it
ignores all the exceptions.

### Data Streams

This stream classes aim to read and write primitive data types in binary format.

## Readers & Writers

Java's native encoding is UTF-16. So when it's not ASCII the chars & bytes are actually the same. So in order to work
So Java provides set of classes for working with characters instead of bytes for text based sources.
Two abstract classes are defined for reading & writing characters:

* Writer
* Reader

> These two classes use Unicode characters.

The most used concrete subclasses are:

* InputStreamReader
* OutputStreamWriter

### Writers:

This class is mirror of OutputStream class.

#### OutputStreamWriter:

This class converts content into bytes according to specific encoding and writes it to underlying output stream.

### Readers:

This class is mirror of InputStream class.

#### InputStreamReader:

This class converts the stream to specific encoding and returns it.

#### FilterReaders & FilterWriters:

We can also chain up readers & writers like outputStreams & inputStreams for different purposes.

* BufferReader & BufferWriter
* LineNumberReader
* PushBackReader
* PrintWriter
