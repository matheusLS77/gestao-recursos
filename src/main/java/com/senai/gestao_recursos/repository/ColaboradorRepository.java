package com.senai.gestao_recursos.repository;

import com.senai.gestao_recursos.entity.ColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorRepository extends JpaRepository<ColaboradorEntity, Long> {
}
