package com.example.cruded.controller;

import com.example.cruded.model.Usuario;
import com.example.cruded.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{cpf}")
    public Usuario buscarPorCpf(@PathVariable Long cpf) {
        return usuarioRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @PutMapping("/{cpf}")
    public Usuario atualizar(@PathVariable Long cpf, @RequestBody Usuario novosDados) {
        Usuario usuario = usuarioRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setNome(novosDados.getNome());
        usuario.setDataNascimento(novosDados.getDataNascimento());
        usuario.setLogin(novosDados.getLogin());
        usuario.setSenha(novosDados.getSenha());
        return usuarioRepository.save(usuario);
    }

    @DeleteMapping("/{cpf}")
    public String deletar(@PathVariable Long cpf) {
        usuarioRepository.deleteById(cpf);
        return "Usuário eliminado com sucesso!";
    }
}