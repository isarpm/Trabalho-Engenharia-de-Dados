package com.example.cruded.controller;

import com.example.cruded.model.Estudante;
import com.example.cruded.model.Usuario;
import com.example.cruded.repository.EstudanteRepository;
import com.example.cruded.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteRepository estudanteRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Estudante> criar(@RequestBody Estudante estudante) {
        // Validar se matrocula ja existe
        if (estudanteRepository.existsByMatricula(estudante.getMatricula())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Matricula ja cadastrada");
        }
        
        // Validar se o usuario existe (quando fornecido)
        if (estudante.getUsuario() != null && estudante.getUsuario().getCpf() != null) {
            Usuario usuario = usuarioRepository.findByCpf(estudante.getUsuario().getCpf())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));
            estudante.setUsuario(usuario);
        }
        
        Estudante saved = estudanteRepository.save(estudante);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<Estudante> listarTodos() {
        return estudanteRepository.findAll();
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Estudante> buscarPorMatricula(@PathVariable String matricula) {
        return estudanteRepository.findByMatricula(matricula)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante nao encontrado"));
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Estudante> atualizar(@PathVariable String matricula, @RequestBody Estudante novosDados) {
        return estudanteRepository.findByMatricula(matricula)
                .map(estudante -> {
                    estudante.setMc(novosDados.getMc());
                    estudante.setAnoIngresso(novosDados.getAnoIngresso());
                    
                    // Atualizar usuário se fornecido
                    if (novosDados.getUsuario() != null && novosDados.getUsuario().getCpf() != null) {
                        Usuario usuario = usuarioRepository.findByCpf(novosDados.getUsuario().getCpf())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));
                        estudante.setUsuario(usuario);
                    }
                    
                    Estudante salvo = estudanteRepository.save(estudante);
                    return ResponseEntity.ok(salvo);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante nao encontrado"));
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deletar(@PathVariable String matricula) {
        return estudanteRepository.findByMatricula(matricula)
                .map(estudante -> {
                    estudanteRepository.delete(estudante);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante nao encontrado"));
    }
}