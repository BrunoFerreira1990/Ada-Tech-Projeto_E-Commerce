package produto;

import cliente.Cliente;
import interfaces.Repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastrarProduto implements Repositorio<Produto> {

    Scanner sc = new Scanner(System.in);
    private List<Produto> listaProdutos = new ArrayList<>();
    private int id = 0;


    @Override
    public void cadastrar(Produto produto) {


        produto.setIdProduto(++id);

        System.out.print("Digite o nome do produto: ");
        produto.setNome(sc.nextLine());

        System.out.print("Digite a categoria do produto: ");
        produto.setCategoria(sc.nextLine());

        System.out.print("Informe o valor de venda do produto: ");
        produto.setValorVenda(sc.nextDouble());
        sc.nextLine();

        System.out.print("Informe o valor do produto: ");
        produto.setValorProduto(sc.nextDouble());
        sc.nextLine();

        listaProdutos.add((produto));

        System.out.println("-------Produto cadastrado com sucesso!-------");
        System.out.println("ID: " + produto.getIdProduto());
        System.out.println("Nome do produto: " + produto.getNome());
        System.out.println("Valor de venda do produto: " + produto.getValorVenda());
        System.out.println("Valor de aquisição do produto: " + produto.getValorProduto());
        System.out.println("--------------------------------------------");

    }

    @Override
    public void listar() {

        for (Produto produto : listaProdutos) {
            System.out.println("Identificador: " + produto.getIdProduto() +
                    " | Nome do produto: " + produto.getNome() +
                    " | Categoria do produto: " + produto.getCategoria() +
                    " | Valor de venda: " + produto.getValorVenda() +
                    " | Valor do produto: " + produto.getValorProduto());
        }

    }

    @Override
    public void atualizar(Produto produto) {

        System.out.println("Digite o ID do produto que deseja atualizar: ");
        int idProduto = sc.nextInt();
        sc.nextLine(); // Consumir a quebra de linha pendente

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
                            System.out.println("Digite o novo nome do produto: ");
                            produtos.setNome(sc.nextLine());
                            break;
                        case 2:
                            System.out.print("Digite a categoria do produto: ");
                            produtos.setCategoria(sc.nextLine());
                            break;
                        case 3:
                            System.out.print("Informe o valor de venda do produto: ");
                            produtos.setValorVenda(sc.nextDouble());
                            sc.nextLine();
                            break;
                        case 4:
                            System.out.print("Informe o valor de aquisição do produto: ");
                            produtos.setValorProduto(sc.nextDouble());
                            sc.nextLine();
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
                System.out.println("ID: " + produtos.getIdProduto());
                System.out.println("Nome: " + produtos.getNome());
                System.out.println("Valor de venda: " + produtos.getValorVenda());
                System.out.println("Valor do produto: " + produtos.getValorProduto());
                return;
            }
        }

        System.out.println("Produto não encontrado para atualizar.");
    }


}
