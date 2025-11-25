package test_cases.abanoub;

import Pages.AccountCreationPage;
import Pages.HomePage;
import Pages.LoginSignupPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC11_Signup_WeakPasswordTest {
    WebDriver driver;
    HomePage homePage;
    LoginSignupPage loginSignupPage;
    AccountCreationPage accountCreationPage;

    @BeforeTest
    public void preconditions() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        homePage = new HomePage(driver);
        loginSignupPage = new LoginSignupPage(driver);
        accountCreationPage = new AccountCreationPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void signupWithWeakPassword() {
        homePage.navigateToHomePage();
        homePage.clickLoginLink();
        loginSignupPage.enterNameAndEmail("WeakPassUser", "weakpass01@gmail.com");
        loginSignupPage.clickSignUpButton();

        accountCreationPage.selectTitleMr();
        accountCreationPage.enterPassword("123"); // weak
        accountCreationPage.selectDay("1");
        accountCreationPage.selectMonth("Jan");
        accountCreationPage.selectYear("2000");
        accountCreationPage.enterFirstName("Weak");
        accountCreationPage.enterLastName("Pass");
        accountCreationPage.enterCompany("TestCo");
        accountCreationPage.enterAddress1("Addr1");
        accountCreationPage.selectCountry("Egypt");
        accountCreationPage.enterState("State");
        accountCreationPage.enterCity("City");
        accountCreationPage.enterZipCode("00000");
        accountCreationPage.enterMobileNumber("0000000000");
        accountCreationPage.clickCreateAccountButton();

        // Assert: account should NOT be created with a weak password
        boolean accountCreated = driver.findElements(By.xpath("//h2[@data-qa='account-created']")).size() > 0;
        Assert.assertFalse(accountCreated, "Account should NOT be created with a weak/short password");
    }
}
