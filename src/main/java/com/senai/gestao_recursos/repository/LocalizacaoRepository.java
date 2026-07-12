package com.senai.gestao_recursos.repository;

import com.senai.gestao_recursos.entity.LocalizacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizacaoRepository extends JpaRepository<LocalizacaoEntity, Long> {
}
