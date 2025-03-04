package Pagamento;

import enums.StatusPedido;
import pedido.FinalizarPedido;
import pedido.Pedido;

import java.util.Scanner;

public class PagamentoPix extends Pagamento{

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

        System.out.println("Opção escolhida: PIX");

        pedido.setStatusPedido(StatusPedido.PAGO);
        System.out.println("O seu pedido foi pago com sucesso!");
    }

}
