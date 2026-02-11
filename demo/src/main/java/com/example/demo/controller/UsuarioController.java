package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> criarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioCriado = usuarioService.criarUsuario(usuario);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Usuário criado com sucesso!");
        resposta.put("usuario", usuarioCriado);

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarTodos() {
        List<Usuario> usuarios = usuarioService.listarTodos();

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("total", usuarios.size());
        resposta.put("usuarios", usuarios);

        if (usuarios.isEmpty()) {
            resposta.put("mensagem", "Nenhum usuário cadastrado no sistema.");
        } else {
            resposta.put("mensagem", String.format("Total de %d usuário(s) encontrado(s).", usuarios.size()));
        }

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Usuário encontrado com sucesso!");
        resposta.put("usuario", usuario);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Map<String, Object>> buscarPorEmail(@PathVariable String email) {
        Usuario usuario = usuarioService.buscarPorEmail(email);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Usuário encontrado com sucesso!");
        resposta.put("usuario", usuario);

        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarUsuario(
            @PathVariable Long id, 
            @RequestBody Usuario usuario) {

        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuario);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Usuário atualizado com sucesso!");
        resposta.put("usuario", usuarioAtualizado);

        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> removerUsuario(@PathVariable Long id) {
        usuarioService.removerUsuario(id);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", String.format("Usuário com ID %d foi removido com sucesso!", id));

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/existe/{id}")
    public ResponseEntity<Map<String, Object>> existeUsuario(@PathVariable Long id) {
        boolean existe = usuarioService.existeUsuario(id);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("existe", existe);
        resposta.put("mensagem", existe ? 
            String.format("Usuário com ID %d existe no sistema.", id) : 
            String.format("Usuário com ID %d não existe no sistema.", id));

        return ResponseEntity.ok(resposta);
    }


}
