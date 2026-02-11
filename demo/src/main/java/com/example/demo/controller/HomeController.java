package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> home() {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "API REST do Sistema de Usuários");
        resposta.put("versao", "1.0.0");
        resposta.put("status", "online");
        resposta.put("endpoints", Map.of(
            "listar_usuarios", "GET /api/usuarios",
            "criar_usuario", "POST /api/usuarios",
            "buscar_por_id", "GET /api/usuarios/{id}",
            "buscar_por_email", "GET /api/usuarios/email/{email}",
            "atualizar_usuario", "PUT /api/usuarios/{id}",
            "deletar_usuario", "DELETE /api/usuarios/{id}",
            "console_h2", "GET /h2-console"
        ));
        
        return ResponseEntity.ok(resposta);
    }
}
