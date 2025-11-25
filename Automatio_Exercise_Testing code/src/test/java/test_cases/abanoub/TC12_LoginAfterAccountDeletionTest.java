package test_cases.abanoub;

import Pages.HomePage;
import Pages.LoginSignupPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC12_LoginAfterAccountDeletionTest {
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
    public void loginAfterAccountDeletion() {
        homePage.navigateToHomePage();
        homePage.clickLoginLink();
        loginSignupPage.enterLoginEmailAndPassword("deleteduser@gmail.com", "1122334455");
        loginSignupPage.clickLoginButton();
        loginSignupPage.assertWrongEmailOrPasswordErrorVisible();
    }
}
