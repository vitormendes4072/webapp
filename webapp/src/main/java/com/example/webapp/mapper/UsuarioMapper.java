package com.example.webapp.mapper;

import com.example.webapp.dto.request.AtualizarCooperadoRequest;
import com.example.webapp.dto.request.CooperadoRequest;
import com.example.webapp.dto.response.UsuarioResponse;
import com.example.webapp.entities.*;

public class UsuarioMapper {

    public static Usuario toUsuarioFromRequest(CooperadoRequest request, String senhaHash) {
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(request.getNomeCompleto());
        usuario.setEmail(request.getEmail());
        usuario.setSenhaHash(senhaHash);
        usuario.setCpf(request.getCpf());
        usuario.setRg(request.getRg());
        usuario.setDataNascimento(request.getDataNascimento());
        usuario.setGenero(request.getGenero());
        usuario.setTelefone(request.getTelefone());
        usuario.setTipoUsuario(TipoUsuario.COOPERADO);

        usuario.setPerfilProfissional(toPerfilProfissional(request));
        usuario.setEndereco(toEndereco(request));
        usuario.setDadosBancarios(toDadosBancarios(request));

        usuario.setDisponibilidade(request.getDisponibilidade());
        usuario.setPreferenciaTrabalho(request.getPreferenciaTrabalho());
        usuario.setAceitouTermos(request.getAceitouTermos());

        return usuario;
    }

    private static PerfilProfissional toPerfilProfissional(CooperadoRequest request) {
        PerfilProfissional perfil = new PerfilProfissional();
        perfil.setProfissao(request.getProfissao());
        perfil.setExperiencia(request.getExperiencia());
        perfil.setHabilidades(request.getHabilidades());
        perfil.setFormacao(request.getFormacao());
        perfil.setPortfolio(request.getPortfolio());
        return perfil;
    }

    private static Endereco toEndereco(CooperadoRequest request) {
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

    private static DadosBancarios toDadosBancarios(CooperadoRequest request) {
        DadosBancarios dados = new DadosBancarios();
        dados.setBanco(request.getBanco());
        dados.setTipoConta(request.getTipoConta());
        dados.setAgencia(request.getAgencia());
        dados.setNumeroConta(request.getNumeroConta());
        dados.setChavePix(request.getChavePix());
        return dados;
    }

    public static UsuarioResponse toUsuarioDTO(Usuario usuario) {
        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNomeCompleto());
        dto.setEmail(usuario.getEmail());
        dto.setTelefone(usuario.getTelefone());
        dto.setFotoPerfil(usuario.getFotoPerfil());

        // Dados do endereço
        if (usuario.getEndereco() != null) {
            Endereco endereco = usuario.getEndereco();
            dto.setCep(endereco.getCep());
            dto.setRua(endereco.getRua());
            dto.setNumero(endereco.getNumero());
            dto.setComplemento(endereco.getComplemento());
            dto.setBairro(endereco.getBairro());
            dto.setCidade(endereco.getCidade());
            dto.setEstado(endereco.getEstado());
        }

        // Dados bancários
        if (usuario.getDadosBancarios() != null) {
            DadosBancarios dados = usuario.getDadosBancarios();
            dto.setBanco(dados.getBanco());
            dto.setTipoConta(dados.getTipoConta());
            dto.setAgencia(dados.getAgencia());
            dto.setNumeroConta(dados.getNumeroConta());
            dto.setChavePix(dados.getChavePix());
        }

        // Perfil profissional
        if (usuario.getPerfilProfissional() != null) {
            PerfilProfissional perfil = usuario.getPerfilProfissional();
            dto.setProfissao(perfil.getProfissao());
            dto.setExperiencia(perfil.getExperiencia());
            dto.setHabilidades(perfil.getHabilidades());
            dto.setFormacao(perfil.getFormacao());
            dto.setPortfolio(perfil.getPortfolio());
        }


        dto.setTipoUsuario(usuario.getTipoUsuario().name());
        dto.setDisponibilidade(usuario.getDisponibilidade());
        dto.setPreferenciaTrabalho(usuario.getPreferenciaTrabalho());

        return dto;
    }

    public static void updateUsuarioFromRequest(Usuario usuario, AtualizarCooperadoRequest request) {
        // Dados Pessoais
        usuario.setNomeCompleto(request.getNomeCompleto());
        usuario.setEmail(request.getEmail());
        usuario.setTelefone(request.getTelefone());
        usuario.setGenero(request.getGenero());
        usuario.setDataNascimento(request.getDataNascimento());
        usuario.setTipoUsuario(TipoUsuario.valueOf(request.getTipoUsuario()));
        usuario.setDisponibilidade(request.getDisponibilidade());
        usuario.setPreferenciaTrabalho(request.getPreferenciaTrabalho());

        // Endereço
        Endereco endereco = usuario.getEndereco();
        if (endereco == null) {
            endereco = new Endereco();
            usuario.setEndereco(endereco);
        }
        endereco.setCep(request.getCep());
        endereco.setRua(request.getRua());
        endereco.setNumero(request.getNumero());
        endereco.setComplemento(request.getComplemento());
        endereco.setBairro(request.getBairro());
        endereco.setCidade(request.getCidade());
        endereco.setEstado(request.getEstado());

        // Perfil Profissional
        PerfilProfissional perfil = usuario.getPerfilProfissional();
        if (perfil == null) {
            perfil = new PerfilProfissional();
            usuario.setPerfilProfissional(perfil);
        }
        perfil.setProfissao(request.getProfissao());
        perfil.setExperiencia(request.getExperiencia());
        perfil.setHabilidades(request.getHabilidades());
        perfil.setFormacao(request.getFormacao());
        perfil.setPortfolio(request.getPortfolio());

        // Dados Bancários
        DadosBancarios dados = usuario.getDadosBancarios();
        if (dados == null) {
            dados = new DadosBancarios();
            usuario.setDadosBancarios(dados);
        }
        dados.setBanco(request.getBanco());
        dados.setTipoConta(request.getTipoConta());
        dados.setAgencia(request.getAgencia());
        dados.setNumeroConta(request.getNumeroConta());
        dados.setChavePix(request.getChavePix());
    }
}


