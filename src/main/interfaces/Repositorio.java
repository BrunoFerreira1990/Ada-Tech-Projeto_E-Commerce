package interfaces;

import cliente.Cliente;

public interface Repositorio<T> {
    void cadastrar(T t);
    void listar();
    void atualizar(T t);
}
