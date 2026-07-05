package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.RecursoDto;
import com.senai.gestao_recursos.entity.RecursoEntity;
import com.senai.gestao_recursos.repository.RecursoRepository;
import com.senai.gestao_recursos.sessoes.SessaoDto;
import com.senai.gestao_recursos.sessoes.SessaoUtil;
import jakarta.servlet.http.HttpSession;
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
    public String getCadastrar(Model model, HttpSession session) {
        SessaoDto sessaoDto = SessaoUtil.obterSessao(session);

        if (sessaoDto == null) {
            return "redirect:/login";
        }

        model.addAttribute("recurso", new RecursoDto());

        return "recursocadastrar";
    }

    @GetMapping("/recursolista")
    public String getRecursos(Model model, HttpSession session) {
        SessaoDto sessaoDto = SessaoUtil.obterSessao(session);

        if (sessaoDto == null) {
            return "redirect:/login";
        }

        List<RecursoEntity> recursos = recursoRepository.findAll();

        model.addAttribute("recursos", recursos);

        return "recursolista";
    }
}
