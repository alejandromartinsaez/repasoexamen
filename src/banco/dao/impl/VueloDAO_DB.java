package banco.dao.impl;
import banco.components.Vuelo;
import banco.dao.VueloDAO;
import banco.database.Database;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.time.*;

public class VueloDAO_DB implements VueloDAO {
    @Override
    public boolean guardar(Vuelo vuelo) {
        String sql = "INSERT INTO vuelo (origen, destino, fecha, plazasDisponibles) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConexion();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, vuelo.getOrigen());
            st.setString(2, vuelo.getDestino());
            st.setDate(3, Date.valueOf(vuelo.getFecha()));
            st.setInt(4, vuelo.getPlazasDisponibles());

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al guardar el vuelo " + e.getMessage());
            return false;
        }
    }

    @Override
    public Vuelo buscarPorID(int id) {
        String sql = "SELECT * FROM vuelo WHERE id = ?";
        try (Connection conn = Database.getConexion();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Vuelo(
                        rs.getInt("id"),
                        rs.getString("origen"),
                        rs.getString("destino"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("plazasDisponibles")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el vuelo " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Vuelo> listarTodos() {
        List<Vuelo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vuelo";
        try (Connection con = Database.getConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vuelo v = new Vuelo(
                        rs.getInt("id"),
                        rs.getString("origen"),
                        rs.getString("destino"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("plazasDisponibles")
                );
                lista.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar vuelos: " + e.getMessage());
        }
        return lista;
    }
}
