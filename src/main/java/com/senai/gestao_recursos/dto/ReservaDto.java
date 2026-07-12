package com.senai.gestao_recursos.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaDto {
    private Long id;

    @NotNull(message = "Colaborador é obrigatório")
    private Long colaboradorId;

    @NotNull(message = "Recurso é obrigatório")
    private Long recursoId;

    @NotNull(message = "Data é obrigatória")
    private LocalDate data;

    @NotNull(message = "Hora inicial é obrigatória")
    private LocalTime horaInicial;

    @NotNull(message = "Hora final é obrigatória")
    private LocalTime horaFinal;

    @NotNull(message = "Retirada é obrigatória")
    private Long retiradaId;

    private LocalDate dataCancelamento;
    private String motivoCancelamento;

    private String retiradaEndereco;
    private String colaboradorNome;
    private String recursoDescricao;

    public ReservaDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getColaboradorId() {
        return colaboradorId;
    }

    public void setColaboradorId(Long colaboradorId) {
        this.colaboradorId = colaboradorId;
    }

    public Long getRecursoId() {
        return recursoId;
    }

    public void setRecursoId(Long recursoId) {
        this.recursoId = recursoId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(LocalTime horaInicial) {
        this.horaInicial = horaInicial;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public LocalDate getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDate dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public String getColaboradorNome() {
        return colaboradorNome;
    }

    public void setColaboradorNome(String colaboradorNome) {
        this.colaboradorNome = colaboradorNome;
    }

    public String getRecursoDescricao() {
        return recursoDescricao;
    }

    public void setRecursoDescricao(String recursoDescricao) {
        this.recursoDescricao = recursoDescricao;
    }

    public Long getRetiradaId() {
        return retiradaId;
    }

    public void setRetiradaId(Long retiradaId) {
        this.retiradaId = retiradaId;
    }

    public String getRetiradaEndereco() {
        return retiradaEndereco;
    }

    public void setRetiradaEndereco(String retiradaEndereco) {
        this.retiradaEndereco = retiradaEndereco;
    }
}