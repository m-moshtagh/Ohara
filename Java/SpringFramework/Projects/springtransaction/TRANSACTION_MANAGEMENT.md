# Spring TX(Transaction)

## Usage

We manage transaction in service layer. Spring provides `@Transactional` annotation to manage transactions.
Spring creates a proxy, or manipulates the class byte-code, to manage the creation, commit, and rollback of the
transaction. In the case of a proxy, Spring ignores `@Transactional` in internal method calls.Spring creates a proxy, or
manipulates the class byte-code, to manage the creation, commit, and rollback of the transaction. In the case of a
proxy, Spring ignores `@Transactional` in internal method calls.

> Placing annotation may override each other according to the priority order; from lowest to highest we have: interface,
> superclass, class, interface method, superclass method, and class method.

## Tx Propagation

Propagation defines our business logic's transaction boundary. Spring manages to start and pause a transaction according
to our propagation setting.

### REQUIRED Propagation

REQUIRED is the default propagation. Spring checks if there is an active transaction, and if nothing exists, it creates
a new one. Otherwise, the business logic appends to the currently active transaction

```java
public class Transaction {
    @Transactional(propagation = Propagation.REQUIRED)
    public void requiredExample(String user) {
        // ... 
    }
}
```

```text
if (isExistingTransaction()) {
    if (isValidateExistingTransaction()) {
        validateExistingAndThrowExceptionIfNotValid();
    }
    return existing;
}
return createNewTransaction();
```

### SUPPORTS Propagation

For SUPPORTS, Spring first checks if an active transaction exists. If a transaction exists, then the existing
transaction will be used. If there isn't a transaction, it is executed non-transactional

```java
public class Transaction {
    @Transactional(propagation = Propagation.SUPPORTS)
    public void supportsExample(String user) {
        // ... 
    }
}
```

```text
if (isExistingTransaction()) {
    if (isValidateExistingTransaction()) {
        validateExistingAndThrowExceptionIfNotValid();
    }
    return existing;
}
return emptyTransaction;
```

### MANDATORY Propagation

When the propagation is MANDATORY, if there is an active transaction, then it will be used. If there isn't an active
transaction, then Spring throws an exception

```java
public class Transaction {
    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatoryExample(String user) {
        // ... 
    }
}
```

```text
if (isExistingTransaction()) {
    if (isValidateExistingTransaction()) {
        validateExistingAndThrowExceptionIfNotValid();
    }
    return existing;
}
throw IllegalTransactionStateException;
```

### NEVER Propagation

For transactional logic with NEVER propagation, Spring throws an exception if there's an active transaction

```java
public class Transaction {
    @Transactional(propagation = Propagation.NEVER)
    public void neverExample(String user) {
        // ... 
    }
}
```

```text
if (isExistingTransaction()) {
    throw IllegalTransactionStateException;
}
return emptyTransaction;
```

### NOT_SUPPORTED Propagation

If a current transaction exists, first Spring suspends it, and then the business logic is executed without a transaction

```java
public class Transaction {
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupportedExample(String user) {
        // ... 
    }
}
```

> The JTATransactionManager supports real transaction suspension out-of-the-box. Others simulate the suspension by
> holding a reference to the existing one and then clearing it from the thread context

### REQUIRES_NEW Propagation

When the propagation is REQUIRES_NEW, Spring suspends the current transaction if it exists, and then creates a new one

```java
public class Transaction {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNewExample(String user) {
        // ... 
    }
}
```

> Similar to NOT_SUPPORTED, we need the JTATransactionManager for actual transaction suspension

```text
if (isExistingTransaction()) {
    suspend(existing);
    try {
        return createNewTransaction();
    } catch (exception) {
        resumeAfterBeginException();
        throw exception;
    }
}
return createNewTransaction();
```

### NESTED Propagation

For NESTED propagation, Spring checks if a transaction exists, and if so, it marks a save point. This means that if our
business logic execution throws an exception, then the transaction rollbacks to this save point. If there's no active
transaction, it works like REQUIRED.

```java
public class Transaction {
    @Transactional(propagation = Propagation.NESTED)
    public void nestedExample(String user) {
        // ... 
    }
}
```

## TX Isolation

Each isolation level prevents zero or more concurrency side effects on a transaction

* Dirty read: read the uncommitted change of a concurrent transaction
* Non-repeatable read: get different value on re-read of a row if a concurrent transaction updates the same row and
  commits
* Phantom read: get different rows after re-execution of a range query if another transaction adds or removes some rows
  in the range and commits

### Default isolation

The default isolation level is DEFAULT. As a result, when Spring creates a new transaction, the isolation level will be
the default isolation of our RDBMS. Therefore, we should be careful if we change the database. We should also consider
cases when we call a chain of methods with different isolation. In the normal flow, the isolation only applies when a
new transaction is created. Thus, if for any reason we don't want to allow a method to execute in different isolation,
we have to set `TransactionManager::setValidateExistingTransaction` to true.

### READ_UNCOMMITTED Isolation

READ_UNCOMMITTED is the lowest isolation level and allows for the most concurrent access.

As a result, it suffers from all three mentioned concurrency side effects. A transaction with this isolation reads
uncommitted data of other concurrent transactions. Also, both non-repeatable and phantom reads can happen. Thus, we can
get a different result on re-read of a row or re-execution of a range query.

```java
public class Isolation {
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void log(String message) {
        // ...
    }
}
```

> Postgres does not support READ_UNCOMMITTED isolation and falls back to READ_COMMITTED instead. Also, Oracle does not
> support or allow READ_UNCOMMITTED.

### READ_COMMITTED Isolation

The second level of isolation, READ_COMMITTED, prevents dirty reads.

The rest of the concurrency side effects could still happen. So uncommitted changes in concurrent transactions have no
impact on us, but if a transaction commits its changes, our result could change by re-querying.

```java
public class Isolation {
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void log(String message) {
        // ...
    }
}
```

> READ_COMMITTED is the default level with Postgres, SQL Server, and Oracle.

### REPEATABLE_READ Isolation

The third level of isolation, REPEATABLE_READ, prevents dirty, and non-repeatable reads. So we are not affected by
uncommitted changes in concurrent transactions.

Also, when we re-query for a row, we don't get a different result. However, in the re-execution of range-queries, we may
get newly added or removed rows.

Moreover, it is the lowest required level to prevent the lost update. The lost update occurs when two or more concurrent
transactions read and update the same row. REPEATABLE_READ does not allow simultaneous access to a row at all. Hence, the
lost update can't happen.

```java
public class Isolation {
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void log(String message) {
        // ...
    }
}
```

### SERIALIZABLE Isolation

SERIALIZABLE is the highest level of isolation. It prevents all mentioned concurrency side effects, but can lead to the
lowest concurrent access rate because it executes concurrent calls sequentially.

In other words, concurrent execution of a group of serializable transactions has the same result as executing them in
serial.

```java
public class Isolation {
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void log(String message) {
        // ...
    }
}
```
