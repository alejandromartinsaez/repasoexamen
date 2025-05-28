package banco.dao.impl;

import banco.components.Cliente;
import banco.dao.ClienteDAO;
import banco.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO_DB implements ClienteDAO {
    @Override
    public boolean guardar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, email) VALUES (?, ?)";
        try (Connection conn = Database.getConexion();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, cliente.getNombre());
            st.setString(2, cliente.getEmail());

            return st.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al agregar el cliente");
            return false;
        }
    }

    @Override
    public Cliente buscarPorID(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (Connection conn = Database.getConexion();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getInt( "id"),
                        rs.getString("nombre"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar los clientes " + e.getMessage());
        }
        return null;
    }

    @Override
    public Cliente buscarPorEmail(String email) {
        String sql = "SELECT * FROM clientes WHERE email = ?";
        try (Connection conn = Database.getConexion();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, email);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Cliente(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar el cliente por email " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> clienteList = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = Database.getConexion();
             PreparedStatement st = conn.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"));
                clienteList.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return clienteList;
    }

    @Override
    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre = ?, email = ? WHERE id = ?";
        try (Connection conn = Database.getConexion();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, cliente.getNombre());
            st.setString(2, cliente.getEmail());

            int filasActualizadas = st.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conn = Database.getConexion();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            int filasEliminadas = st.executeUpdate();
            return filasEliminadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar el cliente " + e.getMessage());
            return false;
        }
        }
}

