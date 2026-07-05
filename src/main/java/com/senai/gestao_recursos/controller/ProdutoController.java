package com.senai.gestao_recursos.controller;

import com.senai.gestao_recursos.dto.ProdutoDto;
import com.senai.gestao_recursos.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/produtos")
    private String cadastrarProduto(@Valid @ModelAttribute("produto") ProdutoDto dto,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "produtocadastrar";
        }

        produtoService.cadastrarProduto(dto);
        redirectAttributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso ");

        return "redirect:/produtolista";
    }

    @GetMapping("/produtos")
    private String listarProdutos(){
        return null;
    }
}
