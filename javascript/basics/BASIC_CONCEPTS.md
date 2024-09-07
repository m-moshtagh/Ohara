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
