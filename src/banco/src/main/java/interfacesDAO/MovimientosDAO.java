package interfacesDAO;

import classes.Movimiento;

import java.util.List;

public interface MovimientosDAO {
    //Guardar en la base de datos.
    void guardar(Movimiento movimiento);

    //Buscar por ID dentro de la BBDD.
    Movimiento buscarPorId(int id);

    //Actualizar un valor por el ID de un cliente.
    void actualizar(Movimiento movimiento);

    //Eliminar un registro si coincide con un Cliente en específico.
    void eliminar(Movimiento movimiento);

    //Listar todos los registros de la BBDD.
    List<Movimiento> listarTodos();
}
