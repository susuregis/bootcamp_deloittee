package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;

@Service
public class CategoriaService implements GenericService<Categoria, Long> {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public void deletar(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return categoriaRepository.existsById(id);
    }

    public boolean existePorNome(String nome) {
        return categoriaRepository.existsByNome(nome);
    }

    public Optional<Categoria> buscarPorNome(String nome) {
        return categoriaRepository.findByNome(nome);
    }
}
