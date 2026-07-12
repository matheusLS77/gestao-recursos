package com.senai.gestao_recursos.dto;

import jakarta.validation.constraints.NotBlank;

public class LocalizacaoDto {
    private Long id;
    private String nome;

    @NotBlank(message = "O endereço é obrigatório")
    private String endereco;

    public LocalizacaoDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
