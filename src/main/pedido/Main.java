package pedido;

import cliente.CadastrarClientes;
import cliente.Cliente;
import produto.CadastrarProduto;
import produto.Produto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CadastrarClientes cadastrarClientes = new CadastrarClientes();
        CadastrarProduto produto = new CadastrarProduto();
        Pedido pedido = new Pedido(produto);

        Scanner sc = new Scanner(System.in);
        int menuPrincipal;
        do {
            System.out.println("====== MENU PRINCIPAL ======");
            System.out.println("1 - Cadastro de clientes");
            System.out.println("2 - Cadastro de produtos");
            System.out.println("3 - Adição de produtos no carrinho de compras");
            System.out.println("4 - Sair do sistema");
            System.out.print("Escolha uma opção: ");
            menuPrincipal = sc.nextInt();
            System.out.println();

            switch (menuPrincipal) {

                case 1:
                    int opcaoCadastro;
                    do{

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
                    }while (opcaoCadastro != 4);
                    break;

                case 2:
                    int opcaoProduto;

                    do{

                        System.out.println("\n------ MENU CADASTRO DE PRODUTOS ------");
                        System.out.println("1 - Cadastrar produto");
                        System.out.println("2 - Atualizar produto");
                        System.out.println("3 - Listar produto");
                        System.out.println("4 - Voltar ao Menu Principal\n");
                        System.out.print("Escolha uma opção: ");

                        opcaoProduto = sc.nextInt();
                        sc.nextLine();

                        switch (opcaoProduto) {
                            case 1:
                                Produto novoProduto = new Produto();
                                produto.cadastrar(novoProduto);
                                break;
                            case 2:
                                Produto atualizarProduto = new Produto();
                                produto.atualizar(atualizarProduto);
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
                        System.out.println("Nenhum cliente associado ao pedido. Selecione um cliente antes de adicionar produtos.");
                        cadastrarClientes.listar();

                        System.out.print("Informe o ID do cliente que deseja associar ao pedido: ");

                        while (!sc.hasNextInt()) {
                            System.out.println("Entrada inválida. Digite um número válido.");
                            sc.next(); // Descarta a entrada inválida
                        }

                        int idCliente = sc.nextInt();
                        sc.nextLine();

                        Cliente clienteSelecionado = cadastrarClientes.buscarClientePorId (idCliente);

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
                    System.out.println("Saindo do sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while(menuPrincipal != 4);
    }
}
