package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.ColaboradorDto;
import com.senai.gestao_recursos.dto.RecursoDto;
import com.senai.gestao_recursos.entity.RecursoEntity;
import com.senai.gestao_recursos.repository.RecursoRepository;
import com.senai.gestao_recursos.service.LocalizacaoService;
import com.senai.gestao_recursos.service.RecursoService;
import com.senai.gestao_recursos.sessoes.SessaoDto;
import com.senai.gestao_recursos.sessoes.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RecursoPageController {

    private final RecursoService recursoService;
    private final LocalizacaoService localizacaoService;

    public RecursoPageController(RecursoService recursoService, LocalizacaoService localizacaoService) {
        this.recursoService = recursoService;
        this.localizacaoService = localizacaoService;
    }

    @GetMapping("/recursocadastrar")
    public String getCadastrar(Model model, HttpSession session) {
        SessaoDto sessaoDto = SessaoUtil.obterSessao(session);

        if (sessaoDto == null) {
            return "redirect:/login";
        }

        model.addAttribute("recurso", new RecursoDto());
        model.addAttribute("localizacoes", localizacaoService.listar());

        return "recursocadastrar";
    }

    @GetMapping("/recursoatualizar/{id}")
    public String getAtualizar(Model model, HttpSession session, @PathVariable Long id) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        RecursoDto recurso = recursoService.obterRecurso(id);

        model.addAttribute("recurso", recurso);
        model.addAttribute("localizacoes", localizacaoService.listar());

        return "recursoatualizar";
    }

    @GetMapping("/recursolista")
    public String getRecursos(Model model, HttpSession session) {
        SessaoDto sessaoDto = SessaoUtil.obterSessao(session);

        if (sessaoDto == null) {
            return "redirect:/login";
        }

        List<RecursoDto> recursos = recursoService.listar();

        model.addAttribute("recursos", recursos);

        return "recursolista";
    }
}
