package com.example.cruded.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "curso", schema = "universidade")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcurso") // Mapeia exatamente para o "idCurso" do banco
    private Long idCurso;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "grau", columnDefinition = "universidade.tipo_grau")
    private String grau;

    @Column(name = "turno", nullable = false, columnDefinition = "universidade.tipo_turno")
    private String turno;

    @Column(name = "campus")
    private String campus;

    @Column(name = "nivel", columnDefinition = "universidade.tipo_nivel")
    private String nivel;
}