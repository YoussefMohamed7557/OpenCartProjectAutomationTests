package StepDefs;

import Pages.HomePage;
import Pages.LoginSignupPage;
import io.cucumber.java.en.*;

import java.time.Duration;

public class Logout_StepDef {

    private Duration getTimeout() {
        int seconds = Hooks.getDefaultTimeoutSeconds();
        return Duration.ofSeconds(seconds > 0 ? seconds : 10);
    }

    @When("the user clicks the logout button")
    public void theUserClicksTheLogoutButton() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        homePage.assertLogoutVisible();
        homePage.clickLogoutButton();
    }

    @Then("the user should be on the login page")
    public void theUserShouldBeOnTheLoginPage() {
        LoginSignupPage loginPage = new LoginSignupPage(Hooks.getDriver(), getTimeout());
        loginPage.assertOnLoginOrSignupPage();
    }

    @Then("the login message should be visible")
    public void theLoginMessageShouldBeVisible() {
        LoginSignupPage loginPage = new LoginSignupPage(Hooks.getDriver(), getTimeout());
        loginPage.assertLoginMessageVisible();
    }

    @And("the user navigates back and refreshes the page")
    public void theUserNavigatesBackAndRefreshesThePage() {
        Hooks.getDriver().navigate().back();
        Hooks.getDriver().navigate().refresh();
    }

    @Then("the user should be logged out")
    public void theUserShouldBeLoggedOut() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        homePage.assertUserLoggedOut();
    }
}
