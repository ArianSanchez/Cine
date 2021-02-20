package Dao;

import java.util.List;

public interface IGenerica<T> {

    void registrar(T modelo) throws Exception;

    List<T> listar() throws Exception;
}
