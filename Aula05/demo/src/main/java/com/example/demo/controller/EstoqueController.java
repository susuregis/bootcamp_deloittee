package com.example.demo.controller;

import com.example.demo.model.Estoque;
import com.example.demo.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping
    public List<Estoque> listarTodos() {
        return estoqueService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estoque> buscarPorId(@PathVariable Long id) {
        return estoqueService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estoque criar(@RequestBody Estoque estoque) {
        return estoqueService.salvar(estoque);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estoque> atualizar(@PathVariable Long id, @RequestBody Estoque estoque) {
        if (!estoqueService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        estoque.setId(id);
        return ResponseEntity.ok(estoqueService.salvar(estoque));
    }
}
