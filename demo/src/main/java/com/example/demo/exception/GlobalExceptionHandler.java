package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<Object> handleUsuarioNotFoundException(
            UsuarioNotFoundException ex, WebRequest request) {

        Map<String, Object> corpo = new HashMap<>();
        corpo.put("timestamp", LocalDateTime.now());
        corpo.put("status", HttpStatus.NOT_FOUND.value());
        corpo.put("erro", "Usuário não encontrado");
        corpo.put("mensagem", ex.getMessage());
        corpo.put("caminho", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(corpo, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {

        Map<String, Object> corpo = new HashMap<>();
        corpo.put("timestamp", LocalDateTime.now());
        corpo.put("status", HttpStatus.BAD_REQUEST.value());
        corpo.put("erro", "Requisição inválida");
        corpo.put("mensagem", ex.getMessage());
        corpo.put("caminho", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(corpo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(
            Exception ex, WebRequest request) {

        Map<String, Object> corpo = new HashMap<>();
        corpo.put("timestamp", LocalDateTime.now());
        corpo.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        corpo.put("erro", "Erro interno do servidor");
        corpo.put("mensagem", "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.");
        corpo.put("caminho", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(corpo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
