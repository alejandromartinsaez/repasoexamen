package banco.exceptions;

import java.sql.SQLException;

public class ErrorDeConexionException extends Exception {
    public ErrorDeConexionException(String message) {
        super(message);
    }
}
