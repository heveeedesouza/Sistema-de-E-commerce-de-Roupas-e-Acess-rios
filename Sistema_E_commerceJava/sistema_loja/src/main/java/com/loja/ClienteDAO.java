package com.loja;

import java.sql.SQLException;

/*Aqui, estou criando duas classes. 
A classe cliente serve para obter os atributos do mesmo, enquanto clienteDAO 
serve para chamar o cliente e implementar métodos para manipular os dados utilizando os comandos em SQL */
public class ClienteDAO {

    public void inserir(Cliente cliente) throws SQLException {
        var sql = "insert into cliente (idCliente, nome, cpf, telefone, numero, rua, cep) " +
                "values (?, ?, ?, ?, ?, ?, ?);";

        try (var conexao = ConexaoSQL.conectarBanco();
                var stmt = conexao.prepareStatement(sql)) {

            /*
             * na var sql eu estou inserindo os valores e susbtituindo pelos ?. cada ?
             * representa uma atributo do cliente que eu quero inserir
             * então eu uso o stmt.set para setar cada atributo do cliente, o primeiro
             * parâmetro é a posição do ? que eu quero substituir, começando do 1
             * o segundo parâmetro é o valor que eu quero inserir, por exemplo o nome do
             * cliente que eu pego com cliente.nome().
             * E assim por diante para os outros atributos
             */
            stmt.setLong(1, cliente.getId());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getNumero());
            stmt.setString(6, cliente.getRua());
            stmt.setString(7, cliente.getCep());

            stmt.executeUpdate(); // aqui está executando o comando sql que eu montei acima
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    public Boolean buscar(int id) throws SQLException {
        var sql = "select * from cliente where idCliente = ?;";
        Cliente cliente = null;

        try (var conexao = ConexaoSQL.conectarBanco();
                var stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("rua"),
                        rs.getString("numero"),
                        rs.getString("cep"));
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar cliente: " + e.getMessage());
        }

        if (cliente != null) {
            System.out.println("Cliente encontrado: " + cliente.getNome());
            return true;
        } else {
            System.out.println("Cliente não encontrado.");
            return false;
        }
    }

    public void remover(int idCliente) throws SQLException {
        var sql = "delete from cliente where idCliente = ?;";

        try (var conexao = ConexaoSQL.conectarBanco();
                var stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao remover cliente: " + e.getMessage());
        }
    }

}
