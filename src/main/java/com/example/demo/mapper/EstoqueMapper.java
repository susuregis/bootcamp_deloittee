package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.EstoqueDTO;
import com.example.demo.model.Estoque;
import com.example.demo.repository.PecaRepository;

/**
 * Mapper para converter entre Estoque e EstoqueDTO
 * Implementa EntityMapper seguindo SRP (Single Responsibility Principle)
 */
@Component
public class EstoqueMapper implements EntityMapper<Estoque, EstoqueDTO> {
    
    private final PecaRepository pecaRepository;
    
    public EstoqueMapper(PecaRepository pecaRepository) {
        this.pecaRepository = pecaRepository;
    }
    
    @Override
    public EstoqueDTO toDTO(Estoque entity) {
        if (entity == null) {
            return null;
        }
        
        EstoqueDTO dto = new EstoqueDTO();
        dto.setId(entity.getId());
        dto.setQuantidade(entity.getQuantidade());
        
        if (entity.getPeca() != null) {
            dto.setPecaId(entity.getPeca().getId());
        }
        
        return dto;
    }
    
    @Override
    public Estoque toEntity(EstoqueDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Estoque entity = new Estoque();
        return updateEntityFromDTO(dto, entity);
    }
    
    @Override
    public Estoque updateEntityFromDTO(EstoqueDTO dto, Estoque entity) {
        if (dto == null) {
            return entity;
        }
        
        entity.setQuantidade(dto.getQuantidade());
        
        if (dto.getPecaId() != null) {
            entity.setPeca(pecaRepository.findById(dto.getPecaId()).orElse(null));
        }
        
        return entity;
    }
}
