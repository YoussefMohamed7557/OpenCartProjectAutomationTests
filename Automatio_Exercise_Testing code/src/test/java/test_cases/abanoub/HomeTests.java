package test_cases.abanoub;

import Pages.HomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTests extends TestBase {

    @Test
    public void TC01_verifyHomePageLoadsSuccessfully() {

        HomePage homePage = new HomePage(driver);

        homePage.navigateToHomePage();
        homePage.assertURL();
        homePage.assertTitle();
        homePage.assertLoginLinkVisible();
        homePage.assertFeaturedItemsVisible();
        homePage.assertHomePageLogoVisible();
        homePage.assertSignUpLinkVisible();
    }

    @Test
    public void TC24_navigateToContactUsPage() {

        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();

        homePage.clickContactUsLink();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/contact_us"),
                "Contact Us page URL is incorrect"
        );

        boolean getInTouchVisible =
                !driver.findElements(By.xpath("//h2[contains(text(),'Get In Touch')]")).isEmpty();

        Assert.assertTrue(
                getInTouchVisible,
                "'Get In Touch' heading is NOT visible on Contact Us page"
        );
    }

    @Test
    public void TC25_navigateToProductsPage() {

        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();

        homePage.clickProductsLink();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/products"),
                "Products page URL is incorrect"
        );

        boolean allProductsVisible =
                !driver.findElements(By.xpath("//h2[contains(text(),'All Products')]")).isEmpty();

        Assert.assertTrue(
                allProductsVisible,
                "'All Products' heading is NOT visible on Products page"
        );
    }

    @Test
    public void TC26_clickLogoFromInnerPageReturnsHome() {

        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();

        homePage.clickProductsLink();
        Assert.assertTrue(
                driver.getCurrentUrl().contains("/products"),
                "Navigation to Products page failed before clicking logo"
        );

        homePage.clickHomeLogo();

        homePage.assertURL();
        homePage.assertHomePageLogoVisible();
    }
}
