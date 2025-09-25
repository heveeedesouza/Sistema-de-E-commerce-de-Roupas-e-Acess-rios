package com.loja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQL {
    private static final String URL = "jdbc:sqlserver://localhost:1434;databaseName=master;encrypt=true;trustServerCertificate=true;";
    private static final String USUARIO = "sa";
    private static final String SENHA = "usuario";

    public static Connection conectarBanco() {
        Connection conexao = null;

        try {
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão bem-sucedida!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }

        return conexao;
    }

}
