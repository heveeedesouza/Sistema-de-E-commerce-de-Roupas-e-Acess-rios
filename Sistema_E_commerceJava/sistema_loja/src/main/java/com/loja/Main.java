package com.loja;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner teclado = new Scanner(System.in);
    static ImprimeTela imprime = new ImprimeTela();

    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1434;databaseName=master;encrypt=true;trustServerCertificate=true;";
        String usuario = "sa";
        // nota: acredito que será necessário alterar o usuário e a senha conforme o seu
        // ambiente, posso pedir para o usuário alterar inserir essa info
        String senha = "usuario";

        int opcao;
        do {
            imprime.exibirMenu();
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
                    inserirPedido();
                    break;
                case 4:
                    definirFornecedorProduto();
                    break;
                case 5:
                    excluirCliente();
                    break;
                case 6:
                    listarFornecedores();
                    break;
                case 7:
                    vincularFornecedorProduto();
                    break;
                case 8:
                    atualizarStatusPedido();
                    break;
                case 9:

                    listarPedidos(teclado.nextInt());
                    break;
                case 10:
                    exibirTotalPedidosPeriodo();

                    break;
                case 11:
                    exibirTotalVendasPeriodo();

                    break;

                default:
                    System.out.println("Opção inválida!");
            }
            imprime.limparTela();
        } while (opcao != 0);

    }

    private static void inserirClientes() {

        Cliente cliente = imprime.inserirInformacoesCliente();
        ClienteDAO clienteDAO = new ClienteDAO();

        try {
            clienteDAO.inserir(cliente);
            System.out.println("Cliente inserido com sucesso!\n");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }

    }

    /* Op com fornecedores */
    private static void inserirFornecedores() {

        Fornecedor fornecedor = imprime.inserirInformacoesFornecedor();

        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        try {
            fornecedorDAO.inserir(fornecedor);
            System.out.println("Fornecedor inserido com sucesso!\n");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir fornecedor: " + e.getMessage());
        }

    }

    private static void listarFornecedores() {
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        int op = 0;
        do {
            imprime.limparTela();
            imprime.exibirMenuFornecedor();
            op = teclado.nextInt();
            // Limpa o buffer
            teclado.nextLine();

            switch (op) {
                case 1:
                    try {
                        List<Fornecedor> fornecedores = fornecedorDAO.listarFornecedoresSemProdutos();
                        imprime.imprimirFornecedoresSemProduto(fornecedores);
                    } catch (Exception e) {
                        System.out.println("Erro ao listar fornecedores: " + e.getMessage());
                    }

                    // Listar fornecedores sem produtos
                    break;

                case 2:
                    System.out.println("Digite o ID do produto:");
                    int idProduto = teclado.nextInt();
                    try {
                        List<Fornecedor> fornecedores = fornecedorDAO.listarFornecedoresPorProduto(idProduto);
                        imprime.imprimirFornecedoresPorProduto(fornecedores, idProduto);
                    } catch (Exception e) {
                        System.out.println("Erro ao listar fornecedores: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (op != 3);

    }

    private static void vincularFornecedorProduto() {
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        Fornecedor fornecedor = null;
        int idFornecedor = 0;
        System.out.println("Vinculando fornecedor ao produto...");

        try {
            while (true) {// não testado ainda
                System.out.println("Digite o ID do fornecedor:");
                idFornecedor = teclado.nextInt();
                fornecedor = fornecedorDAO.buscar(idFornecedor);

                if (fornecedor == null) {
                    System.out.println("Fornecedor não encontrado.");
                    return;
                } else {
                    break;
                }
            }
            System.out.println("Digite o ID do produto:");
            int idProduto = teclado.nextInt();

            System.out.println("Descreva o vínculo:");
            String descricao = teclado.nextLine();

            fornecedorDAO.vincularFornecedorProduto(idFornecedor, idProduto, descricao);
            System.out.println("Fornecedor vinculado ao produto com sucesso!\n");

        } catch (SQLException e) {
            System.out.println("Erro ao vincular fornecedor ao produto: " + e.getMessage());
        }
    }

    /* Op com pedido */
    // 8. Registrar um novo pedido de um cliente.
    private static void inserirPedido() {

        Pedido pedido = imprime.inserirInformacoesPedido();
        PedidoDAO pedidoDAO = new PedidoDAO();

        try {
            pedidoDAO.inserir(pedido);
            System.out.println("Pedido inserido com sucesso!\n");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir pedido: " + e.getMessage());
        }

    }

    // 10. Atualizar o status de um pedido para “em processamento”, “enviado”,
    // “entregue”, “cancelado” ou “devolvido”. ----> Adicionei separado E PAGAMENTO
    // PENDENTE
    private static void atualizarStatusPedido() {
        PedidoDAO pedidoDAO = new PedidoDAO();
        System.out.println("Atualizando status do pedido...");

        try {
            System.out.println("Digite o ID do pedido:");
            int idPedido = teclado.nextInt();

            if (pedidoDAO.buscar(idPedido) == null) {
                return;
            }

            System.out.println("Digite o novo status do pedido:");
            StatusPedido novoStatus = imprime.inserirStatusPedido();

            pedidoDAO.atualizarStatus(idPedido, novoStatus);
            System.out.println("Status do pedido atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar status do pedido: " + e.getMessage());
        }

    }

    // 18. Listar todos os pedidos realizados por um cliente.
    // 21. Mostrar os pedidos que estão com status “pendente de pagamento”.
    private static void listarPedidos(int op) {
        PedidoDAO pedidoDAO = new PedidoDAO();
        List<Cliente> cliente = new ArrayList<>();
        switch (op) {
            case 3:
                System.out.println("Listando pedidos por cliente...");
                System.out.println("Digite o ID do cliente:");
                int idCliente = teclado.nextInt();

                try {
                    List<Pedido> pedidos = pedidoDAO.listarPedidosPorCliente(idCliente, cliente);
                    if (!cliente.isEmpty()) {
                        for (Cliente c : cliente) {
                            System.out.println(
                                    "Pedidos do(a) cliente: " + c.getNome() + " (CPF: " + c.getCpf() + "):\n");
                            break;
                        }
                    }

                    imprime.imprimirPedidos(pedidos);
                } catch (SQLException e) {
                    System.out.println("Erro ao listar pedidos: " + e.getMessage());
                }
                break;
            case 4:
                System.out.println("Listando pedidos com pagamento pendente...");
                try {
                    List<Pedido> pedidos = pedidoDAO.listarPedidosPendentesPagamento();
                    imprime.imprimirPedidos(pedidos);
                } catch (SQLException e) {
                    System.out.println("Erro ao listar pedidos: " + e.getMessage());
                }
                break;

            default:
                System.out.println("Opção inválida!");
        }

    }

    // 30. Exibir o total de pedidos realizados em um período específico.
    private static void exibirTotalPedidosPeriodo() {
        System.out.println("Exibindo total de pedidos em um período específico...");

        LocalDate dataInicial = imprime.lerData("data inicial");
        LocalDate dataFinal = imprime.lerData("data final");

        if (!imprime.verificaData(dataInicial, dataFinal)) {
            return;
        }
        PedidoDAO pedidoDAO = new PedidoDAO();

        try {
            int totalPedidos = pedidoDAO.contarTotalPedidosPeriodo(dataInicial, dataFinal);
            System.out.println("Total de pedidos entre " + dataInicial + " e " + dataFinal + " é: " + totalPedidos);
        } catch (SQLException e) {
            System.out.println("Erro ao contar pedidos: " + e.getMessage());
        }
    }

    // 31. Exibir o valor total arrecadado em vendas em um período.
    private static void exibirTotalVendasPeriodo() {
        System.out.println("Exibindo total de vendas em um período específico...");

        LocalDate dataInicial = imprime.lerData("data inicial");
        LocalDate dataFinal = imprime.lerData("data final");

        if (!imprime.verificaData(dataInicial, dataFinal)) {
            return;
        }
        PedidoDAO pedidoDAO = new PedidoDAO();

        try {
            double totalVendas = pedidoDAO.calcularValorTotalVendasPeriodo(dataInicial, dataFinal);
            System.out.println("Total de vendas entre " + dataInicial + " e " + dataFinal + " é: R$" + totalVendas);
        } catch (SQLException e) {
            System.out.println("Erro ao calcular total de vendas: " + e.getMessage());
        }
    }

    private static void adicionarItem() {
        PedidoDAO pedidoDAO = new PedidoDAO();
        ItensPedido itemPedido = new ItensPedido();
        imprime.inserirItemPedido(pedidoDAO, itemPedido);
        try {
            pedidoDAO.adicionarItemPedido(itemPedido.getIdPedido(), itemPedido.getIdProduto(),
                    itemPedido.getDescricao());
            System.out.println("Item adicionado ao pedido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar item ao pedido: " + e.getMessage());
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
