package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.ColaboradorDto;
import com.senai.gestao_recursos.dto.RecursoDto;
import com.senai.gestao_recursos.service.LocalizacaoService;
import com.senai.gestao_recursos.service.RecursoService;
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
public class RecursoController {

    private final RecursoService recursoService;
    private final LocalizacaoService localizacaoService;

    public RecursoController(RecursoService recursoService, LocalizacaoService localizacaoService) {
        this.recursoService = recursoService;
        this.localizacaoService = localizacaoService;
    }

    @PostMapping("/recurso")
    private String cadastrarRecurso(@Valid @ModelAttribute("recurso") RecursoDto dto, BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes, HttpSession session, Model model) {

        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        if(bindingResult.hasErrors()) {
            model.addAttribute("localizacoes", localizacaoService.listar());
            return "recursocadastrar";
        }

        try {
            recursoService.cadastrar(dto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());

            model.addAttribute("localizacoes", localizacaoService.listar());

            return "recursocadastrar";
        }

        redirectAttributes.addFlashAttribute("mensagem", "Recurso cadastrado com sucesso ");

        return "redirect:/recursolista";
    }

    @PostMapping("/recursoatualizar")
    public String atualizarRecurso(@Valid @ModelAttribute("recurso") RecursoDto dto, BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes, HttpSession session, Model model) {

        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("localizacoes", localizacaoService.listar());
            return "recursoatualizar";
        }

        try {
            recursoService.atualizar(dto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());

            model.addAttribute("localizacoes", localizacaoService.listar());

            return "recursoatualizar";
        }

        redirectAttributes.addFlashAttribute("mensagem", "Recurso atualizado com sucesso ");

        return "redirect:/recursolista";
    }

    @DeleteMapping("/recursosexcluir/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id, HttpSession session) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso não autorizado");
        }

        recursoService.remover(id);
        return ResponseEntity.ok().body("Excluído ");
    }

}
