package test_cases.abanoub;
import Pages.LoginSignupPage;

import org.testng.annotations.Test;
import Pages.HomePage;

import java.time.Duration;

public class AccountDeletionTests extends TestBase {

    @Test
    public void TC09_deleteUserAccountSuccessfully() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.login("abanoub03@gmail.com", "1122334455");

        homePage.assertUserLoggedIn();
        homePage.clickDeleteAccountButton();

        homePage.assertAccountDeletedMessageVisible();
        homePage.clickContinueButton();
        homePage.assertOnHomePage();
    }
}
