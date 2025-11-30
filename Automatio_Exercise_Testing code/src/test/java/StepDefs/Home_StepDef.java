package StepDefs;

import io.cucumber.java.en.*;

import java.time.Duration;

import io.cucumber.java.en.*;
import test_cases.abanoub.Pages.HomePage;
import test_cases.abanoub.Pages.LoginSignupPage;
import utils.Config;


public class Home_StepDef {

    private Duration getTimeout() {
        int seconds = Hooks.getDefaultTimeoutSeconds();
        return Duration.ofSeconds(seconds > 0 ? seconds : 10);
    }

    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        homePage.navigateToHomePage();
        homePage.assertOnHomePage();
    }

    @And("the user navigates to the login page")
    public void theUserNavigatesToTheLoginPage() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        LoginSignupPage loginSignupPage = new LoginSignupPage(Hooks.getDriver(), getTimeout());

        homePage.clickLoginLink();
        loginSignupPage.assertOnLoginOrSignupPage();
    }

    @Then("the home page should be displayed with logo, login link, and featured items")
    public void theHomePageShouldBeDisplayedWithLogoLoginLinkAndFeaturedItems() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        homePage.assertOnHomePage();
        homePage.assertHomeLogoVisible();
        homePage.assertLoginLinkVisible();
        homePage.assertFeaturedItemsVisible();
    }

    @When("the user navigates to the Contact Us page")
    public void theUserNavigatesToTheContactUsPage() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        homePage.clickContactUsLink();
    }

    @Then("the Contact Us page should be displayed")
    public void theContactUsPageShouldBeDisplayed() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        homePage.assertOnContactUsPage();
        homePage.assertContactUsHeaderVisible();
    }

    @When("the user navigates to the Products page")
    public void theUserNavigatesToTheProductsPage() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        homePage.clickProductsLink();
    }

    @Then("the Products page should be displayed")
    public void theProductsPageShouldBeDisplayed() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        homePage.assertOnProductsPage();
        homePage.assertAllProductsHeaderVisible();
    }

    @When("the user clicks the home logo")
    public void theUserClicksTheHomeLogo() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        homePage.clickHomeLogo();
    }

    @Then("the user should be on the home page again")
    public void theUserShouldBeOnTheHomePageAgain() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        homePage.assertOnHomePage();
        homePage.assertHomeLogoVisible();
    }
}
