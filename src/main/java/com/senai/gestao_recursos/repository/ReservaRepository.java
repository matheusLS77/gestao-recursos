package com.senai.gestao_recursos.repository;

import com.senai.gestao_recursos.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
}
