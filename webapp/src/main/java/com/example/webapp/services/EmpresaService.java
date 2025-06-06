package com.example.webapp.services;

import com.example.webapp.dto.request.AtualizarEmpresaRequest;
import com.example.webapp.dto.request.EmpresaRequest;
import com.example.webapp.dto.response.EmpresaResponse;
import com.example.webapp.entities.Empresa;
import com.example.webapp.entities.Usuario;
import com.example.webapp.mapper.EmpresaMapper;
import com.example.webapp.repositories.EmpresaRepository;
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
public class EmpresaService {

    private final PasswordEncoder passwordEncoder;
    private final EmpresaRepository empresaRepository;

    @Value("${upload.dir}")
    private String uploadDir;

    public Empresa criarEmpresaCompleto(EmpresaRequest empresaRequest) {
        String senhaHash = passwordEncoder.encode(empresaRequest.getSenha());
        return EmpresaMapper.toEmpresaFromRequest(empresaRequest, senhaHash);
    }

    public EmpresaResponse buscarPerfil(Long id, String emailAutenticado) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrado"));

        if (!empresa.getEmail().equalsIgnoreCase(emailAutenticado)) {
            throw new AccessDeniedException("Você não tem permissão para acessar este perfil.");
        }

        return EmpresaMapper.toEmpresaDTO(empresa);
    }

    public void atualizarPerfil(Long id, AtualizarEmpresaRequest request, String emailAutenticado) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada."));

        if (!empresa.getEmail().equalsIgnoreCase(emailAutenticado)) {
            throw new AccessDeniedException("Você só pode atualizar seu próprio perfil.");
        }

        if (request.getNaturezaJuridica() != null) {
            empresa.setNaturezaJuridica(request.getNaturezaJuridicaEnum());
        }
        EmpresaMapper.updateEmpresaFromRequest(empresa, request);
        empresaRepository.save(empresa);
    }

    public String uploadFotoPerfil(Long id, MultipartFile foto, String emailAutenticado) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        if (!empresa.getEmail().equalsIgnoreCase(emailAutenticado)) {
            throw new AccessDeniedException("Você só pode atualizar sua própria foto de perfil.");
        }

        validarImagem(foto);

        String extensao = getExtensao(foto.getOriginalFilename());
        String nomeArquivo = "empresa_" + empresa.getId() + "." + extensao;

        Path pasta = Paths.get(uploadDir, "empresas");
        Path caminhoArquivo = pasta.resolve(nomeArquivo);
        String caminhoRelativo = "/uploads/empresas/" + nomeArquivo;

        try {
            Files.createDirectories(pasta);
            foto.transferTo(caminhoArquivo.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagem de perfil", e);
        }

        empresa.setFotoPerfil(caminhoRelativo);
        empresaRepository.save(empresa);

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
