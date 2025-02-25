package cliente;
import interfaces.Repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastrarClientes implements Repositorio<Cliente> {

    private List<Cliente> clientes = new ArrayList<>();
    private int id = 1;
    private Scanner sc = new Scanner(System.in);

    @Override
    public void cadastrar(Cliente cliente) {
        cliente.setIdCliente(id++);

        String nome;
        do {
            System.out.println("Digite o nome do cliente: ");
            nome = sc.nextLine();
            if (nome.trim().isEmpty()) {
                System.out.println("O nome não pode ser vazio. Tente novamente.");
            }
        } while (nome.trim().isEmpty());
        cliente.setNome(nome);


        String cpf;
        do {
            System.out.println("Digite o CPF do cliente: ");
            cpf = sc.nextLine();
            if (cpf.trim().isEmpty()) {
                System.out.println("O CPF não pode ser vazio. Tente novamente.");
            }
        } while (cpf.trim().isEmpty());
        cliente.setDocumento(cpf);


        String email;
        do {
            System.out.println("Digite o Email do cliente: ");
            email = sc.nextLine();
            if (email.trim().isEmpty()) {
                System.out.println("O Email não pode ser vazio. Tente novamente.");
            }
        } while (email.trim().isEmpty());
        cliente.setEmail(email);

        // Verificação do Telefone
        String telefone;
        do {
            System.out.println("Digite o Telefone do cliente: ");
            telefone = sc.nextLine();
            if (telefone.trim().isEmpty()) {
                System.out.println("O Telefone não pode ser vazio. Tente novamente.");
            }
        } while (telefone.trim().isEmpty());
        cliente.setTelefone(telefone);


        String cep;
        do {
            System.out.println("Digite o CEP do cliente: ");
            cep = sc.nextLine();
            if (cep.trim().isEmpty()) {
                System.out.println("O CEP não pode ser vazio. Tente novamente.");
            }
        } while (cep.trim().isEmpty());
        cliente.setCep(cep);

        clientes.add(cliente);

        System.out.println("-------Cliente cadastrado com sucesso!-------");
        System.out.println("ID: " + cliente.getIdCliente());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getDocumento());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("CEP: " + cliente.getCep());
        System.out.println("--------------------------------------------");
    }


    @Override
    public void listar() {

        System.out.println("Lista de Clientes Cadastrados: ");

        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getIdCliente());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Documento: " + cliente.getDocumento());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("CEP: " + cliente.getCep());
            System.out.println("-------------------------------------");
        }
    }

    @Override
    public void atualizar(Cliente clienteAtualizado) {

        int idClienteAtualizar = -1;

        while (idClienteAtualizar < 0) {
            System.out.println("Digite o ID do Cliente que deseja atualizar: ");
            if (sc.hasNextInt()) {
                idClienteAtualizar = sc.nextInt();
                sc.nextLine();
                if (idClienteAtualizar <= 0) {
                    System.out.println("O ID é INVÁLIDO, digite novamente!!!");
                    idClienteAtualizar = -1;
                }
            } else {
                System.out.println("ID INCORRETO! Digite apenas números no ID.");
                sc.nextLine();
            }
        }

        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() == idClienteAtualizar) {
                System.out.println("Cliente encontrado! O que você gostaria de atualizar?");

                boolean continuar = true;
                while (continuar) {
                    System.out.println("1 - Nome");
                    System.out.println("2 - CPF");
                    System.out.println("3 - Email");
                    System.out.println("4 - Telefone");
                    System.out.println("5 - CEP");
                    System.out.println("6 - Finalizar atualização");
                    System.out.print("Digite o número do campo que deseja alterar: ");

                    int opcao = sc.nextInt();
                    sc.nextLine();

                    switch (opcao) {
                        case 1:
                            String nome;
                            do {
                                System.out.println("Digite o novo nome do cliente: ");
                                nome = sc.nextLine();
                                if (nome.trim().isEmpty()) {
                                    System.out.println("O nome não pode ser vazio. Tente novamente.");
                                }
                            } while (nome.trim().isEmpty());
                            cliente.setNome(nome);
                            break;

                        case 2:
                            String cpf;
                            do {
                                System.out.println("Digite o novo CPF do cliente: ");
                                cpf = sc.nextLine();
                                if (cpf.trim().isEmpty()) {
                                    System.out.println("O CPF não pode ser vazio. Tente novamente.");
                                }
                            } while (cpf.trim().isEmpty());
                            cliente.setDocumento(cpf);
                            break;

                        case 3:
                            String email;
                            do {
                                System.out.println("Digite o novo Email do cliente: ");
                                email = sc.nextLine();
                                if (email.trim().isEmpty()) {
                                    System.out.println("O Email não pode ser vazio. Tente novamente.");
                                }
                            } while (email.trim().isEmpty());
                            cliente.setEmail(email);
                            break;

                        case 4:
                            String telefone;
                            do {
                                System.out.println("Digite o novo Telefone do cliente: ");
                                telefone = sc.nextLine();
                                if (telefone.trim().isEmpty()) {
                                    System.out.println("O Telefone não pode ser vazio. Tente novamente.");
                                }
                            } while (telefone.trim().isEmpty());
                            cliente.setTelefone(telefone);
                            break;

                        case 5:
                            String cep;
                            do {
                                System.out.println("Digite o novo CEP do cliente: ");
                                cep = sc.nextLine();
                                if (cep.trim().isEmpty()) {
                                    System.out.println("O CEP não pode ser vazio. Tente novamente.");
                                }
                            } while (cep.trim().isEmpty());
                            cliente.setCep(cep);
                            break;

                        case 6:
                            continuar = false;
                            break;

                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            break;
                    }
                }

                System.out.println("-------Cliente atualizado com sucesso!-------");
                System.out.println("ID: " + cliente.getIdCliente());
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("CPF: " + cliente.getDocumento());
                System.out.println("Email: " + cliente.getEmail());
                System.out.println("Telefone: " + cliente.getTelefone());
                System.out.println("CEP: " + cliente.getCep());
                return;
            }
        }

        System.out.println("Cliente não encontrado para atualizar!!!");
    }

}