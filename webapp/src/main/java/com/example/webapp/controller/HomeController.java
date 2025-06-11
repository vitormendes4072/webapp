package com.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.webapp.services.EmpresaService;
import com.example.webapp.dto.request.EmpresaRequest;
import com.example.webapp.entities.Empresa;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@SessionAttributes("empresaRequest")
@Controller
public class HomeController {

    @Autowired
    private EmpresaService empresaService;

    @ModelAttribute("empresaRequest")
    public EmpresaRequest empresaRequest() {
        return new EmpresaRequest();
    }

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

    @PostMapping("/cadastro")
    public String cadastrar(
            @RequestParam String nome,
            @RequestParam String cpf,
            @RequestParam String rg,
            @RequestParam String genero,
            @RequestParam("estado_civil") String estadoCivil,
            @RequestParam String nacionalidade,
            Model model
    ) {
        // Aqui você pode armazenar os dados em sessão, DTO ou banco

        return "redirect:/cadastro-contato"; // redireciona para a próxima etapa (form02.html)
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
        // Aqui você pode armazenar os dados em sessão, DTO ou banco

        // Redireciona para a próxima etapa (form03.html)
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

    @PostMapping("/cadastro-empresa")
    public String processarCadastroEmpresa(
            @ModelAttribute("empresaRequest") EmpresaRequest empresaRequest,
            @RequestParam("nome_empresa") String nomeEmpresa,
            @RequestParam("cnpj") String cnpj,
            @RequestParam("razao_social") String razaoSocial,
            @RequestParam("setor_atuacao") String setorAtuacao,
            @RequestParam("descricao_empresa") String descricaoEmpresa,
            @RequestParam("natureza_juridica") String naturezaJuridica,
            @RequestParam("nome_fantasia") String nomeFantasia
    ) {
        empresaRequest.setNomeEmpresa(nomeEmpresa);
        empresaRequest.setCnpj(cnpj);
        empresaRequest.setRazaoSocial(razaoSocial);
        empresaRequest.setSetorAtuacao(setorAtuacao);
        empresaRequest.setDescricao(descricaoEmpresa);
        empresaRequest.setNaturezaJuridica(naturezaJuridica);
        empresaRequest.setNomeFantasia(nomeFantasia);

        return "redirect:/cadastro-empresa-contato";
    }

    @PostMapping("/cadastro-empresa-contato")
    public String processarContatoEmpresa(
            @ModelAttribute("empresaRequest") EmpresaRequest empresaRequest,
            @RequestParam String emailcorporativo,
            @RequestParam String telefonecomercial,
            @RequestParam(required = false) String siteempresa,
            @RequestParam String cep,
            @RequestParam(required = false) String logradouro,
            @RequestParam String numero,
            @RequestParam(required = false) String complemento,
            @RequestParam String bairro,
            @RequestParam String cidade,
            @RequestParam String estado
    ) {
        empresaRequest.setEmail(emailcorporativo);
        empresaRequest.setTelefone(telefonecomercial);
        empresaRequest.setSite(siteempresa);
        empresaRequest.setCep(cep);
        empresaRequest.setRua(logradouro);
        empresaRequest.setNumero(numero);
        empresaRequest.setComplemento(complemento);
        empresaRequest.setBairro(bairro);
        empresaRequest.setCidade(cidade);
        empresaRequest.setEstado(estado);

        return "redirect:/cadastro-dados-bancarios-empresa";
    }

    @PostMapping("/cadastro-dados-bancarios-empresa")
    public String processarDadosBancariosEmpresa(
            @ModelAttribute("empresaRequest") EmpresaRequest empresaRequest,
            @RequestParam String banco,
            @RequestParam("tipo-conta") String tipoConta,
            @RequestParam String agencia,
            @RequestParam String conta,
            @RequestParam("chave-pix") String chavePix
    ) {
        empresaRequest.setBanco(banco);
        empresaRequest.setTipoConta(tipoConta);
        empresaRequest.setAgencia(agencia);
        empresaRequest.setNumeroConta(conta);
        empresaRequest.setChavePix(chavePix);

        return "redirect:/cadastro-empresa-sobre";
    }


    @PostMapping("/cadastro-empresa-sobre")
    public String processarSobreEmpresa(
            @ModelAttribute("empresaRequest") EmpresaRequest empresaRequest,
            @RequestParam("nome-ceo") String nomeCeo,
            @RequestParam("telefone-ceo") String telefoneCeo,
            @RequestParam("email-ceo") String emailCeo
    ) {
        empresaRequest.setNomeCeo(nomeCeo);
        empresaRequest.setTelefoneCeo(telefoneCeo);
        empresaRequest.setEmailCeo(emailCeo);

        return "redirect:/cadastro-final-empresa";
    }

    @PostMapping("/cadastro-final-empresa")
    public String processarCadastroFinalEmpresa(
            @RequestParam String senha,
            @RequestParam String confirmaSenha,
            @ModelAttribute("empresaRequest") EmpresaRequest empresaRequest,
            Model model
    ) {
        if (!senha.equals(confirmaSenha)) {
            model.addAttribute("erro", "As senhas não coincidem.");
            return "form05emp";
        }

        empresaRequest.setSenha(senha);

        try {
            Empresa empresa = empresaService.criarEmpresaCompleto(empresaRequest);
            model.addAttribute("mensagem", "Empresa cadastrada com sucesso!");
            return "redirect:/login-empresa";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar empresa: " + e.getMessage());
            return "form05emp";
        }
    }
}
