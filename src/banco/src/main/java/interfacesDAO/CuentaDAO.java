package interfacesDAO;

import classes.Cliente;
import classes.Cuenta;

import java.util.List;

public interface CuentaDAO {
    //Guardar en la base de datos.
    void guardar(Cuenta cuenta);

    //Buscar por ID dentro de la BBDD.
    Cuenta buscarPorId(int id);

    //Actualizar un valor por el ID de un cliente.
    void actualizar(Cuenta cuenta);

    //Eliminar un registro si coincide con un Cliente en específico.
    void eliminar(Cuenta cuenta);

    //Listar todos los registros de la BBDD.
    List<Cuenta> listarTodos();
}
