package Pagamento;

import cliente.Cliente;
import enums.StatusPedido;
import pedido.FinalizarPedido;
import pedido.Pedido;

import java.util.Scanner;

public class PagamentoCartaoCredito extends Pagamento{

    @Override
    public void pagar(Pedido pedido, FinalizarPedido pedidoFinalizado) {
        Scanner sc = new Scanner(System.in);

        if (pedido.getStatusPedido() == StatusPedido.FINALIZADO) {
            System.out.println("O seu pedido já está pago.");
            return;
        }
        if (pedido.getStatusPedido() != StatusPedido.AGUARDANDO_PAGAMENTO) {
            System.out.println("O status do pedido não está em 'aguardando pagamento', finalize o pedido para poder prosseguir.");
            return;
        }

        System.out.println("Opção escolhida: Crédito");

        int opcao;
        do {

            System.out.println("\nEscolha em quantas parcelas irá parcelar:");
            System.out.printf("1 - À vista: " + pedidoFinalizado.getValorPedido());
            System.out.println();
            System.out.printf("2 - Duas vezes de R$%.2f", pedidoFinalizado.getValorPedido()/2);
            System.out.println();
            System.out.printf("3 - Três vezes de R$%.2f", pedidoFinalizado.getValorPedido()/3);
            System.out.println();
            System.out.printf("4 - Quatro vezes de R$%.2f", pedidoFinalizado.getValorPedido()/4);
            System.out.println();
            System.out.printf("5 - Cinco vezes de R$%.2f", pedidoFinalizado.getValorPedido()/5);
            System.out.println();
            System.out.printf("6 - Seis vezes de R$%.2f", pedidoFinalizado.getValorPedido()/6);
            System.out.println();
            System.out.print("\nEscolha uma das opções: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Método à vista.\n" +
                            "Processando...\n" +
                            "Pagamento efetuado com sucesso!");
                    pedido.setStatusPedido(StatusPedido.PAGO);
                    return;
                case 2:
                    parcelarPedido(opcao, pedido, pedidoFinalizado);
                    return;
                case 3:
                    parcelarPedido(opcao, pedido, pedidoFinalizado);
                    return;
                case 4:
                    parcelarPedido(opcao, pedido, pedidoFinalizado);
                    return;
                case 5:
                    parcelarPedido(opcao, pedido, pedidoFinalizado);
                    return;
                case 6:
                    parcelarPedido(opcao, pedido, pedidoFinalizado);
                    return;
                case 7:
                    System.out.println("Saindo para o Menu Pagamento");
                    return;
                default:
                    System.out.println("Digite uma opão válida.");
            }


        } while(opcao != 7);


        pedido.setStatusPedido(StatusPedido.PAGO);
        System.out.println("O seu pedido foi pago com sucesso!");
    }

    private void  parcelarPedido(int opcao, Pedido pedido, FinalizarPedido pedidoFinalizado) {
        System.out.println("\nProcessando...");
        System.out.println("\nPagamento efetuado com sucesso!");
        System.out.printf("Parcelamento da compra de R$%.2f em %dx de R$%.2f%n",
                pedidoFinalizado.getValorPedido(), opcao, pedidoFinalizado.getValorPedido() / opcao);
        pedido.setStatusPedido(StatusPedido.PAGO);
        System.out.println("");
    }
}
