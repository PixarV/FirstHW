package DB.SeconEx;

import java.sql.SQLException;

public class PooledConnectionCloseException extends SQLException {
    public PooledConnectionCloseException(String msg) {
        super(msg);
    }

}
