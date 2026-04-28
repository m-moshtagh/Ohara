# Maven Repositories

***

We can easily define new repositories to work with by creating a settings.xml file under .m2 directory or define a repository tag, in our pom file.

## Maven settings

User settings are kept in `<userHome>/.m2/settings.xml`. We can override this by command line parameter `-s <path/filename`. It's common to override in CI builds.

Global settings kept in `mavenHome/conf/settings.xml`. can be overriden by `-gs <path/filename>`

## Elements

**localRepository**: Allows to override the location of the local maven repo.
**interactiveMode**: Allows to set interactive / batch mode. defaults to interactive.
**usePluginRegistry**: Maven 2.0, no longer used in Maven 3.0
**offline**: defaults to false, if True maven will not connect to remote repositories.
**pluginGroups**: List plugin goup ids, to allow abbreviated plugin goals
**servers**: allows to configure credentials
**mirrors**: allows to configure mirror repositories.
**proxies**: define network proxy
**profiles**: define build profiles
**activeProfiles**: define active build profiles
