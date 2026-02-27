package com.example.demo.controller;

import com.example.demo.dto.EstoqueDTO;
import com.example.demo.mapper.EstoqueMapper;
import com.example.demo.model.Estoque;
import com.example.demo.service.EstoqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Controller REST para gerenciar Estoque.
 * Segue SRP: responsável apenas pela apresentação
 * Utiliza DTOs para separar a camada de apresentação da lógica de negócio
 * Utiliza constructor injection (DIP)
 */
@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;
    private final EstoqueMapper estoqueMapper;

    public EstoqueController(EstoqueService estoqueService, EstoqueMapper estoqueMapper) {
        this.estoqueService = estoqueService;
        this.estoqueMapper = estoqueMapper;
    }

    /**
     * Lista todos os estoques
     */
    @GetMapping
    public ResponseEntity<List<EstoqueDTO>> listarTodos() {
        List<Estoque> estoques = estoqueService.listarTodas();
        List<EstoqueDTO> dtos = estoques.stream()
                .map(estoqueMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    /**
     * Busca um estoque por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<EstoqueDTO> buscarPorId(@PathVariable Long id) {
        return estoqueService.buscarPorId(id)
                .map(estoque -> ResponseEntity.ok(estoqueMapper.toDTO(estoque)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cria um novo estoque
     */
    @PostMapping
    public ResponseEntity<EstoqueDTO> criar(@Valid @RequestBody EstoqueDTO estoqueDTO) {
        Estoque estoque = estoqueMapper.toEntity(estoqueDTO);
        Estoque estoqueSalvo = estoqueService.salvar(estoque);
        return ResponseEntity.status(HttpStatus.CREATED).body(estoqueMapper.toDTO(estoqueSalvo));
    }

    /**
     * Atualiza um estoque existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<EstoqueDTO> atualizar(@PathVariable Long id, @Valid @RequestBody EstoqueDTO estoqueDTO) {
        if (!estoqueService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        Estoque estoqueExistente = estoqueService.buscarPorId(id).orElseThrow();
        Estoque estoqueAtualizado = estoqueMapper.updateEntityFromDTO(estoqueDTO, estoqueExistente);
        Estoque estoqueSalvo = estoqueService.salvar(estoqueAtualizado);
        return ResponseEntity.ok(estoqueMapper.toDTO(estoqueSalvo));
    }

    /**
     * Deleta um estoque
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!estoqueService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        estoqueService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
