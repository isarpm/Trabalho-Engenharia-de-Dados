package com.example.cruded;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class CrudNoSqlApplication {
    
    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;
    
    public static void main(String[] args) {
        SpringApplication.run(CrudNoSqlApplication.class, args);
        System.out.println("CRUD MongoDB rodando na porta 8081!");
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void logConfig() {
        String maskedUri = mongoUri.replaceAll(":[^:@]*@", ":****@");
        System.out.println("Conectando ao MongoDB: " + maskedUri);
    }
}