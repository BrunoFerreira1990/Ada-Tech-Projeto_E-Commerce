package pedido;
import enums.FormasEntrega;
import enums.StatusPedido;
import produto.CadastrarProduto;
import produto.Produto;

import java.util.*;

public class Pedido {

    private int idPedido;
    private Date dataCriacao;
    private StatusPedido statusPedido;
    private int quantidade;
    private Map<Produto, Integer> listaDePedido;


    public Pedido(int idPedido, Date dataCriacao, StatusPedido statusPedido, int quantidade,
                  List<Produto> listaDePedido) {
        this.idPedido = idPedido;
        this.dataCriacao = new Date();
        this.statusPedido = StatusPedido.ABERTO;
        this.quantidade = quantidade;
        this.listaDePedido = new HashMap<>();
    }


    public int getIdPedido() {
        return idPedido;
    }

    public void setId(int id) {
        this.idPedido = idPedido;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }


    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Map<Produto, Integer> getListaDePedido() {
        return listaDePedido;
    }

    public void setListaDePedido(Map<Produto, Integer> listaDePedido) {
        this.listaDePedido = listaDePedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void adicionarItem(CadastrarProduto cadastro, Scanner sc) {
        if (this.statusPedido != StatusPedido.ABERTO) {
            System.out.println("O status do pedido não está aberto. Não é possível adicionar itens.");
            return;
        }

        int opcao;

        do {
            System.out.println("\n=== Menu de Adição de Produto ===");
            System.out.println("1 - Consultar produtos cadastrados");
            System.out.println("2 - Adicionar produto ao pedido");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");


            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Digite um número válido.");
                sc.next();
            }
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastro.listar();
                    break;

                case 2:
                    System.out.print("Informe o número de identificação (ID) do produto desejado: ");

                    while (!sc.hasNextInt()) {
                        System.out.println("Entrada inválida. Digite um número válido.");
                        sc.next();
                    }
                    int idProduto = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Informe a quantidade do produto: ");
                    quantidade = sc.nextInt();

                    Produto produto = buscarProdutoPorId(cadastro, idProduto);
                    if (produto != null) {
                        listaDePedido.put(produto, quantidade);
                        System.out.println("Produto \"" + produto.getNome() + "\" adicionado ao pedido com a quantidade: " + quantidade);
                    } else {
                        System.out.println("Produto não encontrado com o ID informado.");
                    }
                    break;

                case 3:
                    System.out.println("Voltando ao menu principal.");
                    break;

                default:
                    System.out.println("Opção inválida, digite novamente.");
            }
        } while (opcao != 3);
    }


    public void removerProdutoDoPedido(CadastrarProduto cadastro, Scanner sc) {
        if (this.statusPedido != StatusPedido.ABERTO) {
            System.out.println("O status do pedido não está aberto. Não é possível remover itens.");
            return;
        }

        int opcao;

        do {
            System.out.println("=== Menu de Remoção de Produto ====");
            System.out.println("1 - Remover produto");
            System.out.println("2 - Consultar os produtos adicionados");
            System.out.println("3 - Voltar ao Menu Principal");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Informe o ID do produto que deseja remover: ");
                    int idProduto = sc.nextInt();

                    Produto produtoRemover = null;
                    boolean encontrado = false;

                    for (Map.Entry<Produto, Integer> entry : listaDePedido.entrySet()) {
                        Produto produto = entry.getKey();
                        if (produto.getIdProduto() == idProduto) {
                            produtoRemover = produto;
                            encontrado = true;
                            break;
                        }
                    }
                    if (encontrado) {
                        listaDePedido.remove(produtoRemover);
                        System.out.println("Produto " + produtoRemover.getNome() + " removido do pedido com sucesso!");
                    } else {
                        System.out.println("Produto com ID " + idProduto + " não encontrado no pedido.");
                    }
                    break;

                case 2:
                    System.out.println("Produtos no pedido:");
                    if (listaDePedido.isEmpty()) {
                        System.out.println("Nenhum produto foi adicionado ao pedido.");
                    } else {
                        for (Map.Entry<Produto, Integer> entry: listaDePedido.entrySet()) {
                            Produto produto = entry.getKey();
                            int quantidade = entry.getValue();

                            System.out.println("ID: " + produto.getIdProduto() +
                                    " | Nome: " + produto.getNome() +
                                    " | Categoria: " + produto.getCategoria() +
                                    " | Valor de venda: " + produto.getValorVenda() +
                                    " | Quantidade: " + quantidade);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Voltando para o Menu Principal.");
                default:
                    System.out.println("Opção inválida, digite novamente.");
            }

        } while (opcao != 3);

    }

    private Produto buscarProdutoPorId(CadastrarProduto cadastro, int idProduto) {
        for (Produto produto : cadastro.getListaProdutos()) {
            if (produto.getIdProduto() == idProduto) {
                return produto;
            }
        }
        return null;
    }

    public void alterarQuantidadeProduto(Scanner sc) {
        if (this.statusPedido != StatusPedido.ABERTO) {
            System.out.println("O status do pedido não está aberto. Não é possível alterar a quantidade de itens.");
            return;
        }

        System.out.print("Informe o ID do produto que deseja alterar a quantidade: ");
        int idProduto = sc.nextInt();
        sc.nextLine();

        Produto produtoAlterar = null;
        boolean encontrado = false;

        for (Map.Entry<Produto, Integer> entry : listaDePedido.entrySet()) {
            Produto produto = entry.getKey();
            if (produto.getIdProduto() == idProduto) {
                produtoAlterar = produto;
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            System.out.print("Informe a nova quantidade para o produto \"" + produtoAlterar.getNome() + "\": ");
            int novaQuantidade = sc.nextInt();
            sc.nextLine();

            if (novaQuantidade < 0) {
                System.out.println("Quantidade não pode ser negativa.");
            } else {
                listaDePedido.put(produtoAlterar, novaQuantidade);
                System.out.println("Quantidade do produto \"" + produtoAlterar.getNome() + "\" alterada para: " + novaQuantidade);
            }
        } else {
            System.out.println("Produto com ID " + idProduto + " não encontrado no pedido.");
        }
    }



    public static void realizarPagamento() {

    }

    public static void entrega(Pedido pedido, FormasEntrega formaEntrega) {
        if (pedido.getStatusPedido() != StatusPedido.ABERTO) {
            System.out.println("O status do pedido não está aberto. Não é possível definir a entrega.");
            return;
        }


        double valorFrete = formaEntrega.getValor();


        System.out.println("Pedido ID: " + pedido.getIdPedido());
        System.out.println("Forma de Entrega: " + formaEntrega);
        System.out.println("Valor do Frete: R$ " + valorFrete);

    }


}