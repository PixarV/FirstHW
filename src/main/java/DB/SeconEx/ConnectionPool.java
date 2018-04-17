package DB.SeconEx;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConnectionPool implements Supplier<PooledConnection> {
    final BlockingQueue<PooledConnection> queue;
    boolean isClose;

    public ConnectionPool(String filename) {
        ConnectionsFactory factory = new ConnectionsFactory(filename);

        queue = IntStream.range(0, factory.getPoolSize())
                .mapToObj(__ -> factory.get())
                .map(PooledConnection::new)
                .collect(Collectors.toCollection(
                        () -> new ArrayBlockingQueue<PooledConnection>(factory.getPoolSize())));
    }

    @Override
    public PooledConnection get() {
        return queue.poll();
    }

    @SneakyThrows
    public void returnConnection(PooledConnection pooledConnection) {
        if (isClose) {
            pooledConnection.realClose();
            return;
        }
        queue.offer(pooledConnection);
    }
//
    @SneakyThrows
    public void closeConnections() {
        for (PooledConnection connection : queue)
            connection.realClose();
        isClose = true;
    }

}
