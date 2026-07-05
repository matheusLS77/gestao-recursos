package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.RecursoDto;
import com.senai.gestao_recursos.service.RecursoService;
import com.senai.gestao_recursos.sessoes.SessaoDto;
import com.senai.gestao_recursos.sessoes.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RecursoController {

    private final RecursoService recursoService;

    public RecursoController(RecursoService recursoService) {
        this.recursoService = recursoService;
    }

    @PostMapping("/recursos")
    private String cadastrarProduto(@Valid @ModelAttribute("recurso") RecursoDto dto,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session){

        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        if(bindingResult.hasErrors()){
            return "recursocadastrar";
        }

        recursoService.cadastrar(dto);
        redirectAttributes.addFlashAttribute("mensagem", "Recurso cadastrado com sucesso ");

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
