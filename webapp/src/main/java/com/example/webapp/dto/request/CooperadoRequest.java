package com.example.webapp.dto.request;

import com.example.webapp.entities.TipoUsuario;
import com.example.webapp.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CooperadoRequest {

    // ========== DADOS PESSOAIS ==========

    @NotBlank(message = "Nome é obrigatório")
    @JsonProperty("nome_completo")
    private String nomeCompleto;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String senha;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
    private String cpf;

    @NotBlank(message = "RG é obrigatório")
    @Pattern(regexp = "^[A-Za-z0-9.\\-]{5,14}$", message = "RG inválido. Use letras, números, pontos ou hífen.")
    private String rg;

    @NotNull(message = "Data de nascimento é obrigatória")
    @JsonProperty("data_nascimento")
    private LocalDate dataNascimento;

    private String genero;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}", message = "Formato de telefone inválido.")
    private String telefone;

    @NotNull(message = "Tipo de usuário é obrigatório")
    @JsonProperty("tipo_usuario") // Mapeia para o nome na entidade Usuario
    private TipoUsuario tipoUsuario;

    @JsonProperty("foto_perfil")
    private String fotoPerfil;

    // ========== PERFIL PROFISSIONAL ==========

    @NotBlank(message = "Profissão é obrigatória")
    private String profissao;

    private String experiencia;

    private String habilidades;

    private String formacao;

    private String portfolio;

    // ========== ENDEREÇO ==========

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido. Ex: 12345-678")
    private String cep;

    @NotBlank(message = "Rua é obrigatória")
    private String rua;

    @NotBlank(message = "Número do endereço é obrigatório")
    private String numero;

    private String complemento;

    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    private String estado;

    // ========== DADOS BANCÁRIOS ==========

    @NotBlank(message = "Banco é obrigatório")
    private String banco;

    @NotBlank(message = "Tipo de conta é obrigatório")
    @JsonProperty("tipo_conta")
    private String tipoConta;

    @NotBlank(message = "Agência é obrigatória")
    @Pattern(regexp = "\\d{4}", message = "Agência deve conter exatamente 4 dígitos")
    private String agencia;

    @NotBlank(message = "Número da conta é obrigatório")
    @JsonProperty("numero_conta")
    private String numeroConta;

    @NotBlank(message = "Chave PIX é obrigatória")
    @Pattern(
            regexp = "^(\\d{11}|\\d{14}|[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}|\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}|[a-fA-F0-9]{32})$",
            message = "Chave PIX inválida. Use CPF, CNPJ, e-mail, telefone ou chave aleatória (32 caracteres)"
    )
    private String chavePix;

    // ========== PREFERÊNCIAS ==========

    @NotBlank(message = "Disponibilidade é obrigatória")
    private String disponibilidade;

    @NotBlank(message = "Preferência de trabalho é obrigatória")
    @JsonProperty("preferencia_trabalho")
    private String preferenciaTrabalho;

    @NotNull(message = "Aceite dos termos é obrigatório")
    @JsonProperty("aceitou_termos")
    private Boolean aceitouTermos;
}
