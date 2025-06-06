package com.example.webapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class AtualizarSenhaRequest {

    @NotBlank
    private String senhaAtual;

    @NotBlank
    @Size(min = 8, message = "A nova senha deve ter no mínimo 8 caracteres.")
    private String novaSenha;

    @NotBlank
    private String confirmacaoSenha;
}

