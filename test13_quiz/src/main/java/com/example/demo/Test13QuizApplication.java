package com.example.demo;

import jakarta.persistence.EntityListeners;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Test13QuizApplication {

    public static void main(String[] args) {
        SpringApplication.run(Test13QuizApplication.class, args);
    }

}
