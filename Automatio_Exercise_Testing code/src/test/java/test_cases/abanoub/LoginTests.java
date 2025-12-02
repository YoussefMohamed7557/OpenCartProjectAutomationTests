package test_cases.abanoub;

import org.testng.annotations.Test;
import test_cases.abanoub.Pages.HomePage;
import test_cases.abanoub.Pages.LoginSignupPage;

import java.time.Duration;


import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Description;

// ---------- Class ----------
@Epic("Authentication")
@Feature("Login")
public class LoginTests extends TestBase {

    @Story("Login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a registered user can log in successfully using valid email and password, and that the logout link appears.")
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

    @Story("Login with incorrect password")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that the system shows an incorrect credentials error when logging in with a wrong password and stays on the login page.")
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

    @Story("Login attempt after account deletion")
    @Severity(SeverityLevel.NORMAL)
    @Description("Ensure that a deleted account cannot log in and the system responds with incorrect credentials error.")
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

    @Story("Login with empty email and password fields")
    @Severity(SeverityLevel.MINOR)
    @Description("Validate that login fails when both email and password fields are empty and user stays on the login page.")
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


    @Story("Login with uppercase email (BUG)")
    @Severity(SeverityLevel.MINOR)
    @Description("BUG: Login succeeds even when email is written in uppercase. This test captures that behavior.")
    @Test
    public void TC23_loginWithUppercaseEmail() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterLoginEmail("1122334455@GMAIL.COM");
        loginSignupPage.enterLoginPassword("1122334455");
        loginSignupPage.clickLoginButton();

        loginSignupPage.assertWrongCredentialsErrorVisible();
    }

    @Story("Login using Enter key")
    @Severity(SeverityLevel.MINOR)
    @Description("Validate that hitting Enter key in the password field triggers the login action successfully.")
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

    @Story("Multiple failed login attempts")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that repeated invalid login attempts do not lock the user and the system always shows incorrect credentials error.")
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
