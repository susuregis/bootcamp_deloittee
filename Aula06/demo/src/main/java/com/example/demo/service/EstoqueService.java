package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Estoque;
import com.example.demo.repository.EstoqueRepository;

/**
 * Serviço para gerenciar operações de Estoque.
 * Implementa GenericService promovendo DIP (Dependency Inversion Principle)
 * Utiliza constructor injection melhorando testabilidade
 * Separa lógica de negócio da apresentação (SRP - Single Responsibility Principle)
 */
@Service
public class EstoqueService implements GenericService<Estoque, Long> {

    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    @Override
    public List<Estoque> listarTodas() {
        return estoqueRepository.findAll();
    }

    @Override
    public Optional<Estoque> buscarPorId(Long id) {
        return estoqueRepository.findById(id);
    }

    @Override
    public Estoque salvar(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    @Override
    public void deletar(Long id) {
        estoqueRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return estoqueRepository.existsById(id);
    }
}
