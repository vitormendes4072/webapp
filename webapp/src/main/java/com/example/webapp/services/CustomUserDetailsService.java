package com.example.webapp.services;


import com.example.webapp.entities.Empresa;
import com.example.webapp.entities.Usuario;
import com.example.webapp.repositories.EmpresaRepository;
import com.example.webapp.repositories.UsuarioRepository;
import com.example.webapp.security.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Tenta buscar o usuário primeiro como Usuario
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isPresent()) {
            return new CustomUserDetails(usuarioOpt.get());
        }

        // Se não encontrar como Usuario, tenta buscar como Empresa
        Optional<Empresa> empresaOpt = empresaRepository.findByEmail(email);
        if (empresaOpt.isPresent()) {
            return new CustomUserDetails(empresaOpt.get());
        }

        // Se não encontrar nenhum dos dois, lança exceção
        throw new UsernameNotFoundException("Usuário ou empresa não encontrado com o e-mail: " + email);
    }
}
