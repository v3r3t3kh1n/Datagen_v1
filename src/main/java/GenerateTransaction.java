import org.apache.kafka.common.message.CreateTopicsRequestData;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GenerateTransaction {
    static String NewTransaction() throws SQLException {
        Connection conn = ConnectionManager.getRootConnection();
        Statement stmt = conn.createStatement();
        Date date = new Date();
        Random rand = new Random();
        int rubLength = rand.nextInt((6 - 1) + 1) + 1;
        String _id = RandomAlphanumericString.rs(String.valueOf(16));
        String ModifiedDate = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss.S").format(date);
        String PreRub = RandomNumericString.rs(String.valueOf(rubLength));
        String Balance = "%s.%s".formatted(PreRub.replaceFirst("^0+(?!$)", ""), RandomNumericString.rs(String.valueOf(2)));
        ResultSet ResultCusIdQuery = stmt.executeQuery(DBDataGeneratingStrings.CusIdQuery);
        ResultCusIdQuery.next();
        int CusIdMax = ResultCusIdQuery.getInt("cusId");
        int randomCusId = rand.nextInt((CusIdMax - 1) + 1) + 1;
        String CusQuery = ("SELECT CUS FROM customer WHERE cusId='%d'".formatted(randomCusId));
        ResultSet ResultCusQuery = stmt.executeQuery(CusQuery);
        ResultCusQuery.next();
        String Customer = ResultCusQuery.getString("CUS");
        String SSN = RandomNumericString.rs(String.valueOf(12));
        String CreditScoring = RandomNumericString.rs(String.valueOf(6));
        String Age = RandomNumericString.rs(String.valueOf(2));
        String Transactions = RandomNumericString.rs(String.valueOf(8));
        String LoyaltyScoring = RandomNumericString.rs(String.valueOf(4));
        String SecurityCode = RandomAlphabeticString.rs(String.valueOf(6));
//        String sql = "INSERT INTO data (_id,ModifiedDate,Balance,Customer,SSN,CreditScoring,Age,Transactions,LoyaltyScoring,SecurityCode) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')".formatted(_id, ModifiedDate, Balance, Customer, SSN, CreditScoring.replaceFirst("^0+(?!$)", ""), Age.replaceFirst("^0+(?!$)", ""), Transactions.replaceFirst("^0+(?!$)", ""), LoyaltyScoring.replaceFirst("^0+(?!$)", ""), SecurityCode);
//        stmt.executeUpdate(sql);
        return (_id + " / " + ModifiedDate + " / " + Balance + " / " + Customer + " / " + SSN + " / " + CreditScoring + " / " + Age + " / " + Transactions + " / " + LoyaltyScoring + " / " + SecurityCode);
    }
}