import java.sql.*;

public class Customers {
    public static void main(String[] args) {
        final String DB_URL = "jdbc:mysql://localhost:3306/datagenbase";
        final String USER = "juser";
        final String PASS = "juser";
        final String ROOT = "root";
        final String ROOTPASS = "rootpassword";
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, ROOT, ROOTPASS);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(" CREATE DATABASE IF NOT EXISTS datagenbase");
                conn.close();
            } catch(SQLException se) {
                System.out.println(se.getMessage());
            }
            try {
                String sqlcreatetable = "CREATE TABLE IF NOT EXISTS customer ("
                        + "cusId INT(4),"
                        + "CUS VARCHAR(6),"
                        + "PRIMARY KEY(cusId))";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sqlcreatetable);
            } catch(SQLException se) {
                System.out.println(se.getMessage());
            }
            long CustomerStartTime = System.currentTimeMillis();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String zaglushkableat = "INSERT IGNORE INTO customer (cusId,CUS)" + " VALUES (0,0)";
            stmt.executeUpdate(zaglushkableat);
            String CusIdQuery = ("SELECT cusId FROM customer ORDER BY cusId DESC LIMIT 1;");
            ResultSet ResultCusIdQuery = stmt.executeQuery(CusIdQuery);
            ResultCusIdQuery.next();
            int CusIdMax = ResultCusIdQuery.getInt("cusId");
            int inputnum = 10;
            int repeats = CusIdMax+inputnum;
            for (int iteration=CusIdMax+1; iteration<repeats; iteration++) {
                int cusId = iteration;
                String Customer = RandomAlphanumericString.rs(String.valueOf(6));
                String sql = "INSERT INTO customer (cusId,CUS)" + " VALUES ('"+cusId+"','"+Customer+"')";
                stmt.executeUpdate(sql);
            }
            try {
                conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
            long workTime = System.currentTimeMillis() - CustomerStartTime;
            System.out.println("CustomerWorkTime " + (workTime) + "ms");
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}