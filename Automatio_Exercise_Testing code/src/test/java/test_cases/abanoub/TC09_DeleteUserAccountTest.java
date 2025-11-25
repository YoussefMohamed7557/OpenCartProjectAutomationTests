package test_cases.abanoub;

import Pages.HomePage;
import Pages.LoginSignupPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC09_DeleteUserAccountTest {
    WebDriver driver;
    HomePage homePage;
    LoginSignupPage loginSignupPage;

    @BeforeTest
    public void preconditions() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        homePage = new HomePage(driver);
        loginSignupPage = new LoginSignupPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void deleteUserAccountSuccessfully() {
        homePage.navigateToHomePage();
        homePage.clickLoginLink();
        loginSignupPage.enterLoginEmailAndPassword("abanoub03@gmail.com", "1122334455");
        loginSignupPage.clickLoginButton();
        homePage.clickDeleteAccountButton();
        homePage.assertAccountDeletedMessageVisible();
        homePage.clickContinueButton();
    }
}
