package com.example.webapp.controller;


import com.example.webapp.dto.request.*;
import com.example.webapp.dto.response.AuthResponse;
import com.example.webapp.dto.response.RegisterResponse;
import com.example.webapp.security.auth.AuthService;
import com.example.webapp.services.PasswordResetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final PasswordResetService passwordResetService;



    @PostMapping("/register-cooperado")
    public ResponseEntity<RegisterResponse> registerCooperado(
            @Valid @RequestBody CooperadoRequest cooperadoRequest
    ) {
        RegisterResponse responseCooperado = authService.registerCooperado(cooperadoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseCooperado);
    }

    @PostMapping("/register-empresa")
    public ResponseEntity<RegisterResponse> registerEmpresa(
            @Valid @RequestBody EmpresaRequest empresaRequest
    ){
        RegisterResponse responseEmpresa = authService.registerEmpresa(empresaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseEmpresa);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/esqueci-senha")
    public ResponseEntity<String> enviarTokenRedefinicao(@Valid @RequestBody ForgotPasswordRequest request) {
        passwordResetService.enviarTokenRedefinicaoSenha(request.getEmail());
        return ResponseEntity.ok("Um e-mail com instruções de redefinição foi enviado: " + request.getEmail());
    }

    @PostMapping("/redefinir-senha")
    public ResponseEntity<String> redefinirSenha(@Valid @RequestBody ResetPasswordRequest request) {
        passwordResetService.redefinirSenha(request.getToken(), request.getNovaSenha());
        return ResponseEntity.ok("Senha redefinida com sucesso.");
    }

    @PutMapping("/senha")
    public ResponseEntity<?> alterarSenha(@RequestBody @Valid AtualizarSenhaRequest request, Principal principal) {
        authService.alterarSenha(principal.getName(), request); // ou getUsername do Token
        return ResponseEntity.ok("Senha alterada com sucesso.");
    }
}
