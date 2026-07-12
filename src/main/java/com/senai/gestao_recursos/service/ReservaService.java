package com.senai.gestao_recursos.service;

import com.senai.gestao_recursos.dto.CancelamentoDto;
import com.senai.gestao_recursos.dto.ReservaDto;
import com.senai.gestao_recursos.entity.ColaboradorEntity;
import com.senai.gestao_recursos.entity.RecursoEntity;
import com.senai.gestao_recursos.entity.ReservaEntity;
import com.senai.gestao_recursos.entity.LocalizacaoEntity;
import com.senai.gestao_recursos.repository.ColaboradorRepository;
import com.senai.gestao_recursos.repository.RecursoRepository;
import com.senai.gestao_recursos.repository.ReservaRepository;
import com.senai.gestao_recursos.repository.LocalizacaoRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
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
                .orElseThrow(() -> new RuntimeException("Colaborador não cadastrado"));

        RecursoEntity recurso = recursoRepository.findById(dto.getRecursoId())
                .orElseThrow(() -> new RuntimeException("Recurso não cadastrado"));

        DayOfWeek diaReserva = dto.getData().getDayOfWeek();

        if (dto.getData().isBefore(recurso.getDataInicialAgendamento()) || dto.getData().isAfter(recurso.getDataFinalAgendamento())) {
            throw new IllegalArgumentException("A data da reserva não está dentro da data disponível do recurso.");
        }

        if (!recurso.getDiasDaSemanaDisponivel().contains(diaReserva)) {
            throw new IllegalArgumentException("O recurso não está disponível neste dia da semana");
        }

        if (dto.getHoraInicial().isBefore(recurso.getHoraInicialAgendamento()) || dto.getHoraFinal().isAfter(recurso.getHoraFinalAgendamento())) {
            throw new IllegalArgumentException("O horário da reserva está fora do horário disponível do recurso");
        }

        if (!dto.getHoraFinal().isAfter(dto.getHoraInicial())) {
            throw new IllegalArgumentException("A hora final não pode ser anterior à hora inicial.");
        }

        boolean reserva = reservaRepository.existsByRecursoIdAndDataAndHoraInicialLessThanEqualAndHoraFinalGreaterThanEqual(
                dto.getRecursoId(), dto.getData(), dto.getHoraFinal(), dto.getHoraInicial()
        );

        if (reserva) {
            throw new IllegalArgumentException("Este recurso já está reservado nesse horário");
        }

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

    public void cancelar(CancelamentoDto dto) {
        ReservaEntity reserva = reservaRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada"));

        if (reserva.getDataCancelamento() != null) {
            throw new IllegalArgumentException("Reserva já cancelada.");
        }

        if (LocalDate.now().isAfter(reserva.getData().minusDays(1))) {
            throw new IllegalArgumentException("Cancelamento só até 1 dia antes.");
        }

        if (dto.getMotivoCancelamento() == null || dto.getMotivoCancelamento().isBlank()) {
            throw new IllegalArgumentException("Motivo do cancelamento é obrigatório.");
        }

        reserva.setDataCancelamento(LocalDate.now());
        reserva.setMotivoCancelamento(dto.getMotivoCancelamento());

        reservaRepository.save(reserva);
    }

    public void remover(Long id) {
        reservaRepository.deleteById(id);
    }

    public ReservaDto obterReserva(Long id) {
        Optional<ReservaEntity> reservaOP = reservaRepository.findById(id);

        ReservaDto dto = new ReservaDto();

        if (reservaOP.isPresent()) {
            ReservaEntity reserva = reservaOP.get();

            dto = paraDto(reserva);
            dto.setDataCancelamento(reserva.getDataCancelamento());
            dto.setMotivoCancelamento(reserva.getMotivoCancelamento());
        }

        return dto;
    }

    public ReservaDto paraDto(ReservaEntity entity) {
        ReservaDto dto = new ReservaDto();

        dto.setId(entity.getId());
        dto.setColaboradorId(entity.getColaborador().getId());
        dto.setRecursoId(entity.getRecurso().getId());
        dto.setData(entity.getData());
        dto.setHoraInicial(entity.getHoraInicial());
        dto.setHoraFinal(entity.getHoraFinal());
        dto.setColaboradorNome(entity.getColaborador().getNome());
        dto.setRecursoDescricao(entity.getRecurso().getDescricao());

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

        return entity;
    }
}
