package com.example.demo.repository;

import com.example.demo.model.Peca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Long> {
    Optional<Peca> findByCodigo(String codigo);
    boolean existsByCodigo(String codigo);
    List<Peca> findByAtivoTrue();
    List<Peca> findByFornecedorId(Long fornecedorId);
    List<Peca> findByCategoriaId(Long categoriaId);
}
