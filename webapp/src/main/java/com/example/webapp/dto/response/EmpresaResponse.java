package com.example.webapp.dto.response;

import com.example.webapp.entities.Empresa;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmpresaResponse {

    private Long id;

    // Dados da empresa
    private String nomeEmpresa;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String setorAtuacao;
    private String descricao;
    private String email;
    private String telefone;
    private String site;
    private String naturezaJuridica;
    private String tipoUsuario;

    // Endereço
    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    // Dados bancários
    private String banco;
    private String tipoConta;
    private String agencia;
    private String numeroConta;
    private String chavePix;

    // Responsável
    private String nomeCeo;
    private String emailCeo;
    private String telefoneCeo;
    private String fotoPerfil;

    public EmpresaResponse() {}

    public EmpresaResponse(Long id, String nomeEmpresa, String cnpj, String razaoSocial, String nomeFantasia,
                           String setorAtuacao, String descricao, String email, String telefone, String site,
                           String naturezaJuridica, String tipoUsuario, String fotoPerfil,
                           String cep, String rua, String numero, String complemento, String bairro,
                           String cidade, String estado,
                           String banco, String tipoConta, String agencia, String numeroConta, String chavePix,
                           String nomeCeo, String emailCeo, String telefoneCeo) {
        this.id = id;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.setorAtuacao = setorAtuacao;
        this.descricao = descricao;
        this.email = email;
        this.telefone = telefone;
        this.site = site;
        this.naturezaJuridica = naturezaJuridica;
        this.tipoUsuario = tipoUsuario;
        this.fotoPerfil = fotoPerfil;

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

        this.nomeCeo = nomeCeo;
        this.emailCeo = emailCeo;
        this.telefoneCeo = telefoneCeo;
    }

    public EmpresaResponse(Empresa empresa) {
        this.id = empresa.getId();
        this.nomeEmpresa = empresa.getNomeEmpresa();
        this.cnpj = empresa.getCnpj();
        this.razaoSocial = empresa.getRazaoSocial();
        this.nomeFantasia = empresa.getNomeFantasia();
        this.setorAtuacao = empresa.getSetorAtuacao();
        this.descricao = empresa.getDescricao();
        this.email = empresa.getEmail();
        this.telefone = empresa.getTelefone();
        this.site = empresa.getSite();
        this.naturezaJuridica = String.valueOf(empresa.getNaturezaJuridica());
        this.tipoUsuario = String.valueOf(empresa.getTipoUsuario());
        this.fotoPerfil = empresa.getFotoPerfil();

        if (empresa.getEndereco() != null) {
            this.cep = empresa.getEndereco().getCep();
            this.rua = empresa.getEndereco().getRua();
            this.numero = empresa.getEndereco().getNumero();
            this.complemento = empresa.getEndereco().getComplemento();
            this.bairro = empresa.getEndereco().getBairro();
            this.cidade = empresa.getEndereco().getCidade();
            this.estado = empresa.getEndereco().getEstado();
        }

        if (empresa.getDadosBancarios() != null) {
            this.banco = empresa.getDadosBancarios().getBanco();
            this.tipoConta = empresa.getDadosBancarios().getTipoConta();
            this.agencia = empresa.getDadosBancarios().getAgencia();
            this.numeroConta = empresa.getDadosBancarios().getNumeroConta();
            this.chavePix = empresa.getDadosBancarios().getChavePix();
        }

        if (empresa.getResponsavel() != null) {
            this.nomeCeo = empresa.getResponsavel().getNomeCeo();
            this.emailCeo = empresa.getResponsavel().getEmailCeo();
            this.telefoneCeo = empresa.getResponsavel().getTelefoneCeo();
        }

    }

}

