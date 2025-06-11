package com.example.webapp.security.auth;

import com.example.webapp.entities.Empresa;
import com.example.webapp.entities.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final Object entity;  // Pode ser tanto um Usuario quanto uma Empresa

    // O construtor recebe tanto Usuario quanto Empresa
    public CustomUserDetails(Object entity) {
        this.entity = entity;
    }

    // Retorna a entidade (Usuario ou Empresa)
    public Object getEntity() {
        return entity;
    }

    public boolean isEmpresa() {
        return entity instanceof Empresa;
    }

    public boolean isUsuario() {
        return entity instanceof Usuario;
    }

    // Determina as authorities com base no tipo da entidade
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (entity instanceof Usuario) {
            Usuario usuario = (Usuario) entity;
            return List.of(new SimpleGrantedAuthority("COOPERADO"));
        } else if (entity instanceof Empresa) {
            // Para empresas, podemos definir um tipo de authority específico
            return List.of(new SimpleGrantedAuthority("EMPRESA"));
        }
        return List.of();
    }

    @Override
    public String getPassword() {
        if (entity instanceof Usuario) {
            return ((Usuario) entity).getSenhaHash();
        }
        else if (entity instanceof Empresa){
            return ((Empresa) entity).getPassword();
        }
        return null;
    }

    @Override
    public String getUsername() {
        if (entity instanceof Usuario) {
            return ((Usuario) entity).getEmail();
        } else if (entity instanceof Empresa) {
            return ((Empresa) entity).getEmail();
        }
        return null;
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
}
