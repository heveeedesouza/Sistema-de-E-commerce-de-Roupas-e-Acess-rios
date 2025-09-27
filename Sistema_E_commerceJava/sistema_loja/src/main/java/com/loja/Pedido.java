package com.loja;

import java.time.LocalDate;

public class Pedido {
    private int id;
    private LocalDate dataCompra;
    private double valorTotal;
    private StatusPedido status;
    private String formaDePagamento;
    private String historico;
    private int idEntrega;
    private int idCliente;

    public Pedido() {
    }

    public Pedido(int id, LocalDate dataCompra, double valorTotal, StatusPedido status, String formaDePagamento,
            String historico, int idEntrega, int idCliente) {
        this.id = id;
        this.dataCompra = dataCompra;
        this.valorTotal = valorTotal;
        this.status = status;
        this.formaDePagamento = formaDePagamento;
        this.historico = historico;
        this.idEntrega = idEntrega;
        this.idCliente = idCliente;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Data da Compra: " + this.getDataCompra() + ", Valor Total: R$"
                + this.getValorTotal()
                + ", Status: " + (this.getStatus() != null ? this.getStatus().name() : "-")
                + ",\nForma de Pagamento: " + this.getFormaDePagamento()
                + ", Histórico: " + this.getHistorico() + ", ID da Entrega: " + this.getIdEntrega()
                + ", ID do Cliente: " + this.getIdCliente() + "\n\n";
    }
}