# What is javascript

Javascript is one of the most famous languages which is used for all purposes. At first javascript was designed to be run inside browsers however, this changed after Ryan Dahl took the chrome V8 engine and embedded it inside C++ code and created nodejs we can now run javascript anywhere.

## Javascript engines

* Firefox: Spidermonkey
* Chrome: V8

## ECMAscript

This is specification that defines standards for how javascript should be. Since 2015 it has annual release.

## Variables & Constants

We store data temporary inside memory inside a container named variables. In legacy before ES6 we used `var` to declare variabls, now we use `let` for modifiable variables and `const` for constant variables.
`let name = 'foo';`

> The problem with var is that it even exists outside the block it's defined in which leads to bugs.

### Naming rules

* Can't be keyword
* Should be meaningful
* Can't start with number
* Can't contain a space or hyphen
* Case sensative

## Primitive types

We have String, booleans, Number, undefined and null in javascript.
Javascript is a dynamic language and the type of the variable is determined at runtime based on the value. We can determine a type using `typeof`.

> undefined is a type and the value is also "undefined", Also this is the default value of variables if no value is declared for them.

## Reference types

We have Objects, Arrays and Functions.

### Objects

an Object is a representation of a thing with some properties and behaviors.

```javascript
let person = {
    name: 'foo',
    age: 20
};
person.name = 'bar';
person['name'] = 'foo';
```

> we can use bracket notation in case of runtime accessing a property of an object.

### Arrays

In case of we need a list of a type we use arrays. Arrays are declared using `[]`. Like other languages we use indices to access array propertis.
Size and element types of arrays are dynamic in javascript.

> Arrays are kind of objects which can be determind by `typeof` keyword.

### Functions

functions are set of statements that calculate a tast and can return a value. They are declared using `function` keyword.
Functions can be declared with multiple parameters and during the call we can pass the arguments.

```javascript
function greet(name) {
    console.log("Hello, " + name);
}

greet('john');
```

## Operators

### Arithmatic

* '+'
* '-'
* '*'
* '/'
* '%'
* '**'
* '++'
* '--'

### Assignment

We have `=` and also all arithmatics like `+=`.

### Comparison

* '>'
* '<'
* '>='
* '<='
* '==='
* '!=='

> we have `==` as equality which is called loose equality operator which doesn't check the type of the varibles However `===` strict equality operator checks that the two operands have the same type. using the loose one we can even compare `true == 1` and it returns true.

### Ternary operator

The ternary or condition checks a condition and depending on the result it returns the value.

```javascript
let points = 110;
let type = points > 100 ? 'gold' : 'silver';
```

### Logical operators

* '&&'
* '||'
* '!'

The operands of logical operators are not only booleans and we can use other types and base on the truthy and falsy the result will be determind.

falsy:

* false
* undefined
* null
* 0
* ''
* NaN

> also we have short-cicuting for example in the expression `false || 1 || 2' the returned value is '1' and the '2' is not even evaluated.

### bitwise

* '|'
* '&'

## Control Flow

### conditional

In javascript we have `if`, `else if` and `else` also, switch cases and syntex is pretty much the same as other languages.

### Loops

The synatx for lagacy for and while loops is the same with other langs like java.
For iterating over elements in array or objects we have two loops: `for-in` & `for-of`

```javascript
let person = {
    name: 'foo',
    age: 10
};

for(let property in person)
    console.log(property, person[property]);
```

> Since we may not know the exact property names of the person objects we use bracket notation instead of dot notation to access the fields.

For of started from ECS6. It's more ideal to loop through arrays because it doesn't deal with the index stuff and directly picks the elements.

```javascript
let colors = ["green", "red", "blue"];
for(let color of colors)
    console.log(color);
```

## Objects in Js

We can use objects to group highly related components and maintain and use them as a single point inside our programs.

Objects are dynamic in javascript which means, we can always add new fields or functions to the objects in our programs.

> This is not prohibitet even for constant objects because `const` prevents these objects to be reassigned to other variables.

Below we use OOP style object literal notation to define a circle object:

```javascript
const circle = {
    radius: 1,
    location: {
        x: 1,
        y: 1
    },
    isVisible: true,
    draw: function() {
        console.log('draw');
    }
};
```

In this notation value of the keys can be anything.

### Factory functions

Problem with above notation is that if we need to define another object we need to duplicate it and in that case we are duplicating the draw function. Here we can use a factory function to create the object for us.

```javascript
function createCircle(radius) {
    return {
        radius, // this line is equalivant to radius: radius,
        draw() {
            console.log('draw');
        }
    }
}
```

> omitted the location and isVisible fields for simplicity.

### Constructor functions

On the other hand we can also create Constructors and create objects using `new` keyword.

```javascript
function Circle(radius) {
    this.radius = radius,
    this.draw = function() {
        console.log('draw');
    }
}

