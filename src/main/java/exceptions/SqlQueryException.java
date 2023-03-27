package exceptions;

import java.sql.SQLException;

public class SqlQueryException extends RuntimeException {
    public SqlQueryException(String message, SQLException e) {
        super(message);
    }

}
