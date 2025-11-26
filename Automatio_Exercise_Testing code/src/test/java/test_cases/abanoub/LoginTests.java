package test_cases.abanoub;

import Pages.HomePage;
import Pages.LoginSignupPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void TC06_loginWithValidCredentials() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.login("1122334455@gmail.com", "1122334455");

        homePage.assertLoggedInAsUserVisible();
        homePage.assertLogoutLinkVisible();
    }

    @Test
    public void TC07_loginWithIncorrectCredentials() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterLoginEmailAndPassword("1122334455@gmail.com", "wrongpassword");
        loginSignupPage.clickLoginButton();

        loginSignupPage.assertUrlBeTheSameWrongEmail();
        loginSignupPage.assertWrongEmailOrPasswordErrorVisible();
        loginSignupPage.assertLoginEmailAndPasswordInputVisible();
    }

    @Test
    public void TC12_loginAfterAccountDeletion() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterLoginEmailAndPassword("deleteduser@gmail.com", "1122334455");
        loginSignupPage.clickLoginButton();

        loginSignupPage.assertUrlBeTheSameWrongEmail();
        loginSignupPage.assertWrongEmailOrPasswordErrorVisible();
        loginSignupPage.assertLoginEmailAndPasswordInputVisible();
    }

    @Test
    public void TC14_loginWithEmptyFields() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterLoginEmailAndPassword("", "");
        loginSignupPage.clickLoginButton();

        loginSignupPage.assertUrlBeTheSameWrongEmail();
        loginSignupPage.assertLoginEmailAndPasswordInputVisible();
    }

    @Test
    public void TC22_loginWithSpacesAroundCredentials() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        String emailWithSpaces = " 1122334455@gmail.com ";
        String passwordWithSpaces = " 1122334455 ";

        loginSignupPage.enterLoginEmailAndPassword(emailWithSpaces, passwordWithSpaces);
        loginSignupPage.clickLoginButton();

        homePage.assertLoggedInAsUserVisible();
        homePage.assertLogoutLinkVisible();
    }

    @Test
    public void TC23_loginWithUppercaseEmail() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        String upperCaseEmail = "1122334455@GMAIL.COM";

        loginSignupPage.enterLoginEmailAndPassword(upperCaseEmail, "1122334455");
        loginSignupPage.clickLoginButton();

        homePage.assertLoggedInAsUserVisible();
        homePage.assertLogoutLinkVisible();
    }

    @Test
    public void TC27_loginWithEnterKey() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterLoginEmailAndPassword("1122334455@gmail.com", "1122334455");

        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys(Keys.ENTER);

        homePage.assertLoggedInAsUserVisible();
        homePage.assertLogoutLinkVisible();
    }
}
