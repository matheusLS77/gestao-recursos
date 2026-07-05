package com.senai.gestao_recursos.service;

import com.senai.gestao_recursos.dto.ColaboradorDto;
import com.senai.gestao_recursos.dto.ReservaDto;
import com.senai.gestao_recursos.entity.ColaboradorEntity;
import com.senai.gestao_recursos.entity.RecursoEntity;
import com.senai.gestao_recursos.entity.ReservaEntity;
import com.senai.gestao_recursos.repository.ColaboradorRepository;
import com.senai.gestao_recursos.repository.RecursoRepository;
import com.senai.gestao_recursos.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    private final RecursoRepository recursoRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final ReservaRepository reservaRepository;

    public ReservaService(RecursoRepository repository, ColaboradorRepository colaboradorRepository, ReservaRepository reservaRepository) {
        this.recursoRepository = repository;
        this.colaboradorRepository = colaboradorRepository;
        this.reservaRepository = reservaRepository;
    }

    public void cadastrar(ReservaDto dto) {
        ColaboradorEntity colaborador = colaboradorRepository.findById(dto.getColaboradorId())
                .orElseThrow(() -> new RuntimeException("Colaborador não existe"));

        RecursoEntity recurso = recursoRepository.findById(dto.getRecursoId())
                .orElseThrow(() -> new RuntimeException("Recurso não existe"));

        reservaRepository.save(paraEntity(dto, colaborador, recurso));
    }

    public List<ReservaDto> listar() {
        List<ReservaEntity> reservas = reservaRepository.findAll();
        List<ReservaDto> reservaDtos = new ArrayList<>();

        for (ReservaEntity reserva : reservas) {
            reservaDtos.add(paraDto(reserva));
        }
        return reservaDtos;
    }

    public void atualizar(ReservaDto dto) {
        Optional<ReservaEntity> reservaOp = reservaRepository.findById(dto.getId());

        ColaboradorEntity colaborador = colaboradorRepository.findById(dto.getColaboradorId()).orElseThrow();
        RecursoEntity recurso = recursoRepository.findById(dto.getRecursoId()).orElseThrow();

        if (reservaOp.isPresent()) {
            ReservaEntity reserva = reservaOp.get();

            reserva.setId(dto.getId());
            reserva.setColaborador(colaborador);
            reserva.setRecurso(recurso);
            reserva.setData(dto.getData());
            reserva.setHoraInicial(dto.getHoraInicial());
            reserva.setHoraFinal(dto.getHoraFinal());
            reserva.setDataCancelamento(dto.getDataCancelamento());
            reserva.setMotivoCancelamento(dto.getMotivoCancelamento());

            reservaRepository.save(reserva);
        }
    }

    public void remover(Long id) {
        reservaRepository.deleteById(id);
    }

    public ReservaDto paraDto(ReservaEntity entity) {
        ReservaDto dto = new ReservaDto();

        dto.setId(entity.getId());
        dto.setColaboradorId(entity.getColaborador().getId());
        dto.setRecursoId(entity.getRecurso().getId());
        dto.setData(entity.getData());
        dto.setHoraInicial(entity.getHoraInicial());
        dto.setHoraFinal(entity.getHoraFinal());
        dto.setDataCancelamento(entity.getDataCancelamento());
        dto.setMotivoCancelamento(entity.getMotivoCancelamento());

        return dto;
    }

    public ReservaEntity paraEntity(ReservaDto dto, ColaboradorEntity colaborador, RecursoEntity recurso) {
        ReservaEntity entity = new ReservaEntity();

        entity.setId(dto.getId());
        entity.setColaborador(colaborador);
        entity.setRecurso(recurso);
        entity.setData(dto.getData());
        entity.setHoraInicial(dto.getHoraInicial());
        entity.setHoraFinal(dto.getHoraFinal());
        entity.setDataCancelamento(dto.getDataCancelamento());
        entity.setMotivoCancelamento(dto.getMotivoCancelamento());

        return entity;
    }
}
