# File System Structure in Linux

In Unix We have FHS(File system Hierarchy Standard)

## Structure

There's tree-like structure which `/` called root(not `/root`) in the first node. All other directories are inside `/`.

each directory is meant to conserve things base on their functionality.

### bin

All binary commands we run are inside this directory.

### boot

all prerequisites to boot the system up belong in this directory.

### dev

All devices are here.

### etc

All system configurations are inside this directory.

### home

home directory of users. A directory with usernames will be created in this directory.

### lib

shared libraries, kernel libs.

### media

mounting point for removeable devices

### mnt

mount point for mounting a filesystem temporary

### opt

We put our important programs inside this directory. For example Our server is to serve a tomcat app server. The Tomcat application will be located here.

### root

The home directory for user root is `/root`

### sbin

Files and commands more important than /bin commands which are mostly used by root user.

### srv

Data for services provided by this system

### tmp

all temporary files are written here.

### usr

It is called also secondary hierarchy.

> `/bin` and some other directories are all shortlinked to `/usr/bin`

### var

All the things that change most often are inside here. Most relevant files are log files.
