package test_cases.abanoub;

import Pages.HomePage;
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
}
