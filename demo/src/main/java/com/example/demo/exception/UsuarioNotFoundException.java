package com.example.demo.exception;

public class UsuarioNotFoundException extends RuntimeException {

    public UsuarioNotFoundException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public UsuarioNotFoundException(Long id) {
        super(String.format("Usuário com ID %d não foi encontrado no sistema.", id));
    }
}
