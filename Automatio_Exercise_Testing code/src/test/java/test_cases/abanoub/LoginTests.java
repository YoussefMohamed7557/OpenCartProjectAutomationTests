package test_cases.abanoub;

import org.testng.annotations.Test;
import Pages.HomePage;
import Pages.LoginSignupPage;

import java.time.Duration;

public class LoginTests extends TestBase {

    @Test
    public void TC06_loginWithValidCredentials() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.login("1122334455@gmail.com", "1122334455");

        homePage.assertUserLoggedIn();
        homePage.assertLogoutVisible();
    }

    @Test
    public void TC07_loginWithIncorrectCredentials() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterLoginEmail("1122334455@gmail.com");
        loginSignupPage.enterLoginPassword("wrongpassword");
        loginSignupPage.clickLoginButton();

        loginSignupPage.assertWrongCredentialsErrorVisible();
        loginSignupPage.assertOnLoginOrSignupPage();
    }

    @Test
    public void TC12_loginAfterAccountDeletion() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterLoginEmail("deleteduser@gmail.com");
        loginSignupPage.enterLoginPassword("1122334455");
        loginSignupPage.clickLoginButton();

        loginSignupPage.assertWrongCredentialsErrorVisible();
    }

    @Test
    public void TC14_loginWithEmptyFields() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterLoginEmail("");
        loginSignupPage.enterLoginPassword("");
        loginSignupPage.clickLoginButton();

        loginSignupPage.assertOnLoginOrSignupPage();
    }

    @Test
    public void TC22_loginWithSpacesAroundCredentials() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterLoginEmail(" 1122334455@gmail.com ");
        loginSignupPage.enterLoginPassword(" 1122334455 ");
        loginSignupPage.clickLoginButton();

        homePage.assertUserLoggedIn();
    }

    @Test
    public void TC23_loginWithUppercaseEmail() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterLoginEmail("1122334455@GMAIL.COM");
        loginSignupPage.enterLoginPassword("1122334455");
        loginSignupPage.clickLoginButton();

        homePage.assertUserLoggedIn();
    }

    @Test
    public void TC27_loginWithEnterKey() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterLoginEmail("1122334455@gmail.com");
        loginSignupPage.enterLoginPassword("1122334455");
        getDriver()
                .findElement(org.openqa.selenium.By.xpath("//input[@data-qa='login-password']"))
                .sendKeys(org.openqa.selenium.Keys.ENTER);

        homePage.assertUserLoggedIn();
    }

    @Test
    public void TC31_noRateLimitingOnRepeatedFailedLoginAttempts() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        String email = "1122334455@gmail.com";

        for (int i = 0; i < 15; i++) {
            loginSignupPage.enterLoginEmail(email);
            loginSignupPage.enterLoginPassword("wrongPassword_" + i);
            loginSignupPage.clickLoginButton();

            loginSignupPage.assertWrongCredentialsErrorVisible();
            loginSignupPage.assertOnLoginOrSignupPage();
        }
    }
}
