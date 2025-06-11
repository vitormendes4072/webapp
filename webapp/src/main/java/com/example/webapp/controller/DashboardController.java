package com.example.webapp.controller;

import com.example.webapp.entities.Empresa;
import com.example.webapp.entities.Usuario;
import com.example.webapp.security.auth.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping
    public String exibirDashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            Object entity = userDetails.getEntity();

            if (entity instanceof Empresa empresa) {
                model.addAttribute("nome", empresa.getNomeEmpresa());
                model.addAttribute("email", empresa.getEmail());
                return "pag1emp"; // seu HTML deve estar em templates/dashboard-empresa.html
            }

            if (entity instanceof Usuario usuario) {
                model.addAttribute("nome", usuario.getNomeCompleto());
                model.addAttribute("email", usuario.getEmail());
                return "pag1coop"; // se quiser um dashboard para cooperado
            }
        }

        return "redirect:/login";
    }
}

