package com.example.webapp.repositories;


import com.example.webapp.entities.Candidatura;
import com.example.webapp.entities.Usuario;
import com.example.webapp.entities.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {

    boolean existsByUsuarioAndVaga(Usuario cooperado, Vaga vaga);

    List<Candidatura> findByVagaId(Long vagaId);

    List<Candidatura> findByUsuario(Usuario cooperado);


}
