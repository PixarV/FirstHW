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
    public static Map<Object, Object> get(String filename) {
        String withProp = String.format("/%s.properties", filename);
        Properties properties = new Properties();
        try(InputStream input = PropertyToMap.class.getResourceAsStream(withProp)) {
            properties.load(input);
        } catch (NullPointerException e) {
            throw new RuntimeException("File doesn't exist.", e);
        } catch (IOException e) {
            throw new RuntimeException("Smh wrong with properties file.", e);
        }
        Map<Object, Object> result = new HashMap<>();
        for (String key : properties.stringPropertyNames())
            result.put(key, properties.getProperty(key));
        return result;
    }

    public static void main(String... args) {
        Map<Object, Object> map = get("random");

        System.out.println(map);
    }
}
