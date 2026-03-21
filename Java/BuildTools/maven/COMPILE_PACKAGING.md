# Compile and packaging using maven

Maven wraps manual compiling and packaging stuff of our projects so, we don't have to compile and package our code using JDK commandline tools.
***

## Java compile and packaging process

The runtime process:

1. source code(.java)
2. passed to javac
3. bytecode(.class)
4. jvm runs the bytecode

The packaging:

Usually we have many classes, we zip them in some formats and jvm unzips them and runs the code.

**.jar** zip file containing one or more java classes.
**.war** zip file containing web application includes one or more jar files,Java class files and web resources
**.ear** zip file containing one or more war files.
**.fat JAR** aka Uber JAR is an executable jar containing all dependencies.(Used by spring boot)
**Docker container** Docker image containing runtime env, JVM and java package.

## Compiling and packaging with maven

We have POM.xml file in our project structure which defines maven configurations.

For minimal configuration we just define project cordinates and some properties like:

* project.build.sourceEncoding
* project.reporting.outputEncoding
* java.version
* maven.compiler.source
* maven.compiler.target

> These configrations are inside `<properties>` tags

We can simply build our project using `mvn clean package` command.

The result is a jar file inside target directory.

> In order to provide source code we have to satisfy maven directory structrure.

## Dependency managemment

Maven also handles dependency management for our project so we can simply specify target dependency cordinates inside `<dependencies>` tag.

It simply fetches the dependency from maven repository and include it inside our project.
