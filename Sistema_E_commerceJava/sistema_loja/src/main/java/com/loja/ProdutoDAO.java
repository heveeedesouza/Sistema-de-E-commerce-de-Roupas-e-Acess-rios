package com.loja;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    
    public void inserir(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (idProduto, descricao, nome, preco, quantEstoque, cor, tamanho, idCategoria) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conexao = ConexaoSQL.conectarBanco();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setInt(1, produto.getId());
            stmt.setString(2, produto.getDescricao());
            stmt.setString(3, produto.getNome());
            stmt.setDouble(4, produto.getPreco());
            stmt.setInt(5, produto.getQuantEstoque());
            stmt.setString(6, produto.getCor());
            stmt.setString(7, produto.getTamanho());
            stmt.setInt(8, produto.getIdCategoria());
            
            stmt.executeUpdate();
        }
    }
    
    public void atualizarPreco(int idProduto, double novoPreco) throws SQLException {
        String sql = "UPDATE produto SET preco = ? WHERE idProduto = ?";
        
        try (Connection conexao = ConexaoSQL.conectarBanco();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setDouble(1, novoPreco);
            stmt.setInt(2, idProduto);
            stmt.executeUpdate();
        }
    }
    
    public void atualizarEstoque(int idProduto, int novaQuantidade) throws SQLException {
        String sql = "UPDATE produto SET quantEstoque = ? WHERE idProduto = ?";
        
        try (Connection conexao = ConexaoSQL.conectarBanco();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, idProduto);
            stmt.executeUpdate();
        }
    }
}