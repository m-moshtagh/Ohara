# MapStruct

In order to map DTOs to Entity objects we need solutions, mapStruct is one of the solutions.

## Other Solutions

We have objectMapper, JMapper & other solutions. Mostly use MapStruct but, because of the usage of reflection some
people prefer JMapper.

## DTO

Martin Fowler introduced the concept of a Data Transfer Object(DTO) as an object that carries data between processes in
order to reduce the number of method calls.
For example, we have a user entity like this:

```java

@Entity(name = "user")
@Table(name = "user")
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
}
```

For DTO of this entity we divide the functionality, means for posting a user we want the password attribute however, for
fetching data of user we don't need the password attribute. We define two separate DTOs for this purpose.

```java

@Getter
@Setter
public class UserPostDTO {
    @JsonProperty("id")
    private int id;
    @Email
    @NotNull
    @JsonProperty("email")
    private String email;
    @NotNull
    @JsonProperty("password")
    private String password;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
}
```

```java

@Getter
@Setter
public class UserGetDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
}
```

> DTO layer is where we define our validation.

## Define MapStruct Mappers

A MapStruct mapper is an interface or an abstract class annotated with `@Mapper`. This mapper allows mapStruct to
generate code in build time.

```java

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    BookSlimDto bookToBookSlimDto(Book book);

    BookDto bookToBookDto(Book book);

    AuthorDto authorToAuthorDto(Author author);

    AuthorAllDto authorToAuthorAllDto(Author author);

    List<AuthorAllDto> authorsToAuthorAllDto(List<Author> authors);

    UserGetDto userToUserGetDto(User user);

    User userPostDtoToUser(UserPostDto userPostDto);
}
```

> By setting the componentModel attribute to spring, the MapStruct processor will produce a singleton Spring Bean mapper
> injectable wherever you need

> If we need other dependencies inside our mappers we can use abstract classes instead of interface so, we can inject
> other dependencies inside it.

### mapping fields with different field names

```java

@Mapper
public interface EmployeeMapper {

    @Mapping(target = "employeeId", source = "entity.id")
    @Mapping(target = "employeeName", source = "entity.name")
    EmployeeDTO employeeToEmployeeDTO(Employee entity);

    @Mapping(target = "id", source = "dto.employeeId")
    @Mapping(target = "name", source = "dto.employeeName")
    Employee employeeDTOtoEmployee(EmployeeDTO dto);
}
```

We can find more in this post of [baeldung](https://www.baeldung.com/mapstruct)
