# Oracle database

Oracle is one of the most infamous relational databases which is used by major of enterprises. Here we are going through
advance features of oracle database.

## Architecture

> Tables data are registered in DBF files. The smallest thing in DBF is data block then it's data segment.

> We can find where dbf files are located inside control files(CTL files).

> We have redo files which conceal data about all failed and succeeded transactions. These files are always open till we
> turn down the instance. (If the data is committed Then it's metadata is written in DBF). We have three redo files by
> default, and we can log the data in archive mode.

> SGA

> PGA

## Sub-queries

Sub-queries are nested queries inside another sql query. These allow us to use a query output on another query
input. These can be used in:

* WHERE clause
* FROM clause
* SELECT clause

Oracle first executes sub-queries and stores them in a virtual table then executes the main query using the result.

> We need to consider sub-query returns single or multiple values so, we can use equal or in keyword.

A correlated sub-query is which the nested query is dependent on the outer query(We have condition in sub-query that
needs a value from main query). For this query inner query is executed multiple times.

We also can have multiple rows in a sub-query.

```roomsql
SELECT * FROM employees
WHERE (salary, job_id) IN (SELECT min_salary, job_id) FROM JOBS;
```

> When a multi-column sub-query is used within query we call it inline view. This is a virtual table which is used for
> the sake of our query. We can do all operations like join and other things with this sub-query.

## Indices

We have several options for indices in oracle.

### B-tree

This is used by default on ROW_ID column of tables. This is usually applicable on columns with NUMERIC values. The tree
contains branches which are the ranges of numbers first looks that which value is relevant to each branch then gets to
leaf and finds the number.

### Bitmap

Oracle provides an index for low cardinality fields like genders which have distinct values.

> The more indices we create the speed of UPDATE, INSERT and delete is slower because everytime we update or insert we
> need to update our indices.

## Data Dictionary

All the metadata about our database entities are stored inside Data Dictionary. We can query the certain tables to get
these information. We can get what information is available to us by querying a view called DICT.

The information is based on what current user has access to.

## Table partitioning

We can split table columns across multiple data files and improve performance.

* List -> We choose a column which has several limited values and partition by it.
* Range -> we can choose a range(date, number) to split the table into pieces.
* Hash -> When the above don't work, or we have a number like sale_no which does not have a regular basis we can use
  this partitioning type.
