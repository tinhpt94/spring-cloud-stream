package com.example.demoapp.client;

import com.example.demoapp.model.Greeting;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GreetingClient {

  private final WebClient client;

  public GreetingClient(WebClient.Builder builder) {
    this.client = builder.baseUrl("http://localhost:8080").build();
  }

  public Mono<String> getGreetingMessage() {
    return this.client.get()
        .uri("/greeting/1")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(Greeting.class)
        .map(Greeting::getMessage);
  }


  public Mono<String> postGreetingMessage() {
    return this.client.post()
        .uri("/greeting")
        .accept(MediaType.APPLICATION_JSON)
        .body((BodyInserter<?, ? super ClientHttpRequest>) new Greeting(1L, "Hello From Post"))
        .retrieve()
        .bodyToMono(Greeting.class)
        .map(Greeting::getMessage);
  }
}
