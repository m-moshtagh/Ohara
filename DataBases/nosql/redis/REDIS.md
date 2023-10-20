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

## Data Structures

[Redis commands DOC](https://redis.io/commands/)

### Keys SET & GET

Keys are the primary way to access data values within redis. Keys are `Unique`, `Binary safe`, `up to 512mb` however tradeoff for length and readability should be considered.

Redis goal is to avoid complexity so for key spaces we have logical databases and there's a flat key space which all keys are stored there. and there is no automatic namespacing, so we need to use proper naming conventions.

A logical database is identified by a zero based index. The default is 0. Within a logical database, the key names are unique but same key name can appear in multiple logical databases. So when we need seperate key spaces we can use different logical databases.

> For microservices the key definitions are important to avoid key name clashes across applications.

WE use `SET key value [EX seconds] [PX milliseconds] [NX|XX]` to set a key name and `GET key` to retrieve it.

```bash
SET customer:100:age 54 # This is a naming convention
```

We can retrieve all keys with two commands: `KEYS` & `SCAN`

* KEYS blocks until completion and is not suitable for production usage.
* SCAN iterates using SCAN and returns a slot reference. may return 0 or more keys per call and is safe for production.

We can use `KEYS pattern` & `SCAN slot [MATCH pattern] [COUNT count]`

```bash
KEYS customer:1*

SCCAN 0 MATCH customer:1* COUNT 1000
# Scan may return a integer value which is a slot and we need to call another time with that slot number. It takes more service calls but is more efficinet.
```

The `DEL` command removes a key and associated memory and is a blocking operation. `UNLINK` is a non-blocking command which removes the name and the memory will be reclaimed by a async process.

We can check if key exists with `EXISTS` command.

> If we seperate `SET` & `EXISTS` commands we may face inconsistency. Hence, WE can set XX(If exists) and NX(not exists) options in SET command.

```bash
SET CUSTOMER:1000 foo XX # SETS key value if the key already exits
SET CUSTOMER:1000 bar NX # SETS key value if the key doesn't exits.
```

#### Expiration

Redis keeps the key until space is required or is forced out by eviction policy. However we have an extra attribute to apply expiration time or TTL to keys. The time can be milliseconds, seconds and unix timestamp.

> Expiration can be set, removed and changed by user(no seperate code thread to clean data)

```bash
EXPIRE key seconds
EXPIREAT key timestamp
PEXPIRE key milliseconds
PEXPIREAT key milliseconds-timestamp
TTL key # get expire time seconds.
PTTL key # get expire time of milliseonds
PERSIST key # remove ttl
```

> Threre are options like XX(if expiry is set) NG(if expiry is not set) GT(if expiry is greater than current value) LT(if expiry is less than current value)

### Strings

Strings are binary safe sequences of bytes. We cam store almost anything in them.
The most common use is for caching API responses, session storage, HTML pages. Can also be used as counters since we have `INCR` & `DECR` and `INCRBY` & `DECRBY` commands in redis. We also have `INCRBYFLOAT`
String can have maximum of 512mb value.
Internaly Redis stores encoding of the value, stores a knowledge whether its a text, number or binary.

> We use SET & GET command to store strings.

```bash
INCR key increments value by 1
INCRBY key value # can also be a negative number
```

### Type & Object

We can check the type of key with `TYPE key` command. We can also check encoding of the value with `OBJECT encoding key`

```bash
SET CUSTOMER:1000:age 22 # create a string for customer number 1000 age
TYPE CUSTOMER:1000:age # This returns string which shows we have string type
OBJECT encoding CUSTOMER:1000:age # This returns int
```

> WHen we use `INCR` command and such Redis automatically checks upward commands to check the encoding of the key.
> Redis also applies polymorphysm for key values. You can set different encided value for a key unlike RDBMS which has fixed type columns. for ex) We can set CUSTOMER:1000:age value of `twenty three`.

### Hashes
