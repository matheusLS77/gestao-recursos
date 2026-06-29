package com.senai.gestao_recursos.service;

import com.senai.gestao_recursos.dto.ColaboradorDto;
import com.senai.gestao_recursos.entity.ColaboradorEntity;
import com.senai.gestao_recursos.repository.ColaboradorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {
    private final ColaboradorRepository repository;

    public ColaboradorService(ColaboradorRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(ColaboradorDto dto) {
        repository.save(toEntity(dto));
    }

    public ColaboradorDto realizarLogin(ColaboradorDto usuarioDto){
        Optional<ColaboradorEntity> colaboradorOp = repository.findByEmailAndSenha(usuarioDto.getEmail(), usuarioDto.getSenha());

        ColaboradorDto dto = new ColaboradorDto();

        if (colaboradorOp.isPresent()) {
            dto = toDto(colaboradorOp.get());
        }

        return dto;
    }

    public List<ColaboradorDto> listar() {
        List<ColaboradorEntity> colaboradores = repository.findAll();
        List<ColaboradorDto> colaboradorDtos = new ArrayList<>();

        for (ColaboradorEntity colaborador : colaboradores) {
            colaboradorDtos.add(toDto(colaborador));
        }
        return colaboradorDtos;
    }

    public void atualizar(ColaboradorDto dto) {
        Optional<ColaboradorEntity> colaboradorOp = repository.findById(dto.getId());

        if (colaboradorOp.isPresent()) {
            ColaboradorEntity colaborador = colaboradorOp.get();

            colaborador.setNome(dto.getNome());
            colaborador.setEmail(dto.getEmail());
            colaborador.setDataNascimento(dto.getDataNascimento());
            colaborador.setSenha(dto.getSenha());
            colaborador.setMatricula(dto.getMatricula());

            repository.save(colaborador);
        }
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    public ColaboradorDto toDto(ColaboradorEntity entity) {
        ColaboradorDto dto = new ColaboradorDto();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setMatricula(entity.getMatricula());

        return dto;
    }

    public ColaboradorEntity toEntity(ColaboradorDto dto) {
        ColaboradorEntity entity = new ColaboradorEntity();

        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setSenha(dto.getSenha());
        entity.setMatricula(dto.getMatricula());

        return entity;
    }
}
