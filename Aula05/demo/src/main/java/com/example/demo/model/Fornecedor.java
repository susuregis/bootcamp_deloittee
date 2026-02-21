package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "fornecedores", indexes = {
        @Index(name = "idx_fornecedor_cnpj", columnList = "cnpj")
})
public class Fornecedor extends Pessoa {

    @NotBlank(message = "CNPJ é obrigatório")
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "CNPJ deve estar no formato XX.XXX.XXX/XXXX-XX")
    @Column(unique = true, nullable = false, length = 18)
    private String cnpj;

    @Embedded
    private Endereco endereco;

    @Min(value = 1, message = "Prazo de entrega deve ser de pelo menos 1 dia")
    @Max(value = 365, message = "Prazo de entrega não pode exceder 365 dias")
    @Column(name = "prazo_entrega_dias")
    private Integer prazoEntregaDias;

    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Peca> pecasFornecidas = new ArrayList<>();

    public Fornecedor() {
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
