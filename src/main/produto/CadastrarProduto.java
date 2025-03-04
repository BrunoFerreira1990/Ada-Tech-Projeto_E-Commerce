package produto;

import interfaces.Repositorio;
import interfaces.ValidacaoProduto;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastrarProduto extends Produto implements Repositorio<Produto> {

    private ValidacaoProduto validacaoProduto;
    private Scanner sc = new Scanner(System.in);
    public List<Produto> listaProdutos = new ArrayList<>();
    private int id = 0;

    public CadastrarProduto(ValidacaoProduto validacaoProduto) {
        this.validacaoProduto = validacaoProduto;
    }

    @Override
    public void cadastrar(Produto produto) {
        produto.setIdProduto(++id);

        String nome;

        do {
            System.out.print("Digite o nome do produto: ");
            nome = sc.nextLine();
            if (validacaoProduto.validarNome(nome)) {
                break;
            } else {
                System.out.println("O nome do produto não pode ser vazio ou nulo.");
            }
        } while (true);
        produto.setNome(nome);

        String categoria;

        do {
            System.out.print("Digite a categoria do produto: ");
            categoria = sc.nextLine();
            if (validacaoProduto.validarCategoria(categoria)) {
                break;
            } else {
                System.out.println("A categoria do produto não pode ser vazia ou nula.");
            }
        } while (true);
        produto.setCategoria(categoria);

        double valorVenda;

        do {
            System.out.print("Informe o valor de venda do produto: ");
            valorVenda = sc.nextDouble();
            sc.nextLine();
            if (validacaoProduto.validarValorVenda(valorVenda)) {
                break;
            } else {
                System.out.println("O valor de venda deve ser maior que zero.");
            }
        } while (true);
        produto.setValorVenda(valorVenda);

        double valorProduto;

        do {
            System.out.print("Informe o valor do produto: ");
            valorProduto = sc.nextDouble();
            sc.nextLine();
            if (validacaoProduto.validarValorProduto(valorProduto)) {
                break;
            } else {
                System.out.println("O valor do produto deve ser maior que zero.");
            }
        } while (true);
        produto.setValorProduto(valorProduto);

        listaProdutos.add(produto);

        System.out.println("-------Produto cadastrado com sucesso!-------");
        System.out.printf("ID: %d | Nome do produto: %s | Categoria do produto: %s | Valor de aquisição: R$%.2f | Valor de venda: R$%.2f%n",
                produto.getIdProduto(), produto.getNome(), produto.getCategoria(), produto.getValorProduto(), produto.getValorVenda());
        System.out.println("--------------------------------------------");
    }

    @Override
    public void listar() {
        System.out.println("\nLista de produtos Cadastrados: ");
        for (Produto produto : listaProdutos) {
            System.out.printf("ID: %d | Nome do produto: %s | Categoria do produto: %s | Valor de aquisição: R$%.2f | Valor de venda: R$%.2f%n",
                    produto.getIdProduto(), produto.getNome(), produto.getCategoria(), produto.getValorProduto(), produto.getValorVenda());
        }
        System.out.println("---------------------------------");
    }

    @Override
    public void atualizar(Produto produto) {
        System.out.println("Digite o ID do produto que deseja atualizar: ");
        int idProduto = sc.nextInt();
        sc.nextLine();

        for (Produto produtos : listaProdutos) {
            if (produtos.getIdProduto() == idProduto) {

                boolean loop = true;

                while (loop) {
                    System.out.println("Qual informação você deseja atualizar?");
                    System.out.println("1 - Nome do produto \n" +
                            "2 - Categoria do produto\n" +
                            "3 - Valor de venda do produto \n" +
                            "4 - Valor de compra de aquisição do produto\n" +
                            "5 - Sair");

                    System.out.print("Digite a opção desejada: ");
                    int opcao = sc.nextInt();
                    sc.nextLine();

                    switch (opcao) {
                        case 1:

                            String novoNome;
                            do {
                                System.out.print("Digite o novo nome do produto: ");
                                novoNome = sc.nextLine();
                                if (validacaoProduto.validarNome(novoNome)) {
                                    break;
                                } else {
                                    System.out.println("O nome do produto não pode ser vazio ou nulo.");
                                }
                            } while (true);
                            produtos.setNome(novoNome);
                            break;

                        case 2:

                            String novaCategoria;
                            do {
                                System.out.print("Digite a nova categoria do produto: ");
                                novaCategoria = sc.nextLine();
                                if (validacaoProduto.validarCategoria(novaCategoria)) {
                                    break;
                                } else {
                                    System.out.println("A categoria do produto não pode ser vazia ou nula.");
                                }
                            } while (true);
                            produtos.setCategoria(novaCategoria);
                            break;

                        case 3:

                            double novoValorVenda;
                            do {
                                System.out.print("Informe o novo valor de venda do produto: ");
                                novoValorVenda = sc.nextDouble();
                                sc.nextLine();
                                if (validacaoProduto.validarValorVenda(novoValorVenda)) {
                                    break;
                                } else {
                                    System.out.println("O valor de venda deve ser maior que zero.");
                                }
                            } while (true);
                            produtos.setValorVenda(novoValorVenda);
                            break;

                        case 4:
                            double novoValorProduto;
                            do {
                                System.out.print("Informe o novo valor de aquisição do produto: ");
                                novoValorProduto = sc.nextDouble();
                                sc.nextLine();
                                if (validacaoProduto.validarValorProduto(novoValorProduto)) {
                                    break;
                                } else {
                                    System.out.println("O valor do produto deve ser maior que zero.");
                                }
                            } while (true);
                            produtos.setValorProduto(novoValorProduto);
                            break;

                        case 5:
                            System.out.println("Saindo...");
                            loop = false;
                            break;

                        default:
                            System.out.println("Digite uma opção válida.");
                    }
                }


                System.out.println("-------Produto atualizado com sucesso!-------");
                System.out.printf("ID: %d | Nome do produto: %s | Categoria do produto: %s | Valor de aquisição: R$%.2f | Valor de venda: R$%.2f%n",
                        produtos.getIdProduto(), produtos.getNome(), produtos.getCategoria(), produtos.getValorProduto(), produtos.getValorVenda());

                return;
            }
        }

        System.out.println("Produto não encontrado para atualizar.");
    }


    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }
}