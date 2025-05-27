package banco.database;

import java.sql.*;

public class Database {
    private static String url = "jdbc:mariadb://localhost:3306/agenciaViajes";
    private static String user = "root";
    private static String password = "";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
