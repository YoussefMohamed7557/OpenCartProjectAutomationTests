package StepDefs;

import io.cucumber.java.en.*;
import Pages.HomePage;
import utils.Config;

public class Home_StepDef {

    private HomePage homePage;

    @When("the user navigates to the home page")
    public void navigate_to_home() {
        ensureHomePageInitialized();
        homePage.navigateToHomePage();
    }

    @Given("the user is on the home page")
    public void user_on_home() {
        ensureHomePageInitialized();
        homePage.navigateToHomePage();
    }

    @Given("the user is on a non-home page")
    public void user_on_non_home() {
        if (Hooks.driver == null) throw new IllegalStateException("Hooks.driver is null");
        Hooks.driver.get(Config.getBaseUrl() + "/products");
    }

    @When("the user clicks the Contact Us button")
    public void click_contact_us() {
        ensureHomePageInitialized();
        homePage.clickContactUsLink();
    }

    @When("the user clicks the Products link")
    public void click_products() {
        ensureHomePageInitialized();
        homePage.clickProductsLink();
    }

    @When("the user clicks the site logo")
    public void click_logo() {
        ensureHomePageInitialized();
        homePage.clickHomeLogo();
    }

    @Then("the home page should be displayed")
    public void verify_home_page() {
        ensureHomePageInitialized();
        homePage.assertOnHomePage();
    }

    @Then("the main header or logo should be visible")
    public void verify_main_header_or_logo() {
        ensureHomePageInitialized();
        homePage.assertHomeLogoVisible();
    }

    @Then("the Contact Us page should be displayed")
    public void verify_contact_us() {
        ensureHomePageInitialized();
        homePage.assertOnContactUsPage();
    }

    @Then("the contact form should be visible")
    public void verify_contact_form() {
        ensureHomePageInitialized();
        homePage.assertContactUsHeaderVisible();
    }

    @Then("the Products page should be displayed")
    public void verify_products_page() {
        ensureHomePageInitialized();
        homePage.assertOnProductsPage();
    }

    @Then("at least one product listing should be visible")
    public void verify_products_visible() {
        ensureHomePageInitialized();
        homePage.assertAllProductsHeaderVisible();
    }

    @Then("the user should be redirected to the home page")
    public void redirected_home() {
        ensureHomePageInitialized();
        homePage.assertOnHomePage();
    }

    // helper
    private void ensureHomePageInitialized() {
        if (Hooks.driver == null) throw new IllegalStateException("Hooks.driver is null");
        if (homePage == null) {
            // HomePage(WebDriver) constructor must exist (we discussed adding the single-arg ctor)
            homePage = new HomePage(Hooks.driver);
        }
    }
}
