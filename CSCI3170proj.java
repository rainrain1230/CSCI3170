
// code to connect java to database and execute query
/*
 * connect to mysql in MariaDB:
 * mysql --host=projgw --port=2633 -u Group44 -p
 * CSCI3170
 * 
 * execution in terminal:
 * javac xxx.java
 * java -classpath ./mysql-jdbc.jar:./ xxx
 */
import java.io.*;
import java.sql.*;
import java.util.*;

public class CSCI3170proj {
    public static void UpdateDatabase(String query) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
            }
            conn = DriverManager.getConnection(
                    "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db44?autoReconnect=true&useSSL=false", "Group44",
                    "CSCI3170");
            ps = conn.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }

    public static List<Map<String, Object>> QueryDatabase(String query) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
            }
            conn = DriverManager.getConnection(
                    "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db44?autoReconnect=true&useSSL=false", "Group44",
                    "CSCI3170");
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            Integer columnCount = metaData.getColumnCount();
            Map<String, Object> row = null;

            while (rs.next()) {
                row = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                resultList.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        UpdateDatabase("CREATE TABLE Student (UserID VARCHAR(10), Password VARCHAR(8), Age INT)");
        UpdateDatabase("INSERT INTO Student VALUES ('3344', 'pw34', 15);");
        UpdateDatabase("INSERT INTO Student VALUES ('1122', 'pw12', 18);");
        System.out.println("done updating database");

        List<Map<String, Object>> resultList = QueryDatabase("SELECT * FROM Student");

        for (Map<String, Object> row : resultList) {
            System.out.println(row.get("UserID"));
            System.out.println(row.get("Password"));
            System.out.print(row.get("Age"));
            System.out.println(row.get("Age").getClass());
        }
    }
}
