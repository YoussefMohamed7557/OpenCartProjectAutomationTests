package StepDefs;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import utils.Config;
import utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Hooks {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Duration timeout;

    @Before
    public void setUpScenario() {
        try {
            String browser = Config.getBrowser();
            if (browser == null || browser.trim().isEmpty()) browser = "chrome";
            driver = DriverFactory.createDriver(browser);
            driver.manage().window().maximize();

            timeout = Duration.ofSeconds(Math.max(5, Config.getTimeoutSeconds()));
            try {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
                driver.manage().timeouts().pageLoadTimeout(timeout.multipliedBy(2));
            } catch (Exception ignore) { /* some drivers/environments may not support these */ }

            wait = new WebDriverWait(driver, timeout);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver in Hooks: " + e.getMessage(), e);
        }
    }

    @After
    public void tearDownScenario() {
        try {
            if (driver != null) driver.quit();
        } finally {
            driver = null;
            wait = null;
            timeout = null;
        }
    }
}
