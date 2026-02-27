package com.example.demo.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ProdutoDTO;
import com.example.demo.model.Peca;
import com.example.demo.service.CategoriaService;

/**
 * Mapper para converter entre Peca (como Produto) e ProdutoDTO
 * Implementa EntityMapper seguindo SRP (Single Responsibility Principle)
 * Usa DIP (Dependency Inversion) ao depender de Service ao invés de Repository
 */
@Component
public class ProdutoMapper implements EntityMapper<Peca, ProdutoDTO> {
    
    private final CategoriaService categoriaService;
    
    public ProdutoMapper(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    
    @Override
    public ProdutoDTO toDTO(Peca entity) {
        if (entity == null) {
            return null;
        }
        
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());
        dto.setPreco(entity.getPreco());
        
        if (entity.getCategoria() != null) {
            ProdutoDTO.CategoriaResumo categoriaResumo = new ProdutoDTO.CategoriaResumo();
            categoriaResumo.setId(entity.getCategoria().getId());
            categoriaResumo.setNome(entity.getCategoria().getNome());
            dto.setCategoria(categoriaResumo);
        }
        
        return dto;
    }
    
    @Override
    public Peca toEntity(ProdutoDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Peca entity = new Peca();
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setCodigo(gerarCodigo());
        entity.setEstoqueMinimo(10);
        entity.setEstoqueMaximo(1000);
        entity.setUnidadeMedida("UN");
        
        if (dto.getCategoria() != null && dto.getCategoria().getId() != null) {
            categoriaService.buscarPorId(dto.getCategoria().getId())
                    .ifPresent(entity::setCategoria);
        }
        
        return entity;
    }
    
    @Override
    public Peca updateEntityFromDTO(ProdutoDTO dto, Peca entity) {
        if (dto == null) {
            return entity;
        }
        
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        
        if (dto.getCategoria() != null) {
            if (dto.getCategoria().getId() != null) {
                categoriaService.buscarPorId(dto.getCategoria().getId())
                        .ifPresent(entity::setCategoria);
            } else {
                entity.setCategoria(null);
            }
        }
        
        return entity;
    }
    
    /**
     * Gera um código único para o produto
     * @return Código no formato PRD-XXXXXXXXXXXX
     */
    private String gerarCodigo() {
        String raw = UUID.randomUUID().toString().replace("-", "");
        return "PRD-" + raw.substring(0, 12).toUpperCase();
    }
}
