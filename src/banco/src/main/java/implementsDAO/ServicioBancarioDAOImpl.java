package implementsDAO;

import classes.Cliente;
import classes.Cuenta;
import classes.Movimiento;
import interfacesDAO.ClienteDAO;
import interfacesDAO.ServicioBancario;
import utils.BBDDConfiguration;
import utils.Obtener;
import utils.exceptions.CuentaNoEncontradaException;
import utils.exceptions.SaldoInsuficienteException;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ServicioBancarioDAOImpl implements ServicioBancario {
    //Attributes
    private Map<Integer, List<Movimiento>> cuentaMovimientoMap = new HashMap<>();
    ClienteDAOimpl clienteDAOimpl = new ClienteDAOimpl();
    CuentaDAOimpl cuentaDAOimpl = new CuentaDAOimpl();
    MovimientoDAOimpl movimientoDAOimpl = new MovimientoDAOimpl();
    Obtener obtener = new Obtener();

    //Constructor
    public ServicioBancarioDAOImpl(){};

    //Methods
    // Crear una cuenta.
    @Override
    public void abrirCuenta(Cliente cliente, String tipoCuenta) {
        clienteDAOimpl.guardar(cliente);
        cuentaDAOimpl.guardar(new Cuenta(obtener.obtenerMaxId("Cuentas"), cliente.getId(), tipoCuenta));
        System.out.println("Creada la cuenta con exito.");
    }

    // Ingresar dinero de una cuenta específica.
    @Override
    public void ingresarDinero(int cuentaID, double cantidad) throws CuentaNoEncontradaException {
        Cuenta cuenta = cuentaDAOimpl.buscarPorId(cuentaID);
        if (cuenta == null){
            throw new CuentaNoEncontradaException("La cuenta no existe.");
        }
        cuenta.setSaldo(cuenta.getSaldo()+cantidad);
        cuentaDAOimpl.actualizar(cuenta);
        Movimiento movimiento = new Movimiento(obtener.obtenerMaxId("Movimientos"),cuentaID,(int)cantidad,"ingreso", Date.valueOf(LocalDate.now()));

        if (!cuentaMovimientoMap.containsKey(cuentaID)){
            cuentaMovimientoMap.put(cuentaID, new ArrayList<>());
        }
        cuentaMovimientoMap.get(cuentaID).add(movimiento);
        movimientoDAOimpl.guardar(movimiento);

        System.out.println("Ingreso confirmado.");
    }

    // Retirar dinero de una cuanta específica.
    @Override
    public void retirarDinero(int cuentaID, double cantidad) throws CuentaNoEncontradaException, SaldoInsuficienteException{
        Cuenta cuenta = cuentaDAOimpl.buscarPorId(cuentaID);
        if (cuenta == null){
            throw new CuentaNoEncontradaException("La cuenta no existe.");
        }
        if (cuenta.getSaldo() <= 0){
            throw new SaldoInsuficienteException("Saldo a 0, no se permiten retiradas.");
        } else if ((cuenta.getSaldo()-cantidad) < 0){
            throw new SaldoInsuficienteException("No hay suficiente dinero en la cuenta, no se permite la operación.");
        }
        cuenta.setSaldo(cuenta.getSaldo()-cantidad);
        cuentaDAOimpl.actualizar(cuenta);
        Movimiento movimiento = new Movimiento(obtener.obtenerMaxId("Movimientos"),cuentaID,(int)cantidad,"retirada", Date.valueOf(LocalDate.now()));
        if (!cuentaMovimientoMap.containsKey(cuentaID)){
            cuentaMovimientoMap.put(cuentaID, new ArrayList<>());
        }
        cuentaMovimientoMap.get(cuentaID).add(movimiento);
        movimientoDAOimpl.guardar(movimiento);

        System.out.println("Retiro confirmado.");
    }

    // Transferir un saldo de una cuanta a otra.
    @Override
    public void transferir(int cuentaOrigenId, int cuentaDestinoId, double cantidad) throws CuentaNoEncontradaException, SaldoInsuficienteException{
        Cuenta cuentaOrigen = cuentaDAOimpl.buscarPorId(cuentaOrigenId);
        Cuenta cuentaDestino = cuentaDAOimpl.buscarPorId(cuentaDestinoId);
        if (cuentaOrigen == null || cuentaDestino == null){
            throw new CuentaNoEncontradaException("La cuenta no existe.");
        }
        if (cuentaOrigen.getSaldo() <= 0){
            throw new SaldoInsuficienteException("Saldo a 0, no se permiten transferencias.");
        } else if ((cuentaOrigen.getSaldo()-cantidad) < 0){
            throw new SaldoInsuficienteException("No hay suficiente dinero en la cuenta, no se permite la operación.");
        }
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo()-cantidad);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo()+cantidad);
        cuentaDAOimpl.actualizar(cuentaOrigen);
        cuentaDAOimpl.actualizar(cuentaDestino);

        Movimiento movimientoOrigen = new Movimiento(obtener.obtenerMaxId("Movimientos"),cuentaOrigenId,-(int)cantidad,"transferencia", Date.valueOf(LocalDate.now()));
        Movimiento movimientoDestino = new Movimiento(obtener.obtenerMaxId("Movimientos"),cuentaDestinoId,(int)cantidad,"transferencia", Date.valueOf(LocalDate.now()));
        if (!cuentaMovimientoMap.containsKey(cuentaOrigenId)){
            cuentaMovimientoMap.put(cuentaOrigenId, new ArrayList<>());
        }
        if (!cuentaMovimientoMap.containsKey(cuentaDestinoId)) {
            cuentaMovimientoMap.put(cuentaDestinoId, new ArrayList<>());
        }
        cuentaMovimientoMap.get(cuentaOrigenId).add(movimientoOrigen);
        movimientoDAOimpl.guardar(movimientoOrigen);
        cuentaMovimientoMap.get(cuentaDestinoId).add(movimientoDestino);
        movimientoDAOimpl.guardar(movimientoDestino);
        System.out.println("Transferencia realizada.");
    }
}
