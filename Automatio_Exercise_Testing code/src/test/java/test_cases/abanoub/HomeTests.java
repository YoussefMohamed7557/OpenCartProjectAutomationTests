package test_cases.abanoub;

import org.testng.annotations.Test;
import Pages.HomePage;
import Pages.LoginSignupPage;

import java.time.Duration;

public class HomeTests extends TestBase {

    @Test
    public void TC01_verifyHomePageLoadsSuccessfully() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        homePage.navigateToHomePage();

        homePage.assertOnHomePage();
        homePage.assertLoginLinkVisible();
        homePage.assertFeaturedItemsVisible();
        homePage.assertHomeLogoVisible();
    }

    @Test
    public void TC24_navigateToContactUsPage() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        homePage.navigateToHomePage();
        homePage.clickContactUsLink();

        homePage.assertOnContactUsPage();
        homePage.assertContactUsHeaderVisible();
    }

    @Test
    public void TC25_navigateToProductsPage() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        homePage.navigateToHomePage();
        homePage.clickProductsLink();

        homePage.assertOnProductsPage();
        homePage.assertAllProductsHeaderVisible();
    }

    @Test
    public void TC26_clickLogoFromInnerPageReturnsHome() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        homePage.navigateToHomePage();
        homePage.clickProductsLink();

        homePage.assertOnProductsPage();

        homePage.clickHomeLogo();
        homePage.assertOnHomePage();
        homePage.assertHomeLogoVisible();
    }
}
