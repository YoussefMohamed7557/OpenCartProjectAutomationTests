package test_cases.abanoub;
import Pages.AccountCreatedPage;
import Pages.AccountCreationPage;
import Pages.HomePage;
import Pages.LoginSignupPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC03_RegisterNewUser_PositiveTest {
    WebDriver driver;
    HomePage homePage;
    LoginSignupPage loginSignupPage;
    AccountCreationPage accountCreationPage;
    AccountCreatedPage accountCreatedPage;

    @BeforeTest
    public void preconditions() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        homePage = new HomePage(driver);
        loginSignupPage = new LoginSignupPage(driver);
        accountCreationPage = new AccountCreationPage(driver);
        accountCreatedPage = new AccountCreatedPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void registerNewUserSuccessfully() {
        homePage.navigateToHomePage();
        homePage.clickLoginLink();
        loginSignupPage.enterNameAndEmail("TestUser0010", "abanoub03@gmail.com");
        loginSignupPage.clickSignUpButton();
        accountCreationPage.selectTitleMr();
        accountCreationPage.enterPassword("1122334455");
        accountCreationPage.selectDay("10");
        accountCreationPage.selectMonth("May");
        accountCreationPage.selectYear("1990");
        accountCreationPage.checkNewsletter();
        accountCreationPage.checkOffers();
        accountCreationPage.enterFirstName("Test");
        accountCreationPage.enterLastName("User");
        accountCreationPage.enterCompany("TestCompany");
        accountCreationPage.enterAddress1("123 Test St");
        accountCreationPage.enterAddress2("Apt 4B");
        accountCreationPage.selectCountry("Egypt");
        accountCreationPage.enterState("TestState");
        accountCreationPage.enterCity("TestCity");
        accountCreationPage.enterZipCode("12345");
        accountCreationPage.enterMobileNumber("1234567890");
        accountCreationPage.clickCreateAccountButton();
        accountCreatedPage.assertAccountCreatedMsgVisible();
        accountCreatedPage.clickContinueButton();
        homePage.assertLoggedInAsUserVisible();
    }
}
