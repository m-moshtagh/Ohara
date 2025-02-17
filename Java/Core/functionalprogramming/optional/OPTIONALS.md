# Optional

## Null

null is a type defined in java language specification, however since it doesn't have a name we can't define a variable
of type null or cast or use instance of operator on it.

when null appears in program NullPointerException will be thrown.

The traditional ways to avoid nulls:

* assertions
* if/else statements
* methods of Objects class
* try/catch blocks

best practices for parts of app that we don't have control on:

* document API
* check for nulls in upper layers
* fail fast
* use exceptions to indicate that invalid value has been received

best practices for indoor code:

* never pass null to a function
* never return null from a function
* return empty collections or optionals

> never overcomplicate things

## Annotations

### spring

spring provides annotations for static analysis for null values in scope of fields, return types, parameters, package
and API.

* @NonNull
* @Nullable
* @NonNullApi
* @NonNullFields

these produce warnings at compile time.

### Hibernate validator

This is the implementation of a bean validation JSR and is not bound to only persistence layer. These validations are
performed at runtime. we can

* @NotNull
* @Size
* @Min/@Max
* @PositiveOrZero

> @Column(nullable == false) provides a constraint on table.

We apply the annotations on our DTO or entity classes.
in controller level we need to use `@Valid` in parameter of getting that type.
In service layer we also need to annotate the class with `@Validated`.
In persistence level the entity will be validated when entity manager tries to flush.

> above using @NotNull on top of fields we can use it in a collection definition. `List<@NotNull String> strings.`

### Lombok

Lombok also has annotation that will generate condition checks for null values and throw exception.

* @NonNull

> This can be used with @Data annotation to add checks on fields annotated with @NonNull in constructors and setter
> getters.

## Null is a smell!

In case of null we the worst part that always comes to mind is runtime NullPointerException Which is a pain in the ass.
However, since Java 8 We can use Wrapper class optional to determine if the result inside this optional is available or
not.

Here are some of best practices:

#### Wrap object with Optional if there is probability of existence

> If we have function which is returning a value for us, we should never return null directly we should wrap it inside
> Optional class. In case of collection it's recommended that we return an empty collection.

#### Optional as input is awful

> If we want to implement a function which may have a default behavior if the argument is not present, We avoid *
*expecting Null** value. This is an awful idea. We also prevent sending an Optional as method argument which is a
> punishment for developer to use factory method of Optional class every time wants to send empty or full object.
> However, The best option is to create a function with no parameter and do the default behavior Then, overload it with
> the proper parameter we need.

####               

```java
import java.util.Optional;

public class Sample {
    public static void setName() {
        //use the default value Joe
    }

    public static Optional<String> getName() {
        //return null; //Bad idea
        if (Math.random() > 0.5) {
            return Optional.of("Joe");
        }

        return Optional.empty();
    }

  /*
    setName(Optional.empty());

    setName(Optional.of("Sara")); 
    punishment
  */

    //A good design has empathy

    //legacy
    public static void setName(String name) {
        if (name == null) { //smell
            //use default value of Joe
        }

        //use the given name
    }

    //tempting, but don't
    public static void setName(Optional<String> name) {
        if (name.isPresent()) {
            //use the given name
        } else {
            //use the default value Joe
        }
    }

  /*
    setName();
    setName("Sara");
  */

    public static void setName(String name) {
        //use the given name
    }

    public static void main(String[] args) {
        var result = getName();

        System.out.println(result.orElse("not found"));

        //please don't do this:
        System.out.println(result.get());
        //get does not reveal its intention

        //if at all you need to use get, then use orThrow instead
        //System.out.println(result.orThrow());


    }
}
```

