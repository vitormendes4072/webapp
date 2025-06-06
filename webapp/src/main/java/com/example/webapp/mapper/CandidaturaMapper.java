package com.example.webapp.mapper;

import com.example.webapp.dto.response.UsuarioCandidatoResponse;
import com.example.webapp.entities.Candidatura;
import com.example.webapp.entities.Usuario;

public class CandidaturaMapper {

    public static UsuarioCandidatoResponse toCooperadoCandidatoResponse(Candidatura candidatura) {
        Usuario usuario = candidatura.getUsuario();
        return new UsuarioCandidatoResponse(
                usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getEmail(),
                usuario.getTelefone()
        );
    }
}

