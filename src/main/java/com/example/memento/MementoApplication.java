package com.example.memento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.example.memento")
public class MementoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MementoApplication.class, args);
    }

}
