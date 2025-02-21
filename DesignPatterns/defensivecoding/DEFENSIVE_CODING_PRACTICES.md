# Defensive Coding

Defensive approach is a way of programming in order to improve the app in aspect of:

* General Quality
* Readability
* Be predictive against unexpected behaviour

## Validating method inputs

### Throwing Exceptions

Throw specific exceptions.

### numbers

be careful about divisions.

### Strings

Use Enums when appropriate because they are less error-prone. We should also be careful when comparing Strings.
for example

`inputString.equals("ad")`; => This can lead to null pointerException if inputString is null.
`"foo".equals(inputString);` => this returns false if inputString is null.

### Date

Use Java8 new Date API.
Don't store dates as Strings. Store them as Java8 date objects.
Avoid regex to validate String dates. use `parse()` method instead.

### Return type of methods

it can be boolean to indicate the process is done or failed. in this case throwing exception is not good.
we need to avoid returning magic numbers.
it can be void an the failure can be forced with throwing an exception.
returning Optional is good.
