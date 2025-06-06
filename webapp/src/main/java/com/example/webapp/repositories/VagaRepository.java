package com.example.webapp.repositories;

import com.example.webapp.entities.Vaga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

    Page<Vaga> findAll(Pageable pageable);

    Page<Vaga> findByEmpresaId(Long empresaId, Pageable pageable);
}