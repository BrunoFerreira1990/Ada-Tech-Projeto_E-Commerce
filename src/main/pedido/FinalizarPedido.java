package pedido;

import enums.FormasEntrega;
import enums.StatusPedido;
import produto.Produto;
import Notificacao.Notificacao;
import cliente.Cliente;

import java.util.Map;

public class FinalizarPedido {

    public void finalizar(Pedido pedido, FormasEntrega formaEntrega) {
        // Verifica se o pedido está aberto
        if (pedido.getStatusPedido() != StatusPedido.ABERTO) {
            System.out.println("O status do pedido não está aberto. Não é possível finalizar.");
            return;
        }

        double valorProdutos = calcularValorProdutos(pedido);
        double valorFrete = formaEntrega.getValor();
        double valorTotal = valorProdutos + valorFrete;

        System.out.println("Resumo do Pedido:");
        System.out.println("Valor dos Produtos: R$ " + valorProdutos);
        System.out.println("Valor do Frete (Forma de Entrega: " + formaEntrega + "): R$ " + valorFrete);
        System.out.println("Valor Total do Pedido: R$ " + valorTotal);

        pedido.setStatusPedido(StatusPedido.AGUARDANDO_PAGAMENTO);
        System.out.println("Pedido " + pedido.getIdPedido() + " finalizado e status alterado para: " + pedido.getStatusPedido());

        // Enviar notificações após alteração de status
        enviarNotificacoes(pedido);
    }

    private void enviarNotificacoes(Pedido pedido) {
        Cliente cliente = pedido.getCliente();
        Notificacao notificacao = new Notificacao(cliente, pedido);

        // Enviar notificações de email, WhatsApp e SMS
        notificacao.enviarEmail(cliente.getEmail());
        notificacao.enviarWhatsapp(cliente.getTelefone());
        notificacao.enviarSMS(cliente.getTelefone());
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

