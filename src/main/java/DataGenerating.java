import java.sql.*;

public class DataGenerating {
    public static void main(String[] args) throws SQLException {
        Customers createCustomers = new Customers();
        createCustomers.CreateCustomer(10);
        GenerateTransaction generateTransaction = new GenerateTransaction();
        long startTime = System.currentTimeMillis();
        Connection conn = ConnectionManager.getRootConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(DBDataGeneratingStrings.dbCreate);
        stmt.executeUpdate(DBDataGeneratingStrings.createTableData);
        int inputnum = 100;
        for (int iteration=0; iteration<inputnum; iteration++) {
            generateTransaction.NewTransaction();
        }
        try {
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        }
        long workTime = System.currentTimeMillis() - startTime;
        System.out.println("WorkTime " + (workTime) + "ms");
    }
}