package com.example.webapp.dto.response;

import com.example.webapp.entities.Vaga;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class VagaResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private String localizacao;
    private BigDecimal salario;
    private String requisitos;
    private String tipoContrato;
    private String empresa;

    public VagaResponse(Vaga vaga) {
        this.id = vaga.getId();
        this.titulo = vaga.getTitulo();
        this.descricao = vaga.getDescricao();
        this.localizacao = vaga.getLocalizacao();
        this.salario = vaga.getSalario();
        this.tipoContrato = vaga.getTipoContrato();
        this.requisitos = vaga.getRequisitos();
        this.empresa = (vaga.getEmpresa() != null) ? vaga.getEmpresa().getNomeEmpresa() : null;
    }
}