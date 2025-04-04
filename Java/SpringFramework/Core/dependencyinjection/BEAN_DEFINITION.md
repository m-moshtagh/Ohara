# Bean Definition

In order to use the power of Spring we have several ways to register beans in spring application context. We can achieve
this by using these methods:

* XML configuration
* Annotation Configuration
* Programmatically

> We can mix these solutions whenever needed.

## XML configuration

we need to create xml configuration inside resources classpath or wherever and load it by application context.
we can use `ClassPathXmlApplicationContext`, `GenericXmlApplicationContext`.

All it takes is to define a bean tag inside the `config.xml` file. Give the path of the class inside the class attribute
we can use id and name for the bean to use for identification in order to get it with context.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="beanOne" name="one1; one2; one3" class="org.dogigiri.di.xml.BeanOne"/>
    <bean id="beanTwo" class="org.dogigiri.di.xml.BeanTwo">
        <property name="name" value="beanTwoName"/>
    </bean>

</beans>
```

```java
package org.dogigiri.di.xml;

public class BeanOne {
    public BeanOne() {
        System.out.println("bean one created");
    }
}

```

```java
package org.dogigiri.di.xml;

public class BeanTwo {
    private String name;

    public BeanTwo() {
        System.out.println("bean two created");
    }

    public BeanTwo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

```

```java
package org.dogigiri.di.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context1.xml");
        Object beanOne = context.getBean("beanOne");
        Object beanTwo = context.getBean("beanTwo");
        System.out.println(beanOne);
        System.out.println(beanTwo);
        Object beanOne2 = context.getBean("one1");
        System.out.println(beanOne2);
    }
}

```

## XML & DI using c namespace

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:c="http://www.springframework.org/schema/c"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="beanOne" name="one1; one2; one3" class="org.dogigiri.di.xml.BeanOne"/>
    <bean id="beanTwo" class="org.dogigiri.di.xml.BeanTwo">
        <property name="name" value="beanTwoName"/>
    </bean>

    <bean class="org.dogigiri.di.xml.OracleRepository" name="oracleRepository" lazy-init="true"/>
    <bean class="org.dogigiri.di.xml.MySqlRepository" name="mySqlRepository" lazy-init="true"/>

    <bean class="org.dogigiri.di.xml.VoyageService" name="voyageService1" c:_0-ref="oracleRepository" lazy-init="true"/>
    <bean class="org.dogigiri.di.xml.VoyageService" name="voyageService2" p:repository-ref="mySqlRepository"
          lazy-init="true"/>

</beans>
```

```java
package org.dogigiri.di.xml;

public interface Repository {
    void create();
}

public class OracleRepository implements Repository {
    @Override
    public void create() {
        System.out.println("Oracle Repository");
    }
}

public class MySqlRepository implements Repository {
    @Override
    public void create() {
        System.out.println("MySql Repository");
    }
}

public class VoyageService {
    private Repository repository;

    public VoyageService() {
    }

    public VoyageService(Repository repository) {
        this.repository = repository;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
```

```java
package org.dogigiri.di.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        var generalContext = new GenericXmlApplicationContext();
        generalContext.load("file:C:\\SpaceShip\\Projects\\springdemo\\src\\main\\resources\\context1.xml");
        generalContext.refresh();
        var voyageService = generalContext.getBean("voyageService1", VoyageService.class);
        Repository repository = voyageService.getRepository();
        repository.create();
    }
}
```

## XML use a factory method for DI

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:c="http://www.springframework.org/schema/c"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">


    <bean id="beanOneFact" class="org.dogigiri.di.xml.BeanFactory" factory-method="beanOne"/>

</beans>
```

```java
package org.dogigiri.di.xml;

public class BeanOne {
    public BeanOne() {
        System.out.println("bean one created");
    }
}

public class BeanFactory {
    private static final BeanOne beanOne = new BeanOne();

    private BeanFactory() {

    }

    public static BeanOne beanOne() {
        return beanOne;
    }
}
```

```java
package org.dogigiri.di.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext generalContext = new GenericXmlApplicationContext();
        generalContext.load("file:C:\\SpaceShip\\Projects\\springdemo\\src\\main\\resources\\context1.xml");
        generalContext.refresh();
        var beanOne = generalContext.getBean("beanOneFact", BeanOne.class);
        System.out.println(beanOne);
    }
}
```

## enable Autowire and Processor annotation in XML

We just enable `annotation-config` and autowire repository inside our service

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

    <bean class="org.dogigiri.di.annotation.OracleRepository" name="oracleRepository" lazy-init="true"/>
    <bean class="org.dogigiri.di.annotation.MySqlRepository" name="mySqlRepository" lazy-init="true"/>

    <bean class="org.dogigiri.di.annotation.VoyageService" name="voyageService" lazy-init="true"/>
</beans>
```

