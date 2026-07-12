package com.senai.gestao_recursos.service;

import com.senai.gestao_recursos.dto.RetiradaDto;
import com.senai.gestao_recursos.entity.RetiradaEntity;
import com.senai.gestao_recursos.repository.RetiradaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RetiradaService {
    private final RetiradaRepository repository;

    public RetiradaService(RetiradaRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(RetiradaDto retiradaDto) {
        repository.save(paraEntity(retiradaDto));
    }


    public List<RetiradaDto> listar() {
        List<RetiradaEntity> locaisRetirada = repository.findAll();
        List<RetiradaDto> retiradaDtos = new ArrayList<>();

        for (RetiradaEntity retirada : locaisRetirada) {
            retiradaDtos.add(paraDto(retirada));
        }
        return retiradaDtos;
    }

    public void atualizar(RetiradaDto dto) {
        RetiradaEntity retirada = repository.findById(dto.getId()).orElseThrow(() -> new IllegalArgumentException("Retirada não encontrada"));

        retirada.setNome(dto.getNome());
        retirada.setEndereco(dto.getEndereco());

        repository.save(paraEntity(dto));

    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    public RetiradaDto obterRetirada(Long id) {
        RetiradaEntity retirada = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Retirada não encontrada"));

        return paraDto(retirada);
    }

    public RetiradaDto paraDto(RetiradaEntity entity) {
        RetiradaDto dto = new RetiradaDto();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEndereco(entity.getEndereco());

        return dto;
    }

    public RetiradaEntity paraEntity(RetiradaDto dto) {
        RetiradaEntity entity = new RetiradaEntity();

        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setEndereco(dto.getEndereco());

        return entity;
    }
}
