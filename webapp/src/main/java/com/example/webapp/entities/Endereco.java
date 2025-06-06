package com.example.webapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity // Indica que é uma tabela no banco de dados
@Table(name = "enderecos") // Nome da tabela
public class Endereco {

    // Chave primária com auto-incremento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // CEP para preencher automaticamente os outros campos
    @Column(nullable = false, length = 9)
    private String cep;

    // Nome da rua ou avenida
    @Column(nullable = false)
    private String rua;

    // Número do imóvel
    @Column(nullable = false)
    private String numero;

    // Complemento (ex: apto, bloco) — opcional
    private String complemento;

    // Bairro
    @Column(nullable = false)
    private String bairro;

    // Cidade
    @Column(nullable = false)
    private String cidade;

    // Estado (UF)
    @Column(nullable = false, length = 2)
    private String estado;
}