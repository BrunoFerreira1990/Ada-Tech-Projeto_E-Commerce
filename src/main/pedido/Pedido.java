package pedido;

import produto.Produto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {

    private int id;
    private Date dataCriacao;
    private StatusPedido statusPedido;
    private int quantidade;
    private List <Produto> listaDePedido = new ArrayList<>();


    public Pedido(int id, Date dataCriacao, StatusPedido statusPedido, int quantidade) {
        this.id = id;
        this.dataCriacao = new Date();
        this.statusPedido = StatusPedido.ABERTO;
        this.quantidade = quantidade;
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

    public List<Produto> getListaDePedido() {
        return listaDePedido;
    }

    public void setListaDePedido(List<Produto> listaDePedido) {
        this.listaDePedido = listaDePedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

// metodo dependente da criação do cadastro de produtos
//    public void adicionarItem(Produto produto, int quantidade) {
//        if(this.statusPedido != StatusPedido.ABERTO) {
//            System.out.println("O status do pedido não está aberto. Nâo é possível remover itens.");
//            return;
//        }
//
//        if(!Produto.getProdutoCadastrado().contains(produto)) {
//            System.out.println("Produto não cadastrado.");
//            return;
//        }
//
//        listaDePedido.add(produto);
//        System.out.println("Produto " + produto.getNome() + " adicionado ao pedido.");
//    }

    public void removerProduto(Produto nome) {

        if(this.statusPedido != StatusPedido.ABERTO) {
            System.out.println("O status do pedido não está aberto. Nâo é possível remover itens.");
            return;
        }
        if (listaDePedido.isEmpty()) {
            System.out.println("Não há itens na lista de pedido.");
        } else if (!listaDePedido.contains(nome)) {
            System.out.println("O item informado não consta na lista de pedido.");
        } else {
            listaDePedido.remove(nome);
            System.out.println("O item " + nome + " foi removido da lista de pedido.");
        }

    }

    public void alterarQuantidadeProduto(Produto nome, int quantidade) {

        if(this.statusPedido != StatusPedido.ABERTO) {
            System.out.println("O status do pedido não está aberto. Nâo é alterar a quantidade de itens.");
            return;
        }
        if (listaDePedido.isEmpty()) {
            System.out.println("Não há itens na lista de pedido.");
        } else if (!listaDePedido.contains(nome)) {
            System.out.println("O item informado não consta na lista de pedido.");
        } else {

        }


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