package test_cases.abanoub;

import org.testng.annotations.Test;
import test_cases.abanoub.Pages.HomePage;

import java.time.Duration;

// ---------- Allure Annotations ----------
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Description;

@Epic("Navigation & Smoke Tests")
@Feature("Home Page & Main Navigation")
public class HomeTests extends TestBase {

    @Story("Home page basic smoke check")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the home page loads successfully with correct URL, title, login link, featured items and logo.")
    @Test
    public void TC01_verifyHomePageLoadsSuccessfully() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        homePage.navigateToHomePage();

        homePage.assertOnHomePage();
        homePage.assertLoginLinkVisible();
        homePage.assertFeaturedItemsVisible();
        homePage.assertHomeLogoVisible();
    }

    @Story("Navigate from home page to Contact Us")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that the user can navigate from the home page to the Contact Us page and see the 'Get In Touch' header.")
    @Test
    public void TC24_navigateToContactUsPage() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        homePage.navigateToHomePage();
        homePage.clickContactUsLink();

        homePage.assertOnContactUsPage();
        homePage.assertContactUsHeaderVisible();
    }

    @Story("Navigate from home page to Products page")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the user can navigate from home page to the Products page and view the All Products header.")
    @Test
    public void TC25_navigateToProductsPage() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        homePage.navigateToHomePage();
        homePage.clickProductsLink();

        homePage.assertOnProductsPage();
        homePage.assertAllProductsHeaderVisible();
    }

    @Story("Return to home page via logo click")
    @Severity(SeverityLevel.MINOR)
    @Description("Ensure that clicking on the home logo from an inner page (Products) returns the user to the home page.")
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
