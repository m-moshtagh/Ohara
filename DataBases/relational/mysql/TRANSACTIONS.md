# Transactions in MySQL database

We can start a Transaction in MySQL with this syntax

```roomsql
START TRANSACTION
DML, DQL
COMMIT/ROLLBACK
```

## Transaction Isolation levels

In MySQL, we have 4 isolation levels:

* READ UNCOMMITTED -> if we update a value within a transaction which is not committed yet, we can access it through
  QUERIES
* READ COMMITTED -> We can not read a value unless it's committed however, if we have two selects in a transactions,
  only it's applicable on first query in transaction
* REPEATABLE READ(default) -> Solves the above problem
* SERIALIZABLE -> Solves the phantom reads problem

> We need to be careful with the deadlocks in transactions. If we try to have two DML statements that have dependency on
> each other database puts a lock on the first one and the second one can not complete and an error shall return.
