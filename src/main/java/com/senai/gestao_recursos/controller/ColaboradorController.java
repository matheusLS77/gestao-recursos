package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.ColaboradorDto;
import com.senai.gestao_recursos.repository.ColaboradorRepository;
import com.senai.gestao_recursos.service.ColaboradorService;
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
public class ColaboradorController {
    private final ColaboradorService service;

    public ColaboradorController(ColaboradorService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public String realizarLogin(ColaboradorDto colaborador, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        ColaboradorDto colaboradorLogado = service.realizarLogin(colaborador);

        if (colaboradorLogado == null || colaboradorLogado.getId() == null) {
            model.addAttribute("erro", "E-mail ou senha inválidos. ");

            return "login";
        }

        SessaoDto sessaoDto = new SessaoDto();
        sessaoDto.setUsuarioId(colaboradorLogado.getId());
        sessaoDto.setUsuarioNome(colaboradorLogado.getNome());
        SessaoUtil.registrarSessao(session, sessaoDto);

        redirectAttributes.addFlashAttribute("mensagem", " Bem-vindo ao sistema " + colaboradorLogado.getNome() + "! ");

        return "redirect:/home";
    }

    @PostMapping("/colaboradores")
    public String cadastrarColaborador(@Valid @ModelAttribute("colaborador") ColaboradorDto dto,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {

        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            return "colaboradorcadastrar";
        }

        service.cadastrar(dto);
        redirectAttributes.addFlashAttribute("mensagem", "Colaborador cadastrado com sucesso ");

        return "redirect:/colaboradorlista";
    }

    @PostMapping("/colaboradoratualizar")
    public String atualizarrColaborador(@Valid @ModelAttribute("colaborador") ColaboradorDto dto,
                                       BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {

        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            return "colaboradoratualizar";
        }

        service.atualizar(dto);
        redirectAttributes.addFlashAttribute("mensagem", "Colaborador atualizado com sucesso ");

        return "redirect:/colaboradorlista";
    }

    @DeleteMapping("/colaboradoresexcluir/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id, HttpSession session) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso não autorizado");
        }

        service.remover(id);
        return ResponseEntity.ok().body("Excluído ");
    }
}