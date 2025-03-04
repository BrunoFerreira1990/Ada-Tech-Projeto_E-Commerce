package Pagamento;
import cliente.Cliente;
import pedido.FinalizarPedido;
import pedido.Pedido;

abstract class Pagamento {

    FinalizarPedido pedidoFinalizado = new FinalizarPedido();

    public abstract void pagar(Pedido pedido, FinalizarPedido pedidoFinalizado);


}
