# Views

## Concept

Whenever we have complex queries we can create view from them and, we can treat them as tables.

```roomsql
CREATE VIEW clients_view AS
SELECT 
    c.client_id,
    c.name
    FROM clients c
```

In order to recreate a view we can drop and create it or, we can use CREATE or Replace. WE can execute it as much as we
want.

> Whenever we don't have Distinct, Aggregate Functions, Group by/having and union WE can use view in insert, update and
> delete statements.

We can use `WITH CHECK OPTION` to prevent an update or delete remove a row from view.
