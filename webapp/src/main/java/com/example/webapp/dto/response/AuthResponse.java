package com.example.webapp.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private String token;
    private String nome;
    private String email;
    private String tipoUsuario;

    public AuthResponse(String token, String nome, String email, String tipoUsuario){
        this.token = token;
        this.nome = nome;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }
}

