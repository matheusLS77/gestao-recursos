package com.senai.gestao_recursos.service;

import com.senai.gestao_recursos.dto.ColaboradorDto;
import com.senai.gestao_recursos.entity.ColaboradorEntity;
import com.senai.gestao_recursos.repository.ColaboradorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        LocalDate hoje = LocalDate.now();

        if (dto.getDataNascimento().isBefore(hoje.minusYears(500))) {
            throw new IllegalArgumentException("A data de nascimento não pode ser anterior a 500 anos ");
        }

        if (repository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado ");
        }

        repository.save(paraEntity(dto));
    }

    public ColaboradorDto realizarLogin(ColaboradorDto colaboradorDto){
        Optional<ColaboradorEntity> colaboradorOp = repository.findByEmailAndSenha(colaboradorDto.getEmail(), colaboradorDto.getSenha());

        ColaboradorDto dto = new ColaboradorDto();

        if (colaboradorOp.isPresent()) {
            dto = paraDto(colaboradorOp.get());
        }

        return dto;
    }

    public List<ColaboradorDto> listar() {
        List<ColaboradorEntity> colaboradores = repository.findAll();
        List<ColaboradorDto> colaboradorDtos = new ArrayList<>();

        for (ColaboradorEntity colaborador : colaboradores) {
            colaboradorDtos.add(paraDto(colaborador));
        }
        return colaboradorDtos;
    }

    public void atualizar(ColaboradorDto dto) {
        Optional<ColaboradorEntity> colaboradorExistente = repository.findByEmail(dto.getEmail());

        if (colaboradorExistente.isPresent()) {
            if (colaboradorExistente.get().getEmail().equals(dto.getEmail()) && !colaboradorExistente.get().getId().equals(dto.getId())) {
                throw new IllegalArgumentException("E-mail já cadastrado ");
            }
        }

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

    public ColaboradorDto obterColaborador(Long id) {
        Optional<ColaboradorEntity> colabodorOp = repository.findById(id);

        ColaboradorDto colaborador = new ColaboradorDto();
        if (colabodorOp.isPresent()) {
            colaborador = paraDto(colabodorOp.get());
        }

        return colaborador;
    }

    public ColaboradorDto paraDto(ColaboradorEntity entity) {
        ColaboradorDto dto = new ColaboradorDto();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setMatricula(entity.getMatricula());

        return dto;
    }

    public ColaboradorEntity paraEntity(ColaboradorDto dto) {
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
