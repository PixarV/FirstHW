package DB.SeconEx;

import lombok.SneakyThrows;
import lombok.experimental.Delegate;

import java.io.Closeable;
import java.sql.Connection;

class PooledConnection implements Connection {
    @Delegate(excludes = Closeable.class)
    Connection connection;

    public PooledConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() {

    }

    @SneakyThrows
    public void realClose() {
        connection.close();
    }
}
