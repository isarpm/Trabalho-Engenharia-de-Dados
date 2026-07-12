package com.example.cruded.repository;

import com.example.cruded.model.Vinculo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VinculoRepository extends MongoRepository<Vinculo, String> {
    
    // Buscar vínculos por estudante (usando ID do MongoDB)
    List<Vinculo> findByEstudanteId(String estudanteId);
    
    // Buscar vínculos por curso (usando ID do MongoDB)
    List<Vinculo> findByCursoId(String cursoId);
    
    // Buscar vínculos por status (Ativo, Concluído, Trancado, etc.)
    List<Vinculo> findByStatus(String status);
    
    // Buscar vínculos por estudante e status
    List<Vinculo> findByEstudanteIdAndStatus(String estudanteId, String status);
    
    // Buscar vínculos ativos (status = "Ativo")
    List<Vinculo> findByStatusOrderByDataEntradaDesc(String status);
}