package DB.FirstEx;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class WorkWIthJDBC {
    @SneakyThrows
    private static Properties getPropertiesFromFile(String filename) {
        String withProp = String.format("/%s.properties", filename);
        Properties properties = new Properties();
        try(InputStream input = WorkWIthJDBC.class.getResourceAsStream(withProp)) {
            properties.load(input);
        }
        return properties;
    }

    @SneakyThrows
    private static String fromSQLFile(String filename) {
        try (InputStream reader = WorkWIthJDBC.class.getResourceAsStream("/sql/" + filename)) {
            return new String(reader.readAllBytes()).replace("\n", "");
        }
    }



    @SneakyThrows
    public static void main(String... args) {
        Properties properties = getPropertiesFromFile("db");
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            Statement st = con.createStatement();
            String query = fromSQLFile("create");
            st.executeUpdate(query);

            String insert = fromSQLFile("insert");
            st.executeUpdate(insert);

            PreparedStatement pst = con.
                    prepareStatement("SELECT name, surname FROM Person WHERE age=20");

            PreparedStatement forUpdate = con.
                    prepareStatement("UPDATE Person set name='Margarita' WHERE id=1");
            forUpdate.executeUpdate();

            ResultSet result = pst.executeQuery();
            while (result.next()) {
                System.out.printf("%s %s\n", result.getString("name"),
                        result.getString("surname"));
            }

            String drop = fromSQLFile("drop");
            st.executeUpdate(drop);
        }

    }
}
