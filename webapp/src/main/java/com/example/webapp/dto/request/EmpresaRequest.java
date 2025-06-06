package com.example.webapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.webapp.entities.Empresa;
import com.example.webapp.entities.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Arrays;
import java.util.stream.Collectors;

@Data
public class EmpresaRequest {

    // ========== EMPRESA ==========

    @NotBlank
    @JsonProperty("nome_empresa")
    private String nomeEmpresa;

    @NotBlank
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "CNPJ inválido. Formato esperado: 00.000.000/0000-00")
    private String cnpj;

    @NotBlank
    @JsonProperty("razao_social")
    private String razaoSocial;

    @NotBlank
    @JsonProperty("nome_fantasia")
    private String nomeFantasia;

    @NotBlank
    @JsonProperty("setor_atuacao")
    private String setorAtuacao;

    private String descricao;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(
            regexp = "^\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}$",
            message = "Telefone inválido. Use o formato (99) 99999-9999"
    )
    private String telefone;

    @Pattern(
            regexp = "^(https?://)?(www\\.)?[a-zA-Z0-9-]+(\\.[a-z]{2,}){1,}(/\\S*)?$",
            message = "URL do site inválida"
    )
    private String site;

    @NotNull(message = "Natureza jurídica é obrigatória")
    @JsonProperty("natureza_juridica")
    private String naturezaJuridica;

    @NotBlank(message = "Senha é obrigatória")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "A senha deve conter no mínimo 8 caracteres, incluindo letra maiúscula, minúscula, número e caractere especial"
    )
    private String senha;

    @NotNull(message = "Tipo de usuário é obrigatório")
    @JsonProperty("tipo_usuario")
    private TipoUsuario tipoUsuario;

    @NotNull(message = "Aceite dos termos é obrigatório")
    @JsonProperty("aceitou_termos")
    private Boolean aceitouTermos;

    @JsonProperty("foto_perfil")
    private String fotoPerfil;

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

    // ========== RESPONSÁVEL ==========

    @NotBlank(message = "Nome do CEO é obrigatório")
    @JsonProperty("nome_ceo")
    private String nomeCeo;

    @NotBlank
    @Email(message = "E-mail do CEO inválido")
    @JsonProperty("email_ceo")
    private String emailCeo;

    @NotBlank
    @Pattern(
            regexp = "^\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}$",
            message = "Telefone inválido. Use o formato (99) 99999-9999"
    )
    @JsonProperty("telefone_ceo")
    private String telefoneCeo;

    // ========== UTILITÁRIO ==========

    public Empresa.NaturezaJuridica getNaturezaJuridicaEnum() {
        if (this.naturezaJuridica == null) {
            throw new IllegalArgumentException("Natureza jurídica não pode ser nula");
        }

        String input = this.naturezaJuridica.trim();

        return Arrays.stream(Empresa.NaturezaJuridica.values())
                .filter(e -> e.getLabel().equals(input))
                .findFirst()
                .orElseThrow(() -> {
                    String valoresPermitidos = Arrays.stream(Empresa.NaturezaJuridica.values())
                            .map(Empresa.NaturezaJuridica::getLabel)
                            .collect(Collectors.joining(", "));
                    return new IllegalArgumentException(
                            String.format("Natureza jurídica '%s' inválida. Valores permitidos: %s",
                                    input, valoresPermitidos)
                    );
                });
    }
}
