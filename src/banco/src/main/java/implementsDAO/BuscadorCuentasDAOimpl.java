package implementsDAO;

import classes.Cliente;
import classes.Cuenta;
import interfacesDAO.BuscadorCuentas;
import utils.BBDDConfiguration;
import utils.exceptions.CuentaNoEncontradaException;

import java.sql.*;
import java.util.*;

public class BuscadorCuentasDAOimpl implements BuscadorCuentas {
    //Attributes
    Map<Integer, List<Cuenta>> cuentasClientesMap = new HashMap<>();
    BBDDConfiguration ConnectionConfiguration = new BBDDConfiguration();

    //Constructor
    public BuscadorCuentasDAOimpl(){
        generarDatos();
    };

    //Methods

    // Obtenemos un listado de cuentas mediante el ID de Cliente que posee Cuenta.
    @Override
    public List<Cuenta> obtenerCuentasPorCliente(int clienteId) throws CuentaNoEncontradaException{
        List<Cuenta> cuentaList = cuentasClientesMap.get(clienteId);
        if (cuentaList == null){
            throw new CuentaNoEncontradaException("No existe la cuenta.");
        }
        return cuentaList;
    }

    // Mediante recorrer el Map sumamos el saldo de un cliente en específico.
    @Override
    public double obtenerSaldoTotal(int clienteId) throws CuentaNoEncontradaException{
        double sum = 0;
        List<Cuenta> cuentaList = obtenerCuentasPorCliente(clienteId);
        for (Cuenta c : cuentaList){
            sum += c.getSaldo();
        }
        return sum;
    }

    // Creamos los datos de cuentasClientesMap.
    public void generarDatos(){
        ClienteDAOimpl clienteDAOimpl = new ClienteDAOimpl();
        CuentaDAOimpl cuentaDAOimpl = new CuentaDAOimpl();
        for (Cliente c : clienteDAOimpl.listarTodos()){
            if (!cuentasClientesMap.containsKey(c.getId())){
                cuentasClientesMap.put(c.getId(), new ArrayList<>());
            }
        }
        for (Cuenta c : cuentaDAOimpl.listarTodos()){
            if (c.getSaldo() == 0){
                cuentasClientesMap.get(c.getCodCliente()).add(new Cuenta(c.getId(),c.getCodCliente(),c.getTipoCuenta()));
            } else {
                cuentasClientesMap.get(c.getCodCliente()).add(new Cuenta(c.getId(),c.getCodCliente(),c.getSaldo(),c.getTipoCuenta()));
            }
        }
    }
}
