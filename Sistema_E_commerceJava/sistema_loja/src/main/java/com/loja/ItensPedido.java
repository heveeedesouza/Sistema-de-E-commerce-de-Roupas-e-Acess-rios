package com.loja;

public class ItensPedido {
    private int idPedido;
    private int idProduto;
    private String descricao;

    public ItensPedido() {
    }

    public ItensPedido(int idPedido, int idProduto, String descricao) {
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "ID: " + this.getIdPedido() + ", Produto: " + this.getIdProduto() + ", Descrição: "
                + this.getDescricao();
    }
}