package ecommerce.components;

import ecommerce.DAO.MetodoDePago;

public class PagoConPayPal implements MetodoDePago {
    public PagoConPayPal() {
    }

    @Override
    public void pagar(double monto) {
        System.out.println("Has pagado con paypal " + monto);
    }
}
