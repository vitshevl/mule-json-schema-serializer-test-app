package org.example.mule.jsonschema.testapp.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.mule.jsonschema.deserializer.MuleKafkaJsonSchemaDeserializer;
import org.example.mule.jsonschema.testapp.dto.kafka.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = "topic",
            containerFactory = "schemaRegistryKafkaListenerContainerFactory")
    public void listen(ConsumerRecord<String, User> record) {


        System.out.println("Schema registry message: " + record.value());
    }


    @KafkaListener(topics = "topic",
          containerFactory = "stringKafkaListenerContainerFactory")
    public void listen2(String message) {

        String encodedMessage = MuleKafkaJsonSchemaDeserializer.decodeJsonWithSchemaId(message);

        System.out.println("Plugin message: " + encodedMessage);
    }

}
