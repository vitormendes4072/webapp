package com.example.webapp.security.jwt;

import com.example.webapp.entities.Empresa;
import com.example.webapp.entities.Usuario;
import com.example.webapp.security.auth.CustomUserDetails;
import com.example.webapp.services.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtSecretProperties secretProperties;
    private final CustomUserDetailsService customUserDetailsService;

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + secretProperties.getExpirationMs());

        Object entity = ((CustomUserDetails) userDetails).getEntity();

        JwtBuilder builder = Jwts.builder()
                .setSubject(userDetails.getUsername()) // CORRETO para versão 0.11.2
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256); // Importante: informar o algoritmo aqui

        if (entity instanceof Usuario usuario) {
            builder.claim("tipo", "COOPERADO");
            builder.claim("nome", usuario.getNomeCompleto());
        } else if (entity instanceof Empresa empresa) {
            builder.claim("tipo", "EMPRESA");
            builder.claim("nome", empresa.getNomeEmpresa());
        }

        return builder.compact();
    }

    public Authentication getAuthentication(String token) {
        String username = extractUsername(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // CORRETO
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            System.out.println("Token inválido: " + e.getMessage());
            return false;
        }
    }

    private String extractUsername(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // CORRETO
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey getSigningKey() {
        try {
            byte[] keyBytes = secretProperties.getSecret().getBytes(StandardCharsets.UTF_8);

            if (keyBytes.length < 32) {
                MessageDigest sha = MessageDigest.getInstance("SHA-256");
                keyBytes = sha.digest(keyBytes);
            }

            keyBytes = Arrays.copyOf(keyBytes, 32);
            return Keys.hmacShaKeyFor(keyBytes);

        } catch (Exception e) {
            throw new RuntimeException("Falha ao gerar chave JWT: " + e.getMessage(), e);
        }
    }
}
