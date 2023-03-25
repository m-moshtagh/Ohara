# ResponseBody

The @ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON and
passed back into the HttpResponse object.

## Code

```java
public class Controller {
    @Controller
    @RequestMapping("/post")
    public class ExamplePostController {

        @Autowired
        ExampleService exampleService;

        @PostMapping("/response")
        @ResponseBody
        public ResponseTransfer postResponseController(
                @RequestBody LoginForm loginForm) {
            return new ResponseTransfer("Thanks For Posting!!!");
        }
    }
}
```

> Remember, we don't need to annotate the @RestController-annotated controllers with the @ResponseBody annotation since
> Spring does it by default.

## Content Type

When we use the @ResponseBody annotation, we're still able to explicitly set the content type that our method returns.

For that, we can use the @RequestMapping‘s produces attribute. Note that annotations like @PostMapping, @GetMapping,
etc. define aliases for that parameter.

```java
public class Controller {
    @PostMapping(value = "/content", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseTransfer postResponseJsonContent(
            @RequestBody LoginForm loginForm) {
        return new ResponseTransfer("JSON Content!");
    }

    @PostMapping(value = "/content", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseTransfer postResponseXmlContent(
            @RequestBody LoginForm loginForm) {
        return new ResponseTransfer("XML Content!");
    }
}
```

> return type differs depending on Accept header sent inside request.
