# Jenkins

We can get the `jenkins/jenkins` image and run it by command:
> docker run -d -v jenkins_home:/var/jenkins_home -p 8080:8080 -p 50000:50000 --restart=on-failure jenkins/jenkins:
> lts-jdk11

Jenkins comes up with lots of useful plugins.

## Manage credentials

first we need two credentials:

* GitHub access token
* SSH-key

## Jenkins file

### Create Pipeline

We create Jenkins file on the root of project which follows groovy DSL syntax.
First we specify the pipeline tag and add `agent [value]` inside it.

> A Jenkins agent is a machine or a container that connects to a Jenkins master. After a successful connection, the
> agent executes the task that is directed by the master. The agent doesn't change the behavior of the master, as it is
> mainly used to offload the build projects from the master.

> In jenkins nodes are systems that builds can be sent to by central jenkins controller. This makes our builds
> distributed and scalable.

#### Stages & Steps

Stages and steps execute our CI process. This starts with `stages` block. We can add individual stages inside this
block. a stage contains commands and these are known as `steps` which is defined with a block. We can write our commands
inside this block.

```jenkinsfile
pipeline {
    agent any

    stages {
        stage('Build application using gradle or maven') {
            steps {
                sh 'echo This is building phase with maven'
                sh ''
            }
        }

        stage('Creating container from image') {
            steps {
                sh 'echo This is Containerizing phase with docker'
            }
        }

        stage('Publishing image') {
            steps {
                sh 'echo This is Publishing image step'
            }
        }
    }
}
```

After this we need to sync Jenkins with repository to fetch and start pipeline with this configuration.
