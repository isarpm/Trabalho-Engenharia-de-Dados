package com.example.cruded.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.domain.Sort.Direction;

import jakarta.annotation.PostConstruct;

@Configuration
public class MongoDBConfig {

    private final MongoTemplate mongoTemplate;

    public MongoDBConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    public void initIndexes() {
        // Criar índices para a coleção "usuarios"
        criarIndicesUsuarios();
        
        // Criar índices para a coleção "estudantes"
        criarIndicesEstudantes();
        
        // Criar índices para a coleção "cursos"
        criarIndicesCursos();
        
        System.out.println("Índices do MongoDB criados com sucesso!");
    }

    private void criarIndicesUsuarios() {
        IndexOperations indexOps = mongoTemplate.indexOps("usuarios");
        
        // Índice único para CPF
        indexOps.ensureIndex(new Index()
            .on("cpf", Direction.ASC)
            .unique()
            .background());
        
        // Índice único para login
        indexOps.ensureIndex(new Index()
            .on("login", Direction.ASC)
            .unique()
            .background());
        
        // Índice para nome (não único, para buscas mais rápidas)
        indexOps.ensureIndex(new Index()
            .on("nome", Direction.ASC)
            .background());
    }

    private void criarIndicesEstudantes() {
        IndexOperations indexOps = mongoTemplate.indexOps("estudantes");
        
        // Índice único para matrícula
        indexOps.ensureIndex(new Index()
            .on("matricula", Direction.ASC)
            .unique()
            .background());
        
        // Índice para ano de ingresso (busca por ano)
        indexOps.ensureIndex(new Index()
            .on("anoIngresso", Direction.DESC)
            .background());
        
        // Índice para o ID do usuário (referência)
        indexOps.ensureIndex(new Index()
            .on("usuario._id", Direction.ASC)
            .background());
    }

    private void criarIndicesCursos() {
        IndexOperations indexOps = mongoTemplate.indexOps("cursos");
        
        // Índice único para nome
        indexOps.ensureIndex(new Index()
            .on("nome", Direction.ASC)
            .unique()
            .background());
        
        // Índice para campus (busca por campus)
        indexOps.ensureIndex(new Index()
            .on("campus", Direction.ASC)
            .background());
        
        // Índice para nível (busca por nível)
        indexOps.ensureIndex(new Index()
            .on("nivel", Direction.ASC)
            .background());
    }
}