```java
package org.dogigiri.di.annotation;

public interface Repository {
    void create();
}

public class MySqlRepository implements Repository {
    @Override
    public void create() {
        System.out.println("MySql Repository");
    }
}

public class OracleRepository implements Repository {
    @Override
    public void create() {
        System.out.println("Oracle Repository");
    }
}

public class VoyageService {
    @Autowired
    @Qualifier("oracleRepository")
    private Repository repository;

    public VoyageService() {
    }


    public VoyageService(Repository repository) {
        this.repository = repository;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
```

```java
package org.dogigiri.di.annotation;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new GenericXmlApplicationContext();
        context.load("file:C:\\SpaceShip\\Projects\\springdemo\\src\\main\\resources\\context2.xml");
        context.refresh();
        var bean = context.getBean("voyageService", VoyageService.class);

        bean.getRepository().create();
    }
}

```

> We can also remove `@Qualifier` and in XML context add attribute primary=true to one of repository beans

## Add Component Scan in XML and also create beans using java code

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="org.dogigiri.di.annotation"/>


</beans>
```

```java
package org.dogigiri.di.annotation;

public interface Repository {
    void create();
}

@Component("mySqlRepository")
@Primary
public class MySqlRepository implements Repository {
    @Override
    public void create() {
        System.out.println("MySql Repository");
    }
}

@Component("oracleRepository")
public class OracleRepository implements Repository {
    @Override
    public void create() {
        System.out.println("Oracle Repository");
    }
}

@Component("voyageService")
public class VoyageService {
    @Autowired
    @Qualifier("oracleRepository")
    private Repository repository;

    public VoyageService() {
    }


    public VoyageService(Repository repository) {
        this.repository = repository;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
```

```java
package org.dogigiri.di.annotation;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new GenericXmlApplicationContext();
        context.load("file:C:\\SpaceShip\\Projects\\springdemo\\src\\main\\resources\\context2.xml");
        context.refresh();
        var bean = context.getBean("voyageService", VoyageService.class);

        bean.getRepository().create();
    }
}

```

## Java Class configuration no XML plain beans

```java
package org.dogigiri.di.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean({"mySqlRepository", "mySQLRepo"})
    public Repository getMySQLRepository() {
        return new MySqlRepository();
    }

    @Bean({"oracleRepository", "oracleRepo"})
    public Repository getOracleRepository() {
        return new OracleRepository();
    }

    @Bean("voyageService")
    public VoyageService getVoyageService() {
        return new VoyageService();
    }
}

```

```java
package org.dogigiri.di.annotation;

public interface Repository {
    void create();
}

public class MySqlRepository implements Repository {
    @Override
    public void create() {
        System.out.println("MySql Repository");
    }
}

public class OracleRepository implements Repository {
    @Override
    public void create() {
        System.out.println("Oracle Repository");
    }
}

public class VoyageService {
    @Autowired
    @Qualifier("mySQLRepo")
    private Repository repository;

    public VoyageService() {
    }


    public VoyageService(Repository repository) {
        this.repository = repository;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
```

```java
package org.dogigiri.di.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Config.class);
        var service = context.getBean("voyageService", VoyageService.class);
        service.getRepository().create();
    }
}

```

## Java Class Configuration with Component Scan

```java
package org.dogigiri.di.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.dogigiri.di.annotation")
public class Config {

}

```

```java
package org.dogigiri.di.annotation;

public interface Repository {
    void create();
}

@Component("mySqlRepository")
public class MySqlRepository implements Repository {
    @Override
    public void create() {
        System.out.println("MySql Repository");
    }
}

@Component("oracleRepository")
public class OracleRepository implements Repository {
    @Override
    public void create() {
        System.out.println("Oracle Repository");
    }
}

@Component("voyageService")
public class VoyageService {
    @Autowired
    @Qualifier("mySqlRepository")
    private Repository repository;

    public VoyageService() {
    }


    public VoyageService(Repository repository) {
        this.repository = repository;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
```

```java
package org.dogigiri.di.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Config.class);
        var service = context.getBean("voyageService", VoyageService.class);
        service.getRepository().create();
    }
}

```

## Register beans Programmatically

We use application context to register beans directly

```java

@Configuration
public class Config {

}
```

```java
package org.dogigiri.di.annotation;

public interface Repository {
    void create();
}

public class MySqlRepository implements Repository {
    @Override
    public void create() {
        System.out.println("MySql Repository");
    }
}

public class OracleRepository implements Repository {
    @Override
    public void create() {
        System.out.println("Oracle Repository");
    }
}

public class VoyageService {
    @Autowired
//    @Qualifier("mySqlRepository")
    private Repository repository;

    public VoyageService() {
    }


    public VoyageService(Repository repository) {
        this.repository = repository;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
```

```java
package org.dogigiri.di.annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Config.class);

        context.registerBean("mySqlRepository", Repository.class, () -> new MySqlRepository(), new BeanDefinitionCustomizer() {
            @Override
            public void customize(BeanDefinition bd) {
                bd.setDescription("Mysql repository");
                bd.setPrimary(true);
            }
        });
        context.registerBean("oracleRepository", Repository.class, () -> new OracleRepository());
        context.registerBean("voyageService", VoyageService.class);

        var service = context.getBean("voyageService", VoyageService.class);
        service.getRepository().create();
    }
}

```

