package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

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
import com.example.demo.model.Categoria;
import com.example.demo.model.Peca;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.service.PecaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final PecaService pecaService; 
    private final CategoriaRepository categoriaRepository;

    public ProdutoController(PecaService pecaService, CategoriaRepository categoriaRepository) {
        this.pecaService = pecaService;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos() {
        List<ProdutoDTO> dtos = pecaService.listarTodas().stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
        return pecaService.buscarPorId(id)
                .map(peca -> ResponseEntity.ok(toDTO(peca)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Peca peca = toEntityForCreate(produtoDTO);
        Peca pecaSalva = pecaService.salvar(peca);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(pecaSalva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoDTO produtoDTO) {
        if (!pecaService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        Peca pecaExistente = pecaService.buscarPorId(id).orElseThrow();
        Peca pecaAtualizada = updateEntityFromDTO(produtoDTO, pecaExistente);
        Peca pecaSalva = pecaService.salvar(pecaAtualizada);
        return ResponseEntity.ok(toDTO(pecaSalva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!pecaService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        pecaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private ProdutoDTO toDTO(Peca peca) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(peca.getId());
        dto.setNome(peca.getNome());
        dto.setDescricao(peca.getDescricao());
        dto.setPreco(peca.getPreco());
        if (peca.getCategoria() != null) {
            ProdutoDTO.CategoriaResumo categoriaResumo = new ProdutoDTO.CategoriaResumo();
            categoriaResumo.setId(peca.getCategoria().getId());
            categoriaResumo.setNome(peca.getCategoria().getNome());
            dto.setCategoria(categoriaResumo);
        }
        return dto;
    }

    private Peca toEntityForCreate(ProdutoDTO dto) {
        Peca entity = new Peca();
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setCodigo(gerarCodigo());
        entity.setEstoqueMinimo(10);
        entity.setEstoqueMaximo(1000);
        entity.setUnidadeMedida("UN");

        if (dto.getCategoria() != null && dto.getCategoria().getId() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getCategoria().getId()).orElse(null);
            entity.setCategoria(categoria);
        }

        return entity;
    }

    private Peca updateEntityFromDTO(ProdutoDTO dto, Peca entity) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        if (dto.getCategoria() != null) {
            if (dto.getCategoria().getId() != null) {
                Categoria categoria = categoriaRepository.findById(dto.getCategoria().getId()).orElse(null);
                entity.setCategoria(categoria);
            } else {
                entity.setCategoria(null);
            }
        }
        return entity;
    }

    private String gerarCodigo() {
        String raw = UUID.randomUUID().toString().replace("-", "");
        return "PRD-" + raw.substring(0, 12).toUpperCase();
    }
}
