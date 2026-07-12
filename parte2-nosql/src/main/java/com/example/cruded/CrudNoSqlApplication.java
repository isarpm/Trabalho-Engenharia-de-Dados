package com.example.cruded;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudNoSqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudNoSqlApplication.class, args);
        System.out.println("CRUD MongoDB rodando na porta 8080!");
    }
}