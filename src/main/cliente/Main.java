package cliente;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CadastrarClientes cadastrarClientes = new CadastrarClientes();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("------ MENU ------");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Atualizar Cliente");
            System.out.println("3. Listar Clientes");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
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
                    System.out.println("Saindo...");
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}



