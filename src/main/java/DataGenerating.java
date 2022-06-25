import kafka.common.KafkaException;
import java.util.concurrent.ExecutionException;
import java.sql.*;

public class DataGenerating {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try { // trying to create some customers to prepare data
            Customers createCustomers = new Customers();
            createCustomers.CreateCustomer(2);
        } catch (SQLException se) {
            se.printStackTrace();
        } try { // check sql database and table created
            Connection conn = ConnectionManager.getRootConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(DBDataGeneratingStrings.dbCreate);
            stmt.executeUpdate(DBDataGeneratingStrings.createTableData);
            conn.close();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } try { // create some data and send to kafka
            int inputnum = 3;
            KafkaProducerClass tryKafkaProducer = new KafkaProducerClass();
            for (int iteration = 0; iteration < inputnum; iteration++) {
                String kafkaMessage = GenerateTransaction.NewTransaction();
                tryKafkaProducer.SendKafkaMessage(kafkaMessage);
                System.out.println(kafkaMessage);
            }
        } catch (SQLException | KafkaException | ExecutionException | InterruptedException ske) {
            System.out.println(ske.getMessage());
        }
        long workTime = System.currentTimeMillis() - startTime;
        System.out.println("WorkTime " + (workTime) + " ms");
    }
}