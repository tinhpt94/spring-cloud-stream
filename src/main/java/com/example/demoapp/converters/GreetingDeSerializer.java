package com.example.demoapp.converters;

import com.example.demoapp.model.Greeting;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class GreetingDeSerializer implements Deserializer<Greeting> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public Greeting deserialize(String s, byte[] bytes) {
    try {
      return objectMapper.readValue(new String(bytes), Greeting.class);
    } catch (IOException e) {
      throw new SerializationException(e);
    }
  }
}
