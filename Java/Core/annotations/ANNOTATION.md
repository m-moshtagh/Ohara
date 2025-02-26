# Annotations:

Using annotations we can provide metadata to our Java Classes and interface.

## Benefits of java annotations:

* check runtime bugs in compile time
* unify the application configuration files(avoid XML files)

Java has some built-in annotations which can be categorized in two forms:

* Applied to code: These types give hints to compiler, these mostly doesn't provide metadata but effect on code.
* Applied to other annotations:

### Three questions before using annotations:

1. which package or framework contains this annotation?
2. which container or manager will recognize and read this annotation?
3. what will be done by the manager, if this annotation presents?

## builtin annotations

* @Override
* @Deprecated
* @FunctionalInterface
* @SuppressWarning
* @SafeVarargs

## Defining custom annotation:

We can define an annotation using `@interface` keyword. annotations can be used at class, method, parameter
and, also property level.
we can define metadata for annotations. They are defined like methods without access identifiers. they have return type.
We can also set default values so, programmers don't provide them if they don't want.

* @Documented allows annotation to be available in java doc.
* @Target let us define where we want to use our annotation.
* @inherited will be available on inherited classes.
* @Repeatable will let us give multiple values to our target.
* @Retention declares the visibility of the annotation.

### @Target

This limits the target usage of the defined annotation. These options are available:

* TYPE -> class, interface
* FIELD -> field declaration including enum constants
* METHOD -> method declaration
* PARAMETER -> formal parameter declaration
* CONSTRUCTOR -> constructor declaration
* LOCAL_VARIABLE -> local variable declaration
* ANNOTATION_TYPE -> annotation type declaration
* PACKAGE -> package declaration

### @Retention

This can be initialized with `RetentionPolicy` enum. Can have three values:

* SOURCE -> the annotation will be removed during compile time. like `@Deprecated`
* CLASS -> Annotation will be available in binary file. this is less used than others. mostly used for code generators.
* RUNTIME -> annotation will be accessible during runtime of application.

> Default value of @Target is limit and @Retention is class.

### attributes

Annotations can have attributes, writing them looks like abstract methods. they have return type and can have default
values.

```Java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomValidator {
    int min() default 0;
    int max() default 100;
}
```
