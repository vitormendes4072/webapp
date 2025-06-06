package com.example.webapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data // Gera automaticamente getters, setters, toString, equals e hashCode
@Entity // Indica que essa classe representa uma tabela no banco de dados
@Table(name = "usuarios") // Nome da tabela no banco
public class Usuario implements UserDetails, Autenticavel {

    @Id // Define a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento
    private Long id;

    // Nome completo do usuário (obrigatório)
    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    // E-mail único do usuário (usado para login)
    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;


    // Senha criptografada do usuário
    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;

    // CPF único do usuário (formato numérico, 11 dígitos)
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    //rg do colaorador pode ter até 14 caracteres no maximo
    @Column(nullable = false, length = 14)
    private String rg;

    // Data de nascimento (formato String, mas ideal seria LocalDate)
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    // Gênero (opcional)
    private String genero;

    // Telefone de contato (obrigatório)
    @Column(nullable = false)
    private String telefone;

    // Tipo de usuário: COOPERADO ou EMPRESA
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    // Disponibilidade do usuário (ex: full-time, part-time)
    private String disponibilidade;

    // Preferência de trabalho (ex: remoto, híbrido, presencial)
    @Column(name = "preferencia_trabalho")
    private String preferenciaTrabalho;

    // Flag que indica se o usuário aceitou os termos de uso
    @Column(name = "aceitou_termos", nullable = false, columnDefinition = "boolean default false")
    private boolean aceitouTermos = false;

    // Data e hora em que o cadastro foi criado
    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    // Relacionamento 1:1 com a entidade Endereco
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    // Relacionamento 1:1 com a entidade DadosBancarios
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dados_bancarios_id", referencedColumnName = "id")
    private DadosBancarios dadosBancarios;

    // Relacionamento 1:1 com a entidade PerfilProfissional
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "perfil_profissional_id", referencedColumnName = "id")
    private PerfilProfissional perfilProfissional;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Candidatura> candidaturas;


    // Métodos obrigatórios da interface UserDetails (usados pelo Spring Security)

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + tipoUsuario.name())); // Não estamos usando permissões específicas no momento
    }

    @Override
    public String getPassword() {
        return senhaHash; // Senha usada no login
    }

    @Override
    public String getUsername() {
        return email; // Email usado como login
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Conta nunca expira (padrão)
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Conta nunca bloqueia (padrão)
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Senha nunca expira (padrão)
    }

    @Override
    public boolean isEnabled() {
        return true; // Usuário sempre ativo (padrão)
    }

    @Override
    public TipoUsuario getTipoUsuario() {
        return this.tipoUsuario;
    }

    @Override
    public String getEmail() {
        return this.email;
    }
    public Long getId() {
        return this.id;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public String getNomeCompleto() {
        return this.nomeCompleto;
    }

}

