package test_cases.abanoub;

import Pages.HomePage;
import Pages.LoginSignupPage;
import org.testng.annotations.Test;

public class AccountDeletionTests extends TestBase {

    @Test
    public void TC09_deleteUserAccountSuccessfully() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.login("abanoub03@gmail.com", "1122334455");

        homePage.assertLoggedInAsUserVisible();
        homePage.clickDeleteAccountButton();
        homePage.assertAccountDeletedMessageVisible();
        homePage.clickContinueButton();
        homePage.assertHomePageLoadedAfterAccountDeletion();
    }
}
