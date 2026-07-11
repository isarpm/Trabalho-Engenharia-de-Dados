package com.example.cruded.controller;

import com.example.cruded.model.Estudante;
import com.example.cruded.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteRepository estudanteRepository;

    @PostMapping
    public Estudante criar(@RequestBody Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

    @GetMapping
    public List<Estudante> listarTodos() {
        return estudanteRepository.findAll();
    }

    @GetMapping("/{matricula}")
    public Estudante buscarPorMatricula(@PathVariable String matricula) {
        return estudanteRepository.findById(matricula)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado"));
    }

    @PutMapping("/{matricula}")
    public Estudante atualizar(@PathVariable String matricula, @RequestBody Estudante novosDados) {
        Estudante estudante = estudanteRepository.findById(matricula)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado"));
        estudante.setMc(novosDados.getMc());
        estudante.setAnoIngresso(novosDados.getAnoIngresso());
       //  Novos dados 
        if (novosDados.getUsuario() != null) {
            estudante.setUsuario(novosDados.getUsuario());
        }
        return estudanteRepository.save(estudante);
    }

    @DeleteMapping("/{matricula}")
    public String deletar(@PathVariable String matricula) {
        estudanteRepository.deleteById(matricula);
        return "Estudante eliminado com sucesso!";
    }
}
