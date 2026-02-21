package com.example.demo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "pecas", indexes = {
        @Index(name = "idx_peca_codigo", columnList = "codigo"),
        @Index(name = "idx_peca_fornecedor", columnList = "fornecedor_id")
})
public class Peca extends Produto {

    @NotBlank(message = "Código é obrigatório")
    @Column(unique = true, nullable = false, length = 50)
    private String codigo;

    @NotNull(message = "Estoque mínimo é obrigatório")
    @Min(value = 0, message = "Estoque mínimo não pode ser negativo")
    @Column(name = "estoque_minimo", nullable = false)
    private Integer estoqueMinimo = 10;

    @NotNull(message = "Estoque máximo é obrigatório")
    @Min(value = 1, message = "Estoque máximo deve ser maior que zero")
    @Column(name = "estoque_maximo", nullable = false)
    private Integer estoqueMaximo = 1000;

    @NotBlank(message = "Unidade de medida é obrigatória")
    @Column(name = "unidade_medida", nullable = false, length = 50)
    private String unidadeMedida = "UN";

    @DecimalMin(value = "0.0", message = "Peso não pode ser negativo")
    @Column(precision = 5, scale = 2)
    private BigDecimal peso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "peca", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movimentacao> movimentacoes = new ArrayList<>();

    public Peca() {
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
    
    @PrePersist
    @PreUpdate
    private void validarEstoque() {
        if (estoqueMaximo != null && estoqueMinimo != null && estoqueMaximo < estoqueMinimo) {
            throw new IllegalStateException("Estoque máximo não pode ser menor que o estoque mínimo");
        }
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
