package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.ColaboradorDto;
import com.senai.gestao_recursos.repository.ColaboradorRepository;
import com.senai.gestao_recursos.service.ColaboradorService;
import com.senai.gestao_recursos.sessoes.SessaoDto;
import com.senai.gestao_recursos.sessoes.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @PostMapping("/login")
    public String realizarLogin(ColaboradorDto colaborador, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        ColaboradorDto colaboradorDto = service.realizarLogin(colaborador);

        if (colaboradorDto.getNome() == null) {
            model.addAttribute("erro", "E-mail ou senha inválidos. ");
            return "login";

        }
        SessaoDto sessaoDto = new SessaoDto();
        sessaoDto.setUsuarioId(colaboradorDto.getId());
        sessaoDto.setUsuarioNome(colaborador.getNome());

        SessaoUtil.RegistrarSessao(session, sessaoDto);

        redirectAttributes.addFlashAttribute("colaborador", " Bem-vindo ao sistema " + colaboradorDto.getNome() + "! ");

        return "redirect:/home";
    }

    @PostMapping("/colaboradores")
    public String cadastrarColaborador(@Valid @ModelAttribute("colaborador") ColaboradorDto dto,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "colaboradorcadastrar";
        }

        service.cadastrar(dto);
        redirectAttributes.addFlashAttribute("mensagem", "Colaborador cadastrado com sucesso ");

        return "redirect:/colaboradorlista";
    }

}
