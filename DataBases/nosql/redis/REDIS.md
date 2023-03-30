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

Hashes are collecitons of field value pairs like Java Hashmaps, python dictionaries. Redis hashesh are also, mutable.
Hashesh field values are String which means they are flat(There are no nested objects or arrays)

We can create and update hashesh using `HSET` and get values using `HGET`. We can also remove keys with `HDEL` command. We can also increment a field value by `HINCRBY` command.

> Most hash commands are in O(1) except (HGETALL). It is also not recommended to use JSON values because we have Redis JSON which is more optimal in case of storage and performance.

```bash
# HSET key field value [field value ...]
HSET customer:1000 name "Foo" age 20
# HGET key field
HGET customer:1000 name
# HGETALL key
HGETALL customer:1000
# HDEL key field [field ...]
HDEL customer:1000 age
# HEXISTS key field
HEXISTS customer:1000 age
# HINCRBY key field increment
HINCRBY customer:1000 age 1 # Creates field if not exists
# HINCRBYFLOAT key field increment
HINCRBYFLOAT mykey field 2.0e2
# HKEYS key
HKEYS customer:1000
# HLEN key
HLEN customer:1000
# HMGET key field [field ...]
HMGET customer:1000 name age nickname
# HMSET key field value [field value ...] # Deprecated!
HMSET myhash field1 "Hello" field2 "World"
# HRANDFIELD key [count [WITHVALUES]]
HRANDFIELD dice 2 # returns keys of a hash in this case array of 2 keys of the hash.
# HSCAN key cursor [MATCH pattern] [COUNT count]
HSCAN dice 0 MATCH f*
# HSETNX key field value
HSETNX customer:1000 age 32 # This has no effect since age exists.
# HSTRLEN key field
HSTRLEN customer:1000 name
# HVALS key
HVALS customer:1000
```

Usercases of using hashes:

* RateLimitting. Storing endpoints and limits we can easily manipulate values.
* Session Cache

### LISTS

Lists are sequence of Strings equlivant to Java ArrayList and JS arrays. Lists are great for storing strings however, they can be used to implement stacks and queues.
In queue we use `RPUSH` & `LPOP` or `LPUSH` & `RPOP` and for Stack we use `RPUSH` & `RPOP` or `LPUSH` & `LPOP` We can traverse a list using `LRANGE` command.
Internaly it implements Doubly Linked lists. We also have commands for inserting or getting elements at a specific index.

```bash
# LPUSH key element [element ...]
LPUSH myList a b c
# LPUSHX key element [element ...]
LPUSHX mylist "Hello"
# LPOP key [count]
LPOP mylist 2
# LPOS key element [RANK rank] [COUNT num-matches] [MAXLEN len]
LPOS mylist 3 COUNT 0 RANK 2
# LMPOP numkeys key [key ...] <LEFT | RIGHT> [COUNT count]
LMPOP 2 non1 non2 LEFT COUNT 10
# LRANGE key start stop
LRANGE mylist -100 100
# LREM key count element
LREM mylist -2 "hello"
# LSET key index element
LSET mylist -2 "five"
# LTRIM key start stop
LTRIM mylist 1 -1
# LINDEX key index
LINDEX mylist 0
# LINSERT key <BEFORE | AFTER> pivot element
LINSERT mylist BEFORE "World" "There"
# LLEN key
LLEN mylist
# LMOVE source destination <LEFT | RIGHT> <LEFT | RIGHT>
LMOVE mylist myotherlist RIGHT LEFT
# RPUSH key element [element ...]
RPUSH mylist "hello"
# RPUSHX key element [element ...]
RPUSHX myotherlist "World"
# RPOP key [count]
RPOP mylist 2
```

Usecases of Lists:

* Activity Stream: When we need to know the most recent acitvity like recent post
* Queue that supports a producer-consumer pattern

### SETS

SETs are unordered collections of strings that contains no duplicates. This makes sets perfect for tasks with deduplication and also afficient membership checks like Did this ip address connect in last hour, is this user online, is this URL blackListed and such in O(1). It also supports intersection, difference and union.

Usecases of Sets:

* Tag Cloud
* Unique visitors
* Game online players with multiple sets with timescoped keys and expiration time