const circle = new Circle(1);
```

> Every object has a `constructor` property which references the object that was used to create the current object.

### Functions are objects

Functions in js are also objects, which have builtin functions and properties.

If we try to access Circle object constructor property we figure out it was created by a built in Function() constructor.

```javascript
const Circle1 = new Function('radius', `
this.radius = radius,
    this.draw = function() {
        console.log('draw');
    }
`);

const cricle = new Circle1(1);
```

Constructor functions have two methods: `call` and `apply` which can be used as alternative to `new Object` syntax.

```javascript
Circle.call({}, 1);
Circle.apply({}, [1,2,3])
```

first argument is an empty object like what `new` creates and the next `1` is the argument passed to Circle function. If we have multiple arguments we pass them in array and use apply function.

### Value Types & Reference Types

In js value types(primitives) are copied by valued and reference types are copied by reference:

Value Types:

* Number
* String
* Boolean
* Symbol
* undefined
* null

Reference types:

* Object
* Function
* Array

### Enumerating objects

We can use for in loops to loop through objects fields. for of loop doesn't work because it only works on iterables. However since everyobject underneath has Object function we can use `Object.keys()` or `object.entries()` to extract a iterable and use for of to loop through our object fields.

```javascript
for(let key in circle)
    console.log(key, circle[key]);

for(let key of Object.keys(circle))
    console.log(key);

for(let entry of Object.entries(circle))
    console.log(entry);

```

> We can also check if an object has a certain field using `if('radius' in circle) console.log('yes');`

### Cloning an object

Old ways we loop through all fields of an object and copied them in the new one.
now we can use `Object.assign()` functions which can be used to copy one or multiple objects into a destination object.
We can also use `...` spread operator to copy values of an object into a new one.

```javascript
const another = {};
for(let key in circle)
    another[key] = circle[key];

const another = Object.assign({}, circle);

const another = {...circle};
```

## Arrays in Js

There are lots of operations built in for arrays in js.

### unshift

We can shif all elements to right and add elements from left using this method.

### splice

we can add new elements in a certain position of an array and also, say to delete 0 or any elements from that position.

### indexOf

We can find elements in array using this method. returns -1 if not present.

### lastIndexOf

same as above but returns the last element's index. We can also, pass an extra argumenmt to start the search from a specific index.

### includes

checks if an element exists in array.

### find

in order to search for reference types in arrays we need to use find or findIndex and pass it a predicate callback function to check the criteria.

```javascript
const courses = [
    { id: 1, name: 'a'},
    { id: 2, name: 'b'},
]

const course = courses.find(function (course) {
    return course.name === 'a';
});

console.log(course)
```

> Above can be simplified using arrow Functions

```javascript
const course = courses.find(course => course.name === 'a');
```

### remove elements

we can remove elements using:

* pop() -> remove the last element and return it.
* shift() -> remove the first element and return it.
* splice() -> remove any element from any index.

### emptying arrays

There are several solutions:

* assign the array to a `[]` but if we have another reference to that array it won't be garbage collected
* `array.length = 0`; -> this will truncate the array.
* `array.splice(0, array.length);`
* put `array.pop()` in a while loop which is not recommended.

### combining and slicing arrays

We can combine arrays using `concat()` method. also we can use `slice()` and slice the arrays.

> zero arguments to slice method would copy the whole array, we can also, give one argument for starting point or two for start and end points.

since ES6 we can use spread operator for a cleaner way to combine arrays.

```javascript
const first = [1, 2, 3, 4];
const second = [6, 7, 8];

