package com.example.demoapp.converters;

import com.example.demoapp.model.Greeting;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class GreetingSerializer implements Serializer<Greeting>{

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public byte[] serialize(String s, Greeting greeting) {
    try {
      return objectMapper.writeValueAsBytes(greeting);
    } catch (JsonProcessingException e) {
      throw new SerializationException(e);
    }
  }
}
