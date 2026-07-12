package com.example.cruded.repository;

import com.example.cruded.model.Curso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends MongoRepository<Curso, String> {
    
    // Buscar por nome (campo único)
    Optional<Curso> findByNome(String nome);
    
    // Buscar cursos por campus
    List<Curso> findByCampus(String campus);
    
    // Buscar cursos por nível (graduação, pós, etc.)
    List<Curso> findByNivel(String nivel);
    
    // Buscar cursos por turno
    List<Curso> findByTurno(String turno);
    
    // Verificar se nome já existe
    boolean existsByNome(String nome);
}