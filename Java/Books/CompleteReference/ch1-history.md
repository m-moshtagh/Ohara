# The history and evolution of Java

computer languages evolve for two reasons: to adapt to changes in environment and to implement advances in the art of programming.
C ===> C++ ===> Java

## ByteCode magic

Java uses hybrid way to execute a program. The java code compiles into a highly optimized sets of instructions which are meant to be interpret and executed by JVM.

> This bytecode is the essence of portablity in Java since The code is architecture neutral however, JRE needs to be implemented for each architecture.

Although Java was designed as an interpreted language, there is nothing about Java that prevents on-the-fly compilation of bytecode into native code in
order to boost performance. For this reason, the HotSpot technology was introduced not long after Java’s initial release. HotSpot provides a just-in-time
(JIT) compiler for bytecode.

> One other point: There has been experimentation with an ahead-of-time compiler for Java. Such a compiler can be used to compile bytecode into native code prior to execution by the JVM, rather than on-the-fly.

## Buzzwords

• Simple
• Secure
• Portable
• Object-oriented
• Robust
• Multithreaded
• Architecture-neutral
• Interpreted
• High performance
• Distributed
• Dynamic
