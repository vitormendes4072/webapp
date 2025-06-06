package com.example.webapp.entities;

import org.springframework.security.core.userdetails.UserDetails;

public interface Autenticavel extends UserDetails {
    TipoUsuario getTipoUsuario();
    String getEmail();
    String getSenhaHash();
}

