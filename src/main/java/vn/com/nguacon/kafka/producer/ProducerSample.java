package vn.com.nguacon.kafka.producer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Date;
import java.util.Properties;
import java.util.Random;
public class ProducerSample {
    private static Producer<String, String> producer;
    public ProducerSample() {
        Properties props = new Properties();
        // Set the broker list for requesting metadata to find the lead broker
        props.put("metadata.broker.list", "127.0.0.1:9092");
        // This specifies the serializer class for keys
        props.put("serializer.class", "kafka.serializer.StringEncoder");

        // Defines the class to be used for determining the partition
        // in the topic where the message needs to be sent.
        //props.put("partitioner.class", "kafka.examples.ch4.SimplePartitioner");

        // 1 means the producer receives an acknowledgment once the lead replica
        // has received the data. This option provides better durability as the
        // client waits until the server acknowledges the request as successful.
        props.put("request.required.acks", "1");

        ProducerConfig config = new ProducerConfig(props);
        producer = new Producer<String, String>(config);
    }
    public static void main(String[] args) {
        String topic = "test";
        int messageCount = 3;
        System.out.println("Topic Name - " + topic);
        System.out.println("Message Count - " + messageCount);
        ProducerSample simpleProducer = new ProducerSample();
        simpleProducer.publishMessage(topic, messageCount);
    }
    private void publishMessage(String topic, int messageCount) {
        Random random = new Random();
        for (int mCount = 0; mCount < messageCount; mCount++) {

            String clientIP = "127.0.0.1";
            String accessTime = new Date().toString();
            String message = accessTime + ",kafka.apache.org," + clientIP;
            System.out.println(message);

            // Creates a KeyedMessage instance
            KeyedMessage<String, String> data =
                    new KeyedMessage<String, String>(topic, clientIP, message);

            // Publish the message
            producer.send(data);
        }
        // Close producer connection with broker.
        producer.close();
    }
}