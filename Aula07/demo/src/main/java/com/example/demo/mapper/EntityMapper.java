package com.example.demo.mapper;

/**
 * Interface genérica para mapear entre DTOs e entidades
 * Segue SRP (Single Responsibility Principle)
 * 
 * @param <E> Tipo da entidade
 * @param <D> Tipo do DTO
 */
public interface EntityMapper<E, D> {
    
    /**
     * Converte uma entidade para DTO
     * @param entity Entidade a converter
     * @return DTO
     */
    D toDTO(E entity);
    
    /**
     * Converte um DTO para entidade
     * @param dto DTO a converter
     * @return Entidade
     */
    E toEntity(D dto);
    
    /**
     * Atualiza uma entidade existente com dados do DTO
     * @param dto DTO com dados atualizados
     * @param entity Entidade a ser atualizada
     * @return Entidade atualizada
     */
    E updateEntityFromDTO(D dto, E entity);
}
