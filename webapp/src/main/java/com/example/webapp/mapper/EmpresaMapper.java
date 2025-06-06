package com.example.webapp.mapper;

import com.example.webapp.dto.request.AtualizarEmpresaRequest;
import com.example.webapp.dto.request.EmpresaRequest;
import com.example.webapp.dto.response.EmpresaResponse;
import com.example.webapp.entities.*;

public class EmpresaMapper {

    public static Empresa toEmpresaFromRequest(EmpresaRequest request, String senhaHash) {
        Empresa empresa = new Empresa();

        empresa.setNomeEmpresa(request.getNomeEmpresa());
        empresa.setCnpj(request.getCnpj());
        empresa.setRazaoSocial(request.getRazaoSocial());
        empresa.setNomeFantasia(request.getNomeFantasia());
        empresa.setSetorAtuacao(request.getSetorAtuacao());
        empresa.setDescricao(request.getDescricao());
        empresa.setEmail(request.getEmail());
        empresa.setTelefone(request.getTelefone());
        empresa.setSite(request.getSite());
        empresa.setSenhaHash(senhaHash);
        empresa.setNaturezaJuridica(request.getNaturezaJuridicaEnum());
        empresa.setTipoUsuario(TipoUsuario.EMPRESA);
        empresa.setAceitouTermos(request.getAceitouTermos());


        empresa.setEndereco(toEndereco(request));
        empresa.setDadosBancarios(toDadosBancarios(request));
        empresa.setResponsavel(toResponsavel(request));

        return empresa;
    }

    private static Endereco toEndereco(EmpresaRequest request) {
        Endereco endereco = new Endereco();
        endereco.setCep(request.getCep());
        endereco.setRua(request.getRua());
        endereco.setNumero(request.getNumero());
        endereco.setComplemento(request.getComplemento());
        endereco.setBairro(request.getBairro());
        endereco.setCidade(request.getCidade());
        endereco.setEstado(request.getEstado());
        return endereco;
    }

    private static DadosBancarios toDadosBancarios(EmpresaRequest request) {
        DadosBancarios dados = new DadosBancarios();
        dados.setBanco(request.getBanco());
        dados.setTipoConta(request.getTipoConta());
        dados.setAgencia(request.getAgencia());
        dados.setNumeroConta(request.getNumeroConta());
        dados.setChavePix(request.getChavePix());
        return dados;
    }

    private static Responsavel toResponsavel(EmpresaRequest request) {
        Responsavel responsavel = new Responsavel();
        responsavel.setNomeCeo(request.getNomeCeo());
        responsavel.setEmailCeo(request.getEmailCeo());
        responsavel.setTelefoneCeo(request.getTelefoneCeo());
        return responsavel;
    }

    public static EmpresaResponse toEmpresaDTO(Empresa empresa) {
        EmpresaResponse dto = new EmpresaResponse();

        dto.setId(empresa.getId());
        dto.setNomeEmpresa(empresa.getNomeEmpresa());
        dto.setCnpj(empresa.getCnpj());
        dto.setRazaoSocial(empresa.getRazaoSocial());
        dto.setNomeFantasia(empresa.getNomeFantasia());
        dto.setSetorAtuacao(empresa.getSetorAtuacao());
        dto.setDescricao(empresa.getDescricao());
        dto.setEmail(empresa.getEmail());
        dto.setTelefone(empresa.getTelefone());
        dto.setSite(empresa.getSite());
        dto.setNaturezaJuridica(empresa.getNaturezaJuridica().name());
        dto.setFotoPerfil(empresa.getFotoPerfil());

        if (empresa.getEndereco() != null) {
            Endereco endereco = empresa.getEndereco();
            dto.setCep(endereco.getCep());
            dto.setRua(endereco.getRua());
            dto.setNumero(endereco.getNumero());
            dto.setComplemento(endereco.getComplemento());
            dto.setBairro(endereco.getBairro());
            dto.setCidade(endereco.getCidade());
            dto.setEstado(endereco.getEstado());
        }

        if (empresa.getDadosBancarios() != null) {
            DadosBancarios dados = empresa.getDadosBancarios();
            dto.setBanco(dados.getBanco());
            dto.setTipoConta(dados.getTipoConta());
            dto.setAgencia(dados.getAgencia());
            dto.setNumeroConta(dados.getNumeroConta());
            dto.setChavePix(dados.getChavePix());
        }

        if (empresa.getResponsavel() != null) {
            Responsavel responsavel = empresa.getResponsavel();
            dto.setNomeCeo(responsavel.getNomeCeo());
            dto.setEmailCeo(responsavel.getEmailCeo());
            dto.setTelefoneCeo(responsavel.getTelefoneCeo());
        }

        dto.setTipoUsuario(empresa.getTipoUsuario().name());

        return dto;
    }

    public static void updateEmpresaFromRequest(Empresa empresa, AtualizarEmpresaRequest request) {
        empresa.setNomeEmpresa(request.getNomeEmpresa());
        empresa.setEmail(request.getEmail());
        empresa.setTelefone(request.getTelefone());
        empresa.setSite(request.getSite());
        empresa.setNaturezaJuridica(request.getNaturezaJuridicaEnum());
        empresa.setTipoUsuario(TipoUsuario.valueOf(request.getTipoUsuario().name()));
        empresa.setRazaoSocial((request.getRazaoSocial()));
        empresa.setSetorAtuacao(request.getSetorAtuacao());
        empresa.setDescricao(request.getDescricao());
        empresa.setNomeFantasia(request.getNomeFantasia());


        // Endereço
        Endereco endereco = empresa.getEndereco();
        if (endereco == null) {
            endereco = new Endereco();
            empresa.setEndereco(endereco);
        }
        endereco.setCep(request.getCep());
        endereco.setRua(request.getRua());
        endereco.setNumero(request.getNumero());
        endereco.setComplemento(request.getComplemento());
        endereco.setBairro(request.getBairro());
        endereco.setCidade(request.getCidade());
        endereco.setEstado(request.getEstado());

        // Dados Bancários
        DadosBancarios dados = empresa.getDadosBancarios();
        if (dados == null) {
            dados = new DadosBancarios();
            empresa.setDadosBancarios(dados);
        }
        dados.setBanco(request.getBanco());
        dados.setTipoConta(request.getTipoConta());
        dados.setAgencia(request.getAgencia());
        dados.setNumeroConta(request.getNumeroConta());
        dados.setChavePix(request.getChavePix());

        // Responsável
        Responsavel responsavel = empresa.getResponsavel();
        if (responsavel == null) {
            responsavel = new Responsavel();
            empresa.setResponsavel(responsavel);
        }
        responsavel.setNomeCeo(request.getNomeCeo());
        responsavel.setEmailCeo(request.getEmailCeo());
        responsavel.setTelefoneCeo(request.getTelefoneCeo());
    }

}

