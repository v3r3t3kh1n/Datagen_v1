import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.sql.*;

public class DataGenerating {
    public static void main(String[] args) {
        int cn = 2;
        int r = 20;
        try { // check sql database and table created !! check mysql credentials !! ConnectionManager.class
           Connection conn = ConnectionManager.getRootConnection();
           Statement stmt = conn.createStatement();
           stmt.executeUpdate(DBDataGeneratingStrings.dbCreate);
           stmt.executeUpdate(DBDataGeneratingStrings.createTableData);
           conn.close();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } try { // trying to create some customers to prepare data
            Customers createCustomers = new Customers();
            createCustomers.CreateCustomer(cn);
        } catch (SQLException se) {
            se.printStackTrace();
        } try { // create some data and send to kafka
            KafkaProducerClass tryKafkaProducer = new KafkaProducerClass();
            for (int i = 0; i < r; i++) {
                String kafkaMessage = GenerateTransaction.NewTransaction();
                tryKafkaProducer.SendKafkaMessage(kafkaMessage);
                TimeUnit.MILLISECONDS.sleep(100); // remove if your kafka so powerful :)
            }
        } catch (SQLException | ExecutionException | InterruptedException ske) {
            System.out.println(ske.getMessage());
        }
    }
}