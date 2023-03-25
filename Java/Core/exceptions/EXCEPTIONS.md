# Exceptions in Java

We handle problems in our code in java via Exceptions. We have three types of exceptions:

1. Checked: checked exceptions are usually prevented by compiler for example when there is no file or SQL exceptions
2. unchecked: these exceptions usually occur in runtime. arithmetic, illegalArgument, illegalState and indexOutOfBond
   nullPointer are part of this.
3. errors: outOfMemory and stackOverFlow

## Hierarchy:

* Throwable
    * Exceptions
      *RuntimeExceptions
    * Errors

## Handle Exceptions

To handle exceptions we use try catch blocks. we wrap the part of code that Exception might occur with try and then use
catch consecutive. We can also catch multiple exceptions but the hierarchy of Exceptions matters.
We also have a `finally` block which executes no matter what happens. This is ideal for closing the resources in our
code.

## Trt With resource

For Objects that implement AutoClosable interface we can use try with resource introduced in java 7 and not close them
explicitly.

> Whenever we validate our code we can throw an Exception.

## Create Custom Exceptions

We can also create custom exceptions by inheriting Exception class and call super class constructor in them.

## Chain Exceptions

There are cases we need to chain some exceptions there is two ways to implement this.

1- use init method of one of them and pass the other one to it.
2- we can also pass it to the constructor of that exception.
