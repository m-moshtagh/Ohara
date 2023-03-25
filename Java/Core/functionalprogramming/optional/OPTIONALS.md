# Optional

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

