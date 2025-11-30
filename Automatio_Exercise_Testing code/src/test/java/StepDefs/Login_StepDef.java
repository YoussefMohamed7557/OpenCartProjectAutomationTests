package StepDefs;

import Pages.HomePage;
import Pages.LoginSignupPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

public class Login_StepDef {

    private Duration getTimeout() {
        int seconds = Hooks.getDefaultTimeoutSeconds();
        return Duration.ofSeconds(seconds > 0 ? seconds : 10);
    }

    @When("the user logs in with email {string} and password {string}")
    public void theUserLogsInWithEmailAndPassword(String email, String password) {
        LoginSignupPage loginPage = new LoginSignupPage(Hooks.getDriver(), getTimeout());
        loginPage.login(email, password);
    }

    @Then("the user should be logged in")
    public void theUserShouldBeLoggedIn() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        homePage.assertUserLoggedIn();
    }

    @Then("the logout link should be visible")
    public void theLogoutLinkShouldBeVisible() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        homePage.assertLogoutVisible();
    }

    @Then("an incorrect credentials error should be visible")
    public void anIncorrectCredentialsErrorShouldBeVisible() {
        LoginSignupPage loginPage = new LoginSignupPage(Hooks.getDriver(), getTimeout());
        loginPage.assertWrongCredentialsErrorVisible();
    }

    @Then("the user should stay on the login page")
    public void theUserShouldStayOnTheLoginPage() {
        LoginSignupPage loginPage = new LoginSignupPage(Hooks.getDriver(), getTimeout());
        loginPage.assertOnLoginOrSignupPage();
    }

    @When("the user enters email {string} and password {string} and submits with Enter key")
    public void theUserEntersEmailAndPasswordAndSubmitsWithEnterKey(String email, String password) {
        LoginSignupPage loginPage = new LoginSignupPage(Hooks.getDriver(), getTimeout());
        loginPage.enterLoginEmail(email);
        loginPage.enterLoginPassword(password);
        Hooks.getDriver()
                .findElement(By.xpath("//input[@data-qa='login-password']"))
                .sendKeys(Keys.ENTER);
    }

    @When("the user attempts to login {int} times with email {string} and wrong passwords")
    public void theUserAttemptsToLoginTimesWithEmailAndWrongPasswords(Integer attempts, String email) {
        LoginSignupPage loginPage = new LoginSignupPage(Hooks.getDriver(), getTimeout());

        for (int i = 0; i < attempts; i++) {
            loginPage.enterLoginEmail(email);
            loginPage.enterLoginPassword("wrongPassword_" + i);
            loginPage.clickLoginButton();
            loginPage.assertWrongCredentialsErrorVisible();
            loginPage.assertOnLoginOrSignupPage();
        }
    }

    @Then("an incorrect credentials error should be visible after each attempt")
    public void anIncorrectCredentialsErrorShouldBeVisibleAfterEachAttempt() {
        // already asserted inside the loop; this step is just for readability
    }
}
