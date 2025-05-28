package interfacesDAO.implementacion;

import interfacesDAO.Main;
import interfacesDAO.classes.Producto;
import interfacesDAO.exceptions.ListaVaciaException;
import interfacesDAO.exceptions.ProductoNoEncontradoException;
import interfacesDAO.interfacesDAO.ProductoDAO;

import java.util.*;

public class ProductoDAOimpl implements ProductoDAO{
    Map<Integer, Producto> idProductoMap = new HashMap<>();

    public ProductoDAOimpl(){};

    @Override
    public void guardar(Producto producto) {
        Producto producto1 = new Producto(producto.getNombre(), producto.getPrecio());
        idProductoMap.put(producto.getId(), producto1);
        System.out.println("Guardado con exito.");
    }

    @Override
    public Producto obtenerPorId(int id) throws ProductoNoEncontradoException, ListaVaciaException {
        if (!idProductoMap.containsKey(id)){
            throw new ProductoNoEncontradoException("No existe este producto.");
        }
        if (idProductoMap.isEmpty()){
            throw new ListaVaciaException();
        }
        return idProductoMap.get(id);
    }

    @Override
    public void eliminar(int id) throws ProductoNoEncontradoException, ListaVaciaException{
        if (!idProductoMap.containsKey(id)){
            throw new ProductoNoEncontradoException("No existe este producto.");
        }
        if (idProductoMap.isEmpty()){
            throw new ListaVaciaException();
        }
        idProductoMap.remove(id);
        System.out.println("Eliminado con exito.");
    }

    @Override
    public void actualizar(Producto producto) throws ProductoNoEncontradoException, ListaVaciaException{
        if (!idProductoMap.containsKey(producto.getId())){
            throw new ProductoNoEncontradoException("No existe este producto.");
        }
        if (idProductoMap.isEmpty()){
            throw new ListaVaciaException();
        }
        idProductoMap.get(producto.getId()).setPrecio(producto.getPrecio());
        System.out.println("Producto actualizado.");
    }

    @Override
    public List<Producto> obtenerTodos() throws ListaVaciaException{
        if (idProductoMap.isEmpty()){
            throw new ListaVaciaException();
        }
        List<Producto> productoList = new ArrayList<>();
        for (Map.Entry<Integer, Producto> m : idProductoMap.entrySet()){
            Producto producto = m.getValue();
            productoList.add(new Producto(m.getKey(),producto.getNombre(),producto.getPrecio()));
        }
        return productoList;
    }
}
