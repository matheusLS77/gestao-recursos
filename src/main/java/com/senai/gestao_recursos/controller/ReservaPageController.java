package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.CancelamentoDto;
import com.senai.gestao_recursos.dto.ReservaDto;
import com.senai.gestao_recursos.service.ColaboradorService;
import com.senai.gestao_recursos.service.RecursoService;
import com.senai.gestao_recursos.service.ReservaService;
import com.senai.gestao_recursos.service.LocalizacaoService;
import com.senai.gestao_recursos.sessoes.SessaoDto;
import com.senai.gestao_recursos.sessoes.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ReservaPageController {
    private final ReservaService reservaService;
    private final RecursoService recursoService;
    private final ColaboradorService colaboradorService;

    public ReservaPageController(ReservaService reservaService, RecursoService recursoService, ColaboradorService colaboradorService) {
        this.reservaService = reservaService;
        this.recursoService = recursoService;
        this.colaboradorService = colaboradorService;
    }


    @GetMapping("/reservacadastrar")
    public String getCadastrar(Model model, HttpSession session) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("reserva", new ReservaDto());

        model.addAttribute("colaboradores", colaboradorService.listar());
        model.addAttribute("recursos", recursoService.listar());

        return "reservacadastrar";
    }

    @GetMapping("/reservalista")
    public String getColaboradores(Model model, HttpSession session) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        List<ReservaDto> reservas = reservaService.listar();
        model.addAttribute("reservas", reservas);

        return "reservalista";
    }

    @GetMapping("/reservavisualizar/{id}")
    public String getVisualizar(Model model, HttpSession session, @PathVariable Long id) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        ReservaDto reserva = reservaService.obterReserva(id);
        model.addAttribute("reserva", reserva);

        return "reservavisualizar";
    }

    @GetMapping("/reservacancelar/{id}")
    public String getCancelar(Model model, HttpSession session, @PathVariable Long id) {
        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        CancelamentoDto reserva = new CancelamentoDto();
        reserva.setId(id);
        model.addAttribute("reserva", reserva);

        return "reservacancelar";
    }
}
