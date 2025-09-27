package com.loja;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class PedidoDAO {

    public void inserir(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedido (idPedido, dataCompra, valorTotal, status, formaDePagamento, historico, idEntrega, idCliente) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoSQL.conectarBanco();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, pedido.getId());
            stmt.setDate(2, java.sql.Date.valueOf(pedido.getDataCompra()));
            stmt.setDouble(3, pedido.getValorTotal());
            stmt.setString(4, pedido.getStatus().name().toLowerCase());
            stmt.setString(5, pedido.getFormaDePagamento());
            stmt.setString(6, pedido.getHistorico());
            stmt.setInt(7, pedido.getIdEntrega());
            stmt.setInt(8, pedido.getIdCliente());

            stmt.executeUpdate();
        }
    }

    public Pedido buscar(int idPedido) throws SQLException {
        String sql = "SELECT * FROM pedido WHERE idPedido = ?";

        try (Connection conexao = ConexaoSQL.conectarBanco();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("idPedido"),
                        rs.getDate("dataCompra").toLocalDate(),
                        rs.getDouble("valorTotal"),
                        StatusPedido.valueOf(rs.getString("status")),
                        rs.getString("formaDePagamento"),
                        rs.getString("historico"),
                        rs.getInt("idEntrega"),
                        rs.getInt("idCliente"));
                System.out.println("Pedido encontrado: " + pedido);
                return pedido;
            } else {
                System.out.println("Pedido não encontrado.");
                return null;
            }
        }
    }

    public void atualizarStatus(int idPedido, StatusPedido novoStatus) throws SQLException {
        String sql = "Update pedido set status = ? WHERE idPedido = ?";

        try (Connection conexao = ConexaoSQL.conectarBanco();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, novoStatus.name().toLowerCase());
            stmt.setInt(2, idPedido);
            stmt.executeUpdate();
        }
    }

    public List<Pedido> listarPedidosPorCliente(int idCliente, List<Cliente> clientel) throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT p.*, c.nome, c.cpf FROM pedido p JOIN cliente c ON p.idCliente = c.idCliente WHERE p.idCliente = ? ORDER BY dataCompra DESC;";

        try (Connection conexao = ConexaoSQL.conectarBanco();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Pedido pedido = new Pedido(
                        rs.getInt("idPedido"),
                        rs.getDate("dataCompra").toLocalDate(),
                        rs.getDouble("valorTotal"),
                        StatusPedido.valueOf(rs.getString("status").toUpperCase()),
                        rs.getString("formaDePagamento"),
                        rs.getString("historico"),
                        rs.getInt("idEntrega"),
                        rs.getInt("idCliente"));
                Cliente cliente = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        null,
                        null,
                        null,
                        null);
                clientel.add(cliente);
                pedidos.add(pedido);
            }
        }

        return pedidos;
    }

    public List<Pedido> listarPedidosPendentesPagamento() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "Select * From pedido Where status = 'pagamento pendente'";

        try (Connection conexao = ConexaoSQL.conectarBanco();
                Statement stmt = conexao.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Pedido pedido = new Pedido(
                        rs.getInt("idPedido"),
                        rs.getDate("dataCompra").toLocalDate(),
                        rs.getDouble("valorTotal"),
                        StatusPedido.valueOf(rs.getString("status")),
                        rs.getString("formaDePagamento"),
                        rs.getString("historico"),
                        rs.getInt("idEntrega"),
                        rs.getInt("idCliente"));
                pedidos.add(pedido);
            }
        }
        return pedidos;
    }

    public int contarTotalPedidosPeriodo(LocalDate dataInicial, LocalDate dataFinal) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM pedido WHERE dataCompra BETWEEN ? AND ?";

        try (Connection conexao = ConexaoSQL.conectarBanco();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(dataInicial));
            stmt.setDate(2, java.sql.Date.valueOf(dataFinal));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    public double calcularValorTotalVendasPeriodo(LocalDate dataInicial, LocalDate dataFinal) throws SQLException {
        String sql = "SELECT SUM(valorTotal) as total FROM pedido WHERE dataCompra BETWEEN ? AND ?";

        try (Connection conexao = ConexaoSQL.conectarBanco();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(dataInicial));
            stmt.setDate(2, java.sql.Date.valueOf(dataFinal));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        }
        return 0.0;
    }

    public void adicionarItemPedido(int idPedido, int idProduto, String descricao) throws SQLException {
        String sql = "INSERT INTO ItensPedido (idPedido, idProduto, descricao) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoSQL.conectarBanco();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            stmt.setInt(2, idProduto);
            stmt.setString(3, descricao);

            stmt.executeUpdate();
        }
    }
}
