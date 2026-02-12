package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "peca_id", unique = true, nullable = false)
    private Peca peca;

    @Column(nullable = false)
    private Integer quantidade = 0;

    @Column(name = "quantidade_reservada")
    private Integer quantidadeReservada = 0;

    public Estoque() {
    }

    public Estoque(Peca peca, Integer quantidade) {
        this.peca = peca;
        this.quantidade = quantidade;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidade - quantidadeReservada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getQuantidadeReservada() {
        return quantidadeReservada;
    }

    public void setQuantidadeReservada(Integer quantidadeReservada) {
        this.quantidadeReservada = quantidadeReservada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estoque estoque = (Estoque) o;
        return Objects.equals(id, estoque.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "id=" + id +
                ", peca=" + (peca != null ? peca.getNome() : "null") +
                ", quantidade=" + quantidade +
                ", disponivel=" + getQuantidadeDisponivel() +
                '}';
    }
}
