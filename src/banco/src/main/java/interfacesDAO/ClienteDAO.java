package interfacesDAO;

import classes.Cliente;

import java.util.List;

public interface ClienteDAO {
    //Guardar en la base de datos.
    void guardar(Cliente cLiente);

    //Buscar por ID dentro de la BBDD.
    Cliente buscarPorId(int id);

    //Actualizar un valor por el ID de un cliente.
    void actualizar(Cliente cliente);

    //Eliminar un registro si coincide con un Cliente en específico.
    void eliminar(Cliente cliente);

    //Listar todos los registros de la BBDD.
    List<Cliente> listarTodos();
}
