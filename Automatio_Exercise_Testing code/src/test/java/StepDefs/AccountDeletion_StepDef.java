package StepDefs;


import io.cucumber.java.en.*;
import test_cases.abanoub.Pages.HomePage;

import java.time.Duration;

public class AccountDeletion_StepDef {

    private Duration getTimeout() {
        int seconds = Hooks.getDefaultTimeoutSeconds();
        return Duration.ofSeconds(seconds > 0 ? seconds : 10);
    }

    @When("the user clicks the delete account button")
    public void theUserClicksTheDeleteAccountButton() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        homePage.clickDeleteAccountButton();
    }

    @Then("the account deleted message should be visible")
    public void theAccountDeletedMessageShouldBeVisible() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        homePage.assertAccountDeletedMessageVisible();
    }

    @And("the user clicks the continue button")
    public void theUserClicksTheContinueButton() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        homePage.clickContinueButton();
    }
}
