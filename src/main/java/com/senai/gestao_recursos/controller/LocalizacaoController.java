package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.LocalizacaoDto;
import com.senai.gestao_recursos.service.LocalizacaoService;
import com.senai.gestao_recursos.sessoes.SessaoDto;
import com.senai.gestao_recursos.sessoes.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LocalizacaoController {
    private final LocalizacaoService service;

    public LocalizacaoController(LocalizacaoService service) {
        this.service = service;
    }


    @PostMapping("/localizacao")
    public String cadastrarLocalizacao(@Valid @ModelAttribute("localizacao") LocalizacaoDto dto,
                                       BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session, Model model) {

        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            return "localizacaocadastrar";
        }

        try {
            service.cadastrar(dto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            return "localizacaocadastrar";
        }

        redirectAttributes.addFlashAttribute("mensagem", "Localização cadastrada com sucesso.");

        return "redirect:/localizacaolista";
    }

    @PostMapping("/localizacaoatualizar")
    public String atualizarLocalizacao(@Valid @ModelAttribute("localizacao") LocalizacaoDto dto,
                                       BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session, Model model) {

        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            return "localizacaoatualizar";
        }

        try {
            service.atualizar(dto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            return "localizacaoatualizar";
        }

        redirectAttributes.addFlashAttribute("mensagem", "Localização atualizada com sucesso ");

        return "redirect:/localizacaolista";
    }

    @DeleteMapping("/localizacaoexcluir/{id}")
    public ResponseEntity<String> removerLocalizacao(@PathVariable Long id, HttpSession session) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso não autorizado.");
        }

        try {
            service.remover(id);
            return ResponseEntity.ok("Localização excluída com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}