package com.senai.gestao_recursos.dto;

import jakarta.validation.constraints.NotBlank;

public class CancelamentoDto {
    private Long id;

    @NotBlank(message = "Motivo do cancelamento é obrigatório")
    private String motivoCancelamento;


    public CancelamentoDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }
}