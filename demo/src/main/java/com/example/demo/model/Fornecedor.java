package com.example.demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "fornecedores")
public class Fornecedor extends Pessoa {

    @Column(unique = true, length = 18)
    private String cnpj;

    
    @Embedded
    private Endereco endereco;

    @Column(name = "prazo_entrega_dias")
    private Integer prazoEntregaDias;

    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
    private List<Peca> pecasFornecidas = new ArrayList<>();

    public Fornecedor() {
        super();
    }

    public Fornecedor(String nome, String cpf, String email, String telefone, 
                      String cnpj) {
        super(nome, cpf, email, telefone);
        this.cnpj = cnpj;
        this.endereco = new Endereco();
    }

    @Override
    public String getTipo() {
        return "FORNECEDOR";
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        if (endereco == null) {
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Integer getPrazoEntregaDias() {
        return prazoEntregaDias;
    }

    public void setPrazoEntregaDias(Integer prazoEntregaDias) {
        this.prazoEntregaDias = prazoEntregaDias;
    }

    public List<Peca> getPecasFornecidas() {
        return pecasFornecidas;
    }

    public void setPecasFornecidas(List<Peca> pecasFornecidas) {
        this.pecasFornecidas = pecasFornecidas;
    }
}
