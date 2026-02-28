package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para criar/atualizar Estoque
 * Segue o SRP (Single Responsibility Principle) - responsável apenas por transferência de dados
 */
public class EstoqueDTO {
    
    private Long id;
    
    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 0, message = "Quantidade não pode ser negativa")
    private Integer quantidade;
    
    @NotNull(message = "Peça ID é obrigatório")
    private Long pecaId;
    
    private String localizacao;
    
    // Construtores
    public EstoqueDTO() {}
    
    public EstoqueDTO(Integer quantidade, Long pecaId) {
        this.quantidade = quantidade;
        this.pecaId = pecaId;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    public Long getPecaId() {
        return pecaId;
    }
    
    public void setPecaId(Long pecaId) {
        this.pecaId = pecaId;
    }
    
    public String getLocalizacao() {
        return localizacao;
    }
    
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
