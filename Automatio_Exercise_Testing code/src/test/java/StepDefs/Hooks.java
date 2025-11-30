package StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.Config;
import utils.DriverFactory;

import java.time.Duration;

public class Hooks {

    private static WebDriver driver;
    private static int defaultTimeoutSeconds;
    private static String baseUrl;

    @Before
    public void setUp(Scenario scenario) {
        String browser = Config.getBrowser();
        baseUrl = Config.getBaseUrl();
        defaultTimeoutSeconds = Config.getTimeoutSeconds() > 0 ? Config.getTimeoutSeconds() : 10;

        driver = DriverFactory.createDriver(browser);
        driver.manage().window().maximize();

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            driver.manage().timeouts().pageLoadTimeout(
                    Duration.ofSeconds(Math.max(30, defaultTimeoutSeconds * 2))
            );
        } catch (Exception ignore) {
            // best effort
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static int getDefaultTimeoutSeconds() {
        return defaultTimeoutSeconds;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}
