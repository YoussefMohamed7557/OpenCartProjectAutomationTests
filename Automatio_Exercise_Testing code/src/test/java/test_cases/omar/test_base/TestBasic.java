package test_cases.omar.test_base;

import io.qameta.allure.Step;
import test_cases.omar.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBasic {


    protected static WebDriver driver;
    protected String baseUrl = "https://automationexercise.com/";

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--force-device-scale-factor=0.55");
        options.addArguments("--high-dpi-support=1");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(baseUrl);
    }
    public static WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("Verify that home page is visible successfully")
    public static void verifyThatHomePageIsVisibleSuccessfully() {
        boolean homePageVisible = new HomePage(getDriver())
                .homePageIsVisible()
                .isDisplayed();
        Assert.assertTrue(homePageVisible, "Home page should be visible");
    }

}