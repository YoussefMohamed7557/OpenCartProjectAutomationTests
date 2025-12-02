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

@Epic("Account Management")
@Feature("Account Deletion")
public class AccountDeletionTests extends TestBase {

    @Story("Delete existing user account successfully")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a logged-in user can delete their account successfully and is redirected to the home page afterwards.")
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
