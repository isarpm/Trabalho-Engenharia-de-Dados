package com.example.cruded.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Document(collection = "estudantes")
public class Estudante {
    
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String matricula;  // Matrícula única (antiga chave primária)
    
    private String mc;  // Código do curso (ex: "ES001", "CC001")
    private Integer anoIngresso;
    
    @DBRef  // Referência para o documento Usuario (mantém integridade referencial)
    private Usuario usuario;
    
    // Dados de auditoria
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    
    // Construtor padrão
    public Estudante() {}
    
    // Construtor com parâmetros
    public Estudante(String matricula, String mc, Integer anoIngresso, Usuario usuario) {
        this.matricula = matricula;
        this.mc = mc;
        this.anoIngresso = anoIngresso;
        this.usuario = usuario;
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    // Getters e Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public String getMc() {
        return mc;
    }
    
    public void setMc(String mc) {
        this.mc = mc;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public Integer getAnoIngresso() {
        return anoIngresso;
    }
    
    public void setAnoIngresso(Integer anoIngresso) {
        this.anoIngresso = anoIngresso;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }
    
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
    
    @Override
    public String toString() {
        return "Estudante{" +
                "id='" + id + '\'' +
                ", matricula='" + matricula + '\'' +
                ", mc='" + mc + '\'' +
                ", anoIngresso=" + anoIngresso +
                ", usuario=" + (usuario != null ? usuario.getNome() : "null") +
                '}';
    }
}