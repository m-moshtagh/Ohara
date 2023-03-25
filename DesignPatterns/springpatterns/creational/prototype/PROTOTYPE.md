# Prototype Pattern

Times when Creating new objects with same attributes is expensive, we can deeply clone that object to have another
instance similar to the original one. In java this is usually done by cloneable interface. However, in spring we can
configure our beans. Prototype in spring not only means cloning in runtime but also creating new instances from a bean. 

```java
@Configuration
public class BugEngine {
    @Bean
    @Scope("prototype")
    public Bug bug() {
        return new Bug("Spider", "Tortilla");
    }
}
```