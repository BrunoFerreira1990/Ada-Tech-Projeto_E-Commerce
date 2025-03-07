package produto;
import java.text.DecimalFormat;
import java.text.ParseException;
import interfaces.ValidacaoProduto;

public class ValidacaoDadosProduto implements ValidacaoProduto {


    private static double converterParaDouble(String valorStr) {

        valorStr = valorStr.replace(",", ".");

        try {

            DecimalFormat df = new DecimalFormat();
            df.setParseBigDecimal(true);
            return df.parse(valorStr).doubleValue();
        } catch (ParseException e) {

            return -1;
        }
    }

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
        // Converte o valor para string e valida
        String valorVendaStr = String.valueOf(valorVenda);

        double valorConvertido = converterParaDouble(valorVendaStr);
        return valorConvertido != -1 && valorConvertido > 0;
    }

    @Override
    public boolean validarValorProduto(double valorProduto) {

        String valorProdutoStr = String.valueOf(valorProduto);

        double valorConvertido = converterParaDouble(valorProdutoStr);
        return valorConvertido != -1 && valorConvertido > 0;
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
        return dimensoes != null && dimensoes.matches("\\d+(\\.\\d+)?X\\d+(\\.\\d+)?X\\d+(\\.\\d+)?");
    }

    @Override
    public boolean validarCor(String cor) {
        return cor != null && !cor.trim().isEmpty();
    }
}





