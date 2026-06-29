package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.ProdutoDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProdutoPageController {

    @GetMapping("/produtocadastrar")
    public String getCadastrar(Model model) {
        model.addAttribute("produto", new ProdutoDto());

        return "produtocadastrar";
    }
}
