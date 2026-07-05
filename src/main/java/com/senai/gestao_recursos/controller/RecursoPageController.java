package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.RecursoDto;
import com.senai.gestao_recursos.entity.RecursoEntity;
import com.senai.gestao_recursos.repository.RecursoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RecursoPageController {

    private final RecursoRepository recursoRepository;

    public RecursoPageController(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }

    @GetMapping("/recursocadastrar")
    public String getCadastrar(Model model) {
        model.addAttribute("recurso", new RecursoDto());

        return "recursocadastrar";
    }

    @GetMapping("/recursolista")
    public String getRecursos(Model model) {
        List<RecursoEntity> recursos = recursoRepository.findAll();

        model.addAttribute("recursos", recursos);

        return "recursolista";
    }
}
