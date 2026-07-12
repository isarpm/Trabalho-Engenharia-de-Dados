package com.example.cruded.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Document(collection = "cursos")
public class Curso {
    
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String nome;  // Nome do curso como campo único
    
    private String grau;      // Bacharelado, Licenciatura, Tecnólogo
    private String turno;     // Matutino, Vespertino, Noturno, Integral
    private String campus;    // Sede, Centro, etc.
    private String nivel;     // Graduação, Pós-graduação, Mestrado, Doutorado
    
    // Carga horária total do curso (em horas)
    private Integer cargaHorariaTotal;
    
    // Duração em semestres
    private Integer duracaoSemestres;
    
    // Dados de auditoria
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    
    // Construtor padrão
    public Curso() {}
    
    // Construtor com parâmetros
    public Curso(String nome, String grau, String turno, String campus, String nivel) {
        this.nome = nome;
        this.grau = grau;
        this.turno = turno;
        this.campus = campus;
        this.nivel = nivel;
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    // Construtor completo
    public Curso(String nome, String grau, String turno, String campus, String nivel, 
                 Integer cargaHorariaTotal, Integer duracaoSemestres) {
        this.nome = nome;
        this.grau = grau;
        this.turno = turno;
        this.campus = campus;
        this.nivel = nivel;
        this.cargaHorariaTotal = cargaHorariaTotal;
        this.duracaoSemestres = duracaoSemestres;
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
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public String getGrau() {
        return grau;
    }
    
    public void setGrau(String grau) {
        this.grau = grau;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public String getTurno() {
        return turno;
    }
    
    public void setTurno(String turno) {
        this.turno = turno;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public String getCampus() {
        return campus;
    }
    
    public void setCampus(String campus) {
        this.campus = campus;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public String getNivel() {
        return nivel;
    }
    
    public void setNivel(String nivel) {
        this.nivel = nivel;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public Integer getCargaHorariaTotal() {
        return cargaHorariaTotal;
    }
    
    public void setCargaHorariaTotal(Integer cargaHorariaTotal) {
        this.cargaHorariaTotal = cargaHorariaTotal;
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public Integer getDuracaoSemestres() {
        return duracaoSemestres;
    }
    
    public void setDuracaoSemestres(Integer duracaoSemestres) {
        this.duracaoSemestres = duracaoSemestres;
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
        return "Curso{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", grau='" + grau + '\'' +
                ", campus='" + campus + '\'' +
                '}';
    }
}