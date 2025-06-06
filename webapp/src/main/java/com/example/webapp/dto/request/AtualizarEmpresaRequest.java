package com.example.webapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.webapp.entities.Empresa;
import com.example.webapp.entities.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Arrays;
import java.util.stream.Collectors;

@Data
public class AtualizarEmpresaRequest {

    @JsonProperty("nome_empresa")
    private String nomeEmpresa;

    private String cnpj;

    @JsonProperty("razao_social")
    private String razaoSocial;

    @JsonProperty("nome_fantasia")
    private String nomeFantasia;

    @JsonProperty("setor_atuacao")
    private String setorAtuacao;

    private String descricao;

    @Email
    private String email;

    @Pattern(regexp = "^\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}$", message = "Telefone inválido. Use o formato (99) 99999-9999")
    private String telefone;

    @Pattern(regexp = "^(https?://)?(www\\.)?[a-zA-Z0-9-]+(\\.[a-z]{2,}){1,}(/\\S*)?$", message = "URL do site inválida")
    private String site;

    @JsonProperty("natureza_juridica")
    private String naturezaJuridica;

    private String senha;

    @JsonProperty("tipo_usuario")
    private TipoUsuario tipoUsuario;

    @JsonProperty("aceitou_termos")
    private Boolean aceitouTermos;

    @JsonProperty("foto_perfil")
    private String fotoPerfil;

    // ===== Endereço =====
    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    // ===== Dados Bancários =====
    private String banco;

    @JsonProperty("tipo_conta")
    private String tipoConta;

    @Pattern(regexp = "\\d{4}", message = "Agência deve conter exatamente 4 dígitos")
    private String agencia;

    @JsonProperty("numero_conta")
    private String numeroConta;

    @Pattern(
            regexp = "^(\\d{11}|\\d{14}|[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}|\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}|[a-fA-F0-9]{32})$",
            message = "Chave PIX inválida. Use CPF, CNPJ, e-mail, telefone ou chave aleatória (32 caracteres)"
    )
    private String chavePix;

    // ===== Responsável =====
    @JsonProperty("nome_ceo")
    private String nomeCeo;

    @Email(message = "E-mail do CEO inválido")
    @JsonProperty("email_ceo")
    private String emailCeo;

    @Pattern(regexp = "^\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}$", message = "Telefone inválido. Use o formato (99) 99999-9999")
    @JsonProperty("telefone_ceo")
    private String telefoneCeo;

    // ===== Método utilitário para conversão =====
    public Empresa.NaturezaJuridica getNaturezaJuridicaEnum() {
        if (this.naturezaJuridica == null) return null;

        String input = this.naturezaJuridica.trim();
        return Arrays.stream(Empresa.NaturezaJuridica.values())
                .filter(e -> e.getLabel().equals(input))
                .findFirst()
                .orElseThrow(() -> {
                    String valoresPermitidos = Arrays.stream(Empresa.NaturezaJuridica.values())
                            .map(Empresa.NaturezaJuridica::getLabel)
                            .collect(Collectors.joining(", "));
                    throw new IllegalArgumentException(
                            String.format("Natureza jurídica '%s' inválida. Valores permitidos: %s",
                                    input, valoresPermitidos)
                    );
                });
    }
}
