package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.PecaDTO;
import com.example.demo.model.Peca;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.FornecedorRepository;

/**
 * Mapper para converter entre Peca e PecaDTO
 * Implementa EntityMapper seguindo SRP (Single Responsibility Principle)
 */
@Component
public class PecaMapper implements EntityMapper<Peca, PecaDTO> {
    
    private final FornecedorRepository fornecedorRepository;
    private final CategoriaRepository categoriaRepository;
    
    public PecaMapper(FornecedorRepository fornecedorRepository, CategoriaRepository categoriaRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.categoriaRepository = categoriaRepository;
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
            PecaDTO.FornecedorResumo fornecedorResumo = new PecaDTO.FornecedorResumo();
            fornecedorResumo.setId(entity.getFornecedor().getId());
            fornecedorResumo.setNome(entity.getFornecedor().getNome());
            dto.setFornecedor(fornecedorResumo);
        }

        if (entity.getCategoria() != null) {
            PecaDTO.CategoriaResumo categoriaResumo = new PecaDTO.CategoriaResumo();
            categoriaResumo.setId(entity.getCategoria().getId());
            categoriaResumo.setNome(entity.getCategoria().getNome());
            dto.setCategoria(categoriaResumo);
        }
        
        return dto;
    }
    
    @Override
    public Peca toEntity(PecaDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Peca entity = new Peca();
        if (dto.getCodigo() == null || dto.getCodigo().isBlank()) {
            entity.setCodigo(gerarCodigo());
        }
        if (dto.getEstoqueMinimo() != null) {
            entity.setEstoqueMinimo(dto.getEstoqueMinimo());
        } else {
            entity.setEstoqueMinimo(10);
        }
        if (dto.getEstoqueMaximo() != null) {
            entity.setEstoqueMaximo(dto.getEstoqueMaximo());
        } else {
            entity.setEstoqueMaximo(1000);
        }
        if (dto.getUnidadeMedida() != null && !dto.getUnidadeMedida().isBlank()) {
            entity.setUnidadeMedida(dto.getUnidadeMedida());
        } else {
            entity.setUnidadeMedida("UN");
        }
        return updateEntityFromDTO(dto, entity);
    }
    
    @Override
    public Peca updateEntityFromDTO(PecaDTO dto, Peca entity) {
        if (dto == null) {
            return entity;
        }
        
        if (dto.getNome() != null) {
            entity.setNome(dto.getNome());
        }
        if (dto.getDescricao() != null) {
            entity.setDescricao(dto.getDescricao());
        }
        if (dto.getPreco() != null) {
            entity.setPreco(dto.getPreco());
        }
        if (dto.getCodigo() != null && !dto.getCodigo().isBlank()) {
            entity.setCodigo(dto.getCodigo());
        }
        if (dto.getEstoqueMinimo() != null) {
            entity.setEstoqueMinimo(dto.getEstoqueMinimo());
        }
        if (dto.getEstoqueMaximo() != null) {
            entity.setEstoqueMaximo(dto.getEstoqueMaximo());
        }
        if (dto.getUnidadeMedida() != null && !dto.getUnidadeMedida().isBlank()) {
            entity.setUnidadeMedida(dto.getUnidadeMedida());
        }
        if (dto.getPeso() != null) {
            entity.setPeso(dto.getPeso());
        }
        
        if (dto.getFornecedor() != null) {
            if (dto.getFornecedor().getId() != null) {
                entity.setFornecedor(fornecedorRepository.findById(dto.getFornecedor().getId()).orElse(null));
            } else {
                entity.setFornecedor(null);
            }
        } else if (dto.getFornecedorId() != null) {
            entity.setFornecedor(fornecedorRepository.findById(dto.getFornecedorId()).orElse(null));
        }

        if (dto.getCategoria() != null) {
            if (dto.getCategoria().getId() != null) {
                entity.setCategoria(categoriaRepository.findById(dto.getCategoria().getId()).orElse(null));
            } else {
                entity.setCategoria(null);
            }
        }
        
        return entity;
    }

    private String gerarCodigo() {
        String raw = java.util.UUID.randomUUID().toString().replace("-", "");
        return "PEC-" + raw.substring(0, 12).toUpperCase();
    }
}
