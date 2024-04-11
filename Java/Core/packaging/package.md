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

We can create jar files using -cvf command and -C to specify the target directory.

### -c

Creates a new JAR file

### -v

Prints details when working with JAR files

### -f

JAR filename

### -C

Directory containing files to be used to create the JAR
