package com.example.webapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DadosBancariosRequest {

    @NotBlank(message = "Banco é obrigatório")
    private String banco;

    @NotBlank(message = "Tipo de conta é obrigatório")
    private String tipoConta;

    @NotBlank(message = "Agência é obrigatória")
    @Pattern(regexp = "\\d{4}", message = "Agência deve conter exatamente 4 dígitos")
    private String agencia;

    @NotBlank(message = "Número da conta é obrigatório")
    private String numeroConta;

    @NotBlank(message = "Chave PIX é obrigatória")
    @Pattern(
            regexp = "^(\\d{11}|\\d{14}|[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}|\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}|[a-fA-F0-9]{32})$",
            message = "Chave PIX inválida. Use CPF, CNPJ, e-mail, telefone ou chave aleatória (32 caracteres)"
    )
    private String chavePix;
}
