# Annotations:

Using annotations we can provide metadata to our Java Classes and interface.

# Benefits of java annotations:

* check runtime bugs in compile time
* unify the application configuration files(avoid XML files)

Java has some built-in annotations which can be categorized in two forms:

* Applied to code: These types give hints to compiler, these mostly doesn't provide metadata but effect on code.
* Applied to other annotations:

# builtin annotations

* @Override
* @Deprecated
* @FunctionalInterface
* @SuppressWarning
* @SafeVarargs

# Defining custom annotation:

We can define an annotation using @interface keyword. annotations can be used at class, method, parameter
and, also property level.
we can define metadata for annotations. They are defined like methods without access identifiers. they have return type.
We can also set default values so, programmers don't provide them if they don't want.

> To limit the access to metadata we can define @Retention(RetentionPolicy.CONSTANT).
> We have CLASS, RUNTIME and, SOURCE. in RUNTIME mode we can access metadata by reflection.

* @Documented allows annotation to be available in java doc.

* @Target let us define where we want to use our annotation.

* @inherited will be available on inherited classes.

* @Repeatable will let us give multiple values to our target.