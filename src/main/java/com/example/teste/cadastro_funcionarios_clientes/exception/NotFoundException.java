package com.example.teste.cadastro_funcionarios_clientes.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}