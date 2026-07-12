package com.example.cruded.repository;

import com.example.cruded.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    
    // Buscar por CPF (campo único)
    Optional<Usuario> findByCpf(Long cpf);
    
    // Buscar por login (campo único)
    Optional<Usuario> findByLogin(String login);
    
    // Verificar se CPF já existe (para validação)
    boolean existsByCpf(Long cpf);
    
    // Verificar se login já existe (para validação)
    boolean existsByLogin(String login);
}