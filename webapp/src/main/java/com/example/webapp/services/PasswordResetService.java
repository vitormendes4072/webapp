package com.example.webapp.services;

import com.example.webapp.entities.Autenticavel;
import com.example.webapp.entities.Empresa;
import com.example.webapp.entities.PasswordResetToken;
import com.example.webapp.entities.Usuario;
import com.example.webapp.repositories.EmpresaRepository;
import com.example.webapp.repositories.PasswordResetTokenRepository;
import com.example.webapp.repositories.UsuarioRepository;
import com.example.webapp.security.auth.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PasswordResetService {

    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final EmailService emailService;

    public void enviarTokenRedefinicaoSenha(String email) {
        Autenticavel autenticavel = buscarUsuarioOuEmpresa(email);
        if (autenticavel == null) {
            throw new UsernameNotFoundException("E-mail não encontrado");
        }

        String tokenStr = UUID.randomUUID().toString();
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(tokenStr);
        token.setExpiration(LocalDateTime.now().plusHours(1));

        if (autenticavel instanceof Usuario usuario) {
            token.setUsuario(usuario);
        } else if (autenticavel instanceof Empresa empresa) {
            token.setEmpresa(empresa);
        }

        tokenRepository.save(token);

        // Aqui você pode enviar um e-mail real.
        System.out.println("Token enviado: " + tokenStr);
        String link = "TOKEN: " + tokenStr;
        String corpo = "🌟 Olá, Talentoso(a)!\n\n" +
                "Recebemos uma solicitação para redefinir sua senha na plataforma Line Jobs - onde as oportunidades batem à sua porta! 🚪💼\n\n" +
                "🔗 Veja o Token abaixo para criar uma nova senha e continuar acessando os melhores empregos alinhados ao seu perfil:\n\n" +
                link + "\n\n" +
                "⏳ Este link expira em 1 hora para sua segurança.\n\n" +
                "💡 Dica: Sua senha deve conter:\n" +
                "   - Pelo menos 8 caracteres\n" +
                "   - Letras maiúsculas e minúsculas\n" +
                "   - Números e caracteres especiais\n\n" +
                "⚠️ Se você não solicitou esta redefinição, por favor ignore este e-mail ou entre em contato conosco imediatamente através do suporte@linejobs.com para proteger sua conta. 🛡️\n\n" +
                "Agradecemos por fazer parte da comunidade Line Jobs!\n" +
                "Com carinho,\n" +
                "Equipe Line Jobs 💙\n\n" +
                "🔍 Encontre seu emprego ideal - Conectamos talentos a oportunidades perfeitas!";

        emailService.enviarEmail(autenticavel.getEmail(), "Redefinição de Senha", corpo);
    }

    private Autenticavel buscarUsuarioOuEmpresa(String email) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get();
        }

        Optional<Empresa> empresaOpt = empresaRepository.findByEmail(email);
        if (empresaOpt.isPresent()) {
            return empresaOpt.get();
        }

        return null; // Caso não tenha encontrado nem usuário nem empresa
    }


    public void redefinirSenha(String tokenStr, String novaSenha) {
        PasswordResetToken token = tokenRepository.findByToken(tokenStr)
                .orElseThrow(() -> new IllegalArgumentException("Token inválido"));

        if (token.getExpiration().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token expirado");
        }

        Autenticavel autenticavel = token.getAutenticavel();
        String senhaHash = new BCryptPasswordEncoder().encode(novaSenha);

        if (autenticavel instanceof Usuario usuario) {
            usuario.setSenhaHash(senhaHash);
            usuarioRepository.save(usuario);
            System.out.println("Nova senha: " + usuario.getPassword());
        } else if (autenticavel instanceof Empresa empresa) {
            empresa.setSenhaHash(senhaHash);
            empresaRepository.save(empresa);
            System.out.println("Nova senha: " + empresa.getPassword());
        }

        tokenRepository.delete(token); // opcional: remove o token após uso
    }
}

