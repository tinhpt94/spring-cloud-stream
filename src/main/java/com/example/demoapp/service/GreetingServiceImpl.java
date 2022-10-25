package com.example.demoapp.service;

import com.example.demoapp.model.Greeting;
import java.util.Optional;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {

  private            String         cacheName = "message";
  @Autowired private RedissonClient redisson;

  @Override
  public Greeting save(Greeting greeting) {
    RMap<Long, Greeting> map = redisson.getMap(cacheName);
    map.put(greeting.getId(), greeting);
    return greeting;
  }

  @Override
  public Optional<Greeting> getById(Long id) {
    Optional<Greeting> result = Optional.empty();
    RMap<Long, Greeting> map = redisson.getMap(cacheName);
    boolean contains = map.containsKey(id);
    if (contains) {
      result = Optional.of(map.get(id));
    }
    return result;
  }
}
