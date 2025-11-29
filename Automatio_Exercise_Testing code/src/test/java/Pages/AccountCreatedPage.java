package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.testng.Assert;

public class AccountCreatedPage extends Pages.BasePage {

    // ---------------------------
    // Locators
    // ---------------------------
    private final By accountCreatedMessage = By.xpath("//h2[@data-qa='account-created']");
    private final By continueButton = By.xpath("//a[@data-qa='continue-button']");

    // ---------------------------
    // Constructor
    // ---------------------------
    public AccountCreatedPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

    // ---------------------------
    // Actions
    // ---------------------------
    public void clickContinueButton() { waitAndClick(continueButton); }

    // ---------------------------
    // State/Getters
    // ---------------------------
    public boolean isAccountCreatedMessageVisible() { return isVisible(accountCreatedMessage); }

    private boolean isAccountCreatedMarkerInSource() {
        return getPageSource().contains("account-created");
    }

    // ---------------------------
    // Assertions
    // ---------------------------
    public void assertAccountCreatedMessageVisible() {
        Assert.assertTrue(isAccountCreatedMessageVisible(), "Account created message should be visible");
    }

    public void assertAccountNotCreatedInSource() {
        Assert.assertFalse(isAccountCreatedMarkerInSource(), "Account should NOT be created when password is missing");
    }
}
