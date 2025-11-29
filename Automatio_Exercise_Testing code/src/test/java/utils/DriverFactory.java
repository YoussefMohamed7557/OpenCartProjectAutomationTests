package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Locale;

public class DriverFactory {

    public static WebDriver createDriver(String browserName) {
        if (browserName == null) browserName = "edge";
        switch (browserName.toLowerCase(Locale.ROOT)) {
            case "chrome":
                return new ChromeDriver();
            case "edge":
            default:
                return new EdgeDriver();
        }
    }
}
