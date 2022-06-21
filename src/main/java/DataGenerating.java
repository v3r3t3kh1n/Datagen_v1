import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;

public class DataGenerating {
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
                String sqlcreatetable = "CREATE TABLE IF NOT EXISTS data ("
                        + "_id VARCHAR(16),"
                        + "ModifiedDate VARCHAR(25),"
                        + "Balance VARCHAR(9),"
                        + "Customer VARCHAR(6),"
                        + "SSN BIGINT(12),"
                        + "CreditScoring INT(6),"
                        + "Age INT(2),"
                        + "Transactions INT(8),"
                        + "LoyaltyScoring INT(4),"
                        + "SecurityCode CHAR(6),"
                        + "PRIMARY KEY(_id))";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sqlcreatetable);
                conn.close();
            } catch(SQLException se) {
                System.out.println(se.getMessage());
            }
            long startTime = System.currentTimeMillis();
            int inputnum = 100;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            for (int iteration=0; iteration<inputnum; iteration++) {
                Date date = new Date();
                Random rand = new Random();
                int rubLength = rand.nextInt((6 - 1) + 1) + 1;
                String _id = RandomAlphanumericString.rs(String.valueOf(16));
                String ModifiedDate = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss.S").format(date);
                String PreRub = RandomNumericString.rs(String.valueOf(rubLength));
                String Balance = PreRub.replaceFirst("^0+(?!$)", "") + "." + RandomNumericString.rs(String.valueOf(2));
                String CusIdQuery = ("SELECT cusId FROM customer ORDER BY cusId DESC LIMIT 1;");
                ResultSet ResultCusIdQuery = stmt.executeQuery(CusIdQuery);
                ResultCusIdQuery.next();
                int CusIdMax = ResultCusIdQuery.getInt("cusId");
                int randomCusId = rand.nextInt((CusIdMax - 1) + 1) + 1;
                String CusQuery = ("SELECT CUS FROM customer WHERE cusId='"+randomCusId+"'");
                ResultSet ResultCusQuery = stmt.executeQuery(CusQuery);
                ResultCusQuery.next();
                String Customer = ResultCusQuery.getString("CUS");
                String SSN = RandomNumericString.rs(String.valueOf(12));
                String CreditScoring = RandomNumericString.rs(String.valueOf(6));
                String Age = RandomNumericString.rs(String.valueOf(2));
                String Transactions = RandomNumericString.rs(String.valueOf(8));
                String LoyaltyScoring = RandomNumericString.rs(String.valueOf(4));
                String SecurityCode = RandomAlphabeticString.rs(String.valueOf(6));
                String sql = "INSERT INTO data (_id,ModifiedDate,Balance,Customer,SSN,CreditScoring,Age,Transactions,LoyaltyScoring,SecurityCode)" +
                " VALUES ('"+_id+"','"+ModifiedDate+"','"+ Balance+"','"+Customer+"','"+SSN+"','"+CreditScoring.replaceFirst("^0+(?!$)", "")+"','"+Age.replaceFirst("^0+(?!$)", "")+"','"+Transactions.replaceFirst("^0+(?!$)", "")+"','"+LoyaltyScoring.replaceFirst("^0+(?!$)", "")+"','"+SecurityCode+"')";
                stmt.executeUpdate(sql);
            }
            try {
                conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
            long workTime = System.currentTimeMillis() - startTime;
            System.out.println("WorkTime " + (workTime) + "ms");
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
        }

    }
}