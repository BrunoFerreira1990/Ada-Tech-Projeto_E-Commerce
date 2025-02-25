import enums.FormasEntrega;
import enums.StatusPedido;
import pedido.Pedido;
import produto.Produto;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Criando um pedido de exemplo
        //Pedido pedido = new Pedido(1, new Date(), StatusPedido.ABERTO, 2, new ArrayList<>());

        // Mudando o status para um fechado para ver a validação de "não aberto"
        Pedido pedido = new Pedido(1, new Date(), StatusPedido.FINALIZADO, 2, new ArrayList<>()); // Se você quiser testar quando o pedido estiver fechado.

        // Chama o método entrega passando o pedido e a forma de entrega
        Pedido.entrega(pedido, FormasEntrega.SEDEX);

    }
}