```bash
# SADD key member [member ...]
SADD myset "Hello"
# SCARD key
SCARD myset
# SMEMBERS key
SMEMBERS myset
# SMISMEMBER key member [member ...]
SMISMEMBER myset "one" "notamember"
# SDIFF key [key ...]
SDIFF key1 key2
# SDIFFSTORE destination key [key ...]
SDIFFSTORE key key1 key2
# SINTER key [key ...]
SINTER key1 key2
# SINTERCARD numkeys key [key ...] [LIMIT limit]
SINTERCARD 2 key1 key2 LIMIT 1
# SINTERSTORE destination key [key ...]
SINTERSTORE key key1 key2
# SMOVE source destination member
SMOVE myset myotherset "two"
# SPOP key [count]
SPOP myset
# SREM key member [member ...]
SREM myset "four"
# SSCAN key cursor [MATCH pattern] [COUNT count]
SSCAN mySet 0 MATCH *
# SUNION key [key ...]
SUNION key1 key2
# SUNIONSTORE destination key [key ...]
SUNIONSTORE key key1 key2
```

### Sorted SETS

A Redis Sorted Set is an ordered collection of unique members. These Set members are ordered by their associated score. Whenever we add to a sorted set, We are specifying a member and a score. These are good for priority queueus low latency scoreboards and secendary indexing in general.
Like sets we can perform mathematical operations on sorted sets however, we need to store the result in another sortedset.(Difference is added after redis 6.02+)

Usecases of SortedSets:

* Leaderboards
* Priority Queue

> Score is a floating number

```bash
# ZADD key [NX | XX] [GT | LT] [CH] [INCR] score member [score member...]
ZADD myzset 2 "two" 3 "three"
# ZCARD key
ZCARD myzset
# ZCOUNT key min max
ZCOUNT myzset -inf +inf
# ZDIFF numkeys key [key ...] [WITHSCORES]
ZDIFF 2 zset1 zset2 WITHSCORES
# ZDIFFSTORE destination numkeys key [key ...]
ZDIFFSTORE out 2 zset1 zset2
# ZINCRBY key increment member
ZINCRBY myzset 2 "one"
# ZINTER numkeys key [key ...] [WEIGHTS weight [weight ...]] [AGGREGATE <SUM | MIN | MAX>] [WITHSCORES]
ZINTER 2 zset1 zset2
# ZLEXCOUNT key min max
ZLEXCOUNT myzset - +
# ZRANGE key start stop [BYSCORE | BYLEX] [REV] [LIMIT offset count] [WITHSCORES]
ZRANGE myzset 0 1 WITHSCORES
# ZRANGEBYLEX key min max [LIMIT offset count]
ZRANGEBYLEX myzset - (c
# ZRANK key member [WITHSCORE]
ZRANK myzset "three" WITHSCORE
# ZREM key member [member ...]
ZREM myzset "two"
# ZREVRANGE key start stop [WITHSCORES]
ZREVRANGE myzset 0 -1
# ZSCAN key cursor [MATCH pattern] [COUNT count]
ZSCAN myzSet 0 MATCH *
# ZSCORE key member
ZSCORE myzset "one"
# ZUNION numkeys key [key ...] [WEIGHTS weight [weight ...]] [AGGREGATE <SUM | MIN | MAX>] [WITHSCORES]
ZUNION 2 zset1 zset2
```

> Just search the commands for sorted sets operations :) too complicated.

### Cardinality

The cardinality of collections can be checked by `LLEN` for lists, `SCARD` for Sets and `ZCARD` for SortedSerts.

### Capped Collections

In case we want retain specific subset of members like leaderboard.
We can achieve this by:

* `LTRIM`: allows to specify range of elements we want to retain. It can be specified from left with + index and right with - index. So a typical pattern is to push into a list and trim it.
* `ZREMRANGEBYRANK`: With sorted sets this command removes the range specified. So typical usage is to add using ZADD then use ZREMRAGEBYRANK.

> Sorted set capp is useful in case of leaderboards and lists are useful in cases of Activiy Stream.

### Indexing using SETS

We are going to explore indexing redis with three techniques to improve performance:

* Object inspection
* Faceted search
* Hashed Faceting method

#### Object inspection

First we assume we only have Strings and the values are json. One way to find the specific String we want we can retrieve all keys by SCAN then see each value.

> Time complexity of above approach is O(n)

#### Faceted Search

We can split categories into SETs and add the keys to these sets. Then when we are looking for specific rule between these propeties we can simply INTERSECT the sets.
This method is way more optimal than Object inspection because we only do a GET operation and INTERSECT. However downfall is when The distribution of keys between categories increase. The more categories the worse performance.
It's also best if we have the cardinality of sets and start with set with the least cardinality.

> Time complexity of This method is O(n x m): n is cardinality & m is number of sets

#### Hashed index

When we have multiple criteria in case of databases we create compound index, Simple solution is to have a consistent hash with SHA, MD5 or RIPEMD160 algorithms.
We create SETS and set the keys from computing hashesh of the combination values(categories) Then we add the values to the specific categories.
The read is so optimized in this case however in case of change we need to delete from one category and add it to another.

