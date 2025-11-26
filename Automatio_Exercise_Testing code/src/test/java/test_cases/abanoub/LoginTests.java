package test_cases.abanoub;

import Pages.HomePage;
import Pages.LoginSignupPage;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void TC06_loginWithValidCredentials() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterLoginEmailAndPassword("1122334455@gmail.com", "1122334455");
        loginSignupPage.clickLoginButton();

        homePage.assertLoggedInAsUserVisible();
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
}