const combined = [.0, ..first, 5, ...second, 9, 10];
```

### forEach

we can use forEach function which accepts a callback function for iterating arrays.
This function optionaly accepts an index argument.

```javascript
combined.forEach((element, index) =>  console.log(index, element));
```

### join

we can join elements of an array using this method, which accepts an optional seperator argument and returns an string.

### sorting arrays

We can use builtin `sort()` method for sorting arrays. in case of reference types we can pass an optional callback function which is a comparator.

```javascript
const courses = [
    { id: 1, name: 'Nodejs},
    { id: 2, name: 'javascript},
];
courses.sort(function(a, b)) {
    if(a.name.toLowerCase() < b.name.toLowerCase()) return -1;
    if(a.name.toLowerCase() > b.name.toLowerCase()) return 1;
    return 0;
}
```

### every & some

These functions allow us to pass a callback function and check a criteria on the elemetns of the array.
using every we check each element until we find something that doesn't match the criteria and the operation gets stopped.
using some we elements of the array to find atLeast one element that matches the criteria.

### filter

we can use filter method to filter elements of the array base on a criteria and return it.

### map

using this method we can map each element of the array into something else. like mapping a number to an object.

> map and filter methods don't affect the original array and can be chained together.

### reduce

using this method we can reduce all values inside array into a single value. and this single value can be anything.
this method uses an accumulator and current value as arguments of callback function.

```javascript
const numbers = [1, -1, 2, 3];

numbers.reduce((accumulator, currentValue) => accumulator + currentValue);
```

above code is similar to declare an accumulator varible named sum and loop through array and sum each element.
we can also pass accumulator to to the reduce method after callback however if we don't it will use the first element of the array as value of accumulator.

## Functions in Js

In javascript We can create functions using function declaration syntax & function expression.
We already know functions are Objects. We can assign them to variables like object behaviors.

```javascript
// function declaration
function walk() {
    console.log('walk');
}

// anonymous function expression
let run = function() {
    console.log('run');
};

// named function expression
let run = function go() {
    console.log('run');
};
```

### Hoisting

using the function declaration syntax javascript automatically moves function declaration to top so, we can call functions before declaration however this is not possible with function expression since they behave like variables and they are not defined yet.

### Arguments

In javascript even when we have defined two parameters on the method declaration we can still pass any numbers of arguments and the syntax is valid but the result won't be correct.

every function has an arguments property which is an iterable object that represent arguments passed to the function and we can perform operations using them without even using a parameter in method declaration.

```javascript
function sum() {
    let total = 0;
    for(let value of arguments)
        total += value;
    return total;
}
console.log(sum(1, 2, 3, 4, 5, 6));
```

### Rest Operator

Instead of using arguments object we can easily use a varargs like operator which passes all arguments as an array to the function.

```javascript
function sum(discount, ...prices) {
    const total = prices.reduce((a, b) => a + b);
    return total * (1 - discount);
}
console.log(sum(0.1, 20 , 30));
```

> Whenver using ... inside function parantheses we are using Rest operator not spread operator. Also, Rest operator must be the last argument declared in function.

### Default parameter values

In order to give default value for parameters in functions in legacy ways we used truthy OR `||` operator, however since ES6 we can declare them, in the function definition.

> parameter that needs a default value is good to be the last in declaration or all the parameters afterwards have a default value in order to prevent passing undefined argument for parameter with default value to let the engine know that the argument is for the next parameter.

```javascript
// legacy
function interest(principal, rate, years) {
    rate = rate || 3.5
    return principal * rate / 100 * years
}

// ES6
function interest(principal, rate = 3.5, years = 5) {
    return principal * rate / 100 * years
}
console.log(interest(10000));

// prevent
function interest(principal, rate = 3.5, years) {
    return principal * rate / 100 * years
}
console.log(interest(10000, undefined, 5));
```

### Getters & Setters

In order to access properties of an object in a convenient way we can declare special functions using `get` & `set` keywords and access them like properties.

```javascript

// worst way
const person = {
    firstname: 'foo',
    lastName: 'bar',
}

console.log(`${person.firstName} ${person.lastName}`)

// bad way
const person = {
    firstname: 'foo',
    lastName: 'bar',
    fullname() {
        return `${person.firstName} ${person.lastName}`
    }
}
console.log(person.fullName())

// best way
const person = {
    firstname: 'foo',
    lastName: 'bar',
    get fullname() {
        return `${person.firstName} ${person.lastName}`
    },
    set fullName(value) {
        const parts = value.split(' ');
        this.firstName = parts[0];
        this.lastName = parts[2];
    }
}
person.fullName('Foo Bar');
```

### Error handling

We can use defensive programming in order to prevent ugly errors in browser consoles.
all we need to do is to check for the case error condition and throw new Error object with the understandable message. Then when we try to call or use the function wrap it with try catch block.

```javascript
const person = {
    firstname: 'foo',
    lastName: 'bar',
    get fullname() {
        return `${person.firstName} ${person.lastName}`
    },
    set fullName(value) {
        if(typeof value !== 'string')
            throw new Error('value is not a string.');
        const parts = value.split(' ');
        if(parts.length !== 2)
            throw new Error('Enter first & last name.')
        this.firstName = parts[0];
        this.lastName = parts[2];
    }
}
try{
    person.fullName('Foo Bar');
}
catch (e) {
    alert(e);
}
```

### var vs let

1. `var` is function scoped however let is block scopec.
2. global variables declared with `var` attach to browser window object and if a library uses that it will be replaces.
