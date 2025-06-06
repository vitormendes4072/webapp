package com.example.webapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "responsavel")
@Data
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_ceo", nullable = false)
    private String nomeCeo;

    @Column(name = "email_ceo", nullable = false)
    private String emailCeo;

    @Column(name = "telefone_ceo", nullable = false)
    private String telefoneCeo;

}
