package DB.SeconEx;

import DB.FirstEx.WorkWIthJDBC;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.function.Supplier;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConnectionsFactory implements Supplier<Connection> {
    String url;
    String user;
    String password;
    @Getter
    int poolSize;

    @SneakyThrows
    public ConnectionsFactory(String filename) {
        Properties properties = getPropertiesFromFile(filename);
        Class.forName(properties.getProperty("driver"));
        url = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
        poolSize = Integer.valueOf(properties.getProperty("size"));
    }

    @SneakyThrows
    public Connection get() {
        return DriverManager.getConnection(url, user, password);
    }


    @SneakyThrows
    private static Properties getPropertiesFromFile(String filename) {
        String withProp = String.format("/%s.properties", filename);
        Properties properties = new Properties();
        try(InputStream input = WorkWIthJDBC.class.getResourceAsStream(withProp)) {
            properties.load(input);
        }
        return properties;
    }
}
