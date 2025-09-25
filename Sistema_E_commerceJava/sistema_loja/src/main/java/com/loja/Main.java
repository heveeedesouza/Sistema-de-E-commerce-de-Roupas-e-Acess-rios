package com.loja;

import java.io.Console;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    static Console teclado = System.console();

    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1434;databaseName=master;encrypt=true;trustServerCertificate=true;";
        String usuario = "sa";
        // nota: acredito que será necessário alterar o usuário e a senha conforme o seu
        // ambiente, posso pedir para o usuário alterar inserir essa info
        String senha = "usuario";

        int opcao;
        do {
            exibirMenu();
            opcao = Integer.parseInt(teclado.readLine());
            switch (opcao) {
                case 1:
                    inserirClientes();
                    break;
                case 2:
                    inserirFornecedores();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    excluirCliente();
                    break;
                case 6:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 6);
        // inserirProdutos();
        // inserirVendas();
        // inserirItensVenda();
        // consultarClientes();
        // consultarProdutos();
        // consultarVendas();
        // consultarItensVenda();
        // atualizarClientes();
        // atualizarProdutos();
        // atualizarVendas();
        // atualizarItensVenda();

    }

    public static void exibirMenu() {
        System.out.println("Bem-vindo ao sistema de e-commerce!");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Inserir Clientes");
        System.out.println("2. Inserir Fornecedores");
        System.out.println("3. Consultar ");
        System.out.println("4. Atualizar ");
        System.out.println("5. Excluir cliente");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // depois colocar os atributos como parâmetros dos métodos abaixo
    private static void inserirClientes() {
        System.out.println("Inserindo clientes...");

        try (var conexao = ConexaoSQL.conectarBanco();) {
            String sql = "insert into cliente (idCliente, nome, cpf, telefone, numero, rua, cep) " +
                    "values (100, 'Carlos', '12345678910', '99887766', '123', 'Rua das Flores', '49000-000');";

            try (Statement stmt = conexao.createStatement()) {
                stmt.executeUpdate(sql);
                System.out.println("Cliente inserido com sucesso!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }

    }

    private static void inserirFornecedores() {
        System.out.println("Inserindo fornecedores...");

        try (var conexao = ConexaoSQL.conectarBanco();) {
            String sql = "insert into fornecedor (idFornecedor, cnpj, contato, nome, numero, rua, cep)" +
                    "values (100, '11222333000109', '99887766', 'Fornecedor12', '45', 'Rua Itabaiana', '49000-111');";

            try (Statement stmt = conexao.createStatement()) {
                stmt.executeUpdate(sql);
                System.out.println("Fornecedor inserido com sucesso!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir fornecedor: " + e.getMessage());
        }

    }

    private static void excluirCliente() {
        System.out.println("Excluindo cliente...");

        try (var conexao = ConexaoSQL.conectarBanco();) {
            String sql = "DELETE FROM cliente WHERE idCliente = 100;";

            try (Statement stmt = conexao.createStatement()) {
                stmt.executeUpdate(sql);
                System.out.println("Cliente excluído com sucesso!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }

    }
}
