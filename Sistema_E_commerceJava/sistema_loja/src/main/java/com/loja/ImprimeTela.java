package com.loja;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ImprimeTela {
    Scanner teclado = new Scanner(System.in);

    public ImprimeTela() {
    }

    public void exibirMenu() {
        System.out.println("Bem-vindo ao sistema de e-commerce!");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Inserir Clientes");
        System.out.println("2. Inserir Fornecedores");
        System.out.println("3. Inserir Pedido");
        System.out.println("4. Definir fornecedor para o produto");
        System.out.println("5. Excluir cliente");
        System.out.println("6. Listar fornecedores");
        System.out.println("7. Vincular fornecedor a produto");
        System.out.println("8. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public void limparTela() {
        for (int i = 0; i < 10; ++i)
            System.out.println();
    }

    /* Digita e imprime as informações do fornecedor */
    public void exibirMenuFornecedor() {

        System.out.println("Bem-vindo ao sistema de e-commerce!");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Listar fornecedores sem produto");
        System.out.println("2. Listar fornecedores por produto");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public Fornecedor inserirInformacoesFornecedor() {
        Fornecedor fornecedor = new Fornecedor();

        System.out.println("Inserindo fornecedores...");

        System.out.println("Digite o ID:");
        fornecedor.setId(teclado.nextInt());

        System.out.println("Digite o nome do fornecedor:");
        fornecedor.setNome(teclado.nextLine());

        System.out.println("Digite o CNPJ do fornecedor:");
        fornecedor.setCnpj(String.valueOf(teclado.nextLong()));

        System.out.println("Digite o contato do fornecedor:");
        fornecedor.setContato(teclado.nextLine());
        // Limpa o buffer
        teclado.nextLine();

        System.out.println("Digite a rua do fornecedor:");
        fornecedor.setRua(teclado.nextLine());

        System.out.println("Digite o número do fornecedor:");
        fornecedor.setNumero(String.valueOf(teclado.nextLong()));
        // Limpa o buffer
        teclado.nextLine();

        System.out.println("Digite o CEP do fornecedor:");
        fornecedor.setCep(teclado.nextLine());

        return fornecedor;

    }

    public void imprimirFornecedoresSemProduto(List<Fornecedor> fornecedores) {
        if (fornecedores.isEmpty()) {
            System.out.println("Não existe fornecedores sem produtos.");
        } else {
            System.out.println("Fornecedores sem produtos:");
        }
        for (var fornecedor : fornecedores) {
            System.out.print(fornecedor.toString());

        }
        System.out.println("Pressione ENTER para continuar...");
        teclado.nextLine();
    }

    public void imprimirFornecedoresPorProduto(List<Fornecedor> fornecedores, int idProduto) {
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor encontrado para o produto com ID: " + idProduto);
        } else {
            for (var fornecedor : fornecedores) {
                System.out.print(fornecedor.toString());

            }
            System.out.println("Pressione ENTER para continuar...");
            teclado.nextLine();
        }
    }

    /* Digita info de cliente */

    public Cliente inserirInformacoesCliente() {
        Cliente cliente = new Cliente();

        System.out.println("Inserindo clientes...\n");

        System.out.println("Digite o ID do cliente:");
        cliente.setId(teclado.nextInt());

        System.out.println("Digite o nome do cliente:");
        cliente.setNome(teclado.nextLine());

        System.out.println("Digite o CPF do cliente:");
        cliente.setCpf(String.valueOf(teclado.nextLong()));

        System.out.println("Digite o telefone do cliente:");
        cliente.setTelefone(String.valueOf(teclado.nextLong()));
        // Limpa o buffer
        teclado.nextLine();

        System.out.println("Digite a rua do cliente:");
        cliente.setRua(teclado.nextLine());

        System.out.println("Digite o número do cliente:");
        cliente.setNumero(String.valueOf(teclado.nextLong()));
        // Limpa o buffer
        teclado.nextLine();

        System.out.println("Digite o CEP do cliente:");
        cliente.setCep(teclado.nextLine());

        return cliente;

    }

    public void imprimeClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
            return;
        }
        for (var cliente : clientes) {
            System.out.print(cliente.toString());

        }
    }

    /* Digita e imprime as info de pedido */
    public void exibirMenuPedido() {
        System.out.println("Bem-vindo ao sistema de e-commerce!");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Registrar um novo pedido de um cliente.");
        System.out.println("2. Atualizar Status do Pedido");
        System.out.println("3. Listar todos os pedidos realizados por um cliente.");
        System.out.println("4. Exibir o total de pedidos realizados em um período específico.");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public Pedido inserirInformacoesPedido() {
        Pedido pedido = new Pedido();
        System.out.println("Inserindo Pedido...");

        System.out.println("Digite o ID do pedido:");
        pedido.setId(teclado.nextInt());

        // Limpa o buffer
        teclado.nextLine();

        System.out.println("Digite a data (dd/MM/yyyy):");
        String dataStr = teclado.nextLine();

        // TODO: validar data
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        pedido.setDataCompra(LocalDate.parse(dataStr, formato));

        System.out.println("Qual é o valor total do pedido:");
        pedido.setValorTotal(teclado.nextDouble());

        // Limpa o buffer
        teclado.nextLine();
        System.out.println("Escolha o status do pedido:");
        pedido.setStatus(inserirStatusPedido());

        System.out.println("Digite a forma de pagamento:");
        pedido.setFormaDePagamento(teclado.nextLine());

        System.out.println("Digite o histórico do pedido:");
        pedido.setHistorico(teclado.nextLine());

        System.out.println("Digite o ID da entrega:");
        int idEntrega = teclado.nextInt();

        int idCliente = 0;
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            do {
                System.out.println("Digite o ID do cliente:");
                idCliente = teclado.nextInt();

            } while (clienteDAO.buscar(idCliente) == false);
            pedido.setIdCliente(idCliente);
            pedido.setIdEntrega(idEntrega);
        } catch (Exception e) {
            // TODO: handle exception
        }

        // verificar se cliente existe
        return pedido;
    }

    public void imprimirPedidos(List<Pedido> pedidos) {
        int i = 1;
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
            return;
        }
        for (var pedido : pedidos) {
            System.out.print("Pedido" + i + "\n" + pedido.toString());
            i++;
        }
    }

    public LocalDate lerData(String tipoData) {
        LocalDate data = null;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (data == null) {
            System.out.println("Digite a " + tipoData + " (dd/MM/yyyy):");
            String dataStr = teclado.nextLine();
            try {
                // (Aqui pode colocar o código de formatação da string, se quiser aceitar 1
                // dígito)
                data = LocalDate.parse(dataStr, formato);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Tente novamente.");
            }
        }
        System.out.println("");
        return data;

    }

    public boolean verificaData(LocalDate dataInicial, LocalDate dataFinal) {
        if (dataInicial != null && dataFinal != null) {
            if (dataInicial.isAfter(dataFinal)) {
                System.out.println("Data de início não pode ser após a data de fim.");
                return false;
            }
        }
        return true;

    }

    public void inserirItemPedido(PedidoDAO pedidoDAO, ItensPedido itensPedido) {
        System.out.println("Adicionando item ao pedido...");
        System.out.println("Digite o ID do pedido:");
        itensPedido.setIdPedido(teclado.nextInt());
        try {
            if (pedidoDAO.buscar(itensPedido.getIdPedido()) == null) {
                return;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Digite o ID do produto:");
        itensPedido.setIdProduto(teclado.nextInt());
        System.out.println("Descreva o item:");
        teclado.nextLine(); // Limpa o buffer
        itensPedido.setDescricao(teclado.nextLine());
    }

    public StatusPedido inserirStatusPedido() {
        // limparTela();
        System.out.println("1. PAGAMENTO_PENDENTE");
        System.out.println("2. PROCESSANDO");
        System.out.println("3. SEPARADO");
        System.out.println("4. ENVIADO");
        System.out.println("5. ENTREGUE");
        System.out.println("6. DEVOLVIDO");
        System.out.println("7. CANCELADO");
        System.out.print("Escolha uma opção: ");

        StatusPedido status = null;

        do {
            int statusOpcao = teclado.nextInt();
            switch (statusOpcao) {
                case 1:
                    status = StatusPedido.PAGAMENTO_PENDENTE;
                    break;
                case 2:
                    status = StatusPedido.PROCESSANDO;
                    break;
                case 3:
                    status = StatusPedido.SEPARADO;
                    break;
                case 4:
                    status = StatusPedido.ENVIADO;
                    break;
                case 5:
                    status = StatusPedido.ENTREGUE;
                    break;
                case 6:
                    status = StatusPedido.DEVOLVIDO;
                    break;
                case 7:
                    status = StatusPedido.CANCELADO;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (status == null);
        return status;
    }

}