package cliente;

public class Cliente {
    private int idCliente;
    private String nome;
    private String documento;
    private String email;
    private String telefone;
    private String cep;

    public Cliente(int idCliente, String nome, String documento, String email, String telefone, String cep) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Cliente() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setCpf(String cpf) {
    }

    @Override
    public String toString() {
        return
                "ID: " + idCliente + "\n" +
                "Nome: " + nome + "\n" +
                "CPF: " + documento + "\n" +
                "Email: " + email + "\n" +
                "Telefone: " + telefone + "\n" +
                "CEP: " + cep + "\n";
    }
}

