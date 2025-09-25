package com.loja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoSQL {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1434;databaseName=master;encrypt=true;trustServerCertificate=true;";
        String usuario = "lojaUser";
        String senha = "SenhaSegura123";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("Conectado com sucesso ao SQL Server!");
            Statement stmt = conexao.createStatement();
            ResultSet resultadoDoSelect = stmt.executeQuery("SELECT * FROM cliente");

            while (resultadoDoSelect.next()) {
                System.out.println("ID: " + resultadoDoSelect.getInt("idCliente"));
                System.out.println("Nome: " + resultadoDoSelect.getString("nome"));
                System.out.println("Email: " + resultadoDoSelect.getString("telefone"));
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            System.out.println("erro"); // fecha o banco aqui
            e.printStackTrace();
        }
    }

}
