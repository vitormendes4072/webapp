package com.example.webapp.dto.response;

import com.example.webapp.entities.Vaga;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class VagaFeedResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private String localizacao;
    private BigDecimal salario;
    private String tipoContrato;
    private String nomeEmpresa;
    private LocalDateTime dataPublicacao;

    public VagaFeedResponse(Vaga vaga) {
        this.id = vaga.getId();
        this.titulo = vaga.getTitulo();
        this.descricao = vaga.getDescricao();
        this.localizacao = vaga.getLocalizacao();
        this.salario = vaga.getSalario();
        this.tipoContrato = vaga.getTipoContrato();
        this.nomeEmpresa = vaga.getEmpresa().getNomeEmpresa();
        this.dataPublicacao = vaga.getDataCriacao(); // ajuste aqui
    }
}
