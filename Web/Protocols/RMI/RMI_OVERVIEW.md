# RMI overview

When we have two application which have JVM We can remotly call a method from other one directly.

## Benefits

* Fast, based on TCP/IP
* less overhead

## Disadvantages

* Not Scalable -> classes should be upgraded on both sides
* in case of firewalls it needs to be routed on HTTP.
* NO web application server needed.
* Tight to Java
