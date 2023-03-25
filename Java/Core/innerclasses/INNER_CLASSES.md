# Inner Classes in Java

## Static Inner class

```java
public class EnclosingClassA {
    // define members of enclosing class.
    private static int a = 5;
    public static String s = "Java World.";
    public int x = 76;

    public static class StaticInnerClassA {
        // define members of static inner class.
        public static int a = 124;
        public static int aa = 89;
        public static String ss = "Java Language.";

        // define a static method in the static inner class.
        public static void one() {
            System.out.println("Accessing members of enclosing " +
                    "class from within static inner class:");
            System.out.println("a = " + EnclosingClassA.a);
            System.out.println("s = " + s);
        }
    }
}
```

* A static inner class can define its own constructors.
* A static inner class can access to all static members of its enclosing class, even to private static members.
* A static inner class cannot access to non-static members of its enclosing class. For instance, you cannot access the
  variable x from within StaticInnerClassA.
* The members of a static inner class are local to that class, meaning that you can use these names as variables of its
  enclosing class. For instance, the variable `a` is defined in the static inner class and in its enclosing class.
* To access the variable `a` of the enclosing class from within static inner class, use the name of enclosing class:
* To access the members of a static inner class from within its enclosing class, the fully qualified names must be used
* The Java compiler will create two class files named EnclosingClassA.class
  and `EnclosingClassA$StaticInnerClassA.class`.
  Note that the '$' character (Dollar sign) separates the static inner class name and its enclosing class name.

## Member Inner class

```java
public class EnclosingClassB {
    // define members of enclosing class.
    private static int a = 5;
    public static String s = "Java World.";
    public int x = 76;

    public class MemberInnerClassB {
        // define members of member inner class.
        public int a = 124;
        public int aa = 89;
        public String ss = "Java Language.";
        public final static int age;

        // define method one() in the member inner class.
        public void one() {
            System.out.println("Accessing members of enclosing " +
                    "class from within member inner class:");
            System.out.println("a = " + EnclosingClassB.a);
            System.out.println("s = " + s);
            System.out.println("x = " + x);
        }
    }

    // this method returns an instance of member inner class.
    public MemberInnerClassB createMemberClass() {
        return this.new MemberInnerClassB();
    }
}
```

* A member inner class can define its own constructors.
* A member inner class can access to all static and non-static members of its enclosing class, even to private members.
* To access the variable `a` of the enclosing class from within member inner class, the keyword this must be prefixed by
  the name of enclosing class
* The instance of the member inner class is created by using the createMemberClass() method of its enclosing class
* To access the members of the member inner class from within its enclosing class, use an instance of the member inner
  class
* The Java compiler will create two class files named EnclosingClassB.class
  and `EnclosingClassB$MemberInnerClassB.class`.
  Note that the '$' character (Dollar sign) separates the member inner class name and its enclosing class name.

## Local Inner class

```java
public class EnclosingClassC {
    // define members of enclosing class.
    private static int a = 5;
    public static String s = "Java World.";
    public int x = 76;

    // define method one() containing local inner class.
    public void one(final float f) {
// define local variables of the method one().
        final int a = 124;
        final int aa = 89;
        final String ss = "Java Language.";
        class LocalInnerClassC {
            final float ff = f;

            // define method two() contained in local inner class.
            public void two() {
                System.out.println("ff = " + ff);
                System.out.println("a = " + a);
                System.out.println("aa = " + aa);
                System.out.println("ss = " + ss);
            }
        }
        System.out.println("Accessing local variables of " +
                "method one() from within local inner class:");
        new LocalInnerClassC().two();
        System.out.println("-------------------------------------");
        System.out.println("Accessing members of enclosing class " +
                "from within method one() containing local inner class:");
        System.out.println("a = " + EnclosingClassC.a);
        System.out.println("s = " + s);
        System.out.println("x = " + x);
    }
}
```

* A local inner class can define its own constructors.
* You cannot use the access modifiers private, protected, public, and static in declaring a local inner class.
* A local inner class cannot include static members.
* A local inner class can access to all local data of its enclosing method (including the method's parameters), if you
  declare them final.
* The members of a method containing a local inner class are local to that method, meaning that you can use these names
  as variables of its enclosing class. For instance, the variable `a` is defined in the method one() and in its
  enclosing
  class.
* The Java compiler will create two class files named EnclosingClassC.class and
  `EnclosingClassC$1$LocalInnerClassC.class.` Note that the '$' characters (Dollar signs) separate the local inner class
  name, the sequence number, and the enclosing class name.

## Anonymous Inner class

```java
public class EnclosingClassD {
    // define members of enclosing class.
    private static int a = 5;
    public static String s = "Java World.";
    public int x = 76;

    // define method one() containing anonymous inner class.
    public void one(final float f) {
// define local variables of the method one().
        final int a = 124;
        final int aa = 89;
        final String ss = "Java Language.";
        System.out.println("Accessing local variables of " +
                "method one() from within anonymous inner class:");
// create an instance of anonymous inner class.
        (new EnclosingClassD() {
            // define method two() contained in anonymous inner class.
            public void two() {
                System.out.println("ff = " + f);
                System.out.println("a = " + a);
                System.out.println("aa = " + aa);
                System.out.println("ss = " + ss);
            }
        }).two();
        System.out.println("-------------------------------------");
        System.out.println("Accessing members of enclosing class " +
                "from within method one() containing anonymous inner " +
                "class:");
        System.out.println("a = " + EnclosingClassD.a);
        System.out.println("s = " + s);
        System.out.println("x = " + x);
    }
}
```

* An anonymous inner class cannot include constructors.
* You cannot use any access modifier such as private, protected, public, static, and so on, in declaring an anonymous
  inner class.
* The keyword class is not used in declaring an anonymous inner class.
* An anonymous inner class cannot include static members.
* An anonymous inner class can access to all local data of its enclosing method(including the method's parameters), if
  you declare them final.
* The members of a method containing an anonymous inner class are local to that method, meaning that you can use these
  names as variables of its enclosing class. For instance, the variable `a` is defined in the method one() and in its
  enclosing class.
* To access the variable `a` of method one() from within anonymous inner class, we directly call it.
* To access the variable `a` of the enclosing class from within method one(), the keyword this is used
* The Java compiler will create two class files named EnclosingClassD.class and `EnclosingClassD$1.class`. Note that
  the '$' character (Dollar sign) separates the sequence number and the enclosing class name.


