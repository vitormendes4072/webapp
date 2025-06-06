package com.example.webapp.services;

import com.example.webapp.dto.request.CriarVagaRequest;
import com.example.webapp.dto.response.UsuarioCandidatoResponse;
import com.example.webapp.dto.response.VagaComCandidaturasResponse;
import com.example.webapp.dto.response.VagaFeedResponse;
import com.example.webapp.entities.Empresa;
import com.example.webapp.entities.Vaga;
import com.example.webapp.mapper.CandidaturaMapper;
import com.example.webapp.repositories.CandidaturaRepository;
import com.example.webapp.repositories.EmpresaRepository;
import com.example.webapp.repositories.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VagaService {

    private final VagaRepository vagaRepository;
    private final EmpresaRepository empresaRepository;
    private final CandidaturaRepository candidaturaRepository;

    public Vaga criarVaga(CriarVagaRequest request, String emailEmpresa) {
        // Busca a empresa autenticada pelo e-mail
        Empresa empresa = empresaRepository.findByEmail(emailEmpresa)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        // Cria a nova vaga
        Vaga vaga = new Vaga();
        vaga.setTitulo(request.getTitulo());
        vaga.setDescricao(request.getDescricao());
        vaga.setLocalizacao(request.getLocalizacao());
        vaga.setSalario(request.getSalario());
        vaga.setTipoContrato(request.getTipoContrato());
        vaga.setRequisitos(request.getRequisitos());
        vaga.setEmpresa(empresa); // Associa à empresa logada

        return vagaRepository.save(vaga); // Salva e retorna

    }
    public Page<VagaFeedResponse> buscarFeed(Pageable pageable) {
        return vagaRepository.findAll(pageable).map(VagaFeedResponse::new);
    }

    public Page<VagaFeedResponse> listarVagasParaFeed(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dataPublicacao").descending());
        Page<Vaga> vagasPage = vagaRepository.findAll(pageable);
        return vagasPage.map(VagaFeedResponse::new);
    }

    public Page<VagaComCandidaturasResponse> listarCandidaturasDaEmpresa(Long empresaId, Pageable pageable) {
        Page<Vaga> vagas = vagaRepository.findByEmpresaId(empresaId, pageable);

        return vagas.map(vaga -> {
            List<UsuarioCandidatoResponse> candidatos = candidaturaRepository.findByVagaId(vaga.getId())
                    .stream()
                    .map(CandidaturaMapper::toCooperadoCandidatoResponse)
                    .toList();

            return new VagaComCandidaturasResponse(vaga.getId(), vaga.getTitulo(), candidatos);
        });
    }
}