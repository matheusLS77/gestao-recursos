package com.senai.gestao_recursos.service;

import com.senai.gestao_recursos.dto.LocalizacaoDto;
import com.senai.gestao_recursos.entity.LocalizacaoEntity;
import com.senai.gestao_recursos.repository.LocalizacaoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalizacaoService {
    private final LocalizacaoRepository repository;

    public LocalizacaoService(LocalizacaoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(LocalizacaoDto localizacaoDto) {
        repository.save(paraEntity(localizacaoDto));
    }


    public List<LocalizacaoDto> listar() {
        List<LocalizacaoEntity> localizacoes = repository.findAll();
        List<LocalizacaoDto> localizacaoDtos = new ArrayList<>();

        for (LocalizacaoEntity localizacao : localizacoes) {
            localizacaoDtos.add(paraDto(localizacao));
        }
        return localizacaoDtos;
    }

    public void atualizar(LocalizacaoDto dto) {
        LocalizacaoEntity retirada = repository.findById(dto.getId()).orElseThrow(() -> new IllegalArgumentException("Localização não encontrada"));

        retirada.setNome(dto.getNome());
        retirada.setEndereco(dto.getEndereco());

        repository.save(retirada);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    public LocalizacaoDto obterLocalizacao(Long id) {
        LocalizacaoEntity retirada = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Localização não encontrada"));

        return paraDto(retirada);
    }

    public LocalizacaoDto paraDto(LocalizacaoEntity entity) {
        LocalizacaoDto dto = new LocalizacaoDto();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEndereco(entity.getEndereco());

        return dto;
    }

    public LocalizacaoEntity paraEntity(LocalizacaoDto dto) {
        LocalizacaoEntity entity = new LocalizacaoEntity();

        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setEndereco(dto.getEndereco());

        return entity;
    }
}
