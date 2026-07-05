package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.RecursoDto;
import com.senai.gestao_recursos.service.RecursoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "recursocadastrar";
        }

        recursoService.cadastrar(dto);
        redirectAttributes.addFlashAttribute("mensagem", "Recurso cadastrado com sucesso ");

        return "redirect:/recursolista";
    }

}
