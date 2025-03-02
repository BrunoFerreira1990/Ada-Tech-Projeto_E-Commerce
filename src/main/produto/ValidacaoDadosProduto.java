package produto;

import interfaces.ValidacaoProduto;

public class ValidacaoDadosProduto implements ValidacaoProduto {

    @Override
    public boolean validarNome(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }

    @Override
    public boolean validarCategoria(String categoria) {
        return categoria != null && !categoria.trim().isEmpty();
    }

    @Override
    public boolean validarValorVenda(double valorVenda) {
        return valorVenda > 0;
    }

    @Override
    public boolean validarValorProduto(double valorProduto) {
        return valorProduto > 0;
    }
}



