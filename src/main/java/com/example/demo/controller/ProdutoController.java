package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProdutoDTO;
import com.example.demo.mapper.ProdutoMapper;
import com.example.demo.model.Peca;
import com.example.demo.service.PecaService;

import jakarta.validation.Valid;

/**
 * Controller REST para gerenciar Produtos.
 * Segue SRP: responsável apenas pela apresentação
 * Utiliza DTOs para separar a camada de apresentação da lógica de negócio
 * Utiliza constructor injection (DIP)
 */
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final PecaService pecaService;
    private final ProdutoMapper produtoMapper;

    public ProdutoController(PecaService pecaService, ProdutoMapper produtoMapper) {
        this.pecaService = pecaService;
        this.produtoMapper = produtoMapper;
    }

    /**
     * Lista todos os produtos
     */
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos() {
        List<Peca> pecas = pecaService.listarTodas();
        List<ProdutoDTO> dtos = pecas.stream()
                .map(produtoMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    /**
     * Busca um produto por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
        return pecaService.buscarPorId(id)
                .map(peca -> ResponseEntity.ok(produtoMapper.toDTO(peca)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cria um novo produto
     */
    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Peca peca = produtoMapper.toEntity(produtoDTO);
        Peca pecaSalva = pecaService.salvar(peca);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoMapper.toDTO(pecaSalva));
    }

    /**
     * Atualiza um produto existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoDTO produtoDTO) {
        if (!pecaService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        Peca pecaExistente = pecaService.buscarPorId(id).orElseThrow();
        Peca pecaAtualizada = produtoMapper.updateEntityFromDTO(produtoDTO, pecaExistente);
        Peca pecaSalva = pecaService.salvar(pecaAtualizada);
        return ResponseEntity.ok(produtoMapper.toDTO(pecaSalva));
    }

    /**
     * Deleta um produto
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!pecaService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        pecaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
