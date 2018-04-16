package Threads.SeconEx;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertyMultithreadingReader {
    Properties properties = new Properties();
    String filename  = "";

    PropertyMultithreadingReader() {}
    public synchronized void getPropertiesFromFile(String filename) {
        if (this.filename.equals(filename))
            return;
        String withProp = String.format("/%s.properties", filename);
        try(InputStream input = PropertyMultithreadingReader.class.getResourceAsStream(withProp)) {
            properties.load(input);
            this.filename = filename;
        } catch (NullPointerException e) {
            throw new RuntimeException("File doesn't exist.", e);
        } catch (IOException e) {
            throw new RuntimeException("Smh wrong with properties file.", e);
        }
    }

}
