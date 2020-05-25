import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producer {
    private static final String SERVERS_CONFIG  = "127.0.0.1:9092";
    public void createProducer(ProducerRecord<String,String> record){
        generateKafkaProducer(record);
    }
    private Properties getProducerProperties(){
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,SERVERS_CONFIG);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        return properties;
    }
    private void generateKafkaProducer(ProducerRecord<String,String> record){
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(getProducerProperties());
        producer.send(record);
        producer.flush();
        producer.close();
    }
}
