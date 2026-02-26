package com.example.demo.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
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
    
    @NotBlank(message = "Código é obrigatório")
    private String codigo;
    
    @NotNull(message = "Estoque mínimo é obrigatório")
    @Min(value = 0, message = "Estoque mínimo não pode ser negativo")
    private Integer estoqueMinimo = 10;
    
    @NotNull(message = "Estoque máximo é obrigatório")
    @Min(value = 1, message = "Estoque máximo deve ser maior que zero")
    private Integer estoqueMaximo = 1000;
    
    @NotBlank(message = "Unidade de medida é obrigatória")
    private String unidadeMedida = "UN";
    
    @DecimalMin(value = "0.0", message = "Peso não pode ser negativo")
    private BigDecimal peso;
    
    private Long fornecedorId;
    
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
}
