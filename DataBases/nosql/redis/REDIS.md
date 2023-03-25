# Redis

Redis is an open source (BSD licensed), in-memory data structure store used as a database, cache, message broker, and
streaming engine. Redis provides data structures such as strings, hashes, lists, sets, sorted sets with range queries,
bitmaps, HyperLogLog, geospatial indexes, and streams. Redis has built-in replication, Lua scripting, LRU eviction,
transactions, and different levels of on-disk persistence, and provides high availability via Redis Sentinel and
automatic partitioning with Redis Cluster.

## Interactions

External programs use TCP and Redis Specific Protocol to talk to redis. This protocol is implemented in the Redis client
libraries for the different programming languages. However, we have Redis-CLI to send commands to Redis.