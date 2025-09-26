package com.loja;

import com.loja.Fornecedor;
import com.loja.ConexaoSQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    public void inserir(Fornecedor fornecedor) throws SQLException {
        String sql = "INSERT INTO fornecedor (idFornecedor, cnpj, contato, nome, rua, numero, cep) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoSQL.conectarBanco();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, fornecedor.getId());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getContato());
            stmt.setString(4, fornecedor.getNome());
            stmt.setString(5, fornecedor.getRua());
            stmt.setString(6, fornecedor.getNumero());
            stmt.setString(7, fornecedor.getCep());

            stmt.executeUpdate();
        }
    }

    public Fornecedor buscar(Long id) throws SQLException {
        Fornecedor fornecedor = null;
        String sql = "SELECT * FROM fornecedor WHERE idFornecedor = ?";

        try (Connection conexao = ConexaoSQL.conectarBanco();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                fornecedor = new Fornecedor(
                        rs.getLong("idFornecedor"),
                        rs.getString("cnpj"),
                        rs.getString("contato"),
                        rs.getString("nome"),
                        rs.getString("rua"),
                        rs.getString("numero"),
                        rs.getString("cep"));
            }
        }
        return fornecedor;
    }

    public List<Fornecedor> listarFornecedoresSemProdutos() throws SQLException {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT f.* FROM fornecedor f WHERE f.idFornecedor NOT IN (SELECT DISTINCT idFornecedor FROM Fornece)";

        try (Connection conexao = ConexaoSQL.conectarBanco();
                Statement stmt = conexao.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(
                        rs.getLong("idFornecedor"),
                        rs.getString("cnpj"),
                        rs.getString("contato"),
                        rs.getString("nome"),
                        rs.getString("rua"),
                        rs.getString("numero"),
                        rs.getString("cep"));
                fornecedores.add(fornecedor);
            }
        }
        return fornecedores;
    }

    public Fornecedor listarFornecedoresPorProduto(Long idProduto) throws SQLException {
        Fornecedor fornecedor = null;
        String sql = "SELECT f.* FROM fornecedor f " +
                "JOIN Fornece fn ON f.idFornecedor = fn.idFornecedor " +
                "WHERE fn.idProduto = ?";

        try (Connection conexao = ConexaoSQL.conectarBanco();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, idProduto);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                fornecedor = new Fornecedor(
                        rs.getLong("idFornecedor"),
                        rs.getString("cnpj"),
                        rs.getString("contato"),
                        rs.getString("nome"),
                        rs.getString("rua"),
                        rs.getString("numero"),
                        rs.getString("cep"));
            }
        }
        return fornecedor;
    }

    public void vincularFornecedorProduto(Long idFornecedor, Long idProduto, String descricao) throws SQLException {
        String sql = "INSERT INTO Fornece (idProduto, idFornecedor, descricao) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoSQL.conectarBanco();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, idProduto);
            stmt.setLong(2, idFornecedor);
            stmt.setString(3, descricao);

            stmt.executeUpdate();
        }
    }

}