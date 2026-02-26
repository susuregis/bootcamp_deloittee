package com.example.demo.service;

import java.util.List;
import java.util.Optional;

/**
 * Interface genérica que define operações comuns de serviço.
 * Promove ISP (Interface Segregation Principle) e DIP (Dependency Inversion Principle)
 * 
 * @param <T> Tipo da entidade
 * @param <ID> Tipo do identificador
 */
public interface GenericService<T, ID> {
    
    /**
     * Lista todas as entidades
     * @return Lista de entidades
     */
    List<T> listarTodas();
    
    /**
     * Busca uma entidade por ID
     * @param id Identificador
     * @return Optional contendo a entidade se encontrada
     */
    Optional<T> buscarPorId(ID id);
    
    /**
     * Salva uma entidade
     * @param entity Entidade a ser salva
     * @return Entidade salva
     */
    T salvar(T entity);
    
    /**
     * Deleta uma entidade por ID
     * @param id Identificador
     */
    void deletar(ID id);
    
    /**
     * Verifica se uma entidade existe por ID
     * @param id Identificador
     * @return true se existe, false caso contrário
     */
    boolean existe(ID id);
}
