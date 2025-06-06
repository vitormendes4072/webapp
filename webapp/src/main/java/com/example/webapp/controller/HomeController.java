package com.example.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Isso vai buscar login.html dentro de /resources/templates
    }

    @GetMapping("/cadastro")
    public String showCadastroPage() {
        return "form01"; // renderiza form01.html
    }

    @GetMapping("/cadastro-empresa")
    public String showEmpresaForm() {
        return "form01emp";
    }

    @GetMapping("/cadastro-contato")
    public String showContatoForm() {
        return "form02";
    }

    @GetMapping("/cadastro-empresa-contato")
    public String showEmpresaContatoForm() {
        return "form02emp";
    }

    @GetMapping("/cadastro-dados-bancarios")
    public String showForm03() {
        return "form03";
    }

    @GetMapping("/cadastro-dados-bancarios-empresa")
    public String showForm03Emp() {
        return "form03emp";
    }

    @GetMapping("/cadastro-sobre-voce")
    public String showForm04() {
        return "form04";
    }

    @GetMapping("/cadastro-empresa-sobre")
    public String showForm04Emp() {
        return "form04emp";
    }

    @GetMapping("/cadastro-final")
    public String showForm05() {
        return "form05";
    }

    @GetMapping("/cadastro-final-empresa")
    public String showForm05Empresa() {
        return "form05emp";
    }

    @GetMapping("/login-empresa")
    public String showLoginEmpresa() {
        return "loginemp";
    }

    @GetMapping("/verificar-codigo")
    public String showRedCodigo() {
        return "redcodigo"; // Vai procurar em templates/redcodigo.html
    }

    @GetMapping("/redefinir-senha")
    public String showResetPasswordPage() {
        return "redefinir-senha"; // Vai carregar redefinir-senha.html
    }

    @GetMapping("/rednovasenha")
    public String showNewPasswordPage() {
        return "rednovasenha"; // Vai buscar rednovasenha.html
    }


    @GetMapping("/")
    public String home() {
        return "login"; // ou substitua por qualquer página inicial desejada
    }


    @PostMapping("/cadastro-contato")
    public String processarContato(
            @RequestParam String email,
            @RequestParam String celular,
            @RequestParam(required = false) String telefone,
            @RequestParam String cep,
            @RequestParam(required = false) String logradouro,
            @RequestParam String numero,
            @RequestParam(required = false) String complemento,
            @RequestParam String bairro,
            @RequestParam String cidade,
            @RequestParam String estado,
            Model model
    ) {
        return "redirect:/cadastro-dados-bancarios";
    }


    @PostMapping("/cadastro-dados-bancarios")
    public String processarDadosBancarios(
            @RequestParam String banco,
            @RequestParam("tipo-conta") String tipoConta,
            @RequestParam String agencia,
            @RequestParam String conta,
            @RequestParam("chave-pix") String chavePix,
            Model model
    ) {
        // Aqui você pode armazenar os dados em sessão/DTO/etc

        return "redirect:/cadastro-sobre-voce"; // próxima etapa do formulário
    }

    @PostMapping("/cadastro-sobre-voce")
    public String processarSobreVoce(
            @RequestParam String areaAtuacao,
            @RequestParam String experiencia,
            @RequestParam String formacao,
            @RequestParam String disponibilidade,
            @RequestParam String habilidades,
            Model model
    ) {
        // Aqui você pode armazenar os dados se necessário

        return "redirect:/cadastro-final";
    }

    @PostMapping("/cadastro-final")
    public String processarCadastroFinal(
            @RequestParam String senha,
            @RequestParam String confirmaSenha,
            Model model
    ) {
        // Aqui você pode salvar no banco de dados se quiser

        // Redireciona para a tela de login após finalizar o cadastro
        return "redirect:/login";
    }

}
