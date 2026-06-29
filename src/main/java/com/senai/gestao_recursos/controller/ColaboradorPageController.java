package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.ColaboradorDto;
import com.senai.gestao_recursos.service.ColaboradorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ColaboradorPageController {
    private final ColaboradorService service;

    public ColaboradorPageController(ColaboradorService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @GetMapping("/colaboradorcadastrar")
    public String getCadastrar(Model model) {
        model.addAttribute("colaborador", new ColaboradorDto());

        return "colaboradorcadastrar";
    }

    @GetMapping("/colaboradorlista")
    public String getColaboradores(Model model) {
        List<ColaboradorDto> colaboradores = service.listar();

        model.addAttribute("colaboradores", colaboradores);

        return "colaboradorlista";
    }
}
