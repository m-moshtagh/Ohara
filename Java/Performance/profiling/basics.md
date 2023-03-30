# Introduction to profilers

In order to understand what happens under the hood we can use profilers.

## Profiler Tools

A Java Profiler is a tool that monitors Java bytecode constructs and operations at the JVM level. These code constructs and operations include object creation, iterative executions (including recursive calls), method executions, thread executions, and garbage collections.

### Async profiler

Java Sampling Profilers are usually designed using the JVM Tool Interface (JVMTI) and collect stack traces at a safepoint. Therefore, these sampling profilers can suffer from the safepoint bias problem.

For a holistic view of the application, we need a sampling profiler that doesn’t require threads to be at safepoints and can collect the stack traces at any time to avoid the safepoint bias problem.

Async profiler avoids the safepoint bias problem by using the AsyncGetCallTrace API provided by HotSpot JVM to profile the Java code paths, and Linux’s perf_events to profile the native code paths.
