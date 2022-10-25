package com.example.demoapp.service;

import com.example.demoapp.model.Greeting;
import java.util.Optional;

public interface GreetingService {
  Greeting save(Greeting greeting);

  Optional<Greeting> getById(Long id);
}
