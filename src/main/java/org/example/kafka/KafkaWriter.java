package org.example.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.MyEnumType;
import org.example.MyRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaWriter {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String topicName = "test.enum.avro";
        Properties props = getProducerProperties();
        Producer<String, MyRecord> producer = new KafkaProducer<>(props);

        for(int i = 0; i < 5; i++) {
            MyRecord myRecord = MyRecord.newBuilder()
                    .setType(MyEnumType.TypeVal1)
                    .build();

            ProducerRecord<String, MyRecord> record = new ProducerRecord<>(topicName, Integer.toString(i), myRecord);
            producer.send(record).get();
            System.out.println("Record produced" + record);
        }

        producer.close();
    }

    private static Properties getProducerProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9093");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("linger.ms", 1);

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        props.put("schema.registry.url", "localhost:8081");
        props.put("auto.register.schemas", "true");
        props.put("value.subject.name.strategy", "io.confluent.kafka.serializers.subject.RecordNameStrategy");

        return props;
    }
}