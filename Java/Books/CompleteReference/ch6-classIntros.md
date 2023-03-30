# Introduction to classes

a class is a template for an object, and an object is an instance of a class.

## Declaring objects

A class creates a new data type that can be used to create objects. That is, a class creates a logical framework that defines the relationship between its members. When you declare an object of a class, you are creating an instance of that class. Thus, a class is a logical construct. An object has physical reality. (That is, an object occupies space in memory.)

First, you must declare a variable of the class type. Second, you must acquire an actual, physical copy of the object and assign it to that variable. You can do this using the new operator.

> The new operator dynamically allocates (that is, allocates at run time) memory for an object and returns a reference to it. This reference is, essentially, the address in memory of the object allocated by new.

> You do not need to use new for such things as integers or characters. The answer is that Java’s primitive types are not implemented as objects. Rather, they are implemented as “normal” variables. This is done in the interest of efficiency.

It is important to understand that new allocates memory for an object during run time. The advantage of this approach is that your program can create as many or as few objects as it needs during the execution of your program. However, since memory is finite, it is possible that new will not be able to allocate memory for an object because insufficient memory exists. If this happens, a run-time exception will occur.

> _REMEMBER When you assign one object reference variable to another object reference variable, you are not creating a copy of the object, you are only making a copy of the reference._

> We can always unhook a variable when assigning the reference to another variable by setting the first one to 'null'.

## Methods

We define behavior of the class and operations that are done on instance variables by methods.

> Parameters are variables that are defined in method definition which method operates with and arguments are the values which are passed to methods when trying to work with them.

## Constructors

When initializing the class using `new` keyword the constructor does the job, It's always the best place to initialize the instance variables of a class.

> Java creates a default constructor with no parameters that initializes instance variables with default values.
