# Factory pattern

This pattern is used in BeanFactory & FactoryBean class inside spring framework. Spring mostly uses ApplicationContext
which wraps BeanFactory. Factory allows Construction of similar classes of different types using a factory method.
Method call creates object for us and serves it back.

Constructed objects are from classes that share an interface or parent class. 

## Simple Example

```java
public interface Pet {
    String getName();

    void setName(String name);

    void feed();
}
```

```java
public class Dog implements Pet {
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void feed() {
        System.out.println("fed");
    }
}
```

```java
public class Cat implements Pet {
    private String name;

    @Override
    public void feed() {
        System.out.println("fed");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
```

```java
@Component
public class PetFactory {
    public Pet create(String type) throws InvalidKeyException {
        return switch (type) {
            case "cat":
                yield new Cat();
            case "dog":
                yield new Dog();
            default:
                throw new InvalidKeyException();
        };
    }
}
```

```java
@RestController
@RequestMapping("/api/v1/pets")
public class PetController {
    @Autowired
    private PetFactory factory;

    @PostMapping
    @RequestMapping("/{type}/{name}")
    public Pet createPet(@PathVariable String type, @PathVariable String name)  {
        var pet = factory.create(type.toLowerCase());
        pet.setName(name);
        pet.feed();
        return pet;
    }
}
```