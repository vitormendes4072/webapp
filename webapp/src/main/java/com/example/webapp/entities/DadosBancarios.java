package com.example.webapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity // Indica que esta classe representa uma tabela no banco de dados
@Table(name = "dados_bancarios") // Nome da tabela
public class DadosBancarios {

    // Chave primária com auto-incremento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome do banco (ex: Nubank, Caixa, Bradesco)
    @Column(nullable = false)
    private String banco;

    // Tipo da conta: Poupança ou Corrente
    @Column(name = "tipo_conta", nullable = false)
    private String tipoConta;

    // Número da agência bancária
    @Column(nullable = false)
    private String agencia;

    // Número da conta
    @Column(name = "numero_conta", nullable = false)
    private String numeroConta;

    // Chave PIX (pode ser CPF, e-mail, telefone ou aleatória)
    @Column(name = "chave_pix", nullable = false)
    private String chavePix;
}

