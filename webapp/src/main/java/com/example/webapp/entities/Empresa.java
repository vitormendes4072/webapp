package com.example.webapp.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "empresas")
public class Empresa implements UserDetails, Autenticavel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_empresa", nullable = false, unique = true)
    private String nomeEmpresa;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(name = "razao_social", nullable = false, unique = true)
    private String razaoSocial;

    @Column(name = "nome_fantasia", nullable = false)
    private String nomeFantasia;

    @Column(name = "setor_atuacao", nullable = false)
    private String setorAtuacao;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(unique = true, length = 191)
    private String email;

    @Column(nullable = false)
    private String telefone;

    private String site;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dados_bancarios_id", referencedColumnName = "id")
    private DadosBancarios dadosBancarios;

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "natureza_juridica",
            nullable = false,
            columnDefinition = "ENUM('Sociedade Limitada', 'Microempreendedor Individual', 'Empresa Individual de Responsabilidade Limitada', 'Sociedade Anônima', 'Sociedade em Conta de Participação', 'Sociedade Simples', 'Cooperativa', 'Empresa Pública', 'Sociedade de Economia Mista', 'Organização Não Governamental', 'Associação', 'Fundo Privado', 'Consórcio de Empresas')")
    @Convert(converter = NaturezaJuridicaConverter.class)
    private NaturezaJuridica naturezaJuridica;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responsavel_id", referencedColumnName = "id")
    private Responsavel responsavel;

    // Flag que indica se o usuário aceitou os termos de uso
    @Column(name = "aceitou_termos", nullable = false, columnDefinition = "boolean default false")
    private boolean aceitouTermos = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vaga> vagas;

    public enum NaturezaJuridica {
        @jakarta.persistence.Convert(converter = NaturezaJuridicaConverter.class)
        SOCIEDADE_LIMITADA("Sociedade Limitada"),

        @jakarta.persistence.Convert(converter = NaturezaJuridicaConverter.class)
        MICROEMPREENDEDOR_INDIVIDUAL("Microempreendedor Individual"),

        @jakarta.persistence.Convert(converter = NaturezaJuridicaConverter.class)
        EMPRESA_INDIVIDUAL("Empresa Individual de Responsabilidade Limitada"),

        @jakarta.persistence.Convert(converter = NaturezaJuridicaConverter.class)
        SOCIEDADE_ANONIMA("Sociedade Anônima"),

        @jakarta.persistence.Convert(converter = NaturezaJuridicaConverter.class)
        SOCIEDADE_EM_CONTA_DE_PARTICIPACAO("Sociedade em Conta de Participação"),

        @jakarta.persistence.Convert(converter = NaturezaJuridicaConverter.class)
        SOCIEDADE_SIMPLES("Sociedade Simples"),

        @jakarta.persistence.Convert(converter = NaturezaJuridicaConverter.class)
        COOPERATIVA("Cooperativa"),

        @jakarta.persistence.Convert(converter = NaturezaJuridicaConverter.class)
        EMPRESA_PUBLICA("Empresa Pública"),

        @jakarta.persistence.Convert(converter = NaturezaJuridicaConverter.class)
        SOCIEDADE_DE_ECONOMIA_MISTA("Sociedade de Economia Mista"),

        @jakarta.persistence.Convert(converter = NaturezaJuridicaConverter.class)
        ONG("Organização Não Governamental"),

        @jakarta.persistence.Convert(converter = NaturezaJuridicaConverter.class)
        ASSOCIACAO("Associação"),

        @jakarta.persistence.Convert(converter = NaturezaJuridicaConverter.class)
        FUNDO_PRIVADO("Fundo Privado"),

        @jakarta.persistence.Convert(converter = NaturezaJuridicaConverter.class)
        CONSORCIO_DE_EMPRESAS("Consórcio de Empresas");

        private final String label;

        NaturezaJuridica(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(("ROLE_" + tipoUsuario.name())));
    }

    @Override
    public String getPassword() {
        return senhaHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public TipoUsuario getTipoUsuario() {
        return this.tipoUsuario;
    }

    public String getNomeEmpresa() {
        return this.nomeEmpresa;
    }

}
