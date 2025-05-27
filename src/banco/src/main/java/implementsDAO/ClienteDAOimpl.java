package implementsDAO;

import classes.Cliente;
import interfacesDAO.ClienteDAO;
import utils.BBDDConfiguration;

import java.sql.*;
import java.util.*;

public class ClienteDAOimpl implements ClienteDAO {
    //Attributes
    BBDDConfiguration ConnectionConfiguration = new BBDDConfiguration();

    //Constructor
    public ClienteDAOimpl(){};

    //Methods
    // Listar todos los Clientes de la BBDD.
    @Override
    public List<Cliente> listarTodos() {
        String selectSQL = "select * from Clientes";
        List<Cliente> clienteList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(ConnectionConfiguration.getUrl(), ConnectionConfiguration.getUser(), ConnectionConfiguration.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");
                String contraseña = resultSet.getString("contraseña");
                clienteList.add(new Cliente(id,nombre,email,contraseña));
            }
            resultSet.close();
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return clienteList;
    }

    // Añadir un registro a la tabla Clientes.
    @Override
    public void guardar(Cliente cliente) {
        String insertSQL = "INSERT INTO Clientes (id,nombre,email,contraseña) values (?,?,?,?);";

        try (Connection connection = DriverManager.getConnection(ConnectionConfiguration.getUrl(), ConnectionConfiguration.getUser(), ConnectionConfiguration.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)){

            int i = 1;
            preparedStatement.setInt(i++,cliente.getId());
            preparedStatement.setString(i++,cliente.getName());
            preparedStatement.setString(i++,cliente.getEmail());
            preparedStatement.setString(i++,cliente.getPassword());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Se han agregado " + rowsAffected + " lineas.");

        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Buscar un cliente por el ID.
    @Override
    public Cliente buscarPorId(int id) {
        String selectSQL = "select * from Clientes where id = ?";
        Cliente cliente = new Cliente();

        try (Connection connection = DriverManager.getConnection(ConnectionConfiguration.getUrl(), ConnectionConfiguration.getUser(), ConnectionConfiguration.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){

            int i = 1;

            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                cliente.setId(resultSet.getInt(i++));
                cliente.setName(resultSet.getString(i++));
                cliente.setEmail(resultSet.getString(i++));
                cliente.setPassword(resultSet.getString(i++));
            }

        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return cliente;
    }

    // Actualiza mediante un objeto.
    @Override
    public void actualizar(Cliente cliente) {
        String updateSQL = "UPDATE Clientes SET nombre = ?, email = ?, contraseña = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(ConnectionConfiguration.getUrl(), ConnectionConfiguration.getUser(), ConnectionConfiguration.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)){

            int i = 1;

            preparedStatement.setString(i++, cliente.getName());
            preparedStatement.setString(i++, cliente.getEmail());
            preparedStatement.setString(i++, cliente.getPassword());
            preparedStatement.setInt(i++, cliente.getId());
            Cliente cliente1 = buscarPorId(cliente.getId());
            if (cliente1 != null){
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
    public void eliminar(Cliente cliente) {
        String updateSQL = "DELETE FROM Clientes WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(ConnectionConfiguration.getUrl(), ConnectionConfiguration.getUser(), ConnectionConfiguration.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)){

            int i = 1;
            preparedStatement.setInt(i++, cliente.getId());
            Cliente cliente1 = buscarPorId(cliente.getId());
            if (cliente1 != null){
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
