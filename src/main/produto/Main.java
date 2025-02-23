package produto;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CadastrarProduto produto = new CadastrarProduto();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("------ MENU ------");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Atualizar produto");
            System.out.println("3. Listar produto");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
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

                    System.out.println("Saindo...");
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
