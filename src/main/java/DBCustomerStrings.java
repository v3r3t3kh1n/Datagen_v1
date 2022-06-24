public class DBCustomerStrings {
    static String createTable = "CREATE TABLE IF NOT EXISTS customer ("
            + "cusId INT(4),"
            + "CUS VARCHAR(6),"
            + "PRIMARY KEY(cusId))";
    static String zaglushkableat = "INSERT IGNORE INTO customer (cusId,CUS)" + " VALUES (0,0)";
    static String CusIdQuery = ("SELECT cusId FROM customer ORDER BY cusId DESC LIMIT 1;");
}