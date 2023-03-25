# RequestBody

@RequestBody annotation maps the HttpRequest body to a transfer or domain object, enabling automatic deserialization of
the inbound HttpRequest body onto a Java object.

## Code

```java
public class Controller {
    @PostMapping("/request")
    public ResponseEntity postController(
            @RequestBody LoginForm loginForm) {

        exampleService.fakeAuthenticate(loginForm);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
```