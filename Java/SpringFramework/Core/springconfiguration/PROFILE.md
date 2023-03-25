# Multi profile configuration

## Concept

Usually we need profile for each development environment. One for dev, prod, test and what ever we want. We can
customize our beans to use whatever profiles we want.

## Multiple properties or yaml files

We can create multiple files with this convention. `application.properties` & `application-dev.properties` or yaml
files. In yml We can have multiple profiles in a single yaml file but, we need tp separate each config with `---`.

## Code

```yaml
spring:
  profiles:
    active: prod

springdoc:
  api-docs:
    path:
      /v1/api
  swagger-ui:
    path: /documentation.html
```

```yaml
spring:
  config:
    activate:
      on-profile: dev

server:
  port: 8081
```

```yaml
spring:
  config:
    activate:
      on-profile: prod

server:
  port: 8080
```

## Select profile

### Environment variable

We can set `SPRING_PROFILES_ACTIVE`.

### VM options

WE can select a profile by passing `-Dspring.profiles.active=foo`

### Programmatically

We can also configure springContext to load different profiles.

```java

@SpringBootApplication
public class ProfilesApplication {
    public static void main(String[] args) {
        SpringApplication application =
                new SpringApplication(ProfilesApplication.class);
        application.setAdditionalProfiles("baz");
        application.run(args);
    }
}
```

### Tests

We can use `@ActiveProfiles` to active profiles in our test classes.

## Check which profiles are active

```java
class ProfileScannerBean {
  private Environment environment;

  ProfileScannerBean(Environment environment) {
    this.environment = environment;
  }

  @PostConstruct
  void postConstruct(){
    String[] activeProfiles = environment.getActiveProfiles();
    logger.info("active profiles: {}", Arrays.toString(activeProfiles));
  }

}
```

## Load properties from yaml file inside Bean

```java
@Component
@ConfigurationProperties("server")
public class ServerProperties {

    private String email;
    private List<Cluster> cluster = new ArrayList<>();

    public static class Cluster {
        private String ip;
        private String path;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        @Override
        public String toString() {
            return "Cluster{" +
                    "ip='" + ip + '\'' +
                    ", path='" + path + '\'' +
                    '}';
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Cluster> getCluster() {
        return cluster;
    }

    public void setCluster(List<Cluster> cluster) {
        this.cluster = cluster;
    }

    @Override
    public String toString() {
        return "ServerProperties{" +
                "email='" + email + '\'' +
                ", cluster=" + cluster +
                '}';
    }
}
```

```yaml
logging:
  level:
    .: error
    org.springframework: ERROR
    com.okaying: ERROR

spring:
  profiles:
    active: "dev"
  main:
    banner-mode: "off"

server:
  email: default@mkyong.com

---

spring:
  profiles: dev
server:
  email: dev@mkyong.com
  cluster:
    - ip: 127.0.0.1
      path: /dev1
    - ip: 127.0.0.2
      path: /dev2
    - ip: 127.0.0.3
      path: /dev3

---

spring:
  profiles: prod
server:
  email: prod@mkyong.com
  cluster:
    - ip: 192.168.0.1
      path: /app1
    - ip: 192.168.0.2
      path: /app2
    - ip: 192.168.0.3
      path: /app3
```
