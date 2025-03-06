package interfaces;

public interface ValidacaoProduto {
    boolean validarNome(String nome);
    boolean validarCategoria(String categoria);
    boolean validarValorVenda(double valorVenda);
    boolean validarValorProduto(double valorProduto);
    boolean validarVoltagem(String voltagem);
    boolean validarConsumoEnergia(double consumoEnergia);
    boolean validarMesesGarantia(int mesesGarantia);
    boolean validarMaterial(String material);
    boolean validarDimensoes(String dimensoes);
    boolean validarCor(String cor);

}


