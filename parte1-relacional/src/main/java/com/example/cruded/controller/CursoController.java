package com.example.cruded.controller;

import com.example.cruded.model.Curso;
import com.example.cruded.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @PostMapping
    public Curso criar(@RequestBody Curso curso) {
        return repository.save(curso);
    }

    @GetMapping
    public List<Curso> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(curso -> ResponseEntity.ok().body(curso))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable Long id, @RequestBody Curso cursoAtualizado) {
        return repository.findById(id)
                .map(curso -> {
                    curso.setNome(cursoAtualizado.getNome());
                    curso.setGrau(cursoAtualizado.getGrau());
                    curso.setTurno(cursoAtualizado.getTurno());
                    curso.setCampus(cursoAtualizado.getCampus());
                    curso.setNivel(cursoAtualizado.getNivel());
                    Curso salvo = repository.save(curso);
                    return ResponseEntity.ok().body(salvo);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return repository.findById(id)
                .map(curso -> {
                    repository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}