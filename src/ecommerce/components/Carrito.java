package ecommerce.components;

import ecommerce.DAO.MetodoDePago;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    List<Productos> productos = new ArrayList<>();
    int totalCompra;

    public Carrito() {
    }


    public void agregarProducto(Productos producto) {
        if (producto.getStock() <= 0 ) {
            System.out.println("No hay productos en stock");
        } else if (productos.add(producto)) {
            System.out.println("Producto añadido");
        }
    }

    public void eliminarProducto(Productos producto) {
        productos.remove(producto);
    }

    public double calcularCompra() {
        for (Productos producto1 : productos) {
            totalCompra += producto1.getPrecio() * producto1.getStock();
        }
        return totalCompra;
    }
}
