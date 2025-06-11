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
                // 1. Session será criada apenas quando necessário (ex: login via formulário)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                // 2. Regras de rotas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/index.html", "/favicon.ico",
                                "/style/**", "/script/**", "/assets/**", "/templates/**",
                                "/auth/**",
                                "/cadastro**", "/login**", "/verificar-codigo", "/redefinir-senha", "/rednovasenha"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                // 3. Login com formulário
                .formLogin(form -> form
                        .loginPage("/login-empresa") // onde está seu formulário
                        .loginProcessingUrl("/login-empresa") // onde o formulário envia os dados
                        .defaultSuccessUrl("/dashboard", true) // página pós-login
                        .failureUrl("/login-empresa?error=true")
                        .permitAll()
                )
                // 4. Logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login-empresa?logout=true")
                        .permitAll()
                )
                // 5. Filtro JWT (somente para requests que enviam token)
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

