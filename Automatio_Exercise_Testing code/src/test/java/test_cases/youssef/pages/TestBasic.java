package test_cases.youssef.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import test_cases.youssef.test_utilities.BrowserManager;
import test_cases.youssef.test_utilities.TestPropertiesReader;

import java.io.IOException;

public class TestBasic {
    protected static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        return tdriver.get();
    }

    @BeforeMethod
    public void setup() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--force-device-scale-factor=0.55");
        options.addArguments("--high-dpi-support=1");
        String url = TestPropertiesReader.loadProperty("url");
        WebDriver driver = BrowserManager.doBrowserSetup();
        tdriver.set(driver);
        getDriver().get(url);
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
        tdriver.remove();
    }
}
