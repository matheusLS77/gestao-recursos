package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.RetiradaDto;
import com.senai.gestao_recursos.service.RetiradaService;
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
public class RetiradaController {
    private final RetiradaService service;

    public RetiradaController(RetiradaService service) {
        this.service = service;
    }


    @PostMapping("/retirada")
    public String cadastrarRetirada(@Valid @ModelAttribute("retirada") RetiradaDto dto,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session, Model model) {

        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            return "retiradacadastrar";
        }

        try {
            service.cadastrar(dto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            return "retiradacadastrar";
        }

        redirectAttributes.addFlashAttribute("mensagem", "Retirada cadastrada com sucesso.");

        return "redirect:/retiradalista";
    }

    @PostMapping("/retiradaatualizar")
    public String atualizarRetirada(@Valid @ModelAttribute("retirada") RetiradaDto dto,
                                       BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session, Model model) {

        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            return "retiradaatualizar";
        }

        try {
            service.atualizar(dto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            return "retiradaatualizar";
        }

        redirectAttributes.addFlashAttribute("mensagem", "Retirada atualizada com sucesso ");

        return "redirect:/retiradalista";
    }

    @DeleteMapping("/retiradaexcluir/{id}")
    public ResponseEntity<String> removerRetirada(@PathVariable Long id, HttpSession session) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso não autorizado.");
        }

        try {
            service.remover(id);
            return ResponseEntity.ok("Retirada excluída com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}