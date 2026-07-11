package com.senai.gestao_recursos.repository;

import com.senai.gestao_recursos.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
    boolean existsByRecursoIdAndDataAndHoraInicialLessThanEqualAndHoraFinalGreaterThanEqual(
            Long recursoId, LocalDate data, LocalTime horaFinal, LocalTime horaInicial);
    List<ReservaEntity> findByColaboradorId(Long colaboradorId);
}
