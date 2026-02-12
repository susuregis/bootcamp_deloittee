package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;


@Embeddable
public class ContatoInfo {

    @Column(name = "telefone", length = 15)
    private String telefone;

    @Column(name = "celular", length = 15)
    private String celular;

    @Column(name = "email_alternativo", length = 100)
    private String emailAlternativo;

    @Column(name = "whatsapp", length = 15)
    private String whatsapp;

    public ContatoInfo() {
    }

    public ContatoInfo(String telefone, String celular, String emailAlternativo) {
        this.telefone = telefone;
        this.celular = celular;
        this.emailAlternativo = emailAlternativo;
    }

    public boolean temContatoCompleto() {
        return (telefone != null && !telefone.isEmpty()) ||
                (celular != null && !celular.isEmpty());
    }

    public String getContatoPrincipal() {
        if (celular != null && !celular.isEmpty()) {
            return celular;
        }
        return telefone != null ? telefone : "Sem contato";
    }

    public int getTotalContatos() {
        int total = 0;
        if (telefone != null && !telefone.isEmpty()) total++;
        if (celular != null && !celular.isEmpty()) total++;
        if (emailAlternativo != null && !emailAlternativo.isEmpty()) total++;
        if (whatsapp != null && !whatsapp.isEmpty()) total++;
        return total;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmailAlternativo() {
        return emailAlternativo;
    }

    public void setEmailAlternativo(String emailAlternativo) {
        this.emailAlternativo = emailAlternativo;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContatoInfo that = (ContatoInfo) o;
        return Objects.equals(telefone, that.telefone) &&
                Objects.equals(celular, that.celular);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telefone, celular);
    }

    @Override
    public String toString() {
        return "ContatoInfo{" +
                "telefone='" + telefone + '\'' +
                ", celular='" + celular + '\'' +
                ", totalContatos=" + getTotalContatos() +
                '}';
    }
}
