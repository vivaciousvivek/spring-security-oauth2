package com.example.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author VIVEK KUMAR SINGH
 * @since (2018-04-30 12:47:49)
 */
@SpringBootApplication
@EnableJpaRepositories("com.example.security.repository")
@EntityScan("com.example.security.domain")
public class SpringSecurityOAuth2Application {

  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityOAuth2Application.class, args);
  }
}
