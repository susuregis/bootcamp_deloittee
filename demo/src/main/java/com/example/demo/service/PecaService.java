package com.example.demo.service;

import com.example.demo.model.Peca;
import com.example.demo.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PecaService {

    @Autowired
    private PecaRepository pecaRepository;

    public List<Peca> listarTodas() {
        return pecaRepository.findAll();
    }

    public Optional<Peca> buscarPorId(Long id) {
        return pecaRepository.findById(id);
    }

    public Peca salvar(Peca peca) {
        return pecaRepository.save(peca);
    }

    public void deletar(Long id) {
        pecaRepository.deleteById(id);
    }

    public Optional<Peca> buscarPorCodigo(String codigo) {
        return pecaRepository.findByCodigo(codigo);
    }
}
