package Exceptions.SecondEx;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class UniversalPropertyReader {
    Properties properties = new Properties();

    public UniversalPropertyReader() {}

    public void getPropertiesFromFile(String filename) {
        String withProp = String.format("/%s.properties", filename);
        try(InputStream input = UniversalPropertyReader.class.getResourceAsStream(withProp)) {
            properties.load(input);
        } catch (NullPointerException e) {
            throw new RuntimeException("File doesn't exist.", e);
        } catch (IOException e) {
            throw new RuntimeException("Smh wrong with properties file.", e);
        }
    }

    public String getProperty(String key) {
        if (properties.get(key) == null) {
            throw new RuntimeException("Key does't exist.");
        }
        return (String) properties.get(key);
    }

    public static void main(String... args) {
        UniversalPropertyReader upr = new UniversalPropertyReader();
        upr.getPropertiesFromFile("random");
        System.out.println(upr.getProperty("age"));
    }
}
