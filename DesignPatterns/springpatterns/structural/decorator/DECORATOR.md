# Decorator pattern

When we want to add functionality to an object in runtime and apply composition over inheritance we come across this
pattern. This also makes a good use of open closed principle.

## Code

Legacy class

```java
public abstract class Pizza {
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract BigDecimal getCost();
}
```

Basic implementation

```java
public class Margaritta extends Pizza {
    public Margaritta() {
        super();
        this.description = "Margaritta";
    }

    @Override
    public BigDecimal getCost() {
        return BigDecimal.valueOf(15.0);
    }
}
```

Decorator

```java
public abstract class PizzaIngredient extends Pizza {
    @Override
    public abstract String getDescription();
}
```

New Variation

```java
public class Pepperoni extends PizzaIngredient {
    private final Pizza pizza;

    public Pepperoni(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public BigDecimal getCost() {
        return pizza.getCost().add(pizza.getCost());
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + "pepperoni";
    }
}
```

Test
```java
class DecoratorTest {
    @Test
    void decoratorTest() {
        Pizza pizza = new Margaritta();
        System.out.println(pizza.getDescription() + " & " + pizza.getCost());

        Pepperoni pepperoni = new Pepperoni(pizza);
        System.out.println(pepperoni.getDescription() + " & " + pepperoni.getCost());

        Pepperoni pepperoni1 = new Pepperoni(pepperoni);
        System.out.println(pepperoni1.getDescription() + " & " + pepperoni1.getCost());
    }
}
```