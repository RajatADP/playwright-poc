package helpers;

import java.io.IOException;
import java.util.Properties;

public class EnvironmentVariableReader {

    private static Properties properties = new Properties();
    static {
        String envFile = System.getProperty("env");
        if (envFile == null)
            envFile = "qa";

        String filePath = envFile.concat(".properties");
        try {
            properties.load(EnvironmentVariableReader.class.getClassLoader().getResourceAsStream(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperties(String key) {
        return properties.getProperty(key);
    }
}
