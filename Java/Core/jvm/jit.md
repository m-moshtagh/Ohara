# JIT

The power of bytecode makes executing Java code portable for different architectures. First our human readable source code which is .java file gets compiled into bytecode which is portable. Then this bytecode can get executed anywhere Java platform(JRE) is available.
The bytecode gets executed in machine code by interpreter. The interpretation is a slow job, in order to optimize it most interpreter languages use a Just in time compiler mechanism.

## C1 & C2 compilers

Inside JIT a C1 compiler tries to compile the bytecode in machine language and run it. meanwhile also collects statistics like profilers to gather data on hotspots in code how many times a code gets executed. now with this data the C2 compiler optimizes the code in several ways.

### Optimisations

* Dead Code
  * Removing code which is not used
* Escape analysis
  * Moving Objects created in methods that never returned, to stack instead of heap
* Loops
  * Combining loops, unrolling loops, loop inversion etc.
* Method inlining
  * moving bodies of small methods withing the calling methods.
* Lock Removal
  * if only 1 thread ever uses the lock, remove it.
* Null Check elimination
  * if variable is never null, remove the null check code.
