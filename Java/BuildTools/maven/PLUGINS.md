# Maven Plugins

***

## Common maven plugins

**Clean**:

* This is one of lifecycle plugins which has one goal `clean`.
* The purpose is remove files generated durring build process.
* removes /target for all artifacts.

Mostly when refactoring our project, we execute `mvn clean package` goals. however we can easily bind the clean plugin to initial phase of the project so when we execute a goal it automatically executes clean plugin.

```xml
<build>
    <plugins>
        <plugin>
            <artifactId>maven-clean-plugin</artifactId>
            <version>3.1.0</version>
            <executions>
                <execution>
                    <id>auto-clean</id>
                    <phase>initialize</phase>
                    <goals>
                        <goal>clean</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

**Compiler**:

* It's part of default lifecycle.
* It has two goals: `compiler:compile` & `compiler:testCompile`
* By default uses `javax.tools.JavaCompiler`. can be configured to use `javac`
* Default source and target languages are java 1.6

> Apache team encourages these values to be set.

**Resources**:

* Part of default lifecycle.
* Has three goals: `respirces:resources`, `resources:testResourrces` and `resources:copy-resources`
* Purpose is to copy project resources to output directory(target).
* Can be configured for encoding, source and target directories.

**Surefire**:

* Part of default lifecycle
* has one goal `surefire:test`
* is used to execute unit tests in project.
* By default supports Junit 3, 4, 5 and TestNG(Cucumber and Spocke compiles to Junit bytecode)
* By default includes classes named: `**/Test*.java; **/*Test.java; **/*Tests.java; **/*TestCase.java`

**Jar**:

* Part of Default lifecycle
* has two goals `jar:jar` and `jar:test-jar`
* purpose is to build jars from compiled artifacts and project resources
* Can be configured for custom manifests and to make executable jars.

**Deploy**:

* Part of default lifecycle
* two goals `deploy:deploy` & `deploy:deploy-file`
* Purpose is to deploy project artifacts to remote maven repositories.
* often done in CI.
* Configuration is typically part of the maven POM.

**Site**:

* Part of Site lifecycle
* has 7 goals
* Builds a website.
