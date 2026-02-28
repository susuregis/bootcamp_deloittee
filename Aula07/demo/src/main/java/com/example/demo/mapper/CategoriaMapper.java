package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.CategoriaDTO;
import com.example.demo.model.Categoria;

@Component
public class CategoriaMapper implements EntityMapper<Categoria, CategoriaDTO> {

    @Override
    public CategoriaDTO toDTO(Categoria entity) {
        if (entity == null) {
            return null;
        }

        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());
        dto.setTotalProdutos(entity.getProdutos() != null ? entity.getProdutos().size() : 0);
        return dto;
    }

    @Override
    public Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) {
            return null;
        }

        Categoria entity = new Categoria();
        return updateEntityFromDTO(dto, entity);
    }

    @Override
    public Categoria updateEntityFromDTO(CategoriaDTO dto, Categoria entity) {
        if (dto == null) {
            return entity;
        }

        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        return entity;
    }
}
