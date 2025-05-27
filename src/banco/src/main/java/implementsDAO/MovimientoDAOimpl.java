package implementsDAO;

import classes.Cuenta;
import classes.Movimiento;
import interfacesDAO.CuentaDAO;
import interfacesDAO.MovimientosDAO;
import utils.BBDDConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimientoDAOimpl implements MovimientosDAO {
    //Attributes
    BBDDConfiguration ConnectionConfiguration = new BBDDConfiguration();

    //Constructor
    public MovimientoDAOimpl(){};

    //Methods
    // Listar todos los Movimientos de la BBDD.
    @Override
    public List<Movimiento> listarTodos() {
        String selectSQL = "select * from Movimientos";
        List<Movimiento> movimientoList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(ConnectionConfiguration.getUrl(), ConnectionConfiguration.getUser(), ConnectionConfiguration.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int codCuenta = resultSet.getInt("codCuenta");
                int cantidad = resultSet.getInt("cantidad");
                String tipo = resultSet.getString("tipo");
                Date fecha = resultSet.getDate("fecha");
                movimientoList.add(new Movimiento(id,codCuenta,cantidad,tipo,fecha));
            }
            resultSet.close();
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return movimientoList;
    }

    // Añadir un registro a la tabla Movimientos.
    @Override
    public void guardar(Movimiento movimiento) {
        if (!validTipo(movimiento.getTipo())){
            return;
        }
        String insertSQL = "INSERT INTO Movimientos (id,codCuenta,tipo,cantidad,fecha) values (?,?,?,?,?);";

        try (Connection connection = DriverManager.getConnection(ConnectionConfiguration.getUrl(), ConnectionConfiguration.getUser(), ConnectionConfiguration.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)){

            int i = 1;
            preparedStatement.setInt(i++,movimiento.getId());
            preparedStatement.setInt(i++,movimiento.getCodCuenta());
            preparedStatement.setString(i++,movimiento.getTipo());
            preparedStatement.setInt(i++,movimiento.getCantidad());
            preparedStatement.setDate(i++,movimiento.getFecha());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Se han agregado " + rowsAffected + " lineas.");

        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Buscar un cliente por el ID.
    @Override
    public Movimiento buscarPorId(int id) {
        String selectSQL = "select * from Movimientos where id = ?";
        Movimiento movimiento = new Movimiento();

        try (Connection connection = DriverManager.getConnection(ConnectionConfiguration.getUrl(), ConnectionConfiguration.getUser(), ConnectionConfiguration.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){

            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                movimiento.setId(resultSet.getInt("id"));
                movimiento.setCodCuenta(resultSet.getInt("codCuenta"));
                movimiento.setCantidad(resultSet.getInt("cantidad"));
                movimiento.setTipo(resultSet.getString("tipo"));
                movimiento.setFecha(resultSet.getDate("fecha"));
            }

        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return movimiento;
    }

    // Actualiza mediante un objeto.
    @Override
    public void actualizar(Movimiento movimiento) {
        if (!validTipo(movimiento.getTipo())){
            return;
        }

        String updateSQL = "UPDATE Movimientos SET codCuenta = ?, tipo = ?, cantidad = ?, fecha = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(ConnectionConfiguration.getUrl(), ConnectionConfiguration.getUser(), ConnectionConfiguration.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)){

            int i = 1;

            preparedStatement.setInt(i++, movimiento.getCodCuenta());
            preparedStatement.setString(i++, movimiento.getTipo());
            preparedStatement.setInt(i++, movimiento.getCantidad());
            preparedStatement.setDate(i++, movimiento.getFecha());
            preparedStatement.setInt(i++, movimiento.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Se han modificado " + rowsAffected + " lineas.");
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Elimina mediante un objeto.
    @Override
    public void eliminar(Movimiento movimiento) {
        String updateSQL = "DELETE FROM Movimientos WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(ConnectionConfiguration.getUrl(), ConnectionConfiguration.getUser(), ConnectionConfiguration.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)){

            int i = 1;
            preparedStatement.setInt(i++, movimiento.getId());
            Movimiento cuenta1 = buscarPorId(movimiento.getId());
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

    // Verificaciones
    public boolean validTipo(String tipo){
        List<String> valido = List.of("ingreso", "retirada", "transferencia");
        if (valido.contains(tipo.toLowerCase())){
            return true;
        }
        System.out.println("Tipo de movimiento invalido. Válidos: [INGRESO/RETIRADA/TRANSFERENCIA].");
        return false;
    }

}
