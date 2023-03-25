# SpringDoc OpenAPI with swagger ui

We can simply use `implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.0")` this dependency for
springdoc-ui since spring boot version 3.0

## Configuration

We can create Beans 

```java
@Configuration
public class OpenAPIConfiguration {
    @Bean
    @Profile("dev")
    public OpenAPI devOpenAPI() {
        var developmentServer = new Server();
        developmentServer.setDescription("Development Server for BankService");
        developmentServer.setUrl("http://localhost:8081");

        var openApi = new OpenAPI();
        openApi.info(new Info().description("Documentation of BankService microservice")
                .title("BankService API documentation")
                .version("0.0.1-SNAPSHOT")
                .contact(new Contact().email("m-moshtagh@pm.me").name("Dogigiri"))
        );
        openApi.setServers(List.of(developmentServer));
        return openApi;
    }

    @Bean
    @Profile("prod")
    public OpenAPI prodOpenAPI() {
        var prodServer = new Server();
        prodServer.setDescription("Production Server for BankService");
        prodServer.setUrl("http://localhost:8080");

        var openApi = new OpenAPI();
        openApi.info(new Info().description("Documentation of BankService microservice")
                .title("BankService API documentation")
                .version("0.0.1")
                .contact(new Contact().email("m-moshtagh@pm.me").name("Dogigiri"))
        );
        openApi.setServers(List.of(prodServer));
        return openApi;
    }
}
```

The default swagger ui url is `http://localhost:8080/swagger-ui.html` and we can reach openAPI json properties using
`/v3/api-docs`. However, we can customize it using

```yaml
springdoc:
  api-docs:
    path:
      /v1/api
  swagger-ui:
    path: /documentation.html
```

## Documenting RestControllers

```java
@RestController
@RequestMapping("/api/v1")
public class GreetingController {
    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Operation(summary = "greeting", description = "greet custom user", operationId = "greet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, successful operation"),
            @ApiResponse(responseCode = "404", description = "not found, failed operation")
    })
    @GetMapping("/greet")
    public ResponseEntity<String> greet(@RequestParam String name) {
        return ResponseEntity.ok().body(greetingService.greet(name));
    }

    @Operation(summary = "Get all Tutorials", description = "Returns a list of tutorials", operationId = "tutorials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok, successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Tutorial.class)))) ,
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")})
    @GetMapping(value = "/tutorials", produces = "application/json")
    public ResponseEntity<List<Tutorial>> tutorials() {
        List<Tutorial> tutorials = TestData.allTutorials();
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }
}
```

For schema

```java
public class Tutorial {
    private long id;
    private String name;
    @Schema(name = "author", description = "name of author", required = true, example = "John Doe")
    private String author;
}
```
