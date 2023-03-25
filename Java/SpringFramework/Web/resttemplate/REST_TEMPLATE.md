# Spring RestTemplate

RestTemplate is a synchronous client to perform HTTP requests. It is a higher-order API since it performs HTTP requests
by using an HTTP client library like the JDK HttpURLConnection, Apache HttpClient, and others.

## Structure of RestTemplate

The names of most of the methods are based on a naming convention:

* the first part in the name indicates the HTTP method being invoked
* the second part in the name indicates returned element.

* `getForEntity()`: executes a GET request and returns an object of ResponseEntity class that contains both the status
  code
  and the resource as an object.
* `getForObject()`: similar to getForEntity(), but returns the resource directly.
* `exchange()`: executes a specified HTTP method, such as GET, POST, PUT, etc. and returns a ResponseEntity containing
  both the HTTP status code and the resource as an object.
* `execute()`: similar to the exchange() method, but takes additional parameters: RequestCallback and
  ResultSetExtractor.
* `headForHeaders()`: executes a HEAD request and returns all HTTP headers for the specified URL.
* `optionsForAllow()`: executes an OPTIONS request and uses the Allow header to return the HTTP methods that are allowed
  under the specified URL.
* `delete()`: deletes the resources at the given URL using the HTTP DELETE method.
* `put()`: updates a resource for a given URL using the HTTP PUT method.
* `postForObject()`: creates a new resource using HTTP POST method and returns an entity.
* `postForLocation()`: creates a new resource using the HTTP POST method and returns the location of the newly created
  resource.

```java 
public class EmployeeRestClient {
    private static final String RESOURCE_PATH = "/rest/employees";
    private Logger LOG = LoggerFactory.getLogger(EmployeeRestClient.class);
    private String REQUEST_URI;
    private RestTemplate restTemplate;

    public EmployeeRestClient(RestTemplate restTemplate, String host, int port) {
        this.restTemplate = restTemplate;
        this.REQUEST_URI = host + ":" + port + RESOURCE_PATH;
    }

    public ResponseEntity<Employee> getForEntity(long id) {
        ResponseEntity<Employee> entity = restTemplate.getForEntity(REQUEST_URI + "/{id}",
                Employee.class,
                Long.toString(id));
        LOG.info("Status code value: " + entity.getStatusCodeValue());
        LOG.info("HTTP Header 'ContentType': " + entity.getHeaders().getContentType());
        return entity;
    }

    public List<Employee> getAll(int page, int pageSize) {
        String requestUri = REQUEST_URI + "?page={page}&pageSize={pageSize}";
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("page", Integer.toString(page));
        urlParameters.put("pageSize", Long.toString(pageSize));
        ResponseEntity<Employee[]> entity = restTemplate.getForEntity(requestUri,
                Employee[].class,
                urlParameters);
        return entity.getBody() != null ? Arrays.asList(entity.getBody()) :
                Collections.emptyList();
    }

    public Optional<Employee> getForObject(long id) {
        Employee employee = restTemplate.getForObject(REQUEST_URI + "/{id}",
                Employee.class,
                Long.toString(id));
        return Optional.ofNullable(employee);
    }

    public Employee postForObject(Employee newEmployee) {
        return restTemplate.postForObject(REQUEST_URI, newEmployee, Employee.class);
    }

    public URI postForLocation(Employee newEmployee) {
        return restTemplate.postForLocation(REQUEST_URI, newEmployee);
    }

    public ResponseEntity<Employee> postForEntity(Employee newEmployee) {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("User-Agent", "EmployeeRestClient demo class");
        headers.add("Accept-Language", "en-US");
        HttpEntity<Employee> entity = new HttpEntity<>(newEmployee, headers);
        return restTemplate.postForEntity(REQUEST_URI, entity, Employee.class);
    }

    public void put(Employee updatedEmployee) {
        restTemplate.put(REQUEST_URI + "/{id}",
                updatedEmployee,
                Long.toString(updatedEmployee.getId()));
    }
}
```

We can see additional info on version
3 [here](https://spring.io/blog/2009/03/27/rest-in-spring-3-resttemplatehttps://spring.io/blog/2009/03/27/rest-in-spring-3-resttemplate)

> It's recommended to use WebClient interface instead of RestTemplate.
