package com.example.demo.service;

import com.example.demo.model.Movimentacao;
import com.example.demo.repository.MovimentacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço para gerenciar operações de Movimentação.
 * Implementa GenericService promovendo DIP (Dependency Inversion Principle)
 * Utiliza constructor injection melhorando testabilidade
 * Separa lógica de negócio da apresentação (SRP - Single Responsibility Principle)
 */
@Service
public class MovimentacaoService implements GenericService<Movimentacao, Long> {

    private final MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @Override
    public List<Movimentacao> listarTodas() {
        return movimentacaoRepository.findAll();
    }

    @Override
    public Optional<Movimentacao> buscarPorId(Long id) {
        return movimentacaoRepository.findById(id);
    }

    @Override
    public Movimentacao salvar(Movimentacao movimentacao) {
        return movimentacaoRepository.save(movimentacao);
    }

    @Override
    public void deletar(Long id) {
        movimentacaoRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return movimentacaoRepository.existsById(id);
    }

    public List<Movimentacao> buscarPorPeca(Long pecaId) {
        return movimentacaoRepository.findByPecaId(pecaId);
    }
}
