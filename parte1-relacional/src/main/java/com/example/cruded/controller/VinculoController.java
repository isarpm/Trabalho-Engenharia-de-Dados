package com.example.cruded.controller;

import com.example.cruded.model.Vinculo;
import com.example.cruded.repository.VinculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vinculos")
public class VinculoController {

    @Autowired
    private VinculoRepository vinculoRepository;

    @PostMapping
    public Vinculo criar(@RequestBody Vinculo vinculo) {
        return vinculoRepository.save(vinculo);
    }

    @GetMapping
    public List<Vinculo> listarTodos() {
        return vinculoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Vinculo buscarPorId(@PathVariable Integer id) {
        return vinculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vínculo não encontrado"));
    }

    @PutMapping("/{id}")
    public Vinculo atualizar(@PathVariable Integer id, @RequestBody Vinculo novosDados) {
        Vinculo vinculo = vinculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vínculo não encontrado"));
        vinculo.setEstudante(novosDados.getEstudante());
        vinculo.setCurso(novosDados.getCurso());
        vinculo.setDataEntrada(novosDados.getDataEntrada());
        vinculo.setStatus(novosDados.getStatus());
        vinculo.setDataSaida(novosDados.getDataSaida());
        return vinculoRepository.save(vinculo);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Integer id) {
        vinculoRepository.deleteById(id);
        return "Vínculo eliminado com sucesso!";
    }
}