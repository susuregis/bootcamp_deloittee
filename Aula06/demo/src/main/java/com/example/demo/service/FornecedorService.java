package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Fornecedor;
import com.example.demo.repository.FornecedorRepository;

/**
 * Serviço para gerenciar operações de Fornecedor.
 * Implementa GenericService promovendo DIP (Dependency Inversion Principle)
 * Utiliza constructor injection melhorando testabilidade
 * Separa lógica de negócio da apresentação (SRP - Single Responsibility Principle)
 */
@Service
public class FornecedorService implements GenericService<Fornecedor, Long> {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    public List<Fornecedor> listarTodas() {
        return fornecedorRepository.findAll();
    }

    @Override
    public Optional<Fornecedor> buscarPorId(Long id) {
        return fornecedorRepository.findById(id);
    }

    @Override
    public Fornecedor salvar(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    @Override
    public void deletar(Long id) {
        fornecedorRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return fornecedorRepository.existsById(id);
    }

    public Optional<Fornecedor> buscarPorCnpj(String cnpj) {
        return fornecedorRepository.findByCnpj(cnpj);
    }
}
