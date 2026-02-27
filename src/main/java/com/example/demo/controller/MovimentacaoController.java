package com.example.demo.controller;

import com.example.demo.model.Movimentacao;
import com.example.demo.service.MovimentacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para gerenciar Movimentações.
 * Segue SRP: responsável apenas pela apresentação
 * Utiliza constructor injection (DIP)
 */
@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    /**
     * Lista todas as movimentações
     */
    @GetMapping
    public ResponseEntity<List<Movimentacao>> listarTodas() {
        return ResponseEntity.ok(movimentacaoService.listarTodas());
    }

    /**
     * Busca uma movimentação por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Movimentacao> buscarPorId(@PathVariable Long id) {
        return movimentacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cria uma nova movimentação
     */
    @PostMapping
    public ResponseEntity<Movimentacao> criar(@RequestBody Movimentacao movimentacao) {
        Movimentacao movimentacaoSalva = movimentacaoService.salvar(movimentacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimentacaoSalva);
    }

    /**
     * Busca movimentações por ID da peça
     */
    @GetMapping("/peca/{pecaId}")
    public ResponseEntity<List<Movimentacao>> buscarPorPeca(@PathVariable Long pecaId) {
        return ResponseEntity.ok(movimentacaoService.buscarPorPeca(pecaId));
    }
}
