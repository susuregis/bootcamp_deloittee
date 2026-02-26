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

import com.example.demo.dto.CategoriaDTO;
import com.example.demo.mapper.CategoriaMapper;
import com.example.demo.model.Categoria;
import com.example.demo.service.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final CategoriaMapper categoriaMapper;

    public CategoriaController(CategoriaService categoriaService, CategoriaMapper categoriaMapper) {
        this.categoriaService = categoriaService;
        this.categoriaMapper = categoriaMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarTodas() {
        List<Categoria> categorias = categoriaService.listarTodas();
        List<CategoriaDTO> dtos = categorias.stream()
                .map(categoriaMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id)
                .map(categoria -> ResponseEntity.ok(categoriaMapper.toDTO(categoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> criar(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        if (categoriaService.existePorNome(categoriaDTO.getNome())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Categoria categoriaSalva = categoriaService.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaMapper.toDTO(categoriaSalva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaDTO categoriaDTO) {
        if (!categoriaService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        if (nomeConflitante(id, categoriaDTO.getNome())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Categoria categoriaExistente = categoriaService.buscarPorId(id).orElseThrow();
        Categoria categoriaAtualizada = categoriaMapper.updateEntityFromDTO(categoriaDTO, categoriaExistente);
        Categoria categoriaSalva = categoriaService.salvar(categoriaAtualizada);
        return ResponseEntity.ok(categoriaMapper.toDTO(categoriaSalva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!categoriaService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private boolean nomeConflitante(Long id, String nome) {
        return categoriaService.buscarPorNome(nome)
                .map(categoria -> !categoria.getId().equals(id))
                .orElse(false);
    }
}
