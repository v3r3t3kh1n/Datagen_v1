public class DBDataGeneratingStrings {
    static String createTableData = "CREATE TABLE IF NOT EXISTS data ("
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
    static String dbCreate = "CREATE DATABASE IF NOT EXISTS datagenbase";
    static String CusIdQuery = ("SELECT cusId FROM customer ORDER BY cusId DESC LIMIT 1;");
}
