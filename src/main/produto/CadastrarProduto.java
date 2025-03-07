package produto;

import interfaces.Repositorio;
import interfaces.ValidacaoProduto;


import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastrarProduto implements Repositorio<Produto> {

    private ValidacaoProduto validacaoProduto;
    private Scanner sc = new Scanner(System.in);
    public List<Produto> listaProdutos = new ArrayList<>();
    private int id = 0;

    public CadastrarProduto(ValidacaoProduto validacaoProduto) {
        this.validacaoProduto = validacaoProduto;
    }


    private double lerValor() {
        String valorStr = sc.nextLine();
        valorStr = valorStr.replace(",", ".");
        try {
            DecimalFormat df = new DecimalFormat("#.##");
            df.setParseBigDecimal(false);
            return df.parse(valorStr).doubleValue();
        } catch (ParseException e) {
            System.out.println("Valor inválido. Tente novamente.");
            return -1;
        }
    }

    @Override
    public void cadastrar(Produto produto) {
        System.out.println("Selecione o tipo de produto:");
        System.out.println("1 - Eletrodoméstico");
        System.out.println("2 - Mobiliário");
        System.out.print("Digite a opção desejada: ");
        int tipoProduto = sc.nextInt();
        sc.nextLine();

        String categoria;
        if (tipoProduto == 1) {
            categoria = "Eletrodoméstico";
            produto = new ProdutosEletrodomesticos();
        } else if (tipoProduto == 2) {
            categoria = "Mobiliário";
            produto = new ProdutosMobiliarios();
        } else {
            System.out.println("Opção inválida. Produto não cadastrado.");
            return;
        }

        produto.setCategoria(categoria);
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

        double valorVenda;
        do {
            System.out.print("Informe o valor de venda do produto: ");
            valorVenda = lerValor();
            if (valorVenda != -1 && validacaoProduto.validarValorVenda(valorVenda)) {
                break;
            } else {
                System.out.println("O valor de venda deve ser maior que zero.");
            }
        } while (true);
        produto.setValorVenda(valorVenda);

        double valorProduto;
        do {
            System.out.print("Informe o valor do produto: ");
            valorProduto = lerValor();
            if (valorProduto != -1 && validacaoProduto.validarValorProduto(valorProduto)) {
                break;
            } else {
                System.out.println("O valor do produto deve ser maior que zero.");
            }
        } while (true);
        produto.setValorProduto(valorProduto);

        String cor;
        do {
            System.out.print("Informe a cor do produto: ");
            cor = sc.nextLine();
            if (validacaoProduto.validarCor(cor)) {
                break;
            } else {
                System.out.println("A cor do produto não pode ser vazia ou nula.");
            }
        } while (true);
        produto.setCor(cor);

        if (produto instanceof ProdutosEletrodomesticos) {
            produto = cadastrarEletrodomestico((ProdutosEletrodomesticos) produto);
        } else if (produto instanceof ProdutosMobiliarios) {
            produto = cadastrarMobiliario((ProdutosMobiliarios) produto);
        }

        listaProdutos.add(produto);

        System.out.println("-------Produto cadastrado com sucesso!-------");
        System.out.printf("ID: %d | Nome do produto: %s | Categoria do produto: %s | Valor de aquisição: R$%.2f | Valor de venda: R$%.2f | Cor: %s%n",
                produto.getIdProduto(), produto.getNome(), produto.getCategoria(), produto.getValorProduto(), produto.getValorVenda(), produto.getCor());
        System.out.println("--------------------------------------------");
    }

    private Produto cadastrarEletrodomestico(ProdutosEletrodomesticos produto) {
        String voltagem;
        do {
            System.out.print("Digite a voltagem do eletrodoméstico: ");
            voltagem = sc.nextLine();
            if (validacaoProduto.validarVoltagem(voltagem)) {
                break;
            } else {
                System.out.println("A voltagem deve ser 110V ou 220V.");
            }
        } while (true);
        produto.setVoltagem(voltagem);

        int mesesGarantia;
        do {
            System.out.print("Digite os meses de garantia: ");
            mesesGarantia = sc.nextInt();
            sc.nextLine();
            if (validacaoProduto.validarMesesGarantia(mesesGarantia)) {
                break;
            } else {
                System.out.println("A garantia deve ser maior que zero.");
            }
        } while (true);
        produto.setMesesGarantia(mesesGarantia);

        String dimensoes;
        do {
            System.out.print("Digite as dimensões (Altura x Largura x Profundidade): ");
            dimensoes = sc.nextLine();
            if (validacaoProduto.validarDimensoes(dimensoes)) {
                break;
            } else {
                System.out.println("As dimensões devem estar no formato correto.");
            }
        } while (true);
        produto.setDimensoes(dimensoes);

        return produto;
    }

    private Produto cadastrarMobiliario(ProdutosMobiliarios produto) {
        String material;
        do {
            System.out.print("Digite o material do mobiliário: ");
            material = sc.nextLine();
            if (validacaoProduto.validarMaterial(material)) {
                break;
            } else {
                System.out.println("O material não pode ser vazio ou nulo.");
            }
        } while (true);
        produto.setMaterial(material);

        boolean requerMontagem;
        System.out.print("O mobiliário requer montagem? (S/N): ");
        String resposta = sc.nextLine();
        requerMontagem = resposta.equalsIgnoreCase("S");
        produto.setRequerMontagem(requerMontagem);

        String dimensoes;
        do {
            System.out.print("Digite as dimensões (Altura x Largura x Profundidade): ");
            dimensoes = sc.nextLine();
            if (validacaoProduto.validarDimensoes(dimensoes)) {
                break;
            } else {
                System.out.println("As dimensões devem estar no formato correto.");
            }
        } while (true);
        produto.setDimensoes(dimensoes);

        return produto;
    }

    @Override
    public void listar() {
        System.out.println("\nLista de produtos Cadastrados: ");
        for (Produto produto : listaProdutos) {
            System.out.printf("ID: %d | Nome do produto: %s | Categoria do produto: %s | Valor de aquisição: R$%.2f | Valor de venda: R$%.2f | Cor: %s%n",
                    produto.getIdProduto(), produto.getNome(), produto.getCategoria(), produto.getValorProduto(), produto.getValorVenda(), produto.getCor());
        }
        System.out.println("---------------------------------");
    }

    @Override
    public void atualizar(Produto produto) {
        System.out.print("Digite o ID do produto que deseja atualizar: ");
        int idProduto = sc.nextInt();
        sc.nextLine();

        Produto produtoParaAtualizar = null;

        for (Produto p : listaProdutos) {
            if (p.getIdProduto() == idProduto) {
                produtoParaAtualizar = p;
                break;
            }
        }

        if (produtoParaAtualizar == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        boolean loop = true;

        while (loop) {
            System.out.println("\nQual informação você deseja atualizar?");
            System.out.println("1 - Nome do produto");
            System.out.println("2 - Valor de venda do produto");
            System.out.println("3 - Valor de aquisição do produto");
            System.out.println("4 - Cor do produto");

            if (produtoParaAtualizar instanceof ProdutosEletrodomesticos) {
                System.out.println("5 - Voltagem");
                System.out.println("6 - Meses de garantia");
                System.out.println("7 - Dimensões");
            }

            if (produtoParaAtualizar instanceof ProdutosMobiliarios) {
                System.out.println("5 - Material");
                System.out.println("6 - Requer montagem");
                System.out.println("7 - Dimensões");
            }

            System.out.println("8 - Sair");
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
                    produtoParaAtualizar.setNome(novoNome);
                    break;

                case 2:
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
                    produtoParaAtualizar.setValorVenda(novoValorVenda);
                    break;

                case 3:
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
                    produtoParaAtualizar.setValorProduto(novoValorProduto);
                    break;

                case 4:
                    String novaCor;
                    do {
                        System.out.print("Informe a nova cor do produto: ");
                        novaCor = sc.nextLine();
                        if (validacaoProduto.validarCor(novaCor)) {
                            break;
                        } else {
                            System.out.println("A cor do produto não pode ser vazia ou nula.");
                        }
                    } while (true);
                    produtoParaAtualizar.setCor(novaCor);
                    break;

                case 5:
                    if (produtoParaAtualizar instanceof ProdutosEletrodomesticos) {
                        String novaVoltagem;
                        do {
                            System.out.print("Digite a nova voltagem do eletrodoméstico: ");
                            novaVoltagem = sc.nextLine();
                            if (validacaoProduto.validarVoltagem(novaVoltagem)) {
                                break;
                            } else {
                                System.out.println("A voltagem deve ser 110V ou 220V.");
                            }
                        } while (true);
                        ((ProdutosEletrodomesticos) produtoParaAtualizar).setVoltagem(novaVoltagem);
                    } else if (produtoParaAtualizar instanceof ProdutosMobiliarios) {
                        String novoMaterial;
                        do {
                            System.out.print("Digite o novo material do mobiliário: ");
                            novoMaterial = sc.nextLine();
                            if (validacaoProduto.validarMaterial(novoMaterial)) {
                                break;
                            } else {
                                System.out.println("O material não pode ser vazio ou nulo.");
                            }
                        } while (true);
                        ((ProdutosMobiliarios) produtoParaAtualizar).setMaterial(novoMaterial);
                    }
                    break;

                case 6:
                    if (produtoParaAtualizar instanceof ProdutosEletrodomesticos) {
                        int novosMesesGarantia;
                        do {
                            System.out.print("Digite os novos meses de garantia: ");
                            novosMesesGarantia = sc.nextInt();
                            sc.nextLine();
                            if (validacaoProduto.validarMesesGarantia(novosMesesGarantia)) {
                                break;
                            } else {
                                System.out.println("A garantia deve ser maior que zero.");
                            }
                        } while (true);
                        ((ProdutosEletrodomesticos) produtoParaAtualizar).setMesesGarantia(novosMesesGarantia);
                    } else if (produtoParaAtualizar instanceof ProdutosMobiliarios) {
                        System.out.print("O mobiliário requer montagem? (S/N): ");
                        String resposta = sc.nextLine();
                        boolean novoRequerMontagem = resposta.equalsIgnoreCase("S");
                        ((ProdutosMobiliarios) produtoParaAtualizar).setRequerMontagem(novoRequerMontagem);
                    }
                    break;

                case 7:
                    String novasDimensoes;
                    do {
                        System.out.print("Digite as novas dimensões (Altura x Largura x Profundidade): ");
                        novasDimensoes = sc.nextLine();
                        if (validacaoProduto.validarDimensoes(novasDimensoes)) {
                            break;
                        } else {
                            System.out.println("As dimensões devem estar no formato correto.");
                        }
                    } while (true);
                    if (produtoParaAtualizar instanceof ProdutosEletrodomesticos) {
                        ((ProdutosEletrodomesticos) produtoParaAtualizar).setDimensoes(novasDimensoes);
                    } else if (produtoParaAtualizar instanceof ProdutosMobiliarios) {
                        ((ProdutosMobiliarios) produtoParaAtualizar).setDimensoes(novasDimensoes);
                    }
                    break;

                case 8:
                    System.out.println("Saindo...");
                    loop = false;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println("-------Produto atualizado com sucesso!-------");
            System.out.printf("ID: %d | Nome do produto: %s | Categoria do produto: %s | Valor de aquisição: R$%.2f | Valor de venda: R$%.2f | Cor: %s%n",
                    produtoParaAtualizar.getIdProduto(), produtoParaAtualizar.getNome(), produtoParaAtualizar.getCategoria(),
                    produtoParaAtualizar.getValorProduto(), produtoParaAtualizar.getValorVenda(), produtoParaAtualizar.getCor());
        }
    }

        public List<Produto> getListaProdutos() {
        return listaProdutos;
    }
}
