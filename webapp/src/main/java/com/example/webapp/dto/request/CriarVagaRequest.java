package com.example.webapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter @Setter
public class CriarVagaRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotBlank
    private String localizacao;

    @NotNull
    private BigDecimal salario;

    @NotBlank
    private String tipoContrato;

    @NotBlank
    private String requisitos;

}
