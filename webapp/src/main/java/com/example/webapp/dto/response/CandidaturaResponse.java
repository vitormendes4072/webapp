package com.example.webapp.dto.response;

import com.example.webapp.entities.Candidatura;
import com.example.webapp.entities.Empresa;
import lombok.Getter;

@Getter
public class CandidaturaResponse {
    private Long vagaId;
    private String tituloVaga;
    private String descricaoVaga;
    private String statusCandidatura;
    private String empresa;

    public CandidaturaResponse(Candidatura candidatura, Empresa empresa) {
        this.vagaId = candidatura.getVaga().getId();
        this.tituloVaga = candidatura.getVaga().getTitulo();
        this.descricaoVaga = candidatura.getVaga().getDescricao();
        this.statusCandidatura = candidatura.getStatus() != null
                ? candidatura.getStatus().name()
                : "PENDENTE";
        this.empresa = empresa.getNomeEmpresa();
    }

}

