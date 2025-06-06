package com.example.webapp.services;


import com.example.webapp.dto.request.AtualizarCooperadoRequest;
import com.example.webapp.dto.request.CooperadoRequest;
import com.example.webapp.dto.response.UsuarioResponse;
import com.example.webapp.entities.Usuario;
import com.example.webapp.mapper.UsuarioMapper;
import com.example.webapp.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    @Value("${upload.dir}")
    private String uploadDir;

    public Usuario criarUsuarioCompleto(CooperadoRequest request) {
        String senhaCriptografada = passwordEncoder.encode(request.getSenha());
        return UsuarioMapper.toUsuarioFromRequest(request, senhaCriptografada);
    }

    public UsuarioResponse buscarPerfil(Long id, String emailAutenticado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!usuario.getEmail().equalsIgnoreCase(emailAutenticado)) {
            throw new AccessDeniedException("Você não tem permissão para acessar este perfil.");
        }

        return UsuarioMapper.toUsuarioDTO(usuario);
    }

    public void atualizarPerfil(Long id, AtualizarCooperadoRequest request, String emailAutenticado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        if (!usuario.getEmail().equalsIgnoreCase(emailAutenticado)) {
            throw new AccessDeniedException("Você só pode atualizar seu próprio perfil.");
        }

        UsuarioMapper.updateUsuarioFromRequest(usuario, request);
        usuarioRepository.save(usuario);
    }

    public String uploadFotoPerfil(Long id, MultipartFile foto, String emailAutenticado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        if (!usuario.getEmail().equalsIgnoreCase(emailAutenticado)) {
            throw new AccessDeniedException("Você só pode atualizar sua própria foto de perfil.");
        }

        validarImagem(foto);

        String extensao = getExtensao(foto.getOriginalFilename());
        String nomeArquivo = "cooperado_" + usuario.getId() + "." + extensao;

        Path pasta = Paths.get(uploadDir, "cooperados");
        Path caminhoArquivo = pasta.resolve(nomeArquivo);
        String caminhoRelativo = "/uploads/cooperados/" + nomeArquivo;

        try {
            Files.createDirectories(pasta);
            foto.transferTo(caminhoArquivo.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagem de perfil", e);
        }

        usuario.setFotoPerfil(caminhoRelativo);
        usuarioRepository.save(usuario);

        return caminhoRelativo;
    }

    private void validarImagem(MultipartFile foto) {
        String nome = foto.getOriginalFilename().toLowerCase();
        if (foto.getSize() > 2 * 1024 * 1024) {
            throw new IllegalArgumentException("A imagem deve ter no máximo 2MB.");
        }
        if (!(nome.endsWith(".png") || nome.endsWith(".jpg") || nome.endsWith(".jpeg"))) {
            throw new IllegalArgumentException("A imagem deve estar em formato PNG, JPG ou JPEG.");
        }
    }

    private String getExtensao(String nomeArquivo) {
        return nomeArquivo.substring(nomeArquivo.lastIndexOf('.') + 1);
    }
}
