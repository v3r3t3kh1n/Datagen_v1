import java.sql.*;

public class Customers {
    public void CreateCustomer(int inputnum) throws SQLException {
        long CustomerStartTime = System.currentTimeMillis();
        Connection conn = ConnectionManager.getRootConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(DBCustomerStrings.createTable);
        stmt.executeUpdate(DBCustomerStrings.zaglushkableat);
        ResultSet rsc = stmt.executeQuery(DBCustomerStrings.CusIdQuery);
        rsc.next();
        int lastCusId = rsc.getInt("cusId");
        for (int iteration=lastCusId+1; iteration<lastCusId+inputnum; iteration++) {
            String Customer = RandomAlphanumericString.rs(String.valueOf(6));
            String createCustomers = "INSERT INTO customer (cusId,CUS) VALUES ('%d','%s')".formatted(iteration, Customer);
            stmt.executeUpdate(createCustomers);
        }
        conn.close();
        long workTime = System.currentTimeMillis() - CustomerStartTime;
        System.out.println("CustomerWorkTime " + (workTime) + "ms");
    }
}