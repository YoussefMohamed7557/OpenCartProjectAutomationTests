package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {

    private final WebDriver driver;

    // Locators
    private final By signupLoginLink = By.cssSelector("a[href='/login']");
    private final By featuredItemsSection = By.cssSelector(".features_items");
    private final By homePageLogo = By.cssSelector("img[alt='Website for automation practice']");
    private final By loggedInAsUserIcon = By.cssSelector(".fa.fa-user");
    private final By logoutLink = By.cssSelector("a[href='/logout']");
    private final By deleteAccountLink = By.cssSelector("a[href='/delete_account']");
    private final By accountDeletedMessage = By.xpath("//h2[@data-qa='account-deleted']");
    private final By continueButton = By.xpath("//a[@data-qa='continue-button']");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // ACTIONS

    public void navigateToHomePage() {
        driver.navigate().to("https://automationexercise.com/");
    }

    public void clickLoginLink() {

        driver.findElement(signupLoginLink).click();
    }

    public void clickLogoutButton() {
        driver.findElement(logoutLink).click();
    }

    public void clickDeleteAccountButton() {
        driver.findElement(deleteAccountLink).click();
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    // ASSERTIONS

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
                driver.findElement(signupLoginLink).isDisplayed(),
                "'Signup / Login' link is NOT visible"
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
                driver.findElement(signupLoginLink).isDisplayed(),
                "Sign Up link is NOT visible"
        );
    }

    public void assertLoggedInAsUserVisible() {
        Assert.assertTrue(
                driver.findElement(loggedInAsUserIcon).isDisplayed(),
                "\"Logged in as user\" is NOT visible"
        );
    }

    public void assertLogoutLinkVisible() {
        Assert.assertTrue(
                driver.findElement(logoutLink).isDisplayed(),
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

    public void assertLoginPageLoadedAfterLogout() {
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://automationexercise.com/login",
                "Login page did NOT load after logout"
        );
    }

    public void assertHomePageLoadedAfterAccountDeletion() {
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://automationexercise.com/",
                "Home page did NOT load after account deletion"
        );
    }
    public void assertUserNotLoggedIn() {
        boolean loggedInIconExists = driver.findElements(By.cssSelector(".fa.fa-user")).size() > 0;
        Assert.assertFalse(loggedInIconExists, "User appears as logged in on initial home load");
    }
    public boolean isLoggedInAsUserVisible() {
        return driver.findElements(loggedInAsUserIcon).size() > 0;
    }


}
