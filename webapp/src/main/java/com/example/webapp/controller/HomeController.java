package com.example.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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










}
