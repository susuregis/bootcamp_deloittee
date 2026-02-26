package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.PecaDTO;
import com.example.demo.model.Peca;
import com.example.demo.repository.FornecedorRepository;

/**
 * Mapper para converter entre Peca e PecaDTO
 * Implementa EntityMapper seguindo SRP (Single Responsibility Principle)
 */
@Component
public class PecaMapper implements EntityMapper<Peca, PecaDTO> {
    
    private final FornecedorRepository fornecedorRepository;
    
    public PecaMapper(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }
    
    @Override
    public PecaDTO toDTO(Peca entity) {
        if (entity == null) {
            return null;
        }
        
        PecaDTO dto = new PecaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());
        dto.setPreco(entity.getPreco());
        dto.setCodigo(entity.getCodigo());
        dto.setEstoqueMinimo(entity.getEstoqueMinimo());
        dto.setEstoqueMaximo(entity.getEstoqueMaximo());
        dto.setUnidadeMedida(entity.getUnidadeMedida());
        dto.setPeso(entity.getPeso());
        
        if (entity.getFornecedor() != null) {
            dto.setFornecedorId(entity.getFornecedor().getId());
        }
        
        return dto;
    }
    
    @Override
    public Peca toEntity(PecaDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Peca entity = new Peca();
        return updateEntityFromDTO(dto, entity);
    }
    
    @Override
    public Peca updateEntityFromDTO(PecaDTO dto, Peca entity) {
        if (dto == null) {
            return entity;
        }
        
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setCodigo(dto.getCodigo());
        entity.setEstoqueMinimo(dto.getEstoqueMinimo());
        entity.setEstoqueMaximo(dto.getEstoqueMaximo());
        entity.setUnidadeMedida(dto.getUnidadeMedida());
        entity.setPeso(dto.getPeso());
        
        if (dto.getFornecedorId() != null) {
            entity.setFornecedor(fornecedorRepository.findById(dto.getFornecedorId()).orElse(null));
        }
        
        return entity;
    }
}
