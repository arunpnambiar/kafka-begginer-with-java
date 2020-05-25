import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaDemo {
    public static void main(String[] args) {
        Producer producer = new Producer();
        ProducerRecord<String,String> record = new ProducerRecord<String, String>("first_topic","Welcome to kafka");
        producer.createProducer(record);
    }
}
