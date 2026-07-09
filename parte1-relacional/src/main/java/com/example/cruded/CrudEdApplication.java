package com.example.cruded;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.cruded")
public class CrudEdApplication {

    public static void main(String[] args) {

        SpringApplication.run(CrudEdApplication.class, args);
    }

}