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
        resposta.put("mensagem", "API REST - Sistema de Estoque com POO");
        resposta.put("versao", "2.0.0");
        resposta.put("status", "online");
        resposta.put("descricao", "Sistema completo aplicando Herança, Polimorfismo, Encapsulamento e Abstração");
        
        resposta.put("endpoints", Map.of(
            "fornecedores", Map.of(
                "listar", "GET /api/fornecedores",
                "criar", "POST /api/fornecedores",
                "buscar", "GET /api/fornecedores/{id}"
            ),
            "pecas", Map.of(
                "listar", "GET /api/pecas",
                "criar", "POST /api/pecas",
                "buscar_por_codigo", "GET /api/pecas/codigo/{codigo}"
            ),
            "estoque", Map.of(
                "listar", "GET /api/estoque",
                "adicionar", "POST /api/estoque/adicionar",
                "remover", "POST /api/estoque/remover",
                "criticos", "GET /api/estoque/criticos"
            ),
            "movimentacoes", "GET /api/movimentacoes",
            "categorias", "GET /api/categorias",
            "console_h2", "GET /h2-console"
        ));
        
        resposta.put("conceitos_poo", Map.of(
            "heranca", "Pessoa -> Fornecedor/Funcionario | Produto -> Peca",
            "polimorfismo", "Métodos abstratos getTipo(), calcularPrecoFinal(), validar()",
            "encapsulamento", "Atributos privados com getters/setters, lógica protegida",
            "abstracao", "Classes abstratas Pessoa e Produto",
            "composicao", Map.of(
                "fraca", "Peça tem Fornecedor (Agregação - pode existir separadamente)",
                "forte_embeddable", "Pessoa TEM ContatoInfo e Endereco (@Embedded - parte integral)",
                "forte_collection", "Pedido TEM ItemPedidos (@ElementCollection - não existe sem o Pedido)",
                "caracteristica", "Relacionamento 'tem-um' onde componente não existe independentemente"
            )
        ));
        
        return ResponseEntity.ok(resposta);
    }
}
