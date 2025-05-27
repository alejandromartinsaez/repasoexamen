package banco.dao;

import banco.components.Cliente;

public interface ClienteDAO {
    void guardar(Cliente cliente);
    void buscarPorID(int id);
    void buscarPorEmail(String email);
    void actualizar(Cliente cliente);
    void eliminar(int id);
}
