package com.loja;

public class Fornecedor {
    private Long id;
    private String cnpj;
    private String contato;
    private String nome;
    private String rua;
    private String numero;
    private String cep;

    public Fornecedor(Long id, String cnpj, String contato, String nome, String rua, String numero, String cep) {
        this.id = id;
        this.cnpj = cnpj;
        this.contato = contato;
        this.nome = nome;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Nome: " + this.getNome() + ", CNPJ: " + this.getCnpj() + ", Contato: "
                + this.getContato() + ", Endereço: " + this.getRua() + ", " + this.getNumero() + ", CEP: "
                + this.getCep() + "\n";

    }
}