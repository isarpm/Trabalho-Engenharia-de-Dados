package com.example.cruded.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "vinculos")
public class Vinculo {
    
    @Id
    private String id;
    
    @DBRef  // Referência para Estudante
    private Estudante estudante;
    
    @DBRef  // Referência para Curso
    private Curso curso;
    
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private String status;  // "Ativo", "Concluído", "Trancado", "Desistente", "Jubilado"
    
    // Dados adicionais
    private Double mediaFinal;
    private Integer semestreAtual;
    
    // Dados de auditoria
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    
    // Construtor padrão
    public Vinculo() {}
    
    // Construtor com parâmetros
    public Vinculo(Estudante estudante, Curso curso, LocalDate dataEntrada, String status) {
        this.estudante = estudante;
        this.curso = curso;
        this.dataEntrada = dataEntrada;
        this.status = status;
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
    
    public Estudante getEstudante() {
        return estudante;
    }
    
    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public Curso getCurso() {
        return curso;
    }
    
    public void setCurso(Curso curso) {
        this.curso = curso;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public LocalDate getDataEntrada() {
        return dataEntrada;
    }
    
    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public LocalDate getDataSaida() {
        return dataSaida;
    }
    
    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public Double getMediaFinal() {
        return mediaFinal;
    }
    
    public void setMediaFinal(Double mediaFinal) {
        this.mediaFinal = mediaFinal;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public Integer getSemestreAtual() {
        return semestreAtual;
    }
    
    public void setSemestreAtual(Integer semestreAtual) {
        this.semestreAtual = semestreAtual;
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
        return "Vinculo{" +
                "id='" + id + '\'' +
                ", estudante=" + (estudante != null ? estudante.getMatricula() : "null") +
                ", curso=" + (curso != null ? curso.getNome() : "null") +
                ", status='" + status + '\'' +
                '}';
    }
}