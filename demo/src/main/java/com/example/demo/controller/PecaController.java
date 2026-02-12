package com.example.demo.controller;

import com.example.demo.model.Peca;
import com.example.demo.service.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pecas")
public class PecaController {

    @Autowired
    private PecaService pecaService;

    @GetMapping
    public List<Peca> listarTodas() {
        return pecaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Peca> buscarPorId(@PathVariable Long id) {
        return pecaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Peca criar(@RequestBody Peca peca) {
        return pecaService.salvar(peca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Peca> atualizar(@PathVariable Long id, @RequestBody Peca peca) {
        if (!pecaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        peca.setId(id);
        return ResponseEntity.ok(pecaService.salvar(peca));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!pecaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pecaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
