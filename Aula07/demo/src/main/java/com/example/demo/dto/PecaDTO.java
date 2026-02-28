package com.example.demo.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para criar/atualizar uma Peça
 * Segue o SRP (Single Responsibility Principle) - responsável apenas por transferência de dados
 */
public class PecaDTO {
    
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    private String descricao;
    
    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", message = "Preço não pode ser negativo")
    private BigDecimal preco;
    
    private String codigo;
    
    private Integer estoqueMinimo = 10;
    
    private Integer estoqueMaximo = 1000;
    
    private String unidadeMedida = "UN";
    
    @DecimalMin(value = "0.0", message = "Peso não pode ser negativo")
    private BigDecimal peso;
    
    private Long fornecedorId;

    private CategoriaResumo categoria;

    private FornecedorResumo fornecedor;
    
    // Construtores
    public PecaDTO() {}
    
    public PecaDTO(String nome, String descricao, BigDecimal preco, String codigo) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.codigo = codigo;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public BigDecimal getPreco() {
        return preco;
    }
    
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
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
    
    public Long getFornecedorId() {
        return fornecedorId;
    }
    
    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public CategoriaResumo getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaResumo categoria) {
        this.categoria = categoria;
    }

    public FornecedorResumo getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorResumo fornecedor) {
        this.fornecedor = fornecedor;
    }

    public static class CategoriaResumo {
        private Long id;
        private String nome;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }

    public static class FornecedorResumo {
        private Long id;
        private String nome;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }
}
