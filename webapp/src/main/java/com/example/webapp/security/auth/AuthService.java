package com.example.webapp.security.auth;

import com.example.webapp.dto.request.AtualizarSenhaRequest;
import com.example.webapp.dto.request.CooperadoRequest;
import com.example.webapp.dto.request.EmpresaRequest;
import com.example.webapp.dto.request.LoginRequest;
import com.example.webapp.dto.response.AuthResponse;
import com.example.webapp.dto.response.RegisterResponse;
import com.example.webapp.entities.Empresa;
import com.example.webapp.entities.Usuario;
import com.example.webapp.exception.CnpjJaCadastradoException;
import com.example.webapp.exception.EmailJaCadastradoException;
import com.example.webapp.repositories.EmpresaRepository;
import com.example.webapp.repositories.UsuarioRepository;
import com.example.webapp.security.filter.TentativaLoginService;
import com.example.webapp.security.jwt.JwtUtil;
import com.example.webapp.services.EmpresaService;
import com.example.webapp.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    private final EmpresaRepository empresaRepository;
    private final EmpresaService empresaService;
    private final TentativaLoginService tentativaLoginService;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse registerCooperado(CooperadoRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new EmailJaCadastradoException();
        }

        Usuario usuario = usuarioService.criarUsuarioCompleto(request);
        Usuario saveUser = usuarioRepository.save(usuario);

        return new RegisterResponse(
                saveUser.getId(),
                saveUser.getNomeCompleto(),
                saveUser.getEmail(),
                saveUser.getTipoUsuario(),
                saveUser.getDataCriacao(), "Cadastro realizado com sucesso !"
        );
    }

    public RegisterResponse registerEmpresa(EmpresaRequest empresaRequest) {
        if (empresaRepository.existsByEmail(empresaRequest.getEmail())) {
            throw new EmailJaCadastradoException();
        }

        if (empresaRepository.existsByCnpj(empresaRequest.getCnpj())) {
            throw new CnpjJaCadastradoException();
        }

        Empresa empresa = empresaService.criarEmpresaCompleto(empresaRequest);
        Empresa savedEmpresa = empresaRepository.save(empresa);

        return new RegisterResponse(
                savedEmpresa.getId(),
                savedEmpresa.getNomeEmpresa(),
                savedEmpresa.getEmail(),
                savedEmpresa.getTipoUsuario(),
                savedEmpresa.getDataCriacao(),
                "Empresa Cadastrada com sucesso !"
        );
    }

    public AuthResponse login(LoginRequest request) {
        if (tentativaLoginService.isBlocked(request.getEmail())) {
            throw new LockedException("Usuário temporariamente bloqueado por tentativas excessivas. Aguarde 1 minuto");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
            );

            tentativaLoginService.loginSucceeded(request.getEmail());

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Object entidade = userDetails.getEntity();
            String token = jwtUtil.generateToken(userDetails);

            if (entidade instanceof Usuario usuario) {
                return new AuthResponse(token, usuario.getNomeCompleto(), usuario.getEmail(), "COOPERADO");
            } else if (entidade instanceof Empresa empresa) {
                return new AuthResponse(token, empresa.getNomeEmpresa(), empresa.getEmail(), "EMPRESA");
            } else {
                throw new IllegalArgumentException("Tipo de entidade desconhecido no login");
            }

        } catch (AuthenticationException ex) {
            tentativaLoginService.loginFailed(request.getEmail());
            throw ex;
        }
    }


    public void alterarSenha(String email, AtualizarSenhaRequest request) {
        // Tenta encontrar a entidade pelo e-mail informado
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);
        Optional<Empresa> optionalEmpresa = empresaRepository.findByEmail(email);

        if (optionalUsuario.isEmpty() && optionalEmpresa.isEmpty()) {
            throw new UsernameNotFoundException("Usuário ou Empresa não encontrado com o e-mail: " + email);
        }

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            processarAlteracaoSenha(usuario, request);
            usuarioRepository.save(usuario);
        } else {
            Empresa empresa = optionalEmpresa.get();
            processarAlteracaoSenha(empresa, request);
            empresaRepository.save(empresa);
        }
    }

    private void processarAlteracaoSenha(Object entidade, AtualizarSenhaRequest request) {
        String senhaAtualCriptografada;
        Consumer<String> definirNovaSenha;

        if (entidade instanceof Usuario usuario) {
            senhaAtualCriptografada = usuario.getSenhaHash();
            definirNovaSenha = novaSenha -> usuario.setSenhaHash(passwordEncoder.encode(novaSenha));
        } else if (entidade instanceof Empresa empresa) {
            senhaAtualCriptografada = empresa.getSenhaHash();
            definirNovaSenha = novaSenha -> empresa.setSenhaHash(passwordEncoder.encode(novaSenha));
        } else {
            throw new IllegalArgumentException("Entidade desconhecida ao tentar alterar senha.");
        }

        if (!passwordEncoder.matches(request.getSenhaAtual(), senhaAtualCriptografada)) {
            throw new IllegalArgumentException("Senha atual incorreta.");
        }

        if (!request.getNovaSenha().equals(request.getConfirmacaoSenha())) {
            throw new IllegalArgumentException("Nova senha e confirmação não coincidem.");
        }

        definirNovaSenha.accept(request.getNovaSenha());
    }
}

