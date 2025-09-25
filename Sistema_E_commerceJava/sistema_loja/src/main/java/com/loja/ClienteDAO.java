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
            stmt.setLong(1, cliente.id());
            stmt.setString(2, cliente.nome());
            stmt.setString(3, cliente.cpf());
            stmt.setString(4, cliente.telefone());
            stmt.setString(5, cliente.numero());
            stmt.setString(6, cliente.rua());
            stmt.setString(7, cliente.cep());

            stmt.executeUpdate(); // aqui está executando o comando sql que eu montei acima
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir cliente: " + e.getMessage());
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
