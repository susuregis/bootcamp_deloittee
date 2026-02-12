package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "pecas")
public class Peca extends Produto {

    @Column(unique = true, length = 50)
    private String codigo;

    @Column(name = "estoque_minimo")
    private Integer estoqueMinimo = 10;

    @Column(name = "estoque_maximo")
    private Integer estoqueMaximo = 1000;

    @Column(length = 50)
    private String unidadeMedida = "UN";

    @Column(precision = 5, scale = 2)
    private BigDecimal peso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "peca", cascade = CascadeType.ALL)
    private List<Movimentacao> movimentacoes = new ArrayList<>();

    public Peca() {
        super();
    }

    public Peca(String nome, String descricao, BigDecimal preco, String codigo, 
                Integer estoqueMinimo, Fornecedor fornecedor) {
        super(nome, descricao, preco);
        this.codigo = codigo;
        this.estoqueMinimo = estoqueMinimo;
        this.fornecedor = fornecedor;
    }

    @Override
    public String getTipoProduto() {
        return "PEÇA";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Integer estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public Integer getEstoqueMaximo() {
        return estoqueMaximo;
    }

    public void setEstoqueMaximo(Integer estoqueMaximo) {
        this.estoqueMaximo = estoqueMaximo;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }
}
