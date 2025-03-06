package produto;

public class ProdutosEletrodomesticos extends Produto {

    private String voltagem;
    private int mesesGarantia;
    private String dimensoes;

    public ProdutosEletrodomesticos(int idProduto, String nome, String categoria, double valorVenda, double valorProduto, String cor, String voltagem, int mesesGarantia, String dimensoes) {
        super(idProduto, nome, categoria, valorVenda, valorProduto, cor);
        this.setCategoria("Eletrodoméstico");
        this.voltagem = voltagem;
        this.mesesGarantia = mesesGarantia;
        this.dimensoes = dimensoes;
    }

    public ProdutosEletrodomesticos(){
    }

    public String getVoltagem() {
        return voltagem;
    }

    public void setVoltagem(String voltagem) {
        this.voltagem = voltagem;
    }

    public int getMesesGarantia() {
        return mesesGarantia;
    }

    public void setMesesGarantia(int mesesGarantia) {
        this.mesesGarantia = mesesGarantia;
    }

    public String getDimensoes() {
        return dimensoes;
    }

    public void setDimensoes(String dimensoes) {
        this.dimensoes = dimensoes;
    }
}
