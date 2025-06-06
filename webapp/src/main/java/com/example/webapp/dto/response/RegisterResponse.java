package com.example.webapp.dto.response;

import com.example.webapp.entities.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Classe de resposta após o cadastro do usuário.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {

    private Long id;                      // ID gerado no banco
    private String nome;         // Nome do usuário
    private String email;                // Email do usuário
    private TipoUsuario tipoUsuario; // Tipo (COOPERADO, EMPRESA)
    private LocalDateTime dataCriacao;   // Data em que o usuário foi cadastrado
    private String mensagem;             // Mensagem de confirmação
}

