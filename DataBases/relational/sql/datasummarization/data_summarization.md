# Data Summarization

When we are dealing with large data we always summarize data in our queries.

## Aggregate Functions

These built-in functions get series of values and aggregate them to produce a single value. Some of them are:

* MAX()
* MIN()
* AVG()
* SUM()
* COUNT()

```roomsql
SELECT 'First half of 2019' AS date_range,
		SUM(i.invoice_total) AS total_sales,
		SUM(i.payment_total) AS total_payments,
		SUM(i.invoice_total - i.payment_total) AS what_we_expect
	FROM invoices i
	WHERE i.invoice_date BETWEEN '2019-01-01' AND '2019-06-30'
UNION 
SELECT 'Second half of 2019' AS date_range,
		SUM(i.invoice_total) AS total_sales,
		SUM(i.payment_total) AS total_payments,
		SUM(i.invoice_total - i.payment_total) AS what_we_expect
	FROM invoices i
	WHERE i.invoice_date BETWEEN '2019-07-01' AND '2019-12-31'
UNION
SELECT 'Total' AS date_range,
		SUM(i.invoice_total) AS total_sales,
		SUM(i.payment_total) AS total_payments,
		SUM(i.invoice_total - i.payment_total) AS what_we_expect
	FROM invoices i
	WHERE i.invoice_date BETWEEN '2019-01-01' AND '2019-12-31'
```

### ROLLUP operator

MYSQL gives us a feature which we can add at the end of the query. This function adds a row and calculates the
aggregated columns total amount.

```roomsql
SELECT pm.name, SUM(p.amount) AS total
FROM payments p JOIN payment_methods pm ON p.payment_method = pm.payment_method_id
GROUP BY pm.name WITH ROLLUP ;
```

## Group by clause

In order to classify the query by each specific column we want, we can use group by command. To make it simple When we
use an aggregate function, We see a single result in query. But if we want to have that aggregate function for each row
of specific column we can use group by. For example sum(totalSpent) will calculate the sun of all totalSpent in table
but, if we use `group by date, personId` it will calculate the sum of totalSpent of each date and personId.
Group by is used after `WHERE` clause and before `ORDER BY` clause.

```roomsql
SELECT p.date, pm.name, SUM(p.amount) AS total_amount
    FROM payments p INNER JOIN payment_methods pm 
	ON p.payment_method = pm.payment_method_id 
	GROUP  BY p.date, pm.name
	ORDER BY total_amount DESC;
```

## Having clause

We can not put a condition with where on alias columns we have grouped. To do this we can use `HAVING` clause. using
this we can access all the columns inside our query.
There are two difference between WHERE & HAVING:

* WHERE can access all columns whether they are in select or not but in HAVING we don't have access to columns that are
  not included in query.
* Using WHERE we can not access the columns we have created with aggregate functions that are named with ALIASES. But,
  we can access them using HAVING clause.

```roomsql
SELECT c.customer_id ,c.first_name , c.last_name, SUM(oi.quantity * oi.unit_price) AS total
FROM customers c 
JOIN orders o USING (customer_id)
JOIN order_items oi USING (order_id)
WHERE c.state = 'VA'
GROUP BY c.customer_id , c.first_name , c.last_name 
HAVING total > 100;
```
