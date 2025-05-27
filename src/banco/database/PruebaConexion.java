package banco.database;
import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConexion {
    public static void main(String[] args) {
        try (Connection con = Database.getConexion()) {
            if (con != null && !con.isClosed()) {
                System.out.println("✅ Conexión a la base de datos exitosa.");
            } else {
                System.out.println("❌ La conexión está cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
