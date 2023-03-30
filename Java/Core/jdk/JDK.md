# Java Development Kit

JDK is the basic kit which contains Java language and all the required tools which are needed to develop or run a Java program.

JDK contains programs inside it which are usable via command line. like `java`, `javac` and...

We can check JDK version by typing `java -version` inside terminal or windows command line.

> The correct path to JDK_HOME/bin must have been set in windows environment variables or linux $PATH.

## JDK Sharing Mode

When we load multiple processes of JVM. There's section of a process which is always loaded and is common between processes. JVM has a `sharing mode` which optimizes this part of process one time and share it between multiple processes to optimize performance of the system.

## Javap

using `javap` command we can parse the bytecode and see what exactly is going on in our code. This is usually done for debugging and performance tunning.

## Javadoc

This comamnd utilize us to generate Java documents in HTML for our Java source code.
