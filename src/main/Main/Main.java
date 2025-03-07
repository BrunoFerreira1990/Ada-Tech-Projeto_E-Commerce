package Main;

import Logistica.EscolherFormaEntrega;
import Pagamento.PagamentoCartaoCredito;
import Pagamento.PagamentoDebito;
import Pagamento.PagamentoFinalizado;
import Pagamento.PagamentoPix;
import cliente.CadastrarClientes;
import cliente.Cliente;
import interfaces.ValidacaoProduto;
import pedido.FinalizarPedido;
import pedido.Pedido;
import produto.*;
import enums.FormasEntrega;
import enums.StatusPedido;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        CadastrarClientes cadastrarClientes = new CadastrarClientes();
        ValidacaoProduto validacaoProduto = new ValidacaoDadosProduto();
        PagamentoFinalizado pagamentoFinalizado = new PagamentoFinalizado();
        CadastrarProduto produto = new CadastrarProduto(validacaoProduto);
        Pedido pedido = new Pedido(produto);
        FinalizarPedido finalizarPedido = new FinalizarPedido();

        Scanner sc = new Scanner(System.in);
        int menuPrincipal;
        do {
            System.out.println("====== MENU PRINCIPAL ======");
            System.out.println("1 - Cadastro de clientes");
            System.out.println("2 - Cadastro de produtos");
            System.out.println("3 - Adição de produtos no carrinho de compras");
            System.out.println("4 - Finalizar pedido");
            System.out.println("5 - Realizar o pagamento do pedido");
            System.out.println("6 - Consultar pedido");
            System.out.println("7 - Sair do sistema");
            System.out.print("Escolha uma opção: ");
            menuPrincipal = sc.nextInt();
            System.out.println();

            switch (menuPrincipal) {

                case 1:
                    int opcaoCadastro;
                    do {
                        System.out.println("\n------ MENU CADASTRO DE CLIENTES ------");
                        System.out.println("1 - Cadastrar cliente");
                        System.out.println("2 - Atualizar dados de cliente");
                        System.out.println("3 - Listar clientes");
                        System.out.println("4 - Voltar ao Menu Principal\n");
                        System.out.print("Escolha uma opção: ");

                        opcaoCadastro = sc.nextInt();
                        sc.nextLine();

                        switch (opcaoCadastro) {
                            case 1:
                                Cliente novoCliente = new Cliente();
                                cadastrarClientes.cadastrar(novoCliente);
                                break;
                            case 2:
                                Cliente clienteAtualizado = new Cliente();
                                cadastrarClientes.atualizar(clienteAtualizado);
                                break;
                            case 3:
                                cadastrarClientes.listar();
                                break;
                            case 4:
                                System.out.println("Voltando ao Menu Principal\n");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                        }
                    } while (opcaoCadastro != 4);
                    break;

                case 2:
                    int opcaoProduto;

                    do {
                        System.out.println("\n------ MENU CADASTRO DE PRODUTOS ------");
                        System.out.println("1 - Cadastrar produto");
                        System.out.println("2 - Atualizar produto");
                        System.out.println("3 - Listar produtos");
                        System.out.println("4 - Voltar ao Menu Principal\n");
                        System.out.print("Escolha uma opção: ");

                        opcaoProduto = sc.nextInt();
                        sc.nextLine();

                        switch (opcaoProduto) {
                            case 1:
                                Produto novoProduto = null;
                                produto.cadastrar(novoProduto);
                                break;
                            case 2:
                                produto.listar();

                                System.out.print("Informe o ID do produto que deseja atualizar: ");
                                int idProdutoAtualizar = sc.nextInt();
                                sc.nextLine();

                                Produto produtoAtualizar = null;
                                for (Produto p : produto.getListaProdutos()) {
                                    if (p.getIdProduto() == idProdutoAtualizar) {
                                        produtoAtualizar = p;
                                        break;
                                    }
                                }
                                if (produtoAtualizar != null) {
                                    produto.atualizar(produtoAtualizar);
                                } else {
                                    System.out.println("Produto não encontrado.");
                                }
                                break;
                            case 3:
                                produto.listar();
                                break;
                            case 4:
                                System.out.println("Voltando ao Menu Principal\n");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                        }

                    } while (opcaoProduto != 4);
                    break;

                case 3:
                    if (pedido.getCliente() == null) {
                        System.out.println("Nenhum cliente associado ao pedido. Selecione um cliente antes de " +
                                "adicionar produtos.");
                        cadastrarClientes.listar();

                        System.out.print("Informe o ID do cliente que deseja associar ao pedido: ");

                        while (!sc.hasNextInt()) {
                            System.out.println("Entrada inválida. Digite um número válido.");
                            sc.next();
                        }

                        int idCliente = sc.nextInt();
                        sc.nextLine();

                        Cliente clienteSelecionado = cadastrarClientes.buscarPorId(idCliente);

                        if (clienteSelecionado != null) {
                            pedido.setCliente(clienteSelecionado);
                            System.out.println("Cliente " + clienteSelecionado.getNome() + " associado ao pedido.");
                        } else {
                            System.out.println("Cliente não encontrado. Retornando ao menu principal.");
                            break;
                        }
                    }

                    int opcaoPedido;
                    do {
                        System.out.println("\n------ MENU PEDIDOS ------");
                        System.out.println("1 - Adicionar produto no carrinho");
                        System.out.println("2 - Remover produto do carrinho");
                        System.out.println("3 - Alterar quantidade de produto adicionado");
                        System.out.println("4 - Consultar produtos no carrinho");
                        System.out.println("5 - Voltar ao menu principal\n");
                        System.out.print("Escolha uma opção: ");

                        while (!sc.hasNextInt()) {
                            System.out.println("Entrada inválida. Digite um número válido.");
                            sc.next();
                        }

                        opcaoPedido = sc.nextInt();
                        sc.nextLine();

                        switch (opcaoPedido) {
                            case 1:
                                pedido.adicionarProduto();
                                break;
                            case 2:
                                pedido.removerProdutoDoPedido();
                                break;
                            case 3:
                                pedido.alterarQuantidadeProduto();
                                break;
                            case 4:
                                Pedido.consultarProdutosCarrinho(pedido.getListaDePedido());
                                break;
                            case 5:
                                System.out.println("Voltando ao Menu Principal\n");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                        }
                    } while (opcaoPedido != 5);
                    break;
                case 4:
                    if (pedido.getStatusPedido() == StatusPedido.ABERTO) {
                        EscolherFormaEntrega escolherFormaEntrega = new EscolherFormaEntrega();
                        FormasEntrega formaEntrega = escolherFormaEntrega.escolherFormaEntrega(sc);

                        if (formaEntrega != null) {
                            finalizarPedido = new FinalizarPedido();
                            finalizarPedido.finalizar(pedido, formaEntrega);
                            PagamentoFinalizado.finalizarPagamento(cliente, pedido);
                            System.out.println("A forma de entrega escolhida foi: " + EscolherFormaEntrega.formaEntregaEscolhida.name());

                        } else {
                            System.out.println("Não foi possível finalizar o pedido, pois a forma de entrega é " +
                                    "inválida.");
                        }
                    } else {
                        System.out.println("Não é possível finalizar um pedido com status diferente de ABERTO.");
                    }
                    break;


                case 5:
                    int opcaoPagamento;
                    do {
                        System.out.println("\nSelecione o método de pagamento:");
                        System.out.println("1 - Débito");
                        System.out.println("2 - Crédito");
                        System.out.println("3 - Pix");
                        System.out.println("4 - Sair para o Menu Principal\n");
                        System.out.print("Escolha uma opção: ");
                        opcaoPagamento = sc.nextInt();
                        switch (opcaoPagamento) {
                            case 1:
                                PagamentoDebito pagamentoDebito = new PagamentoDebito();
                                pagamentoDebito.pagar(pedido, finalizarPedido);
                                pagamentoFinalizado.finalizarPagamento(cliente, pedido);
                                return;
                            case 2:
                                PagamentoCartaoCredito pagamentoCredito = new PagamentoCartaoCredito();
                                pagamentoCredito.pagar(pedido, finalizarPedido);
                                pagamentoFinalizado.finalizarPagamento(cliente, pedido);
                                return;
                            case 3:
                                PagamentoPix pagamentoPix = new PagamentoPix();
                                pagamentoPix.pagar(pedido, finalizarPedido);
                                pagamentoFinalizado.finalizarPagamento(cliente, pedido);
                                return;
                            case 4:
                                System.out.println("Saindo para o Menu Principal");
                                break;
                            default:
                                System.out.println("Opção inválida, digite novamente.");
                        }
                    } while (opcaoPagamento != 4);

                case 6:

                    System.out.println("\n--- CONSULTAR PEDIDO ---");
                    if (pedido.getCliente() != null) {
                        System.out.println("ID do Pedido: " + pedido.getIdPedido());
                        System.out.println("Cliente: " + pedido.getCliente().getNome());
                        System.out.println("Status do Pedido: " + pedido.getStatusPedido());
                        Pedido.consultarProdutosCarrinho(pedido.getListaDePedido());
                        System.out.println("Valor Total Pedido R$: " + FinalizarPedido.getValorPedido());
                    } else {
                        System.out.println("Nenhum pedido foi realizado ainda.");
                    }
                    break;

                case 7:
                    System.out.println("Saindo do sistema.");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (menuPrincipal != 7);
    }
}

