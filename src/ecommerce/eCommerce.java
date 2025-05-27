package ecommerce;

import ecommerce.components.Carrito;
import ecommerce.components.PagoConPayPal;
import ecommerce.components.PagoConTarjeta;
import ecommerce.components.Productos;

public class eCommerce {
    public static void main(String[] args) {

        Productos productos = new Productos("Fresa", 3, 5);
        Productos productos1 = new Productos("Samuel", 1, 0);

        PagoConTarjeta pagoConTarjeta = new PagoConTarjeta();
        PagoConPayPal pagoConPayPal = new PagoConPayPal();

        Carrito carrito = new Carrito();
        carrito.agregarProducto(new Productos("Platano", 10,0));
        carrito.agregarProducto(new Productos("Banana", 10,3));
        carrito.agregarProducto(new Productos("Canarias", 10,2));

        carrito.eliminarProducto(productos1);

        pagoConTarjeta.pagar(carrito.calcularCompra());


    }
}
