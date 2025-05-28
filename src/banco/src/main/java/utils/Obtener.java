package utils;

import java.sql.*;

public class Obtener {
    public int obtenerMaxId(String tabla) {
        String sql = "SELECT MAX(id) AS max_id FROM " + tabla; // Cambia el nombre de la tabla si es necesario
        int maxId = 0;

        BBDDConfiguration ConnectionConfiguration = new BBDDConfiguration();
        try (Connection connection = DriverManager.getConnection(ConnectionConfiguration.getUrl(), ConnectionConfiguration.getUser(), ConnectionConfiguration.getPassword());
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                maxId = rs.getInt("max_id");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el máximo ID: " + e.getMessage());
        }

        return maxId;
    }
}
