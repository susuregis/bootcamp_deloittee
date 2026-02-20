package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "funcionarios")
public class Funcionario extends Pessoa {

    public Funcionario() {
        super();
    }

    public Funcionario(String nome, String cpf, String email, String telefone) {
        super(nome, cpf, email, telefone);
    }

   
    @Override
    public String getTipo() {
        return "FUNCIONARIO";
    }
}
