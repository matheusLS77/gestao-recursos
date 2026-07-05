package com.senai.gestao_recursos.service;

import com.senai.gestao_recursos.dto.ProdutoDto;
import com.senai.gestao_recursos.entity.ProdutoEntity;
import com.senai.gestao_recursos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void cadastrarProduto(ProdutoDto dto){
        ProdutoEntity entity = paraEntity(dto);

        produtoRepository.save(entity);
        System.out.println("Produto Cadastrado");
    }

    public List<ProdutoDto> listarProdutos(){
        List<ProdutoEntity> produtoEntities = produtoRepository.findAll();
        List<ProdutoDto> dtoList = new ArrayList<>();

        for(ProdutoEntity e : produtoEntities){
            dtoList.add(paraDto(e));
        }
        return dtoList;
    }

    public ProdutoEntity paraEntity(ProdutoDto dto){
        ProdutoEntity entity = new ProdutoEntity();

        entity.setId(dto.getId());
        entity.setDescricao(dto.getDescricao());
        entity.setTipo(dto.getTipo());
//        entity.setDiasDaSemanaDisponivel(dto.getDiasDaSemanaDisponivel());
        entity.setDataInicial(dto.getDataInicial());
        entity.setDataFinal(dto.getDataFinal());
        entity.setHorarioInicial(dto.getHorarioInicial());
        entity.setHorarioFinal(dto.getHorarioFinal());

        return entity;
    }

    public ProdutoDto paraDto(ProdutoEntity entity) {
        ProdutoDto dto = new ProdutoDto();

        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        dto.setTipo(entity.getTipo());
 //       dto.setDiasDaSemanaDisponivel(entity.getDiasDaSemanaDisponivel());
        dto.setDataInicial(entity.getDataInicial());
        dto.setDataFinal(entity.getDataFinal());
        dto.setHorarioInicial(entity.getHorarioInicial());
        dto.setHorarioFinal(entity.getHorarioFinal());

        return dto;
    }
}
