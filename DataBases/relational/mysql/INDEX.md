# Indexes

Indexes are binary tree data structures that helps us improve the performance of our queries. Small ones are in memory
hence, They are fast.

## Create index

```roomsql
CREATE INDEX index_name ON table_name(columns);
```

> It's optimal to index a substring of a String column because indexing string columns is heavier.

```roomsql
CREATE INDEX index_name on table_name(string_column(length));
```

## View indexes

We can review and see an index details using:

```roomsql
SHOW INDEXES ON table_name;
```

> Using `EXPLAIN` keyword before `SELECT` will give us some great explanation on how the query is executed and how the
> index is effecting the query performance.

## FULLTEXT Indexes

If we want to query like a search engine with relevancy and preciseness, we can use FULLTEXT indexes.

```roomsql
CREATE FULLTEXT INDEX index_name ON table_name(column_names);
```

And we can specially use fulltext searches with `MATCH()` `AGAINST()` functions.

```roomsql
SELECT *, MATCH (columns) AGAINST('text')
FROM table_name
WHERE MATCH (columns) AGAINST('text', IN BOOLEAN MODE)
```

> We can specify which index to use explicitly before WHERE clause with `USE INDEX (index_name)`

> with boolean mode we can interact the search like real search engines for example if we pass `-text` in the string
> we search for a text without the text we specified with `-`. Or, if we put the text inside "" it will look for the
> texts exactly matching the String.

## Optimal performance

If we are trying to create a composite index it's the precedence of columns is important because it affects query.

* Place Frequently used columns first
* Place High cardinality columns(columns with more unique values)
* take your queries into account(using EXPLAIN) we can inspect and improve them.

> If we try to use the column we have index on in an expression in WHERE clause the index will get ignored. For example:
> `WHERE points + 10 > 2010` -> Gets ignored. `WHERE points > 2000` -> uses index

## Sorting

We should always be careful that our sorting query uses index && uses im memory sort instead of file sorting. These
data is accessible in EXPLAIN query result.
