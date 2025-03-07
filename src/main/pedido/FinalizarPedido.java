package pedido;

import enums.FormasEntrega;
import enums.StatusPedido;
import produto.Produto;
import Notificacao.Notificacao;

import java.util.Map;

public class FinalizarPedido {
    private static double valorPedido;

    public FinalizarPedido(double valorPedido) {
        this.valorPedido = valorPedido;
    }

    public FinalizarPedido() {

    }

    public static double getValorPedido() {
        return valorPedido;
    }

    public void finalizar(Pedido pedido, FormasEntrega formaEntrega) {
        if (pedido.getStatusPedido() != StatusPedido.ABERTO) {
            System.out.println("O status do pedido não está aberto. Não é possível finalizar.");
            return;
        }

        double valorProdutos = calcularValorProdutos(pedido);
        double valorFrete = formaEntrega.getValor();
        double valorTotal = valorProdutos + valorFrete;
        valorPedido = valorTotal;

        System.out.println("Resumo do Pedido:");
        System.out.println("Valor dos Produtos: R$ " + valorProdutos);
        System.out.println("Valor do Frete (Forma de Entrega: " + formaEntrega + "): R$ " + valorFrete);
        System.out.println("Valor Total do Pedido: R$ " + valorTotal);

        pedido.setStatusPedido(StatusPedido.AGUARDANDO_PAGAMENTO);
        System.out.println("Pedido " + pedido.getIdPedido() + " finalizado e status alterado para: " + pedido.getStatusPedido());

        Notificacao notificacao = new Notificacao(pedido.getCliente(), pedido);
        notificacao.enviarNotificacoes(pedido);

    }

    private double calcularValorProdutos(Pedido pedido) {
        double valorTotal = 0;

        for (Map.Entry<Produto, Integer> entry : pedido.getListaDePedido().entrySet()) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();
            valorTotal += produto.getValorVenda() * quantidade;
        }

        return valorTotal;
    }

}

