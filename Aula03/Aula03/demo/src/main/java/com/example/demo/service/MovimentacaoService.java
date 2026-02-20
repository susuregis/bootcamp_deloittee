package com.example.demo.service;

import com.example.demo.model.Movimentacao;
import com.example.demo.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public List<Movimentacao> listarTodas() {
        return movimentacaoRepository.findAll();
    }

    public Optional<Movimentacao> buscarPorId(Long id) {
        return movimentacaoRepository.findById(id);
    }

    public Movimentacao salvar(Movimentacao movimentacao) {
        return movimentacaoRepository.save(movimentacao);
    }

    public List<Movimentacao> buscarPorPeca(Long pecaId) {
        return movimentacaoRepository.findByPecaId(pecaId);
    }
}
