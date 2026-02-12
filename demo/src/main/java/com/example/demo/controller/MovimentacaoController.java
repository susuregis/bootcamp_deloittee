package com.example.demo.controller;

import com.example.demo.model.Movimentacao;
import com.example.demo.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping
    public List<Movimentacao> listarTodas() {
        return movimentacaoService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimentacao> buscarPorId(@PathVariable Long id) {
        return movimentacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Movimentacao criar(@RequestBody Movimentacao movimentacao) {
        return movimentacaoService.salvar(movimentacao);
    }

    @GetMapping("/peca/{pecaId}")
    public List<Movimentacao> buscarPorPeca(@PathVariable Long pecaId) {
        return movimentacaoService.buscarPorPeca(pecaId);
    }
}
