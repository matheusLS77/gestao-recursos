package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.RetiradaDto;
import com.senai.gestao_recursos.service.RetiradaService;
import com.senai.gestao_recursos.sessoes.SessaoDto;
import com.senai.gestao_recursos.sessoes.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RetiradaPageController {
    private final RetiradaService retiradaService;

    public RetiradaPageController(RetiradaService retiradaService) {
        this.retiradaService = retiradaService;
    }

    @GetMapping("/retiradacadastrar")
    public String getCadastrar(Model model, HttpSession session) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("retirada", new RetiradaDto());

        return "retiradacadastrar";
    }

    @GetMapping("/retiradaatualizar/{id}")
    public String getAtualizar(Model model, HttpSession session, @PathVariable Long id) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        RetiradaDto retirada = retiradaService.obterRetirada(id);
        model.addAttribute("retirada", retirada);

        return "retiradaatualizar";
    }

    @GetMapping("/retiradalista")
    public String getRetiradas(Model model, HttpSession session) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        List<RetiradaDto> retiradas = retiradaService.listar();

        model.addAttribute("retiradas", retiradas);

        return "retiradalista";
    }
}