package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.ColaboradorDto;
import com.senai.gestao_recursos.service.ColaboradorService;
import com.senai.gestao_recursos.sessoes.SessaoDto;
import com.senai.gestao_recursos.sessoes.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ColaboradorPageController {
    private final ColaboradorService colaboradorService;

    public ColaboradorPageController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @GetMapping("/")
    public String getIndex(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/home")
    public String getHome(HttpSession session, Model model) {
        SessaoDto sessaoDto = SessaoUtil.ObterSessao(session);

        if (sessaoDto == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuarioLogado", sessaoDto);
        return "home";
    }

    @GetMapping("/colaboradorcadastrar")
    public String getCadastrar(Model model) {
        model.addAttribute("colaborador", new ColaboradorDto());

        return "colaboradorcadastrar";
    }

    @GetMapping("/colaboradorlista")
    public String getColaboradores(Model model) {
        List<ColaboradorDto> colaboradores = colaboradorService.listar();

        model.addAttribute("colaboradores", colaboradores);

        return "colaboradorlista";
    }
}
