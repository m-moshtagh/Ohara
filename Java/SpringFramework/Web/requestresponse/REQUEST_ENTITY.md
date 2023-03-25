# RequestEntity

RequestEntity is an extension of HttpEntity that exposes the information of HTTP method and uri. RequestEntity is used
in the RestTemplate to prepare outgoing requests and in @Controller methods as request input.

RequestEntity helps in fetching the additional details for incoming requests like HTTP headers, HTTP method, request
url, body and request entity type. If you need these additional details then you use RequestEntity.

## Usage

```java

@Controller
public class Controller {
    @PostMapping("/example/request")
    public ResponseEntity<Student> requestEntityExample(RequestEntity<Student> requestEntity) {
        log.info("RequestEntity Headers {}", requestEntity.getHeaders());
        log.info("RequestEntity method {}", requestEntity.getMethod());
        log.info("RequestEntity URL {}", requestEntity.getUrl());
        log.info("RequestEntity Type {}", requestEntity.getType());
        log.info("RequestEntity body {}", requestEntity.getBody());
        return ResponseEntity.ok(requestEntity.getBody());
    }
}
```
