package com.example.cruded.repository;

import com.example.cruded.model.Estudante;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudanteRepository extends MongoRepository<Estudante, String> {
    
    // Buscar por matrícula (campo único)
    Optional<Estudante> findByMatricula(String matricula);
    
    // Buscar estudantes por ID do usuário
    List<Estudante> findByUsuarioId(String usuarioId);
    
    // Buscar estudantes por ano de ingresso
    List<Estudante> findByAnoIngresso(Integer anoIngresso);
    
    // Verificar se matrícula já existe
    boolean existsByMatricula(String matricula);
}