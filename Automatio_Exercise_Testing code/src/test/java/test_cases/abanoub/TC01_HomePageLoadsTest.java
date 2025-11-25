package test_cases.abanoub;

import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC01_HomePageLoadsTest {
    WebDriver driver;
    HomePage homePage;

    @BeforeTest
    public void preconditions() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifyHomePageLoadsSuccessfully() {
        homePage.navigateToHomePage();
        homePage.assertURL();
        homePage.assertTitle();
        homePage.assertLoginLinkVisible();
        homePage.assertFeaturedItemsVisible();
        homePage.assertHomePageLogoVisible();
        homePage.assertSignUpLinkVisible();
    }
}
