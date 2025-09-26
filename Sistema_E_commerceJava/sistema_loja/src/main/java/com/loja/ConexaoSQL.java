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
            System.out.println("Conexão bem-sucedida!\n");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }

        return conexao;
    }

    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão fechada com sucesso.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

}
