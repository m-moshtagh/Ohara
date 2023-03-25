# User management

## CREATE

```roomsql
CREATE USER username@host IDENTIFIED BY 'password';
```

## View Users

```roomsql
SELECT * FROM mysql.user;
```

## Drop user

```roomsql
DROP USER username@host;
```

## Set password

```roomsql
SET PASSWORD FOR username
```

## Grant

```roomsql
GRANT privileges ON db.table TO username
```

## Show privileges

```roomsql
SHOW GRANTS FOR username;
```

## Revoke privileges

```roomsql
REVOKE CREATE VIEW ON db.table FROM username
```