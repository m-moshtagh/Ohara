# Object class

All Java classes extend from Object class. So there are certain functions in Object class which are inherited to all
classes in java.

## hashcode

hashcode is a native function that is used to create 32-bit number in order to identify each object,

### Algorithms

Algorithm and implementation is base on JVM. By default, hotspot uses -XX:hashCode=5 which takes a unique number per
thread as a seed and generates a random number.
The problem is there's a chance of collision in big numbers so, it's always recommended to override this method in class
and provide an algorithm to produce a unique hashcode base on the business.

## Equals

Equals is used to compare two objects from each other. by default, Object class implementation compares `this`
reference with the target object. However, it's always recommended to provide a decent equals function in the class
correct context.

```java
 public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String anotherString) {
            int n = value.length;
            if (n == anotherString.value.length) {
                char[] v1 = value;
                char[] v2 = anotherString.value;
                int i = 0;
                while (n-- != 0) {
                    if (v1[i] != v2[i])
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }
```