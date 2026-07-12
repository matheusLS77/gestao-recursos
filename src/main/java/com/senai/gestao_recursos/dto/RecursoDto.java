package com.senai.gestao_recursos.dto;

import com.senai.gestao_recursos.enums.TipoRecurso;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RecursoDto {

    private Long id;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotNull(message = "O tipo do recurso é obrigatório.")
    private TipoRecurso tipo;

    private List<DayOfWeek> diasDaSemanaDisponivel = new ArrayList<>();

    @NotNull(message = "A localização é obrigatória.")
    private Long localizacaoId;

    private String localizacaoEndereco;

    @FutureOrPresent(message = "Deve ser uma data futura ou presente")
    private LocalDate dataInicialAgendamento;

    @FutureOrPresent(message = "Deve ser uma data futura ou presente")
    private LocalDate dataFinalAgendamento;

    private LocalTime horaInicialAgendamento;
    private LocalTime horaFinalAgendamento;

    public RecursoDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoRecurso getTipo() {
        return tipo;
    }

    public void setTipo(TipoRecurso tipo) {
        this.tipo = tipo;
    }

    public List<DayOfWeek> getDiasDaSemanaDisponivel() {
        return diasDaSemanaDisponivel;
    }

    public void setDiasDaSemanaDisponivel(List<DayOfWeek> diasDaSemanaDisponivel) {
        this.diasDaSemanaDisponivel = diasDaSemanaDisponivel;
    }

    public LocalDate getDataInicialAgendamento() {
        return dataInicialAgendamento;
    }

    public void setDataInicialAgendamento(LocalDate dataInicialAgendamento) {
        this.dataInicialAgendamento = dataInicialAgendamento;
    }

    public LocalDate getDataFinalAgendamento() {
        return dataFinalAgendamento;
    }

    public void setDataFinalAgendamento(LocalDate dataFinalAgendamento) {
        this.dataFinalAgendamento = dataFinalAgendamento;
    }

    public LocalTime getHoraInicialAgendamento() {
        return horaInicialAgendamento;
    }

    public void setHoraInicialAgendamento(LocalTime horaInicialAgendamento) {
        this.horaInicialAgendamento = horaInicialAgendamento;
    }

    public LocalTime getHoraFinalAgendamento() {
        return horaFinalAgendamento;
    }

    public void setHoraFinalAgendamento(LocalTime horaFinalAgendamento) {
        this.horaFinalAgendamento = horaFinalAgendamento;
    }

    public String getLocalizacaoEndereco() {
        return localizacaoEndereco;
    }

    public void setLocalizacaoEndereco(String localizacaoEndereco) {
        this.localizacaoEndereco = localizacaoEndereco;
    }

    public Long getLocalizacaoId() {
        return localizacaoId;
    }

    public void setLocalizacaoId(Long localizacaoId) {
        this.localizacaoId = localizacaoId;
    }
}
