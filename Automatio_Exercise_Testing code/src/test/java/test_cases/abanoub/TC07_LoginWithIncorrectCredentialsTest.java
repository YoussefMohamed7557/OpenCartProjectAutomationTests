package test_cases.abanoub;

import Pages.HomePage;
import Pages.LoginSignupPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC07_LoginWithIncorrectCredentialsTest {
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
    public void loginWithIncorrectCredentials() {
        homePage.navigateToHomePage();
        homePage.clickLoginLink();
        loginSignupPage.enterLoginEmailAndPassword("1122334455@gmail.com", "wrongpassword");
        loginSignupPage.clickLoginButton();
        loginSignupPage.assertWrongEmailOrPasswordErrorVisible();
    }
}
