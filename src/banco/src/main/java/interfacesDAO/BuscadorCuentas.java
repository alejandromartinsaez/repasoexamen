package interfacesDAO;

import classes.Cuenta;
import utils.exceptions.CuentaNoEncontradaException;

import java.util.List;

public interface BuscadorCuentas {
    List<Cuenta> obtenerCuentasPorCliente(int clienteId) throws CuentaNoEncontradaException;
    double obtenerSaldoTotal(int clienteId) throws CuentaNoEncontradaException;
}
