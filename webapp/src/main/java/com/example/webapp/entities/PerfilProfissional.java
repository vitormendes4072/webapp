package com.example.webapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity // Indica que esta classe representa uma tabela no banco de dados
@Table(name = "perfil_profissional") // Nome da tabela no banco
public class PerfilProfissional {

    // Chave primária com auto-incremento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Profissão ou área de atuação do usuário
    @Column(nullable = false)
    private String profissao;

    // Experiência profissional (campo de texto livre)
    @Column(columnDefinition = "TEXT") // Permite textos longos
    private String experiencia;

    // Habilidades do usuário, separadas por vírgulas (ex: Java, Excel, Comunicação)
    @Column(columnDefinition = "TEXT")
    private String habilidades;

    // Formação acadêmica (ex: Ensino Superior, Técnico, Pós-graduação)
    @Column(columnDefinition = "TEXT")
    private String formacao;

    // Link para portfólio (caso o usuário deseje incluir)
    private String portfolio;
}

