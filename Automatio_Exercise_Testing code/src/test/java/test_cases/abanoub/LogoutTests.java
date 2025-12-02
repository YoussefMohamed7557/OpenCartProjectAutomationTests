package test_cases.abanoub;

import org.testng.annotations.Test;
import test_cases.abanoub.Pages.HomePage;
import test_cases.abanoub.Pages.LoginSignupPage;

import java.time.Duration;

// ---------- Allure Annotations ----------
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Description;

@Epic("Authentication")
@Feature("Logout")
public class LogoutTests extends TestBase {

    @Story("Logout from home page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a logged-in user can successfully log out and is redirected back to the login page with login message visible.")
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

    @Story("Logout and then use back button")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that using the browser back button after logout does not restore the logged-in session.")
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

    @Story("Logout from inner Products page")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that a logged-in user can log out from the Products page and session is ended.")
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

    }
}
