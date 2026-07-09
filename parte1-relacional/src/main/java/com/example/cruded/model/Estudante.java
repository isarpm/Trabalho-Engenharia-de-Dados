package com.example.cruded.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "estudante", schema = "universidade")
@Data
public class Estudante {

    @Id
    @Column(name = "mat_estudante", length = 7)
    private String matEstudante;

    @OneToOne
    @JoinColumn(name = "cpf", referencedColumnName = "cpf")
    private Usuario usuario;

    private Double mc;

    @Column(name = "ano_ingresso")
    private Integer anoIngresso;
}