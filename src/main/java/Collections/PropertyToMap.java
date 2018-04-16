package Collections;
/*
Если добавить в map-коллекцию элемент с ключом, который уже в ней присутствует - значение
по этому ключу будет перезаписано.
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyToMap {
    private static Map<String, String> result = new HashMap<>();
    private static String filename = "";

    public static Map<String, String> get(String filename) {
        String withProp = String.format("/%s.properties", filename);
        if (PropertyToMap.filename.equals(filename))
            return result;
        Properties properties = new Properties();
        try(InputStream input = PropertyToMap.class.getResourceAsStream(withProp)) {
            properties.load(input);
            PropertyToMap.filename = filename;
        } catch (NullPointerException e) {
            throw new RuntimeException("File doesn't exist.", e);
        } catch (IOException e) {
            throw new RuntimeException("Smh wrong with properties file.", e);
        }
        result.clear();
        for (String key : properties.stringPropertyNames())
            result.put(key, properties.getProperty(key));
        return result;
    }

    public static void main(String... args) {
        Map<String, String> map = get("random");
        Map<String, String> mapa = get("random");

        System.out.println(map);
        System.out.println(mapa);
    }
}
