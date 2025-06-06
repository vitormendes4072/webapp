package com.example.webapp.security.config;

import com.example.webapp.security.auth.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/favicon.ico",
                                "/style/**",
                                "/script/**",
                                "/assets/**",
                                "/templates/**",
                                "/auth/register-cooperado",
                                "/auth/register-empresa",
                                "/auth/login",
                                "/auth/esqueci-senha",
                                "/auth/redefinir-senha",
                                "/login",
                                "/cadastro",
                                "/cadastro-contato",                // ← ADICIONAR
                                "/cadastro-empresa",
                                "/cadastro-empresa-contato",        // ← ADICIONAR
                                "/cadastro-dados-bancarios",        // ← ADICIONAR
                                "/cadastro-dados-bancarios-empresa",
                                "/cadastro-sobre-voce",             // ← ADICIONAR
                                "/cadastro-empresa-sobre",
                                "/cadastro-final",                  // ← ADICIONAR
                                "/cadastro-final-empresa",
                                "/login-empresa",
                                "/verificar-codigo",
                                "/redefinir-senha",
                                "/rednovasenha"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

