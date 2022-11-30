# CSCI3170

# Functions
## Connect to DB + Execute SQL
### QueryDatabase(String query) -> ResultSet
- pass query (SELECT) sql, return ResultSet rs

rs is sth like table returned by sql, to retrieve data from it:
try {
  while (rs.next()) {  // next tuple
    String uid = rs.getString("Attribute_name");
  }
}

### UpdateDatabase(String query) -> void
- pass update (UPDATE/INSERT/DELETE) sql to database

## Administrator (Database level update)
### CreateTables()
- create the five tables
- sql: CREATE TABLE TABLE_NAME (attr1 type, attr2 type);
? not sure if need to do checking -> how to handle if table exists:
1. drop all tables first (remove all current entries)
2. ignore that table (keep all current entries)
(cuz if table exists, create table will cause sql error)

### DeleteTables()
- drop all tables
- sql DROP TABLE_NAME 
- not sure if checking needed (preferrably to avoid crashing)
Link: drop table if exists
https://www.mssqltips.com/sqlservertip/6769/sql-server-drop-table-if-exists/

### LoadDataset()
- read 5 txt files from directory
- insert records in txt files into database
* java file I/O
* SQL INSERT INTO TABLE_NAME VALUES (attr1val, attr2val, ...)

### DisplayTable()
- load and display user-inputted table name
- checking for non-existing table name?
- cc if beautify by using some formatting features for alignment
- SQL SELECT * FROM TABLE_NAME -> get rs

## SalesPerson
### SearchParts()
- do searching (wildcard matching, sorting) from table
SQL: SELECT... LIKE ... ORDER BY
link: https://learnsql.com/blog/using-like-match-patterns-sql/

### PerformTransaction()
- 
