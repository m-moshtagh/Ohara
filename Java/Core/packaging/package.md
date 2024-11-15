# Packaging

We use packages to categorize classes within our programs.

## Import

In order to use classes from different packages, we need to import packages.

> Importing using wildcards doesn't effect classes within subdirectories.

## Javac options

### -d

We can compile classes with `javac` into another directory like target using -d option.

### -cp | -classpath | --class-path

Location of classes needed to compile the program.

## Compile with JAR files

We can compile programs including jar files by specifying classPath and paths to jar files.

> we can seperate multiple paths using, ";" in windows and ":" in linux and macos.

## Create a JAR file

We can wrap all the files and classes in a Java archive(JAR) file for a better usability. Java can extract this file in memory and execute it.

We can create jar files using -cvf command and -C to specify the target directory.

### -c

Creates a new JAR file

### -v

Prints details when working with JAR files

### -f

JAR filename

### -C

Directory containing files to be used to create the JAR

### -m

In order for java to know which class is the main class inside the JAR file we have a manifest.mf file inside `JAR/META-INF/MANIFEST.MF`

```MANIFEST.MF
Manifest-version: 1.0
Created-By: Mohammad
Main-Class: HelloWorld

```

> There should be space after `:` and a newline after las line.

file describing the manifest.

## create a bat file script

```bash
set JAVA_HOME=C:\Program Files\Zulu\zulu-23
set PATH=JAVA_HOME;%PATH%
java -version
javac -d .\target\classes\ .\src\*.java
javadoc src\*.java -d doc
jar -cfm hello.jar .\manifest.txt -C .\target\classes\ .
java -jar hello.jar
pause
```
