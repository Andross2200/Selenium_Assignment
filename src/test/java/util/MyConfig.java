package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MyConfig {

    private final Properties properties;

    public MyConfig() {
        this.properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/java/config/config.properties")) {
            this.properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String propertyKey) {
        return this.properties.getProperty(propertyKey);
    }
}
