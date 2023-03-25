# Cassandra Database

Cassandra is a NoSQL, open source distributed database.

## Cassandra vs Relational databases

Cassandra follows its own data modeling methodology unlike RDBMS which follows basic relational model methodology. In
cassandra we build data by the needs of the application not

> Cassandra: App -> Model -> Data
> RDBMS: Data -> Model -> App

In cassandra queries are considered to be the main part however, in rdbms Entities are the main point.

In RDBMS primary keys are for uniqueness However, in cassandra above uniqueness PK is used to determine performance in
large scale.

In order to be fault tolerance and scaling out, cassandra is distributed however, RDBMS has a single point of failure.

Cassandra doesn't support ACID and joins however, uses denormalization instead.

Cassandra doesn't enforce referential integrity.

## Terminology

**Data model**

an abstract model to organizing data. This is based on the queries we want to perform.

**Key space**

The utmost logical container of tables. it stores tables and replications of data.

**Table**

Table is combination of rows and columns.

**Partition**

Database is stored on partitions. a partition is rows of data that are stored on a node. each row is consist of
key-value pairs. Cassandra stores and retrieves data, base on partitions.

**Primary Key**

This does two things:

1. guarantees the uniqueness of data
2. defines the placement of the record in the cluster. This allows the easy access of data.

The partition key is the first part of primary key. It determines where in the cluster and which node the data is
stored.
