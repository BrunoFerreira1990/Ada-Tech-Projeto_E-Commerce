package Pagamento;

import Logistica.EscolherFormaEntrega;
import Notificacao.Notificacao;
import cliente.Cliente;
import enums.FormasEntrega;
import enums.StatusPedido;
import pedido.FinalizarPedido;
import pedido.Pedido;

public class PagamentoFinalizado {

    Cliente cliente;


    public static void finalizarPagamento(Cliente cliente, Pedido pedido) {

        Notificacao notificacao = new Notificacao(cliente, pedido);

        if (pedido.getStatusPedido() == StatusPedido.FINALIZADO) {
            System.out.println("O seu pedido já foi finalizado.");
            return;
        }

        if (pedido.getStatusPedido() != StatusPedido.PAGO) {
            System.out.println("O status do pedido não está em 'pago', realize o pagamento para poder prosseguir.");
            return;
        }
        pedido.setStatusPedido(StatusPedido.FINALIZADO);
        System.out.println("Compra realizada com sucesso!");

        System.out.println(pedido.getCliente().getNome() + " o seu pedido: " + pedido.getIdPedido() +" saiu para Entrega para o Cep: " + pedido.getCliente().getCep() + " enviado via " + EscolherFormaEntrega.formaEntregaEscolhida);

        notificacao.enviarNotificacoes(pedido);

    }

}
