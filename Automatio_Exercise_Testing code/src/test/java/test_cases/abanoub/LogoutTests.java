package test_cases.abanoub;

import org.testng.annotations.Test;
import Pages.HomePage;
import Pages.LoginSignupPage;

import java.time.Duration;

public class LogoutTests extends TestBase {

    @Test
    public void TC08_logoutUserSuccessfully() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.login("1122334455@gmail.com", "1122334455");

        homePage.assertLogoutVisible();
        homePage.clickLogoutButton();

        loginSignupPage.assertOnLoginOrSignupPage();
        loginSignupPage.assertLoginMessageVisible();
    }

    @Test
    public void TC13_logoutAndUseBackButton() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.login("1122334455@gmail.com", "1122334455");

        homePage.assertLogoutVisible();
        homePage.clickLogoutButton();

        getDriver().navigate().back();
        getDriver().navigate().refresh();

        homePage.assertUserLoggedOut();
    }

    @Test
    public void TC28_logoutFromProductsPage() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.login("1122334455@gmail.com", "1122334455");

        homePage.assertUserLoggedIn();
        homePage.assertLogoutVisible();

        homePage.clickProductsLink();
        homePage.assertOnProductsPage();

        homePage.clickLogoutButton();
        loginSignupPage.assertOnLoginOrSignupPage();
    }
}
