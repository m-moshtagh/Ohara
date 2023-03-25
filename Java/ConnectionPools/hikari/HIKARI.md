# Hikari DBCP

We can retrieve connection for JDBC with Singleton pattern manually or use connection pools. To reuse connections for
multiple requests.

## Connections

Connections have two states in cp:

* IDLE: Which means it's not used and, we can use it.
* ACTIVE: It's being used by a request.

> When we close the connection We just change the status to IDLE.

## Usage

First we need to initialize the datasource component. We need to initialize `javax.Datasource` with Two hikari classes:

1. First we Initialize `HikariConfig` class using dataSource properties.
2. We Pass HikariConfig instance to `HikariDataSource` class

> setMaximumPoolSize lets us set the number of connections in order to bootstrap the application.

## Datasource Properties

We can set keyValue properties like:

* `cachePrepStmts`
* `prepStmtCacheSize`
* `prepStmtCacheLimit`

## CODE

```java
public class ConnectionProvider {
    private static final String URL = "jdbc:mysql://localhost:3306/oakjutsu";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123";

    private ConnectionProvider() {

    }

    private static DataSource initDataSource() {
        var config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(10);
        config.setAutoCommit(false);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    public static Connection getConnection() {
        try {
            return initDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```
