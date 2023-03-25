# Spring boot REST API error Handling

Spring's default error response has some serious problems so, we need to customize it and make it clearer for front
stack.

## Custom ApiError class

We can create a customized ApiError model to return to front end.

```java
class ApiError {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }


    ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
```

```java
abstract class ApiSubError {
}
```

```java

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
class ApiValidationError extends ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
```

now the response would look like this:

```json
{
  "api-error": {
    "status": "BAD_REQUEST",
    "timestamp": "18-07-2017 06:49:25",
    "message": "Validation errors",
    "subErrors": [
      {
        "object": "bird",
        "field": "mass",
        "rejectedValue": 999999,
        "message": "must be less or equal to 104000"
      }
    ]
  }
}
```

## Handle Exception using Annotations

We can create Cross cutting class annotated with `@ControllerAdvice` to apply handling globally on controller
operations. We apply methods marked with `@ExceptionHandler` to handle the exceptions.

First we have to handle default exceptions so, we create a class which extends `ResponseEntityExceptionHandler` and
implement methods. Then we can also provide our custom exception handlers.

```java

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    // custom
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
    }
}
```

## ProblemDetail

This feature is available since version 6.0. We don't need to create additional ApiError entity.
