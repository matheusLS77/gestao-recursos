package com.senai.gestao_recursos.service;

import com.senai.gestao_recursos.dto.RecursoDto;
import com.senai.gestao_recursos.entity.RecursoEntity;
import com.senai.gestao_recursos.repository.RecursoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecursoService {

    private final RecursoRepository repository;
    public RecursoService(RecursoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(RecursoDto dto){
        if (dto.getDiasDaSemanaDisponivel() == null) {
            dto.setDiasDaSemanaDisponivel(new ArrayList<>());
        }

        RecursoEntity entity = paraEntity(dto);

        repository.save(entity);
    }

    public List<RecursoDto> listar() {
        List<RecursoEntity> recursos = repository.findAll();
        List<RecursoDto> recursoDtos = new ArrayList<>();

        for (RecursoEntity recurso : recursos) {
            recursoDtos.add(paraDto(recurso));
        }
        return recursoDtos;
    }

    public void atualizar(RecursoDto dto) {
        Optional<RecursoEntity> recursoOp = repository.findById(dto.getId());

        if (recursoOp.isPresent()) {
            RecursoEntity recurso = recursoOp.get();

            recurso.setDescricao(dto.getDescricao());
            recurso.setTipo(dto.getTipo());
            recurso.setDiasDaSemanaDisponivel(dto.getDiasDaSemanaDisponivel());
            recurso.setDataInicialAgendamento(dto.getDataInicialAgendamento());
            recurso.setDataFinalAgendamento(dto.getDataFinalAgendamento());
            recurso.setHoraInicialAgendamento(dto.getHoraInicialAgendamento());
            recurso.setHoraFinalAgendamento(dto.getHoraFinalAgendamento());

            repository.save(recurso);
        }
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    public RecursoEntity paraEntity(RecursoDto dto){
        RecursoEntity entity = new RecursoEntity();

        entity.setId(dto.getId());
        entity.setDescricao(dto.getDescricao());
        entity.setTipo(dto.getTipo());
        entity.setDiasDaSemanaDisponivel(dto.getDiasDaSemanaDisponivel());
        entity.setDataInicialAgendamento(dto.getDataInicialAgendamento());
        entity.setDataFinalAgendamento(dto.getDataFinalAgendamento());
        entity.setHoraInicialAgendamento(dto.getHoraInicialAgendamento());
        entity.setHoraFinalAgendamento(dto.getHoraFinalAgendamento());

        return entity;
    }

    public RecursoDto paraDto(RecursoEntity entity) {
        RecursoDto dto = new RecursoDto();

        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        dto.setTipo(entity.getTipo());
        dto.setDiasDaSemanaDisponivel(entity.getDiasDaSemanaDisponivel());
        dto.setDataInicialAgendamento(entity.getDataInicialAgendamento());
        dto.setDataFinalAgendamento(entity.getDataFinalAgendamento());
        dto.setHoraInicialAgendamento(entity.getHoraInicialAgendamento());
        dto.setHoraFinalAgendamento(entity.getHoraFinalAgendamento());

        return dto;
    }
}
