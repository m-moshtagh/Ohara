# Performance Issues

- Low Performance
- Application crashes
- Memory Leakage

## Low performance

in order to debug these issues we can

- Take snapshot of stacktraces
- visualize via flameGraphs
- Analyze
- Fix
- Repeat

## Crashes

Since crashes are not easy to simulate we can use postmortem debugging

- Take core Dump
- Restart App
  - Continue Serving traffic
- Load core dump elsewhere
  - Debug
  - Engineer fix

> We can alsoe configure node to dump core on error.

## Memory leaks

- Take core dumps using gcore
- compare object counts using ::findjsobjects
- growing objects are likely leaking
- inspect objecst for more context ::jsprint
- walk reverse reference to find root obj ::findjsobjects -r

## More state than logs

- Detailed stacktrace ::jsstack
- function args for each frame ::jsstack -vn()
- get state of any object and its provenance ::jsprint, ::jsconstructor
- get source code of any function ::jssource
- find arbitrary js objects ::findjsobjects
- unmodified node binary!
