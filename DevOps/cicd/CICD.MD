# Continuous Integration & Continuous Deployment

## Concept

In oder to develop large applications we used methodologies like Waterfall which had lots of draw backs so, we moved to
agile methodologies. But, in this iterative tasks we needed to test and prototype our code rapidly.

We have an iterative loop:

* plan
* code
* build
* test
* release
* deploy
* operate
* monitor

### Continuous Integration (CI)

So in order to automate dev jobs we want to trigger an event when developers merge their changes into shared branch
To test and build the application. This process is known as CI.

### Continuous Delivery VS continuous Deployment

Both of these automate release of artifact built by CI but, in Delivery The Production deployments are manually
triggered however, in continuous Deployment, The deployment into production environment is done automatically.

### Delivery pipelines

Series of steps which will take software from repository to deployment.

#### Monorepo VS Poly-repo

In monorepo we put each service as a directory inside single repository.
In Poly-repo, we put each service inside a single repo and couple them together. We need a GitHub organization for a
poly repo.
