package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.FornecedorDTO;
import com.example.demo.model.Fornecedor;

/**
 * Mapper para converter entre Fornecedor e FornecedorDTO
 * Implementa EntityMapper seguindo SRP (Single Responsibility Principle)
 */
@Component
public class FornecedorMapper implements EntityMapper<Fornecedor, FornecedorDTO> {
    
    @Override
    public FornecedorDTO toDTO(Fornecedor entity) {
        if (entity == null) {
            return null;
        }
        
        FornecedorDTO dto = new FornecedorDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCnpj(entity.getCnpj());
        dto.setEmail(entity.getEmail());
        
        // Mapear contato info se existir
        if (entity.getContatoInfo() != null) {
            dto.setTelefone(entity.getContatoInfo().getTelefone());
        }
        
        // Mapear endereço se existir
        if (entity.getEndereco() != null) {
            dto.setEndereco(entity.getEndereco().getNumero());
            dto.setCidade(entity.getEndereco().getCidade());
            dto.setEstado(entity.getEndereco().getEstado());
            dto.setCep(entity.getEndereco().getCep());
        }
        
        return dto;
    }
    
    @Override
    public Fornecedor toEntity(FornecedorDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Fornecedor entity = new Fornecedor();
        return updateEntityFromDTO(dto, entity);
    }
    
    @Override
    public Fornecedor updateEntityFromDTO(FornecedorDTO dto, Fornecedor entity) {
        if (dto == null) {
            return entity;
        }
        
        entity.setNome(dto.getNome());
        entity.setCnpj(dto.getCnpj());
        entity.setEmail(dto.getEmail());
        
        // Atualizar contato info
        if (dto.getTelefone() != null) {
            if (entity.getContatoInfo() == null) {
                entity.setContatoInfo(new com.example.demo.model.ContatoInfo());
            }
            entity.getContatoInfo().setTelefone(dto.getTelefone());
        }
        
        // Atualizar endereço
        if (dto.getEndereco() != null || dto.getCidade() != null || 
            dto.getEstado() != null || dto.getCep() != null) {
            if (entity.getEndereco() == null) {
                entity.setEndereco(new com.example.demo.model.Endereco());
            }
            if (dto.getEndereco() != null) {
                entity.getEndereco().setNumero(dto.getEndereco());
            }
            if (dto.getCidade() != null) {
                entity.getEndereco().setCidade(dto.getCidade());
            }
            if (dto.getEstado() != null) {
                entity.getEndereco().setEstado(dto.getEstado());
            }
            if (dto.getCep() != null) {
                entity.getEndereco().setCep(dto.getCep());
            }
        }
        
        return entity;
    }
}
