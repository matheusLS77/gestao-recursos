package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.ReservaDto;
import com.senai.gestao_recursos.service.ReservaService;
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
public class ReservaController {
    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @PostMapping("/reservas")
    public String cadastrarReserva(@Valid @ModelAttribute("reserva") ReservaDto dto, BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes, HttpSession session, Model model) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            return "reservacadastrar";
        }

        try {
            service.cadastrar(dto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            return "reservacadastrar";
        }

        redirectAttributes.addFlashAttribute("mensagem", "Reserva cadastrada com sucesso. ");

        return "redirect:/reservalista";
    }

    @PostMapping("/reservacancelar")
    public String cancelar(@Valid @ModelAttribute("reserva") ReservaDto dto, BindingResult bindingResult, HttpSession session,
                           RedirectAttributes redirectAttributes, Model model) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("reserva", dto);
            return "reservacancelar";
        }

        try {
            service.cancelar(dto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            return "reservacancelar";
        }

        redirectAttributes.addFlashAttribute("mensagem", "Reserva cancelada com sucesso");

        return "redirect:/reservalista";
    }

    @DeleteMapping("/reservasexcluir/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id, HttpSession session) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso não autorizado. ");
        }

        service.remover(id);
        return ResponseEntity.ok().body("Excluído. ");
    }
}
