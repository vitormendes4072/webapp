package com.example.webapp.services;


import com.example.webapp.dto.response.CandidaturaResponse;
import com.example.webapp.entities.Candidatura;
import com.example.webapp.entities.Empresa;
import com.example.webapp.entities.Usuario;
import com.example.webapp.entities.Vaga;
import com.example.webapp.repositories.CandidaturaRepository;
import com.example.webapp.repositories.VagaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidaturaService {

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private CandidaturaRepository candidaturaRepository;

    public void candidatar(Long vagaId, Usuario cooperado) {
        Vaga vaga = vagaRepository.findById(vagaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada"));

        if (candidaturaRepository.existsByUsuarioAndVaga(cooperado, vaga)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você já se candidatou a esta vaga");
        }

        Candidatura candidatura = new Candidatura(cooperado, vaga);
        candidaturaRepository.save(candidatura);
    }

    public List<CandidaturaResponse> listarCandidaturasDoCooperado(Usuario cooperado) {
        List<Candidatura> candidaturas = candidaturaRepository.findByUsuario(cooperado);
        return candidaturas.stream()
                .map(c -> new CandidaturaResponse(c, c.getVaga().getEmpresa()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void responderCandidatura(Long candidaturaId, Empresa empresa, Candidatura.StatusCandidatura status) {
        Candidatura candidatura = candidaturaRepository.findById(candidaturaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidatura não encontrada"));

        Vaga vaga = candidatura.getVaga();

        if (!vaga.getEmpresa().getId().equals(empresa.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para responder essa candidatura.");
        }

        candidatura.setStatus(status);
        candidaturaRepository.save(candidatura);

        // TODO: Notificar cooperado (por e-mail, ou log/print)
        System.out.println("Notificação enviada para cooperado: " + candidatura.getUsuario().getNomeCompleto() + " - Status: " + status.name());
    }

}
