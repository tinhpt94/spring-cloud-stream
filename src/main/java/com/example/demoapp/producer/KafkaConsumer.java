package com.example.demoapp.producer;

import com.example.demoapp.model.Greeting;
import com.example.demoapp.service.GreetingService;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConsumer {

  @Autowired private GreetingService greetingServiceImpl;

  @Bean
  public Consumer<Greeting> messageConsumer() {

    return message->{
      greetingServiceImpl.save(message);
      System.out.println("received " + message);
    };
  }
}
