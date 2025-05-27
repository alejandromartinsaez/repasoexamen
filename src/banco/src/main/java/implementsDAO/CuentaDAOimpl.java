package implementsDAO;

import classes.Cuenta;
import interfacesDAO.CuentaDAO;
import utils.BBDDConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAOimpl implements CuentaDAO {
    //Attributes
    BBDDConfiguration ConnectionConfiguration = new BBDDConfiguration();

    //Constructor
    public CuentaDAOimpl(){};

    //Methods
    // Listar todos los Cuentas de la BBDD.
    @Override
    public List<Cuenta> listarTodos() {
        String selectSQL = "select * from Cuentas";
        List<Cuenta> cuentaList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(ConnectionConfiguration.getUrl(), ConnectionConfiguration.getUser(), ConnectionConfiguration.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int codCliente = resultSet.getInt("codCliente");
                double saldo = resultSet.getDouble("saldo");
                String tipoCuenta = resultSet.getString("tipoCuenta");
                cuentaList.add(new Cuenta(id,codCliente,saldo,tipoCuenta));
            }
            resultSet.close();
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return cuentaList;
    }

    // Añadir un registro a la tabla Cuentas.
    @Override
    public void guardar(Cuenta cuenta) {
        String insertSQL = "INSERT INTO Cuentas (id,codCliente,saldo,tipoCuenta) values (?,?,?,?);";

        try (Connection connection = DriverManager.getConnection(ConnectionConfiguration.getUrl(), ConnectionConfiguration.getUser(), ConnectionConfiguration.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)){

            int i = 1;
            preparedStatement.setInt(i++,cuenta.getId());
            preparedStatement.setInt(i++,cuenta.getCodCliente());
            preparedStatement.setDouble(i++,cuenta.getSaldo());
            preparedStatement.setString(i++,cuenta.getTipoCuenta());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Se han agregado " + rowsAffected + " lineas.");

        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Buscar un cliente por el ID.
    @Override
    public Cuenta buscarPorId(int id) {
        String selectSQL = "select * from Cuentas where id = ?";
        Cuenta cuenta = new Cuenta();

        try (Connection connection = DriverManager.getConnection(ConnectionConfiguration.getUrl(), ConnectionConfiguration.getUser(), ConnectionConfiguration.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){

            int i = 1;

            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                cuenta.setId(resultSet.getInt(i++));
                cuenta.setCodCliente(resultSet.getInt(i++));
                cuenta.setSaldo(resultSet.getDouble(i++));
                cuenta.setTipoCuenta(resultSet.getString(i++));
            }

        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return cuenta;
    }

    // Actualiza mediante un objeto.
    @Override
    public void actualizar(Cuenta cuenta) {
        String updateSQL = "UPDATE Cuentas SET codCliente = ?, saldo = ?, tipoCuenta = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(ConnectionConfiguration.getUrl(), ConnectionConfiguration.getUser(), ConnectionConfiguration.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)){

            int i = 1;

            preparedStatement.setInt(i++, cuenta.getCodCliente());
            preparedStatement.setDouble(i++, cuenta.getSaldo());
            preparedStatement.setString(i++, cuenta.getTipoCuenta());
            preparedStatement.setInt(i++, cuenta.getId());
            Cuenta cuenta1 = buscarPorId(cuenta.getId());
            if (cuenta1 != null){
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Se han modificado " + rowsAffected + " lineas.");
            } else {
                System.out.println("No existe dicho registro.");
            }
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Elimina mediante un objeto.
    @Override
    public void eliminar(Cuenta cuenta) {
        String updateSQL = "DELETE FROM Cuentas WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(ConnectionConfiguration.getUrl(), ConnectionConfiguration.getUser(), ConnectionConfiguration.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)){

            int i = 1;
            preparedStatement.setInt(i++, cuenta.getId());
            Cuenta cuenta1 = buscarPorId(cuenta.getId());
            if (cuenta1 != null){
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Se han eliminado " + rowsAffected + " lineas.");
            } else {
                System.out.println("No existe dicho registro.");
            }


        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

}
