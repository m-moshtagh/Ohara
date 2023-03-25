# ResponseEntity

ResponseEntity is an extension of HttpEntity that represents an HTTP response including status, headers and body.
ResponseEntity allows you to modify the response with optional headers and status code.

ResponseEntity is used when you need to change HTTP headers or HTTP status code based upon your business logic or
incoming request. ResponseEntity wraps the original object as its body which is optional.

## Usage

We can initialize ResponseEntity with builder or constructor.

### Constructors

| ResponseEntity Constructors Types                                                 | ResponseEntity Constructors Purpose and Example                                                                                                                        |
|-----------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `ResponseEntity(HttpStatus status)`                                               | Creates a new ResponseEntity with the given status code only, without any headers and body `new ResponseEntity(HttpStatus.OK);`                                        |
| `ResponseEntity(MultiValueMap<String,String> headers, HttpStatus status)`         | Creates a new HttpEntity with the given headers and status code and without any body content.Example: `new ResponseEntity(responseHeaders, HttpStatus.NO_CONTENT);`    |
| `ResponseEntity(T body, HttpStatus status)`                                       | Creates a new ResponseEntity with the given body and status code, and without any headers.Example: `new ResponseEntity(“Constructor example”, HttpStatus.NO_CONTENT);` |
| `ResponseEntity(T body, MultiValueMap<String,String> headers, HttpStatus status)` | Creates a new HttpEntity with the given headers, body and status codeExample: `new ResponseEntity(“Constructor example”, responseHeaders, HttpStatus.NO_CONTENT);`     |

## Examples

```java
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    // return with custom header
    @GetMapping("/example/headers")
    public ResponseEntity exampleHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("technical-Example-Header", "https://technicalsand.com");
        return ResponseEntity.ok("success").headers(responseHeaders).build();
    }
    @GetMapping("/customHeader")
    ResponseEntity<String> customHeader() {
        return ResponseEntity.ok()
                .header("Custom-Header", "foo")
                .body("Custom header set");
    }
    // return empty body
    @GetMapping("/example/empty")
    public ResponseEntity empty() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/example/optional/empty")
    public ResponseEntity<List<String>> emptyOptional(@RequestParam(required = false) boolean fillData) {
        if (fillData) {
            List<String> data = Collections.singletonList("Sample Data");
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.noContent().build();
    }
    
    // return string
    @GetMapping("/example/string")
    public ResponseEntity<String> exampleString() {
        return ResponseEntity.ok("String example here");
    }
    @GetMapping("/age")
    ResponseEntity<String> age(@RequestParam("yearOfBirth") int yearOfBirth) {
        if (isInFuture(yearOfBirth)) {
            return ResponseEntity.badRequest()
                    .body("Year of birth cannot be in the future");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body("Your age is " + calculateAge(yearOfBirth));
    }
    // return json
    @GetMapping("/example/json")
    public ResponseEntity<Student> exampleJson() {
        Student student = Student.builder().rollNo(10).name("Student1").className("first").build();
        return ResponseEntity.ok(student);
    }
    
    // return list of objects
    @GetMapping("/example/list")
    public ResponseEntity<List<Student>> exampleList() {
        Student student1 = Student.builder().rollNo(10).name("Student1").className("first").build();
        Student student2 = Student.builder().rollNo(11).name("Student2").className("first").build();
        List<Student> students = List.of(student1, student2);
        return ResponseEntity.ok(students);
    }
    
    // return status
    @GetMapping("/example/status")
    public ResponseEntity exampleOnlyStatus() {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
    
    // return stream
    @GetMapping("/example/stream")
    public ResponseEntity<StreamingResponseBody> exampleStream() {
        StreamingResponseBody streamingResponseBody = outputStream -> {
            for (int i = 1; i <= 100000; i++) {
                outputStream.write(("Data stream line - " + i + "\n").getBytes());
            }
        };
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(streamingResponseBody);
    }
    
    // return csv file
    @GetMapping("/example/csv")
    public ResponseEntity<StreamingResponseBody> exampleCsv() {
        StreamingResponseBody stream = output -> {
            Writer writer = new BufferedWriter(new OutputStreamWriter(output));
            writer.write("name,rollNo,class" + "\n");
            for (int i = 1; i <= 10000; i++) {
                Student st = Student.builder().rollNo(i).name("Student" + i).className("second").build();
                writer.write(st.getName() + "," + st.getRollNo() + "," + st.getClassName() + "\n");
                writer.flush();
            }
        };
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=students.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(stream);
    }
}
```
