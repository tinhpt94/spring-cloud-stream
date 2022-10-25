package com.example.demoapp;

import java.io.IOException;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class DemoAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoAppApplication.class, args);
  }

  @Bean(destroyMethod = "shutdown")
  RedissonClient redisson(@Value("classpath:/redisson.yaml") Resource configFile)
      throws IOException {
    Config config = Config.fromYAML(configFile.getInputStream());
    return Redisson.create(config);
  }
}
