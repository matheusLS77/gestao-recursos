package com.senai.gestao_recursos.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ColaboradorDto {
    private Long id;

    @NotBlank(message = "Informe uma senha ")
    private String nome;

    @Email
    private String email;

    @NotBlank(message = "Informe uma senha ")
    @Size(min = 5)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$", message = "A senha deve ter no mínimo 5 caracteres, conter números e letras")
    private String senha;

    @NotBlank(message = "Informe o número da matrícula  ")
    private String matricula;

    @PastOrPresent
    private LocalDate dataNascimento;

    public ColaboradorDto() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}