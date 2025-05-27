import classes.Cliente;
import implementsDAO.ClienteDAOimpl;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ClienteDAOimpl clienteDAOimpl = new ClienteDAOimpl();

        //clienteDAOimpl.guardar(new Cliente(3,"Pepe","pepe@gamil.com","456"));

        System.out.println(Arrays.asList(clienteDAOimpl.listarTodos()));

        System.out.println(clienteDAOimpl.buscarPorId(2));

        clienteDAOimpl.eliminar(new Cliente(2,"Juan","juan@gmail.com","123"));
        clienteDAOimpl.actualizar(new Cliente(3,"Pepe","pepe@gamil.com","456"));

    }
}
