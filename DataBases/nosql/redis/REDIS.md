# Redis

Redis is an open source (BSD licensed), in-memory data structure store used as a database, cache, message broker, and
streaming engine. Redis provides data structures such as strings, hashes, lists, sets, sorted sets with range queries,
bitmaps, HyperLogLog, geospatial indexes, and streams. Redis has built-in replication, Lua scripting, LRU eviction,
transactions, and different levels of on-disk persistence, and provides high availability via Redis Sentinel and
automatic partitioning with Redis Cluster.

## Interactions

External programs use TCP and Redis Specific Protocol to talk to redis. This protocol is implemented in the Redis client
libraries for the different programming languages. However, we have Redis-CLI to send commands to Redis.
In order to access redis-cli we can use these methods.

### Telnet

```bash
telnet [ip] [port]
auth [user] [password]
```

### kubectl exec

```bash
kubectl exec -it [pod-name] -n [namespace] -c [container] -- bash
redis-cli -h [ip] -p [port] --user [username] -a [password] -n [database-number]
```

### kubectl cp to copy redis-cli out of container

```bash
kubectl cp [namepsace]/[pod-name]:[directory-path-inside-pod] [host-path] -c redis
./redis-cli -h [ip] -p [port] --user [username] -a [password] -n [database-number]
```

> For example
> kubectl cp redis-standalone-test/tsn-redis-bitnami-0:/opt/bitnami/redis/bin /tmp/redisTmp -c redis

#### Security point

> It's also recommended to connect to redis-cli in plain command with only -h -p options then use `AUTH` & `SELECT` commands to login to the correct user and database.

### Login to redis-server

```bash
AUTH [username] [password]
```

### SELECT database

Some users can only be accessed in default and their own specific database

```bash
SELECT [database-number]
```

## Common Configurations & MISC

### Statistics of server

The `INFO` command shows all the redis-server configuration. Information like databases, roles and ...

```bash
INFO
```

### Listing connections

We can get all connections with

```bash
CLIENT LIST
```

and we can terminate each with

```bash
CLIENT KILL [IP]:[PORT]
```

### Monitor

We can trace network using `MONITOR` command.

We can also see slowest queries using:

```bash
SLOWLOG RESET
# wait for some time
SLOWLOG GET 25 # shows 25 slowest queries
```

[How to monitor redis performance metrics](https://www.datadoghq.com/blog/how-to-monitor-redis-performance-metrics/)

### Latency

In order to measure the latency of a PING command that server takes to respond:

```bash
redis-cli --latency
redis-cli --latency-history # monitor latency over a period of a time
```

We can also check `redis-cli --latency-history` which gives us all the latency
including OS, Kernel, Redis and ...
by comparing this and above results we can how much of time is spent by redis and other resources to find bottleneck.

### Changing Runtime configuration

We can get all active configuration variables using:

```bash
CONFIG GET [pattern]
```

We can change each with

```bash
CONFIG SET [variable] [value]
```

### ACL system commands

#### ACL USERS

Lists all users in redis ACL system.

```bash
ACL USERS
```

#### ACL SETUSER

Create an ACL user with specified rules or modify rules of existing user.

```bash
ACL SETUSER jin allkeys allchannels >secretCrush
```

> This one's tricky so we need [redis doc](https://redis.io/commands/acl-setuser/).

#### ACL CAT

This command shows the categories of all the redis commands. for example there is a category named dangarous which we can exlude when creating a user with `ACL SETUSER` command.

```bash
ACL CAT [category]
ACL SETUSER karin on +@all -@dangerous # with this karin can't call commands under dangerous category.
```

#### ACL LIST

shows all the ACL rules active on the current redis-server.

#### ACL DELUSER

we can delete users and terminate all the connections on it.

```bash
ACL DELUSER sadeghi
```

### Memory Commands

We can get memory subcommands with `MEMORY HELP`

We can get a key memory usage with

```bash
MEMORY USAGE [key]
```

### Generic key handling

We can get all keys using

```bash
KEYS [pattern]
DEL [key] # delete a key
EXISTS [key] # Check for a key
EXPIRE [key] [SECONDS] # Set a TTL for key
DUMP [key] # serialize a key
```

## Benchmarking

We can benchmark redis using `redis-benchmark` and `memtier`

### redis-benchmark

Switches are as below:

* -h Redis host
* -p Redis port
* -a If your server requires authentication, you can use this option to provide the password.
* -c Number of clients (parallel connections) to simulate. Default value is 50.
* -n How many requests to make. Default is 100000.
* -d Data size for SET and GET values, measured in bytes. Default is 3.
* -t Run only a subset of tests. For instance, you can use -t get,set to benchmark the performance of GET and SET commands.
* -P Use pipelining for performance improvements.
* -q Quiet mode, shows only the average requests per second information.

### Memtier

switches are as below:

* -s Server host. Default is localhost
* -p Server port. Default is 6379
* -a Authenticate requests using the provided password
* -n Number of requests per client (default is 10000)
* -c Number of clients (default is 50)
* -t Number of threads (default is 4)
* --pipeline Enable pipelining
* --ratio Ratio between SET and GET commands, default is 1:10
* --hide-histogram Hides detailed output information.
