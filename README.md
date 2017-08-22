# NoSQLite
Sometimes you just need a simple key-value database
NoSqlite is built over SQLite

## Maven Configuration
```
<dependency>
  <groupId>com.harium.database</groupId>
  <artifactId>nosqlite</artifactId>
  <version>1.0.0</version>
</dependency>
```

## How to Use It
```
Database.init();
Database.add("key", "value");
Database.get("key"); // Return "value"
```
