package com.senai.gestao_recursos.repository;

import com.senai.gestao_recursos.entity.RecursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecursoRepository extends JpaRepository<RecursoEntity, Long> {
}
