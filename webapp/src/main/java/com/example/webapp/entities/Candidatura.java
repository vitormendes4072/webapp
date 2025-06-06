package com.example.webapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;

    private LocalDateTime dataCandidatura;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusCandidatura status = StatusCandidatura.PENDENTE;

    public enum StatusCandidatura {
        PENDENTE, APROVADO, RECUSADO
    }

    // Construtores, getters e setters
    public Candidatura() {}

    public Candidatura(Usuario usuario, Vaga vaga) {
        this.usuario = usuario;
        this.vaga = vaga;
        this.dataCandidatura = LocalDateTime.now();
    }
}


