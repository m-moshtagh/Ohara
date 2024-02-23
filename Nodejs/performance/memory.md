# Memory Management

Javascript manages memory through semilar mechanism with Heap.

## GC

JS also uses Mark and sweep algorithm for garbage collection

## Memory Leakage

Memory leaks happen when expected short lived objects are attached to long lived ones.

### Causes

- non-cleared timeouts/intervals
- global variables
- Closures with an external variable reference(global variable)
- huge uncached objects
- circular object reference
- unreferenced nodes

### Possible Fixes

- clear timeout using functions
- clear event emitter listners

### Tools

#### Javascript leaks

- process.memoryUsage(not suitable for Production. Dev purpos onlu)
- Heap Snapshots
- Allocation timelines
- Sampling Heap Profiler
- V8 Heap Statistics

#### Native leaks

- Valgrind
- tcmalloc & Sampling Heap Profiler
