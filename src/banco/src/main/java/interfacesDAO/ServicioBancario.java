package interfacesDAO;

import classes.Cliente;
import utils.exceptions.CuentaNoEncontradaException;
import utils.exceptions.SaldoInsuficienteException;

public interface ServicioBancario {
    void abrirCuenta(Cliente cliente, String tipoCuenta);
    void ingresarDinero(int cuentaID, double cantidad) throws CuentaNoEncontradaException;
    void retirarDinero(int cuentaID, double cantidad) throws CuentaNoEncontradaException, SaldoInsuficienteException;
    void transferir(int cuentaOrigenId, int cuentaDestinoId, double cantidad) throws CuentaNoEncontradaException, SaldoInsuficienteException;
}
