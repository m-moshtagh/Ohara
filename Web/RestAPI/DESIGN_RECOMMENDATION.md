# API design Best practices

before implementing API we need to know what functionality we are going to provide and how, we are going to expose this
api. So, We need to consider the useCase, ease of use and user needs.

## Approaches to add an API

We can start with these approaches

* Bolt-on: for existing systems.
    * takes advantage of existing code.
    * problems of application and architecture leak through API.
* GreenField: For new systems. API first mindset.
    * take advantage of new tech.
    * requires massive investment before benefits appear.
* Facade: Replacing piece by piece, shape and modify the api for better results.
    * ideal for legacy systems
    * multiple mindsets in system
    * hard to replicate behavior for a full one on one conversion.

## API modeling

### Identify participants

We need to consider anyone who is involved in our API with boundary consideration. for example people, bots. We also,
need to know if they are internal or external to our systems and, if they are active(wait for response) or deactivate.

### Identify activities & breaking them in steps

We can simply break activities into verbs with isolated definition from each other.

### Associate each activity with HTTP method

We need to define relationships between resources with help of db schema and choose the appropriate method for each
activity.

We can test the API using TDD with a RestClient to see if the API makes sense. Then we can document it.

* List the endpoints -> describe what they do
* List the parameters -> describe what they mean
* List the response code -> describe when you get each
* Show the response payload -> define the fields

> This documentation should be quick not perfect. It should move on along with time

## Rest

Rest is just set of rules and constraints. It's not HTTP(protocol), Notation(json) or describers(xml).

* client-server architecture
* stateless architecture
* cacheable -> depends on idempotent methods(GET, PUT, DELETE)
* layered systems
* code on demand
* uniform interfaces -> each api uses different URL

## Design practices

* Authentication & Authorization -> append token in URL or add it in headers.
* API versioning -> Include in URL or acceptHeader
* Choosing media types & processing contents -> Json gets complicated by time, WE can use Collection+json, HAL, ION
* Hypermedia approaches -> like api.github.com we user can get navigation to other places by calling the api.
* content negotiation -> users can get the content by the format the server supports.
