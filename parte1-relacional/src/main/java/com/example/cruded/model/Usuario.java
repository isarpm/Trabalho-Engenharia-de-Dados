package com.example.cruded.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "usuario", schema = "universidade")
@Data
public class Usuario {

    @Id
    @Column(name = "cpf")
    private Long cpf;

    @Column(nullable = false)
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private String login;
    private String senha;
}