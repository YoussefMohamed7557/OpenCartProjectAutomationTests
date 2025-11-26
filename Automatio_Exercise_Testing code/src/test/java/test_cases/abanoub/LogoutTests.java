package test_cases.abanoub;

import Pages.HomePage;
import Pages.LoginSignupPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTests extends TestBase {

    @Test
    public void TC08_logoutUserSuccessfully() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterLoginEmailAndPassword("1122334455@gmail.com", "1122334455");
        loginSignupPage.clickLoginButton();

        homePage.assertLogoutLinkVisible();
        homePage.clickLogoutButton();

        homePage.assertLoginPageLoadedAfterLogout();
        loginSignupPage.assertLoginMessageVisible();
    }

    @Test
    public void TC13_logoutAndUseBackButton() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterLoginEmailAndPassword("1122334455@gmail.com", "1122334455");
        loginSignupPage.clickLoginButton();

        homePage.assertLogoutLinkVisible();
        homePage.clickLogoutButton();

        driver.navigate().back();
        driver.navigate().refresh();

        boolean loggedIn = homePage.isLoggedInAsUserVisible();
        Assert.assertFalse(loggedIn, "User should NOT be logged in after logout followed by back button");
    }
}
