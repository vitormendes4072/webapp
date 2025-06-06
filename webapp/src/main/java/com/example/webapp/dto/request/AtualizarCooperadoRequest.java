package com.example.webapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AtualizarCooperadoRequest {

    // ========== DADOS PESSOAIS ==========

    @NotBlank(message = "Nome é obrigatório")
    @JsonProperty("nome_completo")
    private String nomeCompleto;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    private String senha;

    private String cpf;

    private String rg;

    @JsonProperty("data_nascimento")
    private LocalDate dataNascimento;

    private String genero;

    private String telefone;

    @JsonProperty("tipo_usuario")
    private String tipoUsuario;

    @JsonProperty("foto_perfil")
    private String fotoPerfil;

    // ========== PERFIL PROFISSIONAL ==========

    private String profissao;
    private String experiencia;
    private String habilidades;
    private String formacao;
    private String portfolio;

    // ========== ENDEREÇO ==========

    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    // ========== DADOS BANCÁRIOS ==========

    private String banco;

    @JsonProperty("tipo_conta")
    private String tipoConta;

    private String agencia;

    @JsonProperty("numero_conta")
    private String numeroConta;

    @JsonProperty("chavePix")
    private String chavePix;

    // ========== PREFERÊNCIAS ==========

    private String disponibilidade;

    @JsonProperty("preferencia_trabalho")
    private String preferenciaTrabalho;

    @JsonProperty("aceitou_termos")
    private Boolean aceitouTermos;
}
