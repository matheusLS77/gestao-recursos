package com.senai.gestao_recursos.entity;

import com.senai.gestao_recursos.enums.TipoRecurso;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recurso")
public class RecursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String descricao;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoRecurso tipo;

    @Column
    private List<DayOfWeek> diasDaSemanaDisponivel = new ArrayList<>();

    @Column
    private LocalDate dataInicialAgendamento;

    @Column
    private LocalDate dataFinalAgendamento;

    @Column
    private LocalTime horaInicialAgendamento;

    @Column
    private LocalTime horaFinalAgendamento;

    public RecursoEntity(Long id, String descricao, TipoRecurso tipo, List<DayOfWeek> diasDaSemanaDisponivel, LocalDate dataInicial, LocalDate dataFinal, LocalTime horarioInicial, LocalTime horarioFinal) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.diasDaSemanaDisponivel = diasDaSemanaDisponivel;
        this.dataInicialAgendamento = dataInicial;
        this.dataFinalAgendamento = dataFinal;
        this.horaInicialAgendamento = horarioInicial;
        this.horaFinalAgendamento = horarioFinal;
    }

    public RecursoEntity() {
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
}
