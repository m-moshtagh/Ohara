# Big Data

## Concept

Data sets that are so large and complex that traditional data processing software can not deal wit them.
Big data is not only the specialized tools that are needed to process such big and complex datasets, A special
infrastructure is also needed.

Big data 4V's characteristics:

* Volume -> The size of Data, data over 1 gigabyte is usually considered as Big Data.
* Velocity -> The speed of growth and also the speed we consider our job to be done. For searching we need to process
  really quick which is called streaming. On the other hand we want to process some data once per day which is called
  batch processing. In the second case we don't care about latency.
* Variety -> Data can be very diverse containing both structured and unstructured with different types.
* veracity -> The sincerity of data which means how accurate or truthful a data set may be.

## Problem

The problem we face is that the data is growing so fast, and we can't store them in RAM to process them fast and if we
store them in hard disks we need to process them so fast and provide analysis. Big data is here to solve this problem.

## Where we use Big Data

### Type of Data

First we need to figure what type of data we are dealing with. if we are working with data of a group of customers we
probably need a relational database or document based NOSQL database. If data is dealing with social media like system
the data is probably interconnected, so we need a graph database.

#### Structured data

type of data which is formatted with a specific structure. We can separate data into fields, so we can access. These
are suitable for type of data that usually won't change. like, application logs, customer information, financial data.

#### Unstructured data

Data that can't fit into a specific label, extracting and processing these kind of data is hard. social media posts,
Books and, health care data are common examples of this type.

#### Image, videos and Audios

Storing, processing these types of data usually needs so much bandwidth and always has challenges.

#### GeoSpatial, GIS Data

most of the time these type are metadata. Any app that collects gep location information gathers these type of data.

#### Sensors and IOT data

Nowadays, all smart things we use collect information using their sensors.

### Data storage

Relational and Non-relational databases which are used to store structured data. Data is usually stored in schemas.
We can access schema and manipulate it using SQL.
Most of the time these databases are suitable for data that need to exist in standalone server and single node.
NOSQL databases are usually based on documents, which is known as a piece of data that is not fit in a defined schema or
structure. These docs can be JSON, XML or plain text blocks. These databases perform better when the data needs to be
distributed. It gives the ability to access and read data in parallel.

We need specialized storage systems to store these data. Hadoop file system and cassandra are popular storage engines.

Graph databases represent data that can be represented as nodes. Social network is a good fit for this kind of apps.

### Data computation

In order to process this volume of data with quite efficient speed we need special solutions:

#### Generalized data processing

In this method, We fed data through streams and run algorithms to compute through it. Systems give us various efficient
APIs to solve our problems. Hadoop is the most famous engine in this case. It's from Google and was used to index the
whole web. Apache Spark is built on top of hadoop but gives us simple APIs for in memory computation which is not
available in hadoop. Spark also gives us ability to stream data, work with graphs and manipulate text data as SQL. Also,
has machine learning engine.

> Both of them are build on top of HDFS. And they can extend to tens and thousands of clusters in single machine.

#### Search

In order to find the whole piece of data with providing only a piece of it, special engines are made like Solr and
Lucene which are both part of Apache foundation. Lucene is the full text search tool that solr uses for more advance
searching features.
Elasticsearch is another tool for this purpose.
These take data from HDFS and index them and provide API for frontend users to access the data they want.

#### machine learning

Another way to define it is to train computers to learn patterns. This is done by static analysis or other algorithms.
Tensorflow or scikit learn are two powerful frameworks.
