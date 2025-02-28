package pedido;

import cliente.Cliente;
import enums.StatusPedido;
import produto.CadastrarProduto;
import produto.Produto;

import java.util.*;

public class Pedido {

    private static int contadorIdPedido = 0;

    private int idPedido;
    private Date dataCriacao;
    private StatusPedido statusPedido;
    private int quantidade;
    private Cliente cliente;
    private Map<Produto, Integer> listaDePedido;
    private CadastrarProduto cadastro;

    public Pedido(int idPedido, Date dataCriacao, StatusPedido statusPedido, int quantidade, Cliente cliente,
                  Map<Produto, Integer> listaDePedido) {
        this.idPedido = ++contadorIdPedido;
        this.dataCriacao = new Date();
        this.statusPedido = StatusPedido.ABERTO;
        this.quantidade = quantidade;
        this.cliente = cliente;
        this.listaDePedido = (listaDePedido != null) ? listaDePedido : new HashMap<>();
    }

    public Pedido(CadastrarProduto cadastro) {
        this.idPedido = ++contadorIdPedido;
        this.cadastro = cadastro;
        this.listaDePedido = new HashMap<>();
        this.statusPedido = StatusPedido.ABERTO;
        this.dataCriacao = new Date();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Map<Produto, Integer> getListaDePedido() {
        return listaDePedido;
    }

    public void setListaDePedido(Map<Produto, Integer> listaDePedido) {
        this.listaDePedido = listaDePedido;
    }

    public void adicionarProduto() {
        Scanner sc = new Scanner(System.in);

        if (this.statusPedido != StatusPedido.ABERTO) {
            System.out.println("O status do pedido não está aberto. Não é possível adicionar itens.");
            return;
        }

        if (this.cliente == null) {
            System.out.println("Erro: Nenhum cliente está vinculado a este pedido.");
            return;
        }

        System.out.println("Cliente associado ao pedido: " + cliente.getNome() + " (ID: " + cliente.getIdCliente() +
                ")");

        int opcao;
        do {
            System.out.println("\n--- Menu de Adição de Produto ---");
            System.out.println("1 - Consultar produtos cadastrados");
            System.out.println("2 - Adicionar produto ao pedido");
            System.out.println("3 - Sair para o Menu de Pedidos");
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
                    int idProduto = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Informe a quantidade do produto: ");
                    int quantidade = sc.nextInt();
                    sc.nextLine();

                    Produto produto = buscarProdutoPorId(cadastro, idProduto);

                    if (produto != null && quantidade > 0) {
                        listaDePedido.put(produto, quantidade);
                        System.out.println("Produto \"" + produto.getNome() + "\" adicionado ao pedido com a " +
                                "quantidade: " + quantidade);
                    } else {
                        if (produto == null) {
                            System.out.println("Produto não encontrado com o ID informado.");
                        } else {
                            System.out.println("Quantidade inválida. A quantidade deve ser maior que zero.");
                        }
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


    public void removerProdutoDoPedido() {
        CadastrarProduto cadastro = new CadastrarProduto();
        Scanner sc = new Scanner(System.in);

        if (this.statusPedido != StatusPedido.ABERTO) {
            System.out.println("O status do pedido não está aberto. Não é possível remover itens.");
            return;
        }

        int opcao;

        do {
            System.out.println("--- Menu de Remoção de Produto ---");
            System.out.println("1 - Remover produto");
            System.out.println("2 - Consultar os produtos adicionados");
            System.out.println("3 - Voltar ao Menu Pedidos\n");
            System.out.print("Escolha uma opção: ");

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
                    consultarProdutosCarrinho(getListaDePedido());
                    break;
                case 3:
                    System.out.println("Voltando para o Menu Principal.");
                default:
                    System.out.println("Opção inválida, digite novamente.");
            }

        } while (opcao != 3);

    }

    public void alterarQuantidadeProduto() {
        Scanner sc = new Scanner(System.in);

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

    private Produto buscarProdutoPorId(CadastrarProduto cadastro, int idProduto) {
        if (cadastro.getListaProdutos() == null || cadastro.getListaProdutos().isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return null;
        }

        for (Produto produto : cadastro.getListaProdutos()) {
            if (produto.getIdProduto() == idProduto) {
                return produto; // Retorna o produto encontrado
            }
        }

        System.out.println("Produto com ID " + idProduto + " não encontrado.");
        return null;
    }

    public static void consultarProdutosCarrinho(Map<Produto, Integer> listaDePedido) {

        System.out.println("Produtos no pedido:");
        if (listaDePedido.isEmpty()) {
            System.out.println("Nenhum produto foi adicionado ao pedido.");
        } else {
            for (Map.Entry<Produto, Integer> entry : listaDePedido.entrySet()) {
                Produto produto = entry.getKey();
                int quantidade = entry.getValue();

                System.out.println("ID: " + produto.getIdProduto() +
                        " | Nome: " + produto.getNome() +
                        " | Categoria: " + produto.getCategoria() +
                        " | Valor de venda: " + produto.getValorVenda() +
                        " | Quantidade: " + quantidade);
            }
        }

    }

}