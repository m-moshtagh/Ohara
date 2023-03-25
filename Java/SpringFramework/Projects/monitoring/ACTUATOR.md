# Spring Actuator

Spring Actuator provides URLs for our application which we can monitor our application in runtime. This is customizable
and, can be integrated with Prometheus & Grafana dashboard.

## Prometheus

Prometheus is an open-source monitoring system that was originally built by SoundCloud. It consists of the following
core components:

* A data scraper that pulls metrics data over HTTP periodically at a configured interval.
* A time-series database to store all the metrics data.
* A simple user interface where you can visualize, query, and monitor all the metrics.

## Grafana

Grafana allows you to bring data from various data sources like Elasticsearch, Prometheus, Graphite, InfluxDB etc. and
visualize them with beautiful graphs.

## Integration with Prometheus using Micrometer

Spring Boot uses Micrometer, an application metrics facade to integrate actuator metrics with external monitoring
systems. To integrate actuator with Prometheus, you need to add the micrometer-registry-prometheus dependency:

```xml

<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```

All the application metrics data are made available at an actuator endpoint called /prometheus . The Prometheus server
can scrape this endpoint to get metrics data periodically.

### yaml config file

```yml
# my global config
global:
  scrape_interval: 15s # Set the scrape interval to every 15 seconds. Default is 1 minute
  evaluation_interval: 15s # Evaluate rules every 15 seconds. The default is every 1 minute
# Load rules once and periodically evaluate them according to the global 'evaluation_interval'.
rule_files:
# - "first_rules.yml"
# - "second_rules.yml"

# A scrape configuration containing exactly one endpoint to scrape:
scrape_configs:
  # The job name is added as a label `job=<job_name>` to any time-series scraped from this config.
  - job_name: 'prometheus'
    # metrics_path defaults to '/metrics'
    # scheme defaults to 'http'.
    static_configs:
      - targets: [ '127.0.0.1:9090' ]

  - job_name: 'spring-actuator'
    metrics_path: '/actuator/prometheus'
    scrape_interval: 5s
    static_configs:
      - targets: [ 'HOST_IP:8080' ]
```

The most important stuff to note in the above configuration file is the spring-actuator job inside scrape_configs
section.

The metrics_path is the path of the Actuator’s prometheus endpoint. The targets section contains the HOST and PORT of
your Spring Boot application.

Please make sure to replace the HOST_IP with the IP address of the machine where your Spring Boot application is
running. Note that, localhost won’t work here because we’ll be connecting to the HOST machine from the docker container.
You must specify the network IP address.

### Install Prometheus

We can install the docker image

```text
docker run -d --name=prometheus -p 9090:9090 -v
<PATH_TO_prometheus.yml_FILE>:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml
```

> Please make sure to replace the <PATH_TO_prometheus.yml_FILE> with the PATH where you have stored the Prometheus
> configuration file.

## Install Grafana using Docker

```text
docker run -d --name=grafana -p 3000:3000 grafana/grafana
```

1. Add the Prometheus data source in Grafana
2. Create a new Dashboard with a Graph
3. Add a Prometheus Query expression in Grafana’s query editor
4. Visualize metrics from Grafana’s dashboard

WE can find more [here](https://dimitri.codes/mastering-spring-boot-actuator/)
