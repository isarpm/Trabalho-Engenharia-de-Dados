package com.example.cruded.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "vinculo", schema = "universidade")
@Data
public class Vinculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvinculo")
    private Integer idVinculo;

    @ManyToOne
    @JoinColumn(name = "mat_estudante", referencedColumnName = "mat_estudante")
    private Estudante estudante;

    @ManyToOne
    @JoinColumn(name = "curso", referencedColumnName = "idcurso")
    private Curso curso;

    @Column(name = "data_entrada")
    private LocalDate dataEntrada;

    private String status; // Mapeia o ENUM status_estudante como String

    @Column(name = "data_saida")
    private LocalDate dataSaida;
}