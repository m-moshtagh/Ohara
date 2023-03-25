# Spring Data

***

## Starter

Spring data JPA has a starter pom to provide necessary things Persistent Layer needs to connect to Database. Spring Data
is a Wrapper on detailed libraries or frameworks which are used to connect to a database. The JPA repository is wrapper
around JDBC in order to connect to relational Databases. However, Spring Data is also a wrapper around Mongodb,
Cassandra, CouchDb ... to connect to NoSQL database.

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

## CRUD Operations using Spring Data JPA with Hibernate underlying

First we need to configure JPA Entity Then we create an interface which extends `JPARepository` or one of its parents.

```java

@Repository
public interface EmployeeRepository
        extends JpaRepository<EmployeeEntity, Long> {
}
```

> We also do not need `persistent.xml` file inside META-INF in Legacy Projects. We just provide the configuration of
> ORM or Database inside application.properties or yaml.

```properties
spring.datasource.url=jdbc:h2:file:~/test
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
# Enabling H2 Console
spring.h2.console.enabled=true
# Custom H2 Console URL
spring.h2.console.path=/h2-console
# create database schema from SQL files
spring.jpa.hibernate.ddl-auto=none
#Turn Statistics on and log SQL statements
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.generate_statistics=false
#logging.level.org.hibernate.type=trace
#logging.level.org.hibernate.stat=debug
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
```

## Add Custom Functionality with Spring Data Repository

### Composite repositories

The definition of a composite repository looks very similar to a standard repository. You start by extending one of
Spring Data JPA’s repository interfaces, e.g., CrudRepository.

```java
public interface AuthorRepository extends CrudRepository<Author, Long>, CustomAuthorRepository {
}
```

`CustomAuthorRepository` is a fragmentRepository which we provide custom functions in it. We also need to provide the
implementation to it.

```java
public interface CustomAuthorRepository {
    List<AuthorSummaryDTO>
    getAuthorsByFirstName(String firstName);
}
```

```java
public class CustomAuthorRepositoryImpl implements CustomAuthorRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<AuthorSummaryDTO> getAuthorsByFirstName(String firstName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuthorSummaryDTO> query = cb.createQuery(AuthorSummaryDTO.class);
        Root<Author> root = query.from(Author.class);

        query.select(cb.construct(AuthorSummaryDTO.class, root.get(Author_.firstName),
                        root.get(Author_.lastName)))
                .where(cb.equal(root.get(Author_.firstName), firstName));
        return entityManager.createQuery(query).

                getResultList();
    }
}
```

### Using Multiple Fragment Repositories

Since Spring 5, your repository can extend multiple fragment interfaces. That gives some extra flexibility for complex
domain models and persistence layers.

> If fragments interfaces have methods with same name, the first interface method which is mentioned in signature is
> going to be used.

### Native functionality

For example We want to add custom query by fetching employees by name

```java
public interface EmployeeRepositoryCustom {
    String getFirstNameById(Long id);
}
```

```java

@Repository
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public String getFirstNameById(Long id) {
// Session session = em.unwrap(Session.class);
// session.createQuery
        Query query = entityManager.createNativeQuery("SELECT em.firstName FROM employee as em" +
                "WHERE em.id ?", Employee.class);
        query.setParameter(1, id + "%");
        return query.getSingleResult();
    }
}
```

```java

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Serializable>, EmployeeRepositoryCustom {
}
```

> This method is not preferable to me. I like custom JPQL queries

## Customize queries

### Spring Data JPA `@Query` Annotation

Creating a JPQL query with Spring Data JPA’s @Query annotation is pretty straightforward. You need to annotate a
method on your repository interface with the @Query annotation and provide a String with the JPQL query statement.

```java
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("FROM Author WHERE firstName = ?1")
    List<Author> findByFirstName(String firstName);

    @Query("SELECT a FROM Author a WHERE firstName = ?1 AND lastName = ?2")
    List<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
```

> In order to sort we can directly use it inside the custom jpql query or pass Sort object to method.

