package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.LocalizacaoDto;
import com.senai.gestao_recursos.service.LocalizacaoService;
import com.senai.gestao_recursos.sessoes.SessaoDto;
import com.senai.gestao_recursos.sessoes.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class LocalizacaoPageController {
    private final LocalizacaoService localizacaoService;

    public LocalizacaoPageController(LocalizacaoService localizacaoService) {
        this.localizacaoService = localizacaoService;
    }

    @GetMapping("/localizacaocadastrar")
    public String getCadastrar(Model model, HttpSession session) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("localizacao", new LocalizacaoDto());
        model.addAttribute("localizacoes", localizacaoService.listar());


        return "localizacaocadastrar";
    }

    @GetMapping("/localizacaoatualizar/{id}")
    public String getAtualizar(Model model, HttpSession session, @PathVariable Long id) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        LocalizacaoDto localizacao = localizacaoService.obterLocalizacao(id);
        model.addAttribute("localizacao", localizacao);

        return "localizacaoatualizar";
    }

    @GetMapping("/localizacaolista")
    public String getLocalizacoes(Model model, HttpSession session) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        List<LocalizacaoDto> localizacoes = localizacaoService.listar();

        model.addAttribute("localizacoes", localizacoes);

        return "localizacaolista";
    }
}