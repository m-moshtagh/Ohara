# Java DataBase Connectivity

JDBC API has two parts:

1. API used by programmers
2. JDBC Driver which is low level configuration to connect to DB.

> We use JDBC template instead of raw JDBC

> We use java.sql & javax.sql packages for JDBC API

## JDBC driver types:

JDBC Driver is an implementation of JDBC API interfaces for a specific DB.

1- JDBC-ODBC driver
2- Java + native code driver
3- All java + middleware translation driver
4- All java driver

### Steps to write a Data access class:

1. import packages
2. Set a JDBC driver (optional since java6 and JDBC 4.0)
3. Open a Connection
4. create a statement
5. execute
6. close resources

> We have statement and preparedStatement interfaces to run sql commands.

### Batch transactions

1. establish a Connection
2. Create statement
3. add SQL command using addBatch()
4. execute batch using executeBatch()

> We can set autocommit to false and manually handle transactions with commit & rollback methods.

## Data source

Data Source is a way for clients to obtain a DBMS connection. Apache dbcp, Hikaricp, TomcatCp. we define a bunch of
parameters like driverClassName, URL, username, password etc.
Data source points to connection pool. obtaining a database connection is an expensive job, so we'll have some free
connections in the pool so the clients can take them and release them.

> WE also add flyway for database migration.

## JDBC RowSet

It's located in `javax.sql` package. It makes working with JDBC easier. We can initialize it with Factory
RowSetProvider. 