```java
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("FROM Author WHERE firstName = ?1")
    List<Author> findByFirstName(String firstName, Sort sort);

    // usage in service
    Sort sort = new Sort(Direction.ASC, "firstName");
    List<Author> authors = authorRepository.findByFirstName("Thorben", sort);
}
```

> In order to use pagination in query result we can pass parameter of type Pageable.

```java
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("FROM Author WHERE firstName = ?1")
    List<Author> findByFirstName(String firstName, Pageable pageable);

    // usage in service
    Pageable pageable = PageRequest.of(0, 10);
    List<Author> authors = authorRepository.findByFirstName("Thorben", pageable);
}
```

### Define advanced like expressions

Another great feature that you get with the SpEL expressions is the definition of advanced like expressions. You can,
for example, append ‘%’ to the beginning and end of a parameter and change the provided bind parameter value to upper
case.

```java
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("FROM Author WHERE UPPER(firstName) LIKE %?#{[0].toUpperCase()}%")
    List<Author> findByFirstNameContainingIgnoreCase(String firstName);
}
```

> We can also avoid hardcoding entity name inside our queries for cases we change their names.

```java
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("FROM #{#entityName} WHERE firstName = ?1")
    List findByFirstName(String firstName);
    
    /* 
            Spring Data JPA replaces the #{#entityName} expression with the entityName of the domain type
            of the repository. So,
            in this example, Spring Data JPA replaces #{#entityName} with Author.
     */
}
```

### Native Query

We use `@Query` but we need to pass a boolean argument to specify the native query.

```java
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(value = "SELECT * FROM author WHERE first_name = :firstName", nativeQuery = true)
    List<Author> findAuthorsByFirstName(@Param("firstName") String firstName);
}
```

#### Binding parameters to queries

We can bind parameters in Ordinal position or Name of parameters. position starts with 1 and needs `?` however name
comes with `:` and we have to specify it in the signature with `@Param`

```java
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("FROM Author WHERE firstName = ?1")
    List<Author> findByFirstName(String firstName);

    @Query("SELECT a FROM Author a WHERE firstName = :firstName AND lastName = :lastName")
    List<Author> findByFirstNameAndLastName(@Param("lastName") String lastName, @Param("firstName") String firstName);
}
```

## Modifying queries

We can also specify INSERT, UPDATE & Delete, but we need to provide additional `@Modifying` to the signature.

```java
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("UPDATE Author SET firstName = :prefix || firstName")
    @Modifying
    void addPrefixToFirstName(@Param("prefix") String prefix);
}
```

## Managing the Persistence Context

If our modifying query changes entities contained in the persistence context, then this context becomes outdated. One
way to manage this situation is to clear the persistence context. By doing that, we make sure that the persistence
context will fetch the entities from the database next time. However, we don't have to explicitly call the `clear()`
method on the `EntityManager`. We can just use the clearAutomatically property from the `@Modifying` annotation.

`@Modifying(clearAutomatically = true)`

But, what if our persistence context contained un flushed changes? Therefore, clearing it would mean dropping unsaved
changes. Fortunately, there's another property of the annotation we can use – `flushAutomatically`

`@Modifying(flushAutomatically = true)`

## Calling stored procedures

```text
DELIMITER $$
CREATE PROCEDURE sp_findBetween(min decimal, max decimal)
BEGIN
SELECT * FROM product where price BETWEEN min and max;
END $$
DELIMITER ;
```

```java

@Repository("productRepository")
public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query(value = "{call sp_findBetween(:min, :max)}", nativeQuery = true)
    List<Product> findAllBetweenStoredProcedure(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
}
```

## Query result into DTO

First We create our DTO class

```java
public class UserNameDTO {
    private Long id;
    private String name;

    public UserNameDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
```

We can store it simply by a JPQL

```java
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User AS u")
    List<User> findAll();

    @Query("SELECT new ir.sematec.example.dto.UserNameDTO(u.id, u.name) FROM User u WHERE u.name =:name")
    List<UserNameDTO> retrieveUsernameAsDTO(@Param("name") String name);
}
```

## Composite Repositories


