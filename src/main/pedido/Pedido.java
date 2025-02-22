package pedido;

import java.util.Date;

public class Pedido {

    private int id;
    private Date dataCriacao;
    private StatusPedido statusPedido;

    public Pedido(int id, Date dataCriacao, StatusPedido statusPedido) {
        this.id = id;
        this.dataCriacao = new Date();
        this.statusPedido = StatusPedido.ABERTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }


    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public static void remover() {

    }

    public static void alterarQuantidade() {

    }

    public static void realizarPagamento() {

    }

    public static void entrega() {

    }



}


//class Pedido {
//  - id: int
//  - dataCriacao: Date
//  - status: String
//  - cliente: Cliente
//  - itens: List<ItemPedido>
//  - pagamento: Pagamento
//  + adicionarItem(produto: Produto, quantidade: int, precoVenda: double)
//  + removerItem(produto: Produto)
//  + alterarQuantidade(produto: Produto, quantidade: int)
//  + finalizarPedido()
//  + realizarPagamento()
//  + entregarPedido()
//}