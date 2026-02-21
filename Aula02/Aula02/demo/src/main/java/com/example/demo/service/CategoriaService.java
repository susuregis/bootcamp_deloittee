package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;

public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService() {
        this.categoriaRepository = new CategoriaRepository();
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void deletar(Long id) {
        categoriaRepository.deleteById(id);
    }

    public Optional<Categoria> buscarPorNome(String nome) {
        return categoriaRepository.findByNome(nome);
    }
}
