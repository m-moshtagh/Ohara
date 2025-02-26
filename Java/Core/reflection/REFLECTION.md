# Reflection

We can do introspection or inspection on objects using reflection API.

## Class

Any object of the `Class` class contains metadata of another class. We can initialize the Class objects in three ways:

* invoking `obj.getClass()`
* using `Class.forName("fully qualified name of the target class")`
* using `FooClass.class`

```java
public class Example {
    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("java.lang.Object");
            System.out.println(aClass.getPackage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("initialize using object of a class");
        Class<?> bClass = getClass(1.2);
        System.out.println(bClass.getPackage());

        System.out.println("initialize using .class");

        System.out.println(getPackage(com.dogigiri.core.security.developsecurejavacode.injection.sql.JdbcConnectionProvider.class));
    }

    public static Class<?> getClass(Object object) {
        return object.getClass();
    }

    public static Package getPackage(Class<?> c) {
        return c.getPackage();
    }
}
```

## Class vs Interface

when calling getMethods from an interface it will only return the abstract method however, calling it from a class will
return the whole methods inheriting and the ones declared itself.

> getMethods vs DeclaredMethods means the declared methods are only the methods defined by the class.

by calling getMethods on a class we only see public methods unless we directly adjust the visibility.
