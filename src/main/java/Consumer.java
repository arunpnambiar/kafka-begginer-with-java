import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Consumer {
     private static final String bootstrap_server = "127.0.0.1:9092";
     private static final String group_id = "kafka_consumer_test_group";
     private static final Logger log = LoggerFactory.getLogger(Consumer.class);
     private Properties getConsumerProperties(){
        Properties consumerProperties = new Properties();
        consumerProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrap_server);
        consumerProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        consumerProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,group_id);
        consumerProperties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        return consumerProperties;
    }

    private void subscribeConsumer(){
        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<>(getConsumerProperties());
        kafkaConsumer.subscribe(Arrays.asList("first_topic","second_topic"));
        while(true){
           ConsumerRecords<String,String>  records = kafkaConsumer.poll(Duration.ofMillis(100));
           for(ConsumerRecord<String,String> record: records ){
               log.info("Value: "+record.value()+"\nKey: "+record.key()+"\n"+record.topic());
           }
        }
    }
}
