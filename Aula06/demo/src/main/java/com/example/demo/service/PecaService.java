package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Peca;
import com.example.demo.repository.PecaRepository;

/**
 * Serviço para gerenciar operações de Peça.
 * Implementa GenericService promovendo DIP (Dependency Inversion Principle)
 * Utiliza constructor injection melhorando testabilidade
 * Separa lógica de negócio da apresentação (SRP - Single Responsibility Principle)
 */
@Service
public class PecaService implements GenericService<Peca, Long> {

    private final PecaRepository pecaRepository;

    public PecaService(PecaRepository pecaRepository) {
        this.pecaRepository = pecaRepository;
    }

    @Override
    public List<Peca> listarTodas() {
        return pecaRepository.findAll();
    }

    @Override
    public Optional<Peca> buscarPorId(Long id) {
        return pecaRepository.findById(id);
    }

    @Override
    public Peca salvar(Peca peca) {
        return pecaRepository.save(peca);
    }

    @Override
    public void deletar(Long id) {
        pecaRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return pecaRepository.existsById(id);
    }

    public Optional<Peca> buscarPorCodigo(String codigo) {
        return pecaRepository.findByCodigo(codigo);
    }
}
