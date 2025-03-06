package produto;

public class ProdutosMobiliarios extends Produto{

    private String material;
    private boolean requerMontagem;
    private String dimensoes;

    public ProdutosMobiliarios(int idProduto, String nome, String categoria, double valorVenda, double valorProduto, String cor, String material, boolean requerMontagem, String dimensoes) {
        super(idProduto, nome, categoria, valorVenda, valorProduto, cor);
        this.setCategoria("Mobiliário");
        this.material = material;
        this.requerMontagem = requerMontagem;
        this.dimensoes = dimensoes;
    }

    public ProdutosMobiliarios() {
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean isRequerMontagem() {
        return requerMontagem;
    }

    public void setRequerMontagem(boolean requerMontagem) {
        this.requerMontagem = requerMontagem;
    }

    public String getDimensoes() {
        return dimensoes;
    }

    public void setDimensoes(String dimensoes) {
        this.dimensoes = dimensoes;
    }
}
