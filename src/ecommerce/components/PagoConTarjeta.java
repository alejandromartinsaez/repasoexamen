package ecommerce.components;

import ecommerce.DAO.MetodoDePago;

public class PagoConTarjeta implements MetodoDePago {
    public PagoConTarjeta() {
    }

    @Override
    public void pagar(double monto) {
        System.out.println("Has pagado " + monto);
    }
}
