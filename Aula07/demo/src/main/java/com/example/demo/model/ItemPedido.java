package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;


@Embeddable
public class ItemPedido {

    @ManyToOne
    @JoinColumn(name = "peca_id")
    private Peca peca;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario", precision = 10, scale = 2)
    private BigDecimal precoUnitario;

    @Column(precision = 5, scale = 2)
    private BigDecimal desconto = BigDecimal.ZERO;

    public ItemPedido() {
    }

    public ItemPedido(Peca peca, Integer quantidade, BigDecimal precoUnitario) {
        this.peca = peca;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal calcularSubtotal() {
        if (precoUnitario == null || quantidade == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal subtotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
        
        if (desconto != null && desconto.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal valorDesconto = subtotal.multiply(desconto).divide(BigDecimal.valueOf(100));
            subtotal = subtotal.subtract(valorDesconto);
        }
        
        return subtotal;
    }

    public String getDescricao() {
        if (peca == null) {
            return "Item sem peça";
        }
        return String.format("%s - Qtd: %d x R$ %.2f = R$ %.2f",
                peca.getNome(), quantidade, precoUnitario, calcularSubtotal());
    }

    // Getters e Setters
    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(peca, that.peca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peca);
    }

    @Override
    public String toString() {
        return getDescricao();
    }
}
