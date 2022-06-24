import java.sql.*;

public class ConnectionManager {
    private static final String db_url = "jdbc:mysql://localhost:3306/datagenbase";
    private static final String DriverName = "com.mysql.cj.jdbc.Driver";
    private static final String root = "root";
    private static final String rootpwd = "rootpassword";
    private static Connection rootConn;

    public static Connection getRootConnection() {
        try {
            Class.forName(DriverName);
            rootConn = DriverManager.getConnection(db_url, root, rootpwd);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rootConn;
    }
}