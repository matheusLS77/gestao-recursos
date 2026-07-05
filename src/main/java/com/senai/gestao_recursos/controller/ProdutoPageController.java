package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.ColaboradorDto;
import com.senai.gestao_recursos.dto.ProdutoDto;
import com.senai.gestao_recursos.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProdutoPageController {

    private final ProdutoService service;

    public ProdutoPageController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/produtocadastrar")
    public String getCadastrar(Model model) {
        model.addAttribute("produtos", new ProdutoDto());

        return "produtocadastrar";
    }

    @GetMapping("/produtolista")
    public String getColaboradores(Model model) {
        List<ProdutoDto> produtoDtos = service.listarProdutos();

        model.addAttribute("produtos", produtoDtos);

        return "produtolista";
    }
}
