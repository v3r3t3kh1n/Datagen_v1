import java.util.concurrent.ExecutionException;
import java.sql.*;
import java.util.concurrent.TimeUnit;

public class DataGenerating {
    public static void main(String[] args) {
        int customersNum = 2;
        int repeats = 20;
        try { // check sql database and table created
           Connection conn = ConnectionManager.getRootConnection();
           Statement stmt = conn.createStatement();
           stmt.executeUpdate(DBDataGeneratingStrings.dbCreate);
           stmt.executeUpdate(DBDataGeneratingStrings.createTableData);
           conn.close();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } try { // trying to create some customers to prepare data
            Customers createCustomers = new Customers();
            createCustomers.CreateCustomer(customersNum);
        } catch (SQLException se) {
            se.printStackTrace();
        } try { // create some data and send to kafka
            KafkaProducerClass tryKafkaProducer = new KafkaProducerClass();
            for (int iteration = 0; iteration < repeats; iteration++) {
                String kafkaMessage = GenerateTransaction.NewTransaction();
                tryKafkaProducer.SendKafkaMessage(kafkaMessage);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (SQLException | ExecutionException | InterruptedException ske) {
            System.out.println(ske.getMessage());
        }
    }
}