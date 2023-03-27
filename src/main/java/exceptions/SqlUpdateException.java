package exceptions;

import java.sql.SQLException;

public class SqlUpdateException extends RuntimeException {
    public SqlUpdateException(String message, SQLException e){
        super(message, e);
    }

    public SqlUpdateException(String message) {
        super(message);
    }
}