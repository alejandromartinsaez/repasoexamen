package banco.dao;

import banco.components.Cliente;

import java.util.List;

public interface ClienteDAO {
    boolean guardar(Cliente cliente);
    Cliente buscarPorID(int id);
    Cliente buscarPorEmail(String email);
    List<Cliente> listarTodos();
    boolean actualizar(Cliente cliente);
    boolean eliminar(int id);
}
