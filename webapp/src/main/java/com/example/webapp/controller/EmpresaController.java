package com.example.webapp.controller;

import com.example.webapp.dto.request.AtualizarEmpresaRequest;
import com.example.webapp.dto.request.EmpresaRequest;
import com.example.webapp.dto.request.RespostaCandidaturaRequest;
import com.example.webapp.dto.response.EmpresaResponse;
import com.example.webapp.dto.response.VagaComCandidaturasResponse;
import com.example.webapp.entities.Empresa;
import com.example.webapp.mapper.EmpresaMapper;
import com.example.webapp.security.auth.CustomUserDetails;
import com.example.webapp.services.CandidaturaService;
import com.example.webapp.services.EmpresaService;
import com.example.webapp.services.VagaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private VagaService vagaService;

    @Autowired
    private CandidaturaService candidaturaService;

    @GetMapping("/perfil/{id}")
    public ResponseEntity<EmpresaResponse> buscarPerfil(@PathVariable Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        EmpresaResponse response = empresaService.buscarPerfil(id, email);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/perfil/{id}")
    public ResponseEntity<Void> atualizarPerfil( @PathVariable Long id,
                                                 @RequestBody @Valid AtualizarEmpresaRequest request,
                                                 Authentication authentication) {

        String email = authentication.getName();
        empresaService.atualizarPerfil(id, request, email);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/perfil/{id}/upload-foto", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, String>> uploadFotoPerfil(
            @PathVariable Long id,
            @RequestParam("foto") MultipartFile foto,
            Authentication authentication) {

        String email = authentication.getName();
        String caminho = empresaService.uploadFotoPerfil(id, foto, email);

        return ResponseEntity.ok(Collections.singletonMap("caminho", caminho));
    }

    @GetMapping("/vagas/candidaturas")
    public ResponseEntity<Page<VagaComCandidaturasResponse>> listarCandidaturasDaEmpresa(
            Authentication authentication,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails customUserDetails) {
            Object entity = customUserDetails.getEntity();

            if (entity instanceof Empresa empresa) {
                Page<VagaComCandidaturasResponse> response = vagaService.listarCandidaturasDaEmpresa(empresa.getId(), pageable);
                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PutMapping("/candidaturas/{id}/responder")
    public ResponseEntity<?> responderCandidatura(
            @PathVariable Long id,
            @RequestBody @Valid RespostaCandidaturaRequest request,
            Authentication authentication
    ) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails customUserDetails) {
            Object entity = customUserDetails.getEntity();

            if (entity instanceof Empresa empresa) {
                candidaturaService.responderCandidatura(id, empresa, request.getStatusCandidatura());
                return ResponseEntity.ok("Candidatura " + request.getStatusCandidatura().name().toLowerCase());
            }
        }

        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas empresas podem responder candidaturas.");
    }

    @PostMapping("/cadastro")
    public ResponseEntity<EmpresaResponse> criarEmpresa(@RequestBody @Valid EmpresaRequest request) {
        Empresa empresa = empresaService.criarEmpresaCompleto(request);
        EmpresaResponse response = EmpresaMapper.toEmpresaDTO(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
