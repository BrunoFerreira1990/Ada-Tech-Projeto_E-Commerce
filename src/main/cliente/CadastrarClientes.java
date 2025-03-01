package cliente;

import interfaces.Repositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastrarClientes implements Repositorio<Cliente> {
    private List<Cliente> clientes = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    @Override
    public void cadastrar(Cliente cliente) {
        cliente.setIdCliente(clientes.size() + 1);  // ID incremental
        solicitarDadosCliente(cliente);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!\n" + cliente);
    }

    @Override
    public void listar() {
        System.out.println("Lista de Clientes:");

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println(cliente);
            System.out.println("---------------");
        }
    }

    @Override
    public void atualizar(Cliente cliente) {
        System.out.println("Digite o ID do cliente a ser atualizado: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        Cliente clienteExistente = buscarPorId(idCliente);
        if (clienteExistente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        System.out.println("Cliente encontrado: " + clienteExistente);

        // Menu para o usuário escolher o campo que deseja atualizar
        System.out.println("\nEscolha o campo que deseja atualizar:");
        System.out.println("1 - Nome");
        System.out.println("2 - CPF");
        System.out.println("3 - Email");
        System.out.println("4 - Telefone");
        System.out.println("5 - CEP");
        System.out.print("Escolha uma opção: ");
        int opcaoAtualizacao = sc.nextInt();
        sc.nextLine();

        switch (opcaoAtualizacao) {
            case 1:
                atualizarNome(clienteExistente);
                break;
            case 2:
                atualizarCPF(clienteExistente);
                break;
            case 3:
                atualizarEmail(clienteExistente);
                break;
            case 4:
                atualizarTelefone(clienteExistente);
                break;
            case 5:
                atualizarCep(clienteExistente);
                break;
            default:
                System.out.println("Opção inválida.");
        }

        System.out.println("Cliente atualizado com sucesso!");
        System.out.println(clienteExistente);  // Exibe o cliente atualizado
    }

    // Métodos para atualizar campos individuais do cliente
    private void atualizarNome(Cliente cliente) {
        String nome;
        do {
            System.out.print("Informe o novo nome do cliente: ");
            nome = sc.nextLine();
            if (nome.isEmpty()) {
                System.out.println("Nome não pode ser vazio.");
            } else if (!nome.matches("[a-zA-Z ]+")) {
                System.out.println("O nome não pode conter números ou caracteres especiais.");
            }
        } while (nome.isEmpty() || !nome.matches("[a-zA-Z ]+"));

        cliente.setNome(nome);
    }

    private void atualizarCPF(Cliente cliente) {
        String cpf;
        do {
            System.out.print("Informe o novo CPF do cliente: ");
            cpf = sc.nextLine();
            if (cpf.isEmpty()) {
                System.out.println("O CPF não pode ser vazio.");
            } else if (cpf.length() != 11) {
                System.out.println("O CPF deve ter 11 dígitos.");
            } else if (!cpf.matches("\\d+")) {
                System.out.println("O CPF deve conter apenas números.");
            }
        } while (cpf.isEmpty() || cpf.length() != 11 || !cpf.matches("\\d+"));

        cliente.setDocumento(FormatarDocumentos.formatarCPF(cpf));
    }

    private void atualizarEmail(Cliente cliente) {
        String email;
        do {
            System.out.print("Informe o novo e-mail do cliente: ");
            email = sc.nextLine();
            if (email.isEmpty()) {
                System.out.println("O e-mail não pode ser vazio.");
            } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                System.out.println("E-mail inválido.");
            }
        } while (email.isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$"));

        cliente.setEmail(email);
    }

    private void atualizarTelefone(Cliente cliente) {
        String telefone;
        do {
            System.out.print("Informe o novo telefone do cliente: ");
            telefone = sc.nextLine();
            if (telefone.isEmpty()) {
                System.out.println("O telefone não pode ser vazio.");
            } else if (!telefone.matches("\\d{10,11}")) {
                System.out.println("Telefone inválido. O telefone deve conter 10 ou 11 dígitos.");
            }
        } while (telefone.isEmpty() || !telefone.matches("\\d{10,11}"));

        cliente.setTelefone(telefone);
    }

    private void atualizarCep(Cliente cliente) {
        String cep;
        do {
            System.out.print("Informe o novo CEP do cliente: ");
            cep = sc.nextLine();
            if (cep.isEmpty()) {
                System.out.println("O CEP não pode ser vazio.");
            } else if (!cep.matches("\\d{8}")) {
                System.out.println("CEP inválido. O CEP deve conter exatamente 8 dígitos.");
            }
        } while (cep.isEmpty() || !cep.matches("\\d{8}"));

        cliente.setCep(cep);
    }

    private void solicitarDadosCliente(Cliente cliente) {
        String nome;
        do {
            System.out.println("Digite o nome do cliente: ");
            nome = sc.nextLine();
            if (!ValidacaoDados.validarNome(nome)) {
                System.out.println("O nome não pode conter números ou ser vazio. Tente novamente.");
            }
        } while (!ValidacaoDados.validarNome(nome));
        cliente.setNome(nome);

        String cpf;
        do {
            System.out.println("Digite o CPF do cliente (apenas números): ");
            cpf = sc.nextLine();
            if (!ValidacaoDados.validarCPF(cpf)) {
                System.out.println("O CPF deve ter 11 dígitos e não pode ser vazio. Tente novamente.");
            }
        } while (!ValidacaoDados.validarCPF(cpf));
        cliente.setDocumento(FormatarDocumentos.formatarCPF(cpf));

        String email;
        do {
            System.out.println("Digite o email do cliente: ");
            email = sc.nextLine();
            if (!ValidacaoDados.validarEmail(email)) {
                System.out.println("O email não pode ser vazio e deve ser válido. Tente novamente.");
            }
        } while (!ValidacaoDados.validarEmail(email));
        cliente.setEmail(email);

        String telefone;
        do {
            System.out.println("Digite o telefone do cliente: ");
            telefone = sc.nextLine();
            if (!ValidacaoDados.validarTelefone(telefone)) {
                System.out.println("O telefone não pode ser vazio. Tente novamente.");
            }
        } while (!ValidacaoDados.validarTelefone(telefone));
        cliente.setTelefone(telefone);

        String cep;
        do {
            System.out.println("Digite o CEP do cliente: ");
            cep = sc.nextLine();
            if (!ValidacaoDados.validarCep(cep)) {
                System.out.println("O CEP não pode ser vazio. Tente novamente.");
            }
        } while (!ValidacaoDados.validarCep(cep));
        cliente.setCep(cep);
    }


    public Cliente buscarPorId(int idCliente) {
        return clientes.stream()
                .filter(cliente -> cliente.getIdCliente() == idCliente)
                .findFirst()
                .orElse(null);
    }
}
