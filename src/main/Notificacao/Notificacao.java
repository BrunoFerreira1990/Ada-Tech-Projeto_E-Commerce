package Notificacao;

import interfaces.Comunicacao;
import cliente.Cliente;
import pedido.Pedido;

public class Notificacao implements Comunicacao {

    private Cliente cliente;
    private Pedido pedido;

    public Notificacao(Cliente cliente, Pedido pedido) {
        this.cliente = cliente;
        this.pedido = pedido;
    }

    private String obterDadosNotificacao() {
        String statusPedido = String.valueOf(pedido.getStatusPedido());
        int idCliente = cliente.getIdCliente();
        int idPedido = pedido.getIdPedido();
        return "O Seu pedido: " + idPedido + ", está com Status: " + statusPedido +
                ", Cadastro: " + idCliente;
    }

    @Override
    public void enviarEmail(String email) {
        String dados = obterDadosNotificacao();
        String emailCliente = cliente.getEmail();
        System.out.println(dados + ", Enviando email para: " + emailCliente);
    }

    @Override
    public void enviarWhatsapp(String whatsapp) {
        String dados = obterDadosNotificacao();
        String telefone = cliente.getTelefone();
        System.out.println(dados + ", Enviando WhatsApp para: " + telefone);
    }

    @Override
    public void enviarSMS(String sms) {
        String dados = obterDadosNotificacao();
        String telefone = cliente.getTelefone();
        System.out.println(dados + ", Enviando SMS para: " + telefone);
    }

    public void enviarNotificacoes(Pedido pedido) {
        Cliente cliente = pedido.getCliente();
        Notificacao notificacao = new Notificacao(cliente, pedido);

        notificacao.enviarEmail(cliente.getEmail());
        notificacao.enviarWhatsapp(cliente.getTelefone());
        notificacao.enviarSMS(cliente.getTelefone());
    }
}
