# Define multiple Data sources in a spring boot application

## Steps

1. Define Java Configuration files for each datasource
    1. Data source bean definition
    2. Entities
    3. Entity Manager Factory bean definition
    4. Transaction Management
    5. Spring Data JPA Repository custom settings
2. Configurations inside application.properties

```properties
#Store card-holder personal details
app.datasource.member.url=jdbc:mysql://localhost:3306/member?createDatabaseIfNotExist=true
app.datasource.member.username=root
app.datasource.member.password=P@ssw0rd#
app.datasource.member.driverClassName=com.mysql.cj.jdbc.Driver
#card number  (cardholder id, card-number)
app.datasource.cardholder.url=jdbc:mysql://localhost:3306/cardholder?createDatabaseIfNotExist=true
app.datasource.cardholder.username=root
app.datasource.cardholder.password=P@ssw0rd#
app.datasource.cardholder.driverClassName=com.mysql.cj.jdbc.Driver
#expiration date (card id, expiration month, expiration year)
app.datasource.card.url=jdbc:mysql://localhost:3306/card?createDatabaseIfNotExist=true
app.datasource.card.username=root
app.datasource.card.password=P@ssw0rd#
app.datasource.card.driverClassName=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true
spring.jpa.database=mysql
```

### Data Source Bean Definition

We need to instantiate `DataSourceProperties` bean with info inside properties file and get DataSourceBuilder from it.

```java
public class MemberDataSourceConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.member")
    public DataSourceProperties memberDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.member.configuration")
    public DataSource memberDataSource() {
        return memberDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }
}
```

> We do the same for other datasource.

### Entities

```java

@Entity
@Table(name = "member", schema = "member")
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String memberId;
}
```

### Entity Manager Factory Bean Definition

When using Spring Data JPA the `EntityManager` is not available directly. We use the EMF bean to obtain instances of EMs
which interact with the JPA entities. This is done by providing the EMF builder class with reference to the data source
and location of entities.

```java
public class Configuration {
    /*Primary Entity manager*/
    @Primary
    @Bean(name = "memberEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean memberEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(memberDataSource())
                .packages(Member.class)
                .build();
    }

    /*Secondary Entity Managers*/
    @Bean(name = "cardHolderEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean cardHolderEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(cardholderDataSource())
                .packages(CardHolder.class)
                .build();
    }

    @Bean(name = "cardEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean cardEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(cardDataSource())
                .packages(Card.class)
                .build();
    }
}
```

### Transaction Management

```java
public class Configuration {
    @Primary
    @Bean
    public PlatformTransactionManager memberTransactionManager(final @Qualifier("memberEntityManagerFactory")
                                                               LocalContainerEntityManagerFactoryBean
                                                                       memberEntityManagerFactory) {
        return new JpaTransactionManager(memberEntityManagerFactory.getObject());
    }
}
```

### JPA Repository Configuration

Since we are going to have multiple data sources we must provide the specific information for each data source
repository using Spring `@EnableJpaRepositories`annotation. In this annotation, we are going to set the reference to an
entity manager, the repositories location and the reference to the transaction manager.

```java

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "guru.springframework.multiprocessing.repository.member",
        entityManagerFactoryRef = "memberEntityManagerFactory",
        transactionManagerRef = "memberTransactionManager"
)
public class MemberDataSourceConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.member")
    public DataSourceProperties memberDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.member.configuration")
    public DataSource memberDataSource() {
        return memberDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "memberEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean memberEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(memberDataSource())
                .packages(Member.class)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager memberTransactionManager(
            final @Qualifier("memberEntityManagerFactory") LocalContainerEntityManagerFactoryBean memberEntityManagerFactory) {
        return new JpaTransactionManager(memberEntityManagerFactory.getObject());
    }
}
```

> **basePackages**: We use this field to set the base package of our repositories. For instance, for the member
> datasource, it must point to the `package guru.springframework.multiprocessing.repository.member`

> **entityManagerFactoryRef**:  We use this field to reference the entity manager factory bean defined in the data
> source configuration file. It is important to take note of the fact that the `entityManagerFactoryRef` value must
> match the bean name (if specified via the name field of the `@Bean` annotation else will default to method name) of
> the entity manager factory defined in the configuration file.

> **transactionManagerRef**: This field references the transaction manager defined in the data source configuration
> file. Again it is important to ensure that the `transactionManagerRef` value matches with the bean name of the
> transaction manager factory.