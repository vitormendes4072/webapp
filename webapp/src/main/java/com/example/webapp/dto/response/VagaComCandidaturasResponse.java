package com.example.webapp.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VagaComCandidaturasResponse {
    private Long vagaId;
    private String tituloVaga;
    private List<UsuarioCandidatoResponse> candidatos;

    public VagaComCandidaturasResponse(Long vagaId, String tituloVaga, List<UsuarioCandidatoResponse> candidatos) {
        this.vagaId = vagaId;
        this.tituloVaga = tituloVaga;
        this.candidatos = candidatos;
    }
}
