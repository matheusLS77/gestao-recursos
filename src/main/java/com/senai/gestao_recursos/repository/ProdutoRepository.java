package com.senai.gestao_recursos.repository;

import com.senai.gestao_recursos.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
