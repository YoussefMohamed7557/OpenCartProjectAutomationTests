package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    By loginLink_Btn = By.cssSelector("a[href='/login']");
    By featuredItemsSection = By.cssSelector(".features_items");
    By homePageLogo = By.cssSelector("img[alt='Website for automation practice']");
    By signUpLink_Btn = By.cssSelector("a[href='/login']");
    By loggedInAsUser = By.cssSelector("[class=\"fa fa-user\"]");
    By logoutLink_Btn = By.cssSelector("a[href='/logout']");
    By clickDeleteAccountButton = By.cssSelector("a[href='/delete_account']");
    By accountDeletedMessage = By.xpath("//h2[@data-qa='account-deleted']");
    By contiuneButton = By.xpath("//a[@data-qa='continue-button']");


    //ACTION
    public void navigateToHomePage() {
        driver.navigate().to("https://automationexercise.com/");
    }

    public void clickLoginLink() {
        driver.findElement(loginLink_Btn).click();
    }

    public void clickLogoutButton() {
        driver.findElement(logoutLink_Btn).click();
    }

    public void clickDeleteAccountButton() {
        driver.findElement(clickDeleteAccountButton).click();
    }

    public void clickContinueButton() {
        driver.findElement(contiuneButton).click();
    }

    //ASSERTION

    public void assertURL() {
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://automationexercise.com/",
                "Home page URL is incorrect"
        );
    }

    public void assertTitle() {
        Assert.assertEquals(
                driver.getTitle(),
                "Automation Exercise",
                "Home page title is incorrect"
        );
    }

    public void assertLoginLinkVisible() {
        Assert.assertTrue(
                driver.findElement(loginLink_Btn).isDisplayed(),
                "Login link is NOT visible"
        );
    }

    public void assertFeaturedItemsVisible() {
        Assert.assertTrue(
                driver.findElement(featuredItemsSection).isDisplayed(),
                "Featured items section is NOT visible"
        );
    }

    public void assertHomePageLogoVisible() {
        Assert.assertTrue(
                driver.findElement(homePageLogo).isDisplayed(),
                "Home page logo is NOT visible"
        );
    }

    public void assertSignUpLinkVisible() {
        Assert.assertTrue(
                driver.findElement(signUpLink_Btn).isDisplayed(),
                "Sign Up link is NOT visible"
        );
    }

    public void assertLoggedInAsUserVisible() {
        Assert.assertTrue(
                driver.findElement(loggedInAsUser).isDisplayed(),
                "\"Logged in as user\" is NOT visible"
        );
    }

    public void assertLogoutLinkVisible() {
        Assert.assertTrue(
                driver.findElement(logoutLink_Btn).isDisplayed(),
                "Logout link is NOT visible"
        );
    }

    public void assertHomePageLoadedAfterLogout() {
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://automationexercise.com/",
                "Home page did NOT load after logout"
        );
    }

    public void assertAccountDeletedMessageVisible() {
        Assert.assertTrue(
                driver.findElement(accountDeletedMessage).isDisplayed(),
                "Account Deleted message is NOT visible"
        );
    }
}
