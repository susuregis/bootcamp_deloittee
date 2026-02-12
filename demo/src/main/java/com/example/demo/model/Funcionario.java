package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "funcionarios")
public class Funcionario extends Pessoa {

    @Column(name = "data_admissao")
    private LocalDate dataAdmissao;

    @Column(length = 20)
    private String matricula;

    public Funcionario() {
        super();
    }

    public Funcionario(String nome, String cpf, String email, String telefone) {
        super(nome, cpf, email, telefone);
        this.dataAdmissao = LocalDate.now();
    }

   
    @Override
    public String getTipo() {
        return "FUNCIONARIO";
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
