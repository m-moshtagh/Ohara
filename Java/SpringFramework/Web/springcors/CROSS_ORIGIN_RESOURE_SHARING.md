# Cross Origin Resource Sharing (CORS)

CORS basically requires each request done from domain A to domain B to be explicitly permitted by domain's B server. In
other words, an AJAX request from domain A to domain B is to be blocked by default. Unless we take active measures on
domain B's side. To enable requests coming from domain A, we need to set the access control policy on domain B. We can
select which domains may make requests to domain B via AJAX and the allowed methods (POST, PUT, GET, etc.). Or we can
just provide a wildcard permission: "*" (which usually isn't recommended).

## Server Response Headers

**For simple requests**:
When the server responds to such a request, it'll contain a header called "Access-Control-Allow-Origin" that can contain
a list of allowed domains or "*" to denote that any domain can do a request to this server. We control the domains,
methods, and headers that can do a CORS request on the server side.

**Requests That Require a Preflight Request First**:
Basically, this type of request includes any request that doesn't fit the definition above. The browser will
automatically send an OPTIONS method to the server to make sure that the original request can be handled. We do this as
these kinds of requests usually alter data on the server, and thus we want to protect the data integrity. The response
for the options request lists the domains, HTTP methods, and HTTP headers for which we allow a CORS request.

## Hands On Code

We can apply `@CrossOrigin` on class level or method level. Or we can configure it as a WebMvcConfigure bean.

```java

@CrossOrigin(origins = "https://a.com")
@RestController
@RequestMapping("/test")
public class SampleController {
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Test info(@PathVariable Long id) { // ...
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void undo(@PathVariable Long id) { // ... 
    }
}
```

```java

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
        registery.allowedOrigins("https://a.com");
    }
}

```

## Spring security configuration

If we use Spring Security, we need to add one extra step. Otherwise, Spring Security will reject the request before
reaching the MVC framework. This is because Spring Security automatically protects every endpoint with a requirement of
an authentication token. This is irrelevant in the case of preflight requests as the purpose of an OPTIONS request, as
described above, is only to establish whether the original request can go through. To make Spring Security bypass
preflight requests, add this configuration

```java

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected
    protected void configure(HttpSecurity http) {
        http.cors();
    }
}
```