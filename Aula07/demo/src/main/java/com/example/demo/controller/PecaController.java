package com.example.demo.controller;

import com.example.demo.dto.PecaDTO;
import com.example.demo.mapper.PecaMapper;
import com.example.demo.model.Peca;
import com.example.demo.service.PecaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Controller REST para gerenciar Peças.
 * Segue SRP: responsável apenas pela apresentação
 * Utiliza DTOs para separar a camada de apresentação da lógica de negócio
 * Utiliza constructor injection (DIP)
 */
@RestController
@RequestMapping("/api/pecas")
public class PecaController {

    private final PecaService pecaService;
    private final PecaMapper pecaMapper;

    public PecaController(PecaService pecaService, PecaMapper pecaMapper) {
        this.pecaService = pecaService;
        this.pecaMapper = pecaMapper;
    }

    /**
     * Lista todas as peças
     */
    @GetMapping
    public ResponseEntity<List<PecaDTO>> listarTodas() {
        List<Peca> pecas = pecaService.listarTodas();
        List<PecaDTO> dtos = pecas.stream()
                .map(pecaMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    /**
     * Busca uma peça por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<PecaDTO> buscarPorId(@PathVariable Long id) {
        return pecaService.buscarPorId(id)
                .map(peca -> ResponseEntity.ok(pecaMapper.toDTO(peca)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cria uma nova peça
     */
    @PostMapping
    public ResponseEntity<PecaDTO> criar(@Valid @RequestBody PecaDTO pecaDTO) {
        Peca peca = pecaMapper.toEntity(pecaDTO);
        Peca pecaSalva = pecaService.salvar(peca);
        return ResponseEntity.status(HttpStatus.CREATED).body(pecaMapper.toDTO(pecaSalva));
    }

    /**
     * Atualiza uma peça existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<PecaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PecaDTO pecaDTO) {
        if (!pecaService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        Peca pecaExistente = pecaService.buscarPorId(id).orElseThrow();
        Peca pecaAtualizada = pecaMapper.updateEntityFromDTO(pecaDTO, pecaExistente);
        Peca pecaSalva = pecaService.salvar(pecaAtualizada);
        return ResponseEntity.ok(pecaMapper.toDTO(pecaSalva));
    }

    /**
     * Deleta uma peça
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
