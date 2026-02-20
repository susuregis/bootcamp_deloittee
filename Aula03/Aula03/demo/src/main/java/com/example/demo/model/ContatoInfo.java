package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;


@Embeddable
public class ContatoInfo {

    @Column(name = "telefone", length = 15)
    private String telefone;

    @Column(name = "email", length = 100)
    private String emailAlternativo;

    public ContatoInfo() {
    }

    public ContatoInfo(String telefone, String emailAlternativo) {
        this.telefone = telefone;
        this.emailAlternativo = emailAlternativo;
    }

    public boolean temContatoCompleto() {
        return telefone != null && !telefone.isEmpty();
    }

    public String getContatoPrincipal() {
        return telefone != null ? telefone : "Sem contato";
    }

    public int getTotalContatos() {
        int total = 0;
        if (telefone != null && !telefone.isEmpty()) total++;
        if (emailAlternativo != null && !emailAlternativo.isEmpty()) total++;
        return total;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmailAlternativo() {
        return emailAlternativo;
    }

    public void setEmailAlternativo(String emailAlternativo) {
        this.emailAlternativo = emailAlternativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContatoInfo that = (ContatoInfo) o;
        return Objects.equals(telefone, that.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telefone);
    }

    @Override
    public String toString() {
        return "ContatoInfo{" +
                "telefone='" + telefone + '\'' +
                ", totalContatos=" + getTotalContatos() +
                '}';
    }
}
