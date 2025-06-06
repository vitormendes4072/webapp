package com.example.webapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter @Setter
@Entity
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private LocalDateTime expiration;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Empresa empresa;

    public Autenticavel getAutenticavel(){
        return usuario != null ? usuario : empresa;
    }
}

