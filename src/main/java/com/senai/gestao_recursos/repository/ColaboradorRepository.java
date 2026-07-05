package com.senai.gestao_recursos.repository;

import com.senai.gestao_recursos.entity.ColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColaboradorRepository extends JpaRepository<ColaboradorEntity, Long> {
    Optional<ColaboradorEntity> findByEmailAndSenha(String email, String senha);
    boolean existsByEmail(String email);

}
