# Bucket4j

Rate limiting is a technology used in networks to control the rate of traffic. We also see applications of this
technology in the HTTP world. Services that provide an HTTP API often limit the number of requests that a client is
allowed to send over a certain period of time.

## Algorithms overview

This can be implemented with many algorithms:

* Token bucket
* Leaky bucket
* Fixed window counter
* Sliding window log
* Sliding window counter

> Bucket4j implements Token bucket algorithm.

There's a bucket That holds tokens which consumer takes upon trying to consume a service. If there's no token consumer
needs to wait till there's enough tokens to consume a service. `Refiller` refreshes tokens inside bucket.

Bucket4j has two different refillers:

* `Refill.intervally(5, Duration.ofMinutes(1));` -> Refiller charges tokens all at once after the interval.
* `Refill.greedy(5, Duration.ofMinutes(1));`     -> This one splits the time and puts each token in specific period. In
  this case, splits a minute into 5 periods and refills tokens (each 12 second).

> Each bucket has a maximum capacity.

## Terminology

### Bucket

The Bucket interface represents the token bucket with a maximum capacity. It provides methods such as tryConsume and
tryConsumeAndReturnRemaining for consuming tokens. These methods return the result of consumption as true if the request
conforms with the limits, and the token was consumed.

### Bandwidth

The Bandwidth class is the key building block of a bucket, as it defines the limits of the bucket. We use Bandwidth to
configure the capacity of the bucket and the rate of refill.

### Refill

The Refill class is used to define the fixed rate at which tokens are added to the bucket. We can configure the rate as
the number of tokens that would be added in a given time period. For example, 10 buckets per second or 200 tokens per 5
minutes, and so on.

> The tryConsumeAndReturnRemaining method in Bucket returns ConsumptionProbe. ConsumptionProbe contains, along with the
> result of consumption, the status of the bucket, such as the tokens remaining, or the time remaining until the
> requested
> tokens are available in the bucket again.

[More details](https://www.baeldung.com/spring-bucket4j)
