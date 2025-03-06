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

    @Override
    public boolean validarVoltagem(String voltagem) {
        return voltagem != null && (voltagem.equals("110V") || voltagem.equals("220V"));
    }

    @Override
    public boolean validarConsumoEnergia(double consumoEnergia) {
        return consumoEnergia > 0;
    }

    @Override
    public boolean validarMesesGarantia(int mesesGarantia) {
        return mesesGarantia > 0;
    }

    @Override
    public boolean validarMaterial(String material) {
        return material != null && !material.trim().isEmpty();
    }

    @Override
    public boolean validarDimensoes(String dimensoes) {
        return dimensoes != null && dimensoes.matches("\\d+(\\.\\d+)?x\\d+(\\.\\d+)?x\\d+(\\.\\d+)?");
    }

    @Override
    public boolean validarCor(String cor) {
        return cor != null && !cor.trim().isEmpty();
    }
}



