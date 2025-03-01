package cliente;

public class ValidacaoDados {


    public static boolean validarCampoNaoVazio(String campo) {
        return campo != null && !campo.trim().isEmpty();
    }


    public static boolean validarNome(String nome) {
        return nome != null && !nome.trim().isEmpty() && nome.matches("[a-zA-Zá-úÁ-Ú ]+");
    }


    public static boolean validarCPF(String cpf) {
        return cpf != null && !cpf.trim().isEmpty() && cpf.replaceAll("[^0-9]", "").length() == 11;
    }


    public static boolean validarEmail(String email) {
        return email != null && !email.trim().isEmpty() && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }


    public static boolean validarTelefone(String telefone) {
        return telefone != null && !telefone.trim().isEmpty();
    }


    public static boolean validarCep(String cep) {
        return cep != null && !cep.trim().isEmpty();
    }
}
