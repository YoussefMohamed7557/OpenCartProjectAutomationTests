package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import utils.Config;
import org.testng.Assert;

public class HomePage extends BasePage {

    // ---------------------------
    // Locators
    // ---------------------------
    private final By loginLink = By.cssSelector("a[href='/login']");
    private final By featuredItemsSection = By.cssSelector(".features_items");
    private final By homePageLogo = By.cssSelector("img[alt='Website for automation practice']");
    private final By signUpLink = By.cssSelector("a[href='/login']");
    private final By loggedInAsUserText = By.xpath("//a[contains(text(),'Logged in as')]");
    private final By logoutLink = By.cssSelector("a[href='/logout']");
    private final By deleteAccountLink = By.cssSelector("a[href='/delete_account']");
    private final By accountDeletedMessage = By.xpath("//h2[@data-qa='account-deleted']");
    private final By continueButton = By.xpath("//a[@data-qa='continue-button']");
    private final By contactUsLink = By.cssSelector("a[href='/contact_us']");
    private final By productsLink = By.cssSelector("a[href='/products']");
    private final By contactUsHeader = By.xpath("//*[contains(text(),'Get In Touch')]");
    private final By allProductsHeader = By.cssSelector(".features_items h2.title.text-center");

    // ---------------------------
    // Constructor
    // ---------------------------
    public HomePage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }
    public HomePage(WebDriver driver) {
        this(driver, Duration.ofSeconds(Config.getTimeoutSeconds() > 0 ? Config.getTimeoutSeconds() : 10));
    }

    // ---------------------------
    // Actions
    // ---------------------------
    public void navigateToHomePage() {
        driver.get(Config.getBaseUrl());
        waitForVisible(featuredItemsSection);
    }

    public void clickLoginLink() { waitAndClick(loginLink); }
    public void clickLogoutButton() { waitAndClick(logoutLink); }
    public void clickDeleteAccountButton() { waitAndClick(deleteAccountLink); }
    public void clickContinueButton() { waitAndClick(continueButton); }
    public void clickContactUsLink() { waitAndClick(contactUsLink); }
    public void clickProductsLink() { waitAndClick(productsLink); }
    public void clickHomeLogo() { waitAndClick(homePageLogo); }

    // ---------------------------
    // State/Getters (helper)
    // ---------------------------
    public String getCurrentUrl() { return driver.getCurrentUrl(); }
    public String getTitle() { return driver.getTitle(); }
    public boolean isLoginLinkVisible() { return isVisible(loginLink); }
    public boolean isSignUpLinkVisible() { return isVisible(signUpLink); }
    public boolean isFeaturedItemsVisible() { return isVisible(featuredItemsSection); }
    public boolean isHomePageLogoVisible() { return isVisible(homePageLogo); }
    public boolean isLoggedInAsUserVisible() { return isVisible(loggedInAsUserText); }
    public String getLoggedInAsUserText() { return safeGetText(loggedInAsUserText); }
    public boolean isLogoutLinkVisible() { return isVisible(logoutLink); }
    public boolean isAccountDeletedMessageVisible() { return isVisible(accountDeletedMessage); }

    // ---------------------------
    // Assertions
    // ---------------------------
    private String normalizeUrl(String url) {
        if (url == null) return "";
        return url.trim().replaceAll("/+$", "").toLowerCase();
    }

    public void assertOnHomePage() {
        String current = getCurrentUrl();
        String expected = Config.getBaseUrl();
        Assert.assertEquals(normalizeUrl(current), normalizeUrl(expected),
                "Expected to be on Home page, but URL was: " + current);
        Assert.assertEquals(getTitle(), "Automation Exercise",
                "Home page title is incorrect");
    }

    public void assertOnContactUsPage() {
        String current = getCurrentUrl();
        Assert.assertTrue(current.contains("/contact_us"),
                "Contact Us URL incorrect. Current URL: " + current);
    }

    public void assertOnProductsPage() {
        String current = getCurrentUrl();
        Assert.assertTrue(current.contains("/products"),
                "Products page URL is incorrect. Current URL: " + current);
    }

    public void assertUserLoggedIn() {
        Assert.assertTrue(isLoggedInAsUserVisible(), "User should be logged in");
    }

    public void assertUserLoggedOut() {
        Assert.assertFalse(isLoggedInAsUserVisible(), "User should NOT be logged in");
    }

    public void assertLogoutVisible() {
        Assert.assertTrue(isLogoutLinkVisible(), "Logout link should be visible");
    }

    public void assertLoginLinkVisible() {
        Assert.assertTrue(isLoginLinkVisible(), "Login link should be visible");
    }

    public void assertFeaturedItemsVisible() {
        Assert.assertTrue(isFeaturedItemsVisible(), "Featured items should be visible");
    }

    public void assertHomeLogoVisible() {
        // improved assertion with contextual message
        try {
            Assert.assertTrue(isHomePageLogoVisible(), "Home logo should be visible. Current URL: " + getCurrentUrl());
        } catch (AssertionError e) {
            throw new AssertionError("Home logo not visible. URL: " + getCurrentUrl() + " Title: " + getTitle(), e);
        }
    }

    public void assertAccountDeletedMessageVisible() {
        Assert.assertTrue(isAccountDeletedMessageVisible(), "Account deleted message should be visible");
    }

    public void assertContactUsHeaderVisible() {
        Assert.assertTrue(isVisible(contactUsHeader), "'Get In Touch' header should be visible on Contact Us page");
    }

    public void assertAllProductsHeaderVisible() {
        Assert.assertTrue(isVisible(allProductsHeader), "'All Products' header should be visible on Products page");
    }

    public void assertLoggedInUsernameSanitized() {
        String loggedInText = getLoggedInAsUserText();
        boolean containsDangerousChars = loggedInText.contains("@") || loggedInText.contains("#") || loggedInText.contains("!");
        Assert.assertFalse(containsDangerousChars, "Logged in username must be sanitized, found: " + loggedInText);
    }
}
