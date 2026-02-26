package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Interface genérica que define operações comuns de repositório.
 * Promove ISP (Interface Segregation Principle) e DIP (Dependency Inversion Principle)
 * 
 * @param <T> Tipo da entidade
 * @param <ID> Tipo do identificador
 */
@NoRepositoryBean
public interface GenericRepository<T, ID> extends JpaRepository<T, ID> {
    // Os métodos herdados do JpaRepository já cobrem as operações básicas
}
