package com.senai.gestao_recursos.service;

import com.senai.gestao_recursos.dto.ColaboradorDto;
import com.senai.gestao_recursos.entity.ColaboradorEntity;
import com.senai.gestao_recursos.repository.ColaboradorRepository;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorService {
    private final ColaboradorRepository repository;

    public ColaboradorService(ColaboradorRepository repository) {
        this.repository = repository;
    }


}
