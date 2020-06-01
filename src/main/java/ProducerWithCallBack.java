import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerWithCallBack {
    private static final String SERVERS_CONFIG  = "127.0.0.1:9092";
    private static final Logger log = LoggerFactory.getLogger(ProducerWithCallBack.class);
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
        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(e == null){
                    log.info("Topic: "+recordMetadata.topic()+"\n"+
                            "Offset: "+recordMetadata.offset()+"\n"+
                            "Partition: "+recordMetadata.partition());
                }else{
                    log.error(e.getMessage());
                }
            }
        });
        producer.flush();
        producer.close();
    }
}
