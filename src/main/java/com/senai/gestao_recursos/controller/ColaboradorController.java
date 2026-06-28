package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.ColaboradorDto;
import com.senai.gestao_recursos.repository.ColaboradorRepository;
import com.senai.gestao_recursos.service.ColaboradorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ColaboradorController {
    private final ColaboradorService service;
    private final ColaboradorRepository repository;

    public ColaboradorController(ColaboradorService service, ColaboradorRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping("/colaboradores")
    public String cadastrarProduto(@Valid @ModelAttribute("colaborador") ColaboradorDto dto,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "colaboradorcadastrar";
        }

        service.cadastrar(dto);
        redirectAttributes.addFlashAttribute("mensagem", "Colaborador cadastrado com sucesso ");

        return "redirect:/colaboradorlista";
    }

}
