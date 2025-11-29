package test_cases.abanoub;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;
import utils.Config;
import listeners.ScreenshotListener;
import io.qameta.allure.testng.AllureTestNg;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

@Listeners({ScreenshotListener.class, AllureTestNg.class})
public class TestBase {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl;
    protected int defaultTimeoutSeconds;
    protected String screenshotsDir;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional String browser) {
        if (browser == null || browser.isEmpty()) {
            browser = Config.getBrowser();
        }
        baseUrl = Config.getBaseUrl();
        defaultTimeoutSeconds = Config.getTimeoutSeconds();
        screenshotsDir = Config.getScreenshotsDir();

        driver = DriverFactory.createDriver(browser);
        driver.manage().window().maximize();

        // prefer explicit waits only — set implicit to 0 to avoid flaky waits
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Math.max(30, defaultTimeoutSeconds * 2)));
        } catch (Exception ignore) { /* WebDriver might not support these in some environments */ }

        wait = new WebDriverWait(driver, Duration.ofSeconds(defaultTimeoutSeconds));

        // ensure screenshots dir exists (TestBase responsibility)
        try {
            if (screenshotsDir != null && !screenshotsDir.trim().isEmpty()) {
                Files.createDirectories(Path.of(screenshotsDir));
            }
        } catch (Exception ignore) { /* best-effort */ }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public String getScreenshotsDir() {
        return screenshotsDir;
    }
}
