# MySQL Special Functions

## Numeric Functions

* ROUND(NUMBER, DIGITS)
* TRUNCATE(NUMBER, DIGITS) -> We can use this to keep the number of the digits We want.
* CEILING(NUMBER) -> Returns the smallest Integer that is >= to the number
* FLOOR(NUMBER) -> Returns largest integer that is <= to number
* ABS(NUMBER) -> Returns positive value
* RAND() -> return random number

## String Functions

* LENGTH(STRING)
* UPPER(STRING)
* LOWER(STRING)
* LTRIM(STRING)
* RTRIM(STRING)
* TRIM(STRING)
* LEFT(STRING, DIGIT) -> Returns number of the digits from left side.
* RIGHT(STRING, DIGIT)
* SUBSTRING(STRING, Start, Length_OPTIONAL)
* LOCATE(Search_String, String) -> Returns position of the search string. Starts from 1, 0 is for not found.
* REPLACE(STRING, actualString, ReplaceString)
* CONCAT(STRING, STRING)

## Date & Time Functions

* NOW()
* CURDATE()
* CURTIME()
* YEAR()
* MONTH()
* DAY()
* HOUR()
* MINUTE()
* SECOND()
* DAYNAME()
* MONTHNAME()
* EXTRACT(UNIT FROM DATE) -> `SELECT EXTRACT(DAY FROM NOW())`
* DATEFORMAT(DATE, STRING)
* TIMEFORMAT(TIME, STRING)
* DATE_ADD(DATE, INTERVAL NUM UNIT)
* DATE_SUB(DATE, INTERVAL NUM UNIT)
* DATEDIFF(DATE, DATE)
* TIME_TO_SEC()

## IFNULL() & COALESCE()

The IfNull() function replace a null with a value But, we provide series of values to coalesce functions and at last we
represent a value if they are all null.

## IF() & CASE()

`IF(EXPRESSION, FirstVALUE, SecondVALUE)`

If we have multiple expressions we use CASE function.

```roomsql
SELECT 
CONCAT(c.first_name, ' ', c.last_name) AS customer,
c.points ,
CASE 
	WHEN c.points > 3000 THEN 'GOLD'
	WHEN c.points > 2000 AND c.points < 3000 THEN 'SILVER'
	ELSE 'BRONZE'
END AS category
FROM customers c ;
```
