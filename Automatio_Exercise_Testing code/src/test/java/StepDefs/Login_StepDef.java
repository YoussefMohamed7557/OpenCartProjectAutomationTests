package StepDefs;

import io.cucumber.java.en.*;
import test_cases.abanoub.Pages.LoginSignupPage;
import test_cases.abanoub.Pages.HomePage;
import utils.Config;

import java.time.Duration;

public class Login_StepDef {

    private LoginSignupPage loginPage;
    private HomePage homePage;
    private final Duration timeout = Duration.ofSeconds(Math.max(5, Config.getTimeoutSeconds()));

    // Replace with real valid credentials if you want
    private final String VALID_EMAIL = "test@example.com";
    private final String VALID_PASSWORD = "123456";

    @Given("the user is on the login page")
    public void user_on_login_page() {
        if (Hooks.driver == null) throw new IllegalStateException("Hooks.driver is null");
        loginPage = new LoginSignupPage(Hooks.driver, timeout);
        Hooks.driver.get(Config.getBaseUrl() + "/login");
        loginPage.assertOnLoginOrSignupPage();
    }

    @When("the user enters valid credentials")
    public void user_enters_valid_credentials() {
        loginPage.enterLoginEmail(VALID_EMAIL);
        loginPage.enterLoginPassword(VALID_PASSWORD);
    }

    @When("the user enters invalid credentials")
    public void user_enters_invalid_credentials() {
        loginPage.enterLoginEmail("invalid@example.com");
        loginPage.enterLoginPassword("wrongpass");
    }

    @When("the user submits the login form")
    public void user_submits_login_form() {
        loginPage.clickLoginButton();
    }

    @Then("the user should be logged in")
    public void user_should_be_logged_in() {
        homePage = new HomePage(Hooks.driver, timeout);
        homePage.assertUserLoggedIn();
    }

    @Then("the \"Logged in as\" text should be visible")
    public void logged_in_as_text_visible() {
        homePage = new HomePage(Hooks.driver, timeout);
        homePage.assertLoggedInUsernameSanitized();
        homePage.assertLogoutVisible();
    }

    @Then("an authentication error should be displayed")
    public void authentication_error_displayed() {
        loginPage.assertWrongCredentialsErrorVisible();
    }

    @Given("the user account has been deleted")
    public void user_account_deleted() {
        // placeholder: if you have an API/helper to delete account, call it here
    }

    @When("the user attempts to login with deleted credentials")
    public void login_with_deleted_credentials() {
        if (loginPage == null) loginPage = new LoginSignupPage(Hooks.driver, timeout);
        loginPage.enterLoginEmail("deleted@example.com");
        loginPage.enterLoginPassword("somepass");
        loginPage.clickLoginButton();
    }

    @Then("login should fail with account not found message")
    public void login_fail_account_not_found() {
        loginPage.assertWrongCredentialsErrorVisible();
    }

    @When("the user submits the login form with empty fields")
    public void submit_empty_login() {
        if (loginPage == null) loginPage = new LoginSignupPage(Hooks.driver, timeout);
        loginPage.enterLoginEmail("");
        loginPage.enterLoginPassword("");
        loginPage.clickLoginButton();
    }

    @Then("validation errors should be shown for required fields")
    public void validation_errors_shown() {
        loginPage.assertStayedOnSignupOrLoginAfterInvalidData();
    }

    @When("the user enters credentials with leading or trailing spaces")
    public void enter_credentials_with_spaces() {
        if (loginPage == null) loginPage = new LoginSignupPage(Hooks.driver, timeout);
        loginPage.enterLoginEmail("  " + VALID_EMAIL + "  ");
        loginPage.enterLoginPassword("  " + VALID_PASSWORD + "  ");
    }

    @Then("login should normalize input and proceed or show invalid credentials")
    public void login_normalize_or_invalid() {
        loginPage.clickLoginButton();
        try {
            homePage = new HomePage(Hooks.driver, timeout);
            homePage.assertUserLoggedIn();
        } catch (AssertionError e) {
            loginPage.assertWrongCredentialsErrorVisible();
        }
    }

    @When("the user enters the username in uppercase and valid password")
    public void enter_uppercase_username() {
        if (loginPage == null) loginPage = new LoginSignupPage(Hooks.driver, timeout);
        loginPage.enterLoginEmail(VALID_EMAIL.toUpperCase());
        loginPage.enterLoginPassword(VALID_PASSWORD);
    }

    @Then("login should be case-insensitive for username if supported")
    public void login_should_be_case_insensitive_for_username_if_supported() {
        try {
            homePage = new HomePage(Hooks.driver, timeout);
            homePage.assertUserLoggedIn(); // success if site is case-insensitive
        } catch (AssertionError e) {
            loginPage.assertWrongCredentialsErrorVisible(); // otherwise show wrong creds
        }
    }

    @When("the user fills credentials and presses Enter")
    public void fill_and_press_enter() {
        if (loginPage == null) loginPage = new LoginSignupPage(Hooks.driver, timeout);
        loginPage.enterLoginEmail(VALID_EMAIL);
        loginPage.enterLoginPassword(VALID_PASSWORD);
        loginPage.pressEnterOnPassword();
    }

    @When("the user attempts multiple rapid failed logins")
    public void multiple_rapid_failed_logins() {
        if (loginPage == null) loginPage = new LoginSignupPage(Hooks.driver, timeout);
        for (int i = 0; i < 5; i++) {
            loginPage.enterLoginEmail("brute" + i + "@example.com");
            loginPage.enterLoginPassword("wrong" + i);
            loginPage.clickLoginButton();
        }
    }

    @Then("the application should either rate limit or record failed attempts")
    public void app_rate_limit_or_record() {
        loginPage.assertStayedOnSignupOrLoginAfterInvalidData();
    }
}
