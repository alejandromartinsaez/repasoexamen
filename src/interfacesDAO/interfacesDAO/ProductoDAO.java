package interfacesDAO.interfacesDAO;

import interfacesDAO.classes.Producto;
import interfacesDAO.exceptions.ListaVaciaException;
import interfacesDAO.exceptions.ProductoNoEncontradoException;

import java.util.List;

public interface ProductoDAO {
    List<Producto> obtenerTodos() throws ListaVaciaException;
    Producto obtenerPorId(int id) throws ProductoNoEncontradoException, ListaVaciaException;
    void guardar(Producto producto);
    void actualizar(Producto producto) throws ProductoNoEncontradoException, ListaVaciaException;
    void eliminar(int id) throws ProductoNoEncontradoException, ListaVaciaException;
}
