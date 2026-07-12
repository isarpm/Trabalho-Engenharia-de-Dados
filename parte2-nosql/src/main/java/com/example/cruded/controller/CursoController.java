package com.example.cruded.controller;

import com.example.cruded.model.Curso;
import com.example.cruded.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<Curso> criar(@RequestBody Curso curso) {
        // Validar se nome do curso já existe
        if (cursoRepository.existsByNome(curso.getNome())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Curso já cadastrado");
        }
        
        Curso saved = cursoRepository.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Curso> buscarPorNome(@PathVariable String nome) {
        return cursoRepository.findByNome(nome)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
    }

    @PutMapping("/{nome}")
    public ResponseEntity<Curso> atualizar(@PathVariable String nome, @RequestBody Curso cursoAtualizado) {
        return cursoRepository.findByNome(nome)
                .map(curso -> {
                    // Atualiza todos os campos
                    curso.setNome(cursoAtualizado.getNome());
                    curso.setGrau(cursoAtualizado.getGrau());
                    curso.setTurno(cursoAtualizado.getTurno());
                    curso.setCampus(cursoAtualizado.getCampus());
                    curso.setNivel(cursoAtualizado.getNivel());
                    Curso salvo = cursoRepository.save(curso);
                    return ResponseEntity.ok(salvo);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<Void> deletar(@PathVariable String nome) {
        return cursoRepository.findByNome(nome)
                .map(curso -> {
                    cursoRepository.delete(curso);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
    }
}