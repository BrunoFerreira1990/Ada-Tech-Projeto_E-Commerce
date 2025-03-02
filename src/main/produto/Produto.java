package produto;

public class Produto {

    private int idProduto;
    private String nome;
    private String categoria;
    private double valorVenda;
    private double valorProduto;

    public Produto(int idProduto, String nome, String categoria, double valorVenda, double valorProduto) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.categoria = categoria;
        this.valorVenda = valorVenda;
        this.valorProduto = valorProduto;
    }

    public Produto() {
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }
}

