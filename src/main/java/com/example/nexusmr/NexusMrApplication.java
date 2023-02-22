package com.example.nexusmr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class NexusMrApplication {

  public static void main(String[] args) {
    SpringApplication.run(NexusMrApplication.class, args);
  }

}
