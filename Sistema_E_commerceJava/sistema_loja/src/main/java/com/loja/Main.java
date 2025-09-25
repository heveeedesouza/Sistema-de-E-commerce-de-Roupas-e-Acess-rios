package com.loja;

import java.io.Console;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1434;databaseName=master;encrypt=true;trustServerCertificate=true;";
        String usuario = "sa";
        // nota: acredito que será necessário alterar o usuário e a senha conforme o seu
        // ambiente, posso pedir para o usuário alterar inserir essa info
        String senha = "usuario";

        int opcao;
        do {
            exibirMenu();
            opcao = teclado.nextInt();
            // Limpa o buffer
            teclado.nextLine();
            switch (opcao) {
                case 1:
                    inserirClientes();
                    break;
                case 2:
                    inserirFornecedores();
                    break;
                case 3:
                    inserirProduto();
                    break;
                case 4:
                    definirFornecedorProduto();
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
        System.out.println("3. Inserir Produto");
        System.out.println("4. Definir fornecedor para o produto");
        System.out.println("5. Excluir cliente");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // depois colocar os atributos como parâmetros dos métodos abaixo
    private static void inserirClientes() {
        System.out.println("Inserindo clientes...\n");

        System.out.println("Digite o ID do cliente:");
        Long id = Long.parseLong(teclado.nextLine());

        System.out.println("Digite o nome do cliente:");
        String nome = teclado.nextLine();

        System.out.println("Digite o CPF do cliente:");
        String cpf = String.valueOf(teclado.nextLong());

        System.out.println("Digite o telefone do cliente:");
        String telefone = String.valueOf(teclado.nextLong());
        // Limpa o buffer
        teclado.nextLine();

        System.out.println("Digite a rua do cliente:");
        String rua = teclado.nextLine();

        System.out.println("Digite o número do cliente:");
        String numero = String.valueOf(teclado.nextLong());
        // Limpa o buffer
        teclado.nextLine();

        System.out.println("Digite o CEP do cliente:");
        String cep = teclado.nextLine();

        Cliente cliente = new Cliente(id, nome, cpf, telefone, rua, numero, cep);
        ClienteDAO clienteDAO = new ClienteDAO();

        try {
            clienteDAO.inserir(cliente);
            System.out.println("Cliente inserido com sucesso!\n");
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

    private static void inserirProduto() {
        System.out.println("Inserindo Produto...");

        try (var conexao = ConexaoSQL.conectarBanco();) {
            String sql = "insert into produto (idProduto, descricao, nome, preco, quantEstoque, cor, tamanho) " +
                    "values (100, 'Vestido Azul longo', 'Vestido Azul', 199.90, 10, 'Azul', 'M');";

            try (Statement stmt = conexao.createStatement()) {
                stmt.executeUpdate(sql);
                System.out.println("Produto inserido com sucesso!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir fornecedor: " + e.getMessage());
        }

    }

    private static void definirFornecedorProduto() {
        System.out.println("Definindo fornecedor para o produto...");

        try (var conexao = ConexaoSQL.conectarBanco();) {
            String sql = "insert into Fornece (idProduto, idFornecedor, descricao) " +
                    "values (100, 100, 'Fornecedor oficial do Vestido Azul');";

            try (Statement stmt = conexao.createStatement()) {
                stmt.executeUpdate(sql);
                System.out.println("Fornecedor definido com sucesso!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao definir fornecedor para o produto: " + e.getMessage());
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
