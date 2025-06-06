package com.example.webapp.dto.response;

import com.example.webapp.entities.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String tipoUsuario;
    private String disponibilidade;
    private String preferenciaTrabalho;

    // Dados Endereco
    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    // Dados Bancarios
    private String banco;
    private String tipoConta;
    private String agencia;
    private String numeroConta;
    private String chavePix;

    // Perfil Profissional
    private String profissao;
    private String experiencia;
    private String habilidades;
    private String formacao;
    private String portfolio;
    private String fotoPerfil;

    public UsuarioResponse() {}

    public UsuarioResponse(Long id, String nome, String email, String telefone, String tipoUsuario, String disponibilidade, String preferenciaTrabalho,
                           String cep, String rua, String numero, String complemento, String bairro, String cidade, String estado,
                           String banco, String tipoConta, String agencia, String numeroConta, String chavePix,
                           String profissao, String experiencia, String habilidades, String formacao, String portfolio, String fotoPerfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.tipoUsuario = tipoUsuario;
        this.disponibilidade = disponibilidade;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.banco = banco;
        this.tipoConta = tipoConta;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.chavePix = chavePix;
        this.profissao = profissao;
        this.experiencia = experiencia;
        this.habilidades = habilidades;
        this.formacao = formacao;
        this.portfolio = portfolio;
        this.preferenciaTrabalho = preferenciaTrabalho;
        this.fotoPerfil = fotoPerfil;
    }

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNomeCompleto();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();
        this.tipoUsuario = usuario.getTipoUsuario() != null ? usuario.getTipoUsuario().toString() : null;
        this.disponibilidade = usuario.getDisponibilidade();
        this.preferenciaTrabalho = usuario.getPreferenciaTrabalho();
        this.fotoPerfil = usuario.getFotoPerfil();

        if (usuario.getEndereco() != null) {
            this.cep = usuario.getEndereco().getCep();
            this.rua = usuario.getEndereco().getRua();
            this.numero = usuario.getEndereco().getNumero();
            this.complemento = usuario.getEndereco().getComplemento();
            this.bairro = usuario.getEndereco().getBairro();
            this.cidade = usuario.getEndereco().getCidade();
            this.estado = usuario.getEndereco().getEstado();
        }

        if (usuario.getDadosBancarios() != null) {
            this.banco = usuario.getDadosBancarios().getBanco();
            this.tipoConta = usuario.getDadosBancarios().getTipoConta();
            this.agencia = usuario.getDadosBancarios().getAgencia();
            this.numeroConta = usuario.getDadosBancarios().getNumeroConta();
            this.chavePix = usuario.getDadosBancarios().getChavePix();
        }

        if (usuario.getPerfilProfissional() != null) {
            this.profissao = usuario.getPerfilProfissional().getProfissao();
            this.experiencia = usuario.getPerfilProfissional().getExperiencia();
            this.habilidades = usuario.getPerfilProfissional().getHabilidades();
            this.formacao = usuario.getPerfilProfissional().getFormacao();
            this.portfolio = usuario.getPerfilProfissional().getPortfolio();
        }
    }
}
