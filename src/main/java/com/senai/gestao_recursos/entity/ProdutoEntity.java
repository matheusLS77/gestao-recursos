package com.senai.gestao_recursos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @NotBlank
    private String descricao;

    @Column
    @NotBlank
    private String tipo;


//    @ElementCollection
//    @CollectionTable(
//            name = "agenda_dias_disponiveis",
//            joinColumns = @JoinColumn(name = "agenda_id")
//    )
//    @OrderColumn(name = "dia")
//    @Column(name = "disponivel")
//    private List<Boolean> diasDaSemanaDisponivel =
//            new ArrayList<>(Arrays.asList(false, false, false, false, false, false, false));

    @Column
    private LocalDate dataInicial;

    @Column
    private LocalDate dataFinal;

    @Column
    private LocalTime horarioInicial;

    @Column
    private LocalTime horarioFinal;

    public ProdutoEntity(Long id, String descricao, String tipo, List<Boolean> diasDaSemanaDisponivel, LocalDate dataInicial, LocalDate dataFinal, LocalTime horarioInicial, LocalTime horarioFinal) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
 //       this.diasDaSemanaDisponivel = diasDaSemanaDisponivel;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.horarioInicial = horarioInicial;
        this.horarioFinal = horarioFinal;
    }

    public ProdutoEntity() {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

//    public List<Boolean> getDiasDaSemanaDisponivel() {
//        return diasDaSemanaDisponivel;
//    }
//
//    public void setDiasDaSemanaDisponivel(List<Boolean> diasDaSemanaDisponivel) {
//        this.diasDaSemanaDisponivel = diasDaSemanaDisponivel;
//    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public LocalTime getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(LocalTime horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public LocalTime getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(LocalTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }
}
