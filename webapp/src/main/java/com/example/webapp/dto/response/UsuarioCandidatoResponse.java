package com.example.webapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor

public class UsuarioCandidatoResponse {

    private Long usuarioId;
    private String nome;
    private String email;
    private String telefone;

}


