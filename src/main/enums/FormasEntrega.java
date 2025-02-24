package enums;

public enum FormasEntrega {

    PAC(10),
    SEDEX(20),
    TRANSPORTADORA(25);

    private double valor;

    FormasEntrega(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}
