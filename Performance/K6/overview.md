# K6 by Grafana

K6 is an open source load testing automation tool by Grafana.

## General KPIs

### Virtual Users

K6 sends reuests to target endpoints simulating more traffic of people using the application with the help of virtual users. each VU resembls a person who is trying to use the application.

### Latency

The latency resmbles waiting time that making a request, doing the job and getting response time takes.

> Total Time = Latency

### Throughput

Shows the number of requests handled over a period of time. We use this metric to calculate the acapacity of the application.

### Iteration

Repeating the steps and set of actions.

## Test

K6 provides [test.k6.io](http://test.k6.io) to let us test basic features.
