package Logistica;

import enums.FormasEntrega;
import enums.StatusPedido;
import pedido.Pedido;
import pedido.FinalizarPedido;

public class Entrega {

    public static void entrega(Pedido pedido, FormasEntrega formaEntrega) {

        if (pedido.getStatusPedido() != StatusPedido.ABERTO) {
            System.out.println("O status do pedido não está aberto. Não é possível definir a entrega.");
            return;
        }


        double valorFrete = formaEntrega.getValor();

        System.out.println("Pedido ID: " + pedido.getIdPedido());
        System.out.println("Forma de Entrega: " + formaEntrega);
        System.out.println("Valor do Frete: R$ " + valorFrete);


        FinalizarPedido finalizarPedido = new FinalizarPedido();
        finalizarPedido.finalizar(pedido, formaEntrega);
    }
}
