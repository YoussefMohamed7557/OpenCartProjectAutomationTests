package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) {
                props.load(is);
            } else {
                System.err.println("Warning: config.properties not found on classpath; using defaults.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key, String def) {
        return props.getProperty(key, def);
    }

    public static String getBaseUrl() {
        return get("baseUrl", "https://automationexercise.com");
    }

    public static String getBrowser() {
        return get("browser", "edge");
    }

    public static int getTimeoutSeconds() {
        try {
            return Integer.parseInt(get("timeoutSeconds", "10"));
        } catch (NumberFormatException e) {
            return 10;
        }
    }

    public static String getScreenshotsDir() {
        return get("screenshots.dir", "target/screenshots");
    }
}
