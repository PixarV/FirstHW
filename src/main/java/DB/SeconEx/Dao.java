package DB.SeconEx;

import DB.FirstEx.WorkWIthJDBC;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class Dao {
    ConnectionPool connectionPool;
    PooledConnection pooledConnection;

    public Dao() {
        connectionPool = new ConnectionPool("db");
        pooledConnection = connectionPool.get();
    }

    @SneakyThrows
    public PreparedStatement getPreparedStatement(String sql) {
        return pooledConnection.prepareStatement(sql);
    }

    @SneakyThrows
    public void closePreparedStatement(PreparedStatement pst) {
        if (pst != null)
            pst.close();
    }

    @SneakyThrows
    public void scriptExecute(String filename) {
        String sql = fromSQLFile(filename);
        PreparedStatement pst = getPreparedStatement(sql);
        pst.executeUpdate();
    }


    @SneakyThrows
    private static String fromSQLFile(String filename) {
        try (InputStream reader = WorkWIthJDBC.class.getResourceAsStream("/sql/" + filename)) {
            return new String(reader.readAllBytes()).replace("\n", "");
        }
    }

    @SneakyThrows
    public static void main(String... args) {
        Dao dao = new Dao();

        dao.scriptExecute("create");
        dao.scriptExecute("insert");
        PreparedStatement pst = dao.getPreparedStatement("Select * from Person");

        ResultSet result = pst.executeQuery();
        dao.closePreparedStatement(pst);

        while (result.next()) {
            System.out.println(result.getString("name"));
        }
        dao.scriptExecute("drop");
    }

}
