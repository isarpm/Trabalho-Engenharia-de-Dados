package com.example.cruded.controller;

import com.example.cruded.model.Vinculo;
import com.example.cruded.model.Estudante;
import com.example.cruded.model.Curso;
import com.example.cruded.repository.VinculoRepository;
import com.example.cruded.repository.EstudanteRepository;
import com.example.cruded.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/vinculos")
public class VinculoController {

    @Autowired
    private VinculoRepository vinculoRepository;
    
    @Autowired
    private EstudanteRepository estudanteRepository;
    
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<Vinculo> criar(@RequestBody Vinculo vinculo) {
        // Validar se estudante existe
        if (vinculo.getEstudante() != null && vinculo.getEstudante().getMatricula() != null) {
            Estudante estudante = estudanteRepository.findByMatricula(vinculo.getEstudante().getMatricula())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não encontrado"));
            vinculo.setEstudante(estudante);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estudante é obrigatório");
        }
        
        // Validar se curso existe
        if (vinculo.getCurso() != null && vinculo.getCurso().getNome() != null) {
            Curso curso = cursoRepository.findByNome(vinculo.getCurso().getNome())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
            vinculo.setCurso(curso);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Curso é obrigatório");
        }
        
        // Se status for null, definir como "Ativo" por padrão
        if (vinculo.getStatus() == null) {
            vinculo.setStatus("Ativo");
        }
        
        Vinculo saved = vinculoRepository.save(vinculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<Vinculo> listarTodos() {
        return vinculoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vinculo> buscarPorId(@PathVariable String id) {
        return vinculoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vínculo não encontrado"));
    }
    
    @GetMapping("/estudante/{matricula}")
    public ResponseEntity<List<Vinculo>> buscarPorEstudante(@PathVariable String matricula) {
        return estudanteRepository.findByMatricula(matricula)
                .map(estudante -> ResponseEntity.ok(vinculoRepository.findByEstudanteId(estudante.getId())))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não encontrado"));
    }
    
    @GetMapping("/curso/{nome}")
    public ResponseEntity<List<Vinculo>> buscarPorCurso(@PathVariable String nome) {
        return cursoRepository.findByNome(nome)
                .map(curso -> ResponseEntity.ok(vinculoRepository.findByCursoId(curso.getId())))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
    }
    
    @GetMapping("/status/{status}")
    public List<Vinculo> buscarPorStatus(@PathVariable String status) {
        return vinculoRepository.findByStatus(status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vinculo> atualizar(@PathVariable String id, @RequestBody Vinculo novosDados) {
        return vinculoRepository.findById(id)
                .map(vinculo -> {
                    // Validar e atualizar estudante
                    if (novosDados.getEstudante() != null && novosDados.getEstudante().getMatricula() != null) {
                        Estudante estudante = estudanteRepository.findByMatricula(novosDados.getEstudante().getMatricula())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não encontrado"));
                        vinculo.setEstudante(estudante);
                    }
                    
                    // Validar e atualizar curso
                    if (novosDados.getCurso() != null && novosDados.getCurso().getNome() != null) {
                        Curso curso = cursoRepository.findByNome(novosDados.getCurso().getNome())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
                        vinculo.setCurso(curso);
                    }
                    
                    vinculo.setDataEntrada(novosDados.getDataEntrada());
                    vinculo.setStatus(novosDados.getStatus());
                    vinculo.setDataSaida(novosDados.getDataSaida());
                    
                    Vinculo salvo = vinculoRepository.save(vinculo);
                    return ResponseEntity.ok(salvo);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vínculo não encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        return vinculoRepository.findById(id)
                .map(vinculo -> {
                    vinculoRepository.delete(vinculo);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vínculo não encontrado"));
    }
}