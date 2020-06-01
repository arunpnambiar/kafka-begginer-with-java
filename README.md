# kafka-begginer-with-java
It is a Kafka beginner level introduction using JAVA programming language.

What is Kafka?
    Kafka is a distributed streaming platform that is used publish and subscribe to streams of records. Kafka developed by Linked-in.
    kafka works with some components, that are,
      1.topics
      2.brokers
      3.producer
      4.consumer
      5.zookeeper(Deprecated)
what is topic?
  topic is the major part of kafka distributted system. messages are stored in topics. In tpoics messages are stored as partitions. each partion contain offset which is the sequence id for partitions. When we produce a message to broker we need to specify the topic name. If we are not specified any name to topic kafka randomly ceate a name for topic. Which means if we are not created a topic and we are saving value to topic it will show a warning and automattically create that topic.
  
  
         kafka-topics --bootstrap-server 127.0.0.1:9092 --topics topic_name --create --partion 3 --replication-factor 2
  
  Above is the command for creating a topic. here partion means how many partion we need to create for particular topic. and replication factor means how many copy of this topic need to distribute in different broker.
  
 What is Broker?
  Broker is nothing but a server distributed over different machines. We can use one broker or different brokers for kafka. It is a distributed server only. 
  
  What is Producer?
    Producer produce value to toics. Which means producer is the publisher.
    
         kafka-console-producer --broker-list 127.0.0.1:9092 --topic topic_name 
         
    Above command is used for produce value to topic. This way we can use kafka producer in cli.
      
