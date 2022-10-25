package com.example.demoapp.controller;

import com.example.demoapp.model.Greeting;
import com.example.demoapp.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

  @Autowired private GreetingService greetingServiceImpl;
  @Autowired private StreamBridge    streamBridge;

  @PostMapping()
  private Mono<Greeting> saveGreeting(@RequestBody Greeting greeting) {
    streamBridge.send("messageProducer-out-0", greeting);
    return Mono.just(greeting);
  }

  @GetMapping("/{id}")
  private Mono<Greeting> getGreeting(@PathVariable Long id) {
    return Mono.just(greetingServiceImpl.getById(id).orElse(new Greeting()));
  }
}