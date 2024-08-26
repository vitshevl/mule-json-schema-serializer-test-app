package org.example.mule.jsonschema.testapp.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.mule.jsonschema.serializer.MuleKafkaJsonSchemaSerializer;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/user")
    public ResponseEntity<String> sendMessageToKafkaTopic(
            @RequestParam("schemaId") int schemaId,
            @RequestBody User message) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        var stringValue = mapper.writeValueAsString(message);

        String encodedMessage = MuleKafkaJsonSchemaSerializer.encodeJsonWithSchemaId(stringValue, schemaId);

        kafkaTemplate.send("topic", encodedMessage);

        return ResponseEntity.ok().build();
    }
}
