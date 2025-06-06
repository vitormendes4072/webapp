package com.example.webapp.repositories;


import com.example.webapp.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Interface do repositório para operações com a tabela de usuários
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca um usuário pelo email (retorna Optional para tratamento seguro)
    Optional<Usuario> findByEmail(String email);

    //verifica s eo emil ja existe
    boolean existsByEmail(String email);
}
