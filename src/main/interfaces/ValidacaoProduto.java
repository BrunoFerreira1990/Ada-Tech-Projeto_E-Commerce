package interfaces;

public interface ValidacaoProduto {
    boolean validarNome(String nome);
    boolean validarCategoria(String categoria);
    boolean validarValorVenda(double valorVenda);
    boolean validarValorProduto(double valorProduto);
}


