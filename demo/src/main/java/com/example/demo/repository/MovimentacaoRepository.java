package com.example.demo.repository;

import com.example.demo.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByPecaId(Long pecaId);
    List<Movimentacao> findByFuncionarioId(Long funcionarioId);
    List<Movimentacao> findByTipo(Movimentacao.TipoMovimentacao tipo);
}