> This is much optimal if we don't have changes in our data. The O(n)

For big O notaion we always need to consider:

* Data cardinality
* Data Distribution
* Time Complexity is not execution time

## Transactions

Redis processes commands in the order the server recieves them. Each data manipulation command is atomic. In order to group some commands so, if the operation is failed both commands fail we wrap them in a transaction.
All the commands that are encapsulated in transactions are serialized and executed sequentially. This gurantees that all the commands executed in a single isolated operation. Redis also gurantees the atomcity of the transaction even when connection is lost.
`MULTI` marks the start of the transaction. `EXEC` executes the queued commands.
`DISCARD` throws away any queued command.

> Nested transactions are not supported in redis.

Redis uses Optimistic Locking or Optimistic cuncurrency control which is a mechanism that allows you to specify an interest in an object and get a notification if that object is changed.(Keyspace notifications)
putting interest is done by `WATCH` command and When EXEC is called the transaction will fail if any watched keys have been modified. We also have `UNWATCH`

* Called before multi
* multiple watch commands are cumulative
* local to client
* All keys unwatched after exec called

## Storing objects in redis

We can store objects in hashes in redis since we can set & get each attribute. each attritbute can be incremented or decremented. Each field can be tested for existence.

### Storing complex objects

* Multiple hashes
* Multiple hashes + sets to find the relationship
* Flattening relationship in a single hash

The way to maintain it is to create hashes and store the object and related object in two separate hashes then maintain the relationship by adding them in a set.

> This has pros and cons. It has extensible independently stored structures and expiration can be added. However, we have lots of keys and SET needs to be maintained with each relationship. Also, REDIS CLUSTER is out of this chart.

## Bitmaps

Redis supports BitFields and BitArrays which are compact, optimized structures and can be used for histogram of counters, File permissions.

### BitFields

Allows for manipulation of one or more variable length integers within String datatype.

For exact info read [Official doc of Bitfield command](https://redis.io/commands/bitfield/)

BitFields can store upto unsinged u64 and signed u63 bits. To set and get values we need offset or position using #1(multiply of provided type to calculate bit of offset).

### BItArrays

BitArrays allow manipulation of individual bits within a String datatype. The offset is zero based and the size can be upto String size however, it's not recommended to have large bitarrays.
`GETBIT`, `SETBIT`, `BITCOUNT` and `BITOP` is used to perfomr operations such as and, or, not and XOR. `BITPOS` finds the index of the first set or unset bit from a given index in the string.

## Pub/Sub

This feature allows redis to create a simple message bus and act as a broker for multiple clietns providing  a way to post and consume messages and events.
We have `PUBLISH`, `SUBSCRIBE`, `UNSUBSCRIBE`, `PSUBSCRIBE`, `PUNSUBSCRIBE` and `PUBSUB` command.

The usecases are :

* FanOUt
  * Subscribe new orders
  * publish new orders
* Interprocess communications

The pattern syndications is best to use.

> Redis pubsub gurantees order however, doesn't gurantees delivery since if no body subscribe to a channel the messages will be missed and if a client subscribe to achannel the early messages are missed.

This also has performance downsides which payloadsize, number of subs and number of patterns needs considerations.

## Geospatial

Some apps like uber and waze store geospatial objects which have pairs of latitude and longitude and then perform queries on those objects by Distance or radius. Redis can provide this functionality with a very low latency.

For each longitude and latitude pair a geohash is computed which is 52 bit integer ins redis. Geohash encodes positionsin a short string of letters and digits.

> The GoType is actualy  a sorted set.

We use `GEOADD`, `GEOHASH` and `GEOPOS` to interact. We can also manipulate the data with SET operations like `ZINTESTORE` or `ZUNIONSTORE` however, summing them will move our location so we need to use AGGREGATEMIN option to retain the geospatial point. We can also delete using `ZREM` and list it using `ZRANGE`

> Using geoadd with same key will update the value(location). This is useful for moving objects.

Redis provides Three commands to query geospatial objects. `GEODIST` to calculate distance between two members held by same key. `GEORADIUS` and `GEORADIUSBYMEMBER` will find geospatial members within specific radius.
`GEORADIUS` accepts langtitude and latitude but `GEORADIUSBYMEMBER` we provide a member to it.

usecases:

* find locations from some specific location
* advertisement for customers in location.

> Redis does these operations in realtime access and high change rates.

## Lua Scripting

Lua is an embeddable lightweight scripting language. We can use this for store procedures inside redis. We use `EVAL` to run lua script. The scripts are atomic and are blocking. The execution time should be tested and maintained correctly cause it can decrease performance.
> Floating numbers are always truncated.

Usecases:

* Limited counters in rate limitting
