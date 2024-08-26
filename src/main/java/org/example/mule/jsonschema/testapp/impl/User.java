package org.example.mule.jsonschema.testapp.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class User {
    private String name;
    private int age;
    private String email;

}
