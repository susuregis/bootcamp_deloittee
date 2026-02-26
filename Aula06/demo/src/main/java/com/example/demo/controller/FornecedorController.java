package com.example.demo.controller;

import com.example.demo.dto.FornecedorDTO;
import com.example.demo.mapper.FornecedorMapper;
import com.example.demo.model.Fornecedor;
import com.example.demo.service.FornecedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Controller REST para gerenciar Fornecedores.
 * Segue SRP: responsável apenas pela apresentação
 * Utiliza DTOs para separar a camada de apresentação da lógica de negócio
 * Utiliza constructor injection (DIP)
 */
@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;
    private final FornecedorMapper fornecedorMapper;

    public FornecedorController(FornecedorService fornecedorService, FornecedorMapper fornecedorMapper) {
        this.fornecedorService = fornecedorService;
        this.fornecedorMapper = fornecedorMapper;
    }

    /**
     * Lista todos os fornecedores
     */
    @GetMapping
    public ResponseEntity<List<FornecedorDTO>> listarTodos() {
        List<Fornecedor> fornecedores = fornecedorService.listarTodas();
        List<FornecedorDTO> dtos = fornecedores.stream()
                .map(fornecedorMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    /**
     * Busca um fornecedor por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDTO> buscarPorId(@PathVariable Long id) {
        return fornecedorService.buscarPorId(id)
                .map(fornecedor -> ResponseEntity.ok(fornecedorMapper.toDTO(fornecedor)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cria um novo fornecedor
     */
    @PostMapping
    public ResponseEntity<FornecedorDTO> criar(@Valid @RequestBody FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = fornecedorMapper.toEntity(fornecedorDTO);
        Fornecedor fornecedorSalvo = fornecedorService.salvar(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorMapper.toDTO(fornecedorSalvo));
    }

    /**
     * Atualiza um fornecedor existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<FornecedorDTO> atualizar(@PathVariable Long id, @Valid @RequestBody FornecedorDTO fornecedorDTO) {
        if (!fornecedorService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        Fornecedor fornecedorExistente = fornecedorService.buscarPorId(id).orElseThrow();
        Fornecedor fornecedorAtualizado = fornecedorMapper.updateEntityFromDTO(fornecedorDTO, fornecedorExistente);
        Fornecedor fornecedorSalvo = fornecedorService.salvar(fornecedorAtualizado);
        return ResponseEntity.ok(fornecedorMapper.toDTO(fornecedorSalvo));
    }

    /**
     * Deleta um fornecedor
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!fornecedorService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        fornecedorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
