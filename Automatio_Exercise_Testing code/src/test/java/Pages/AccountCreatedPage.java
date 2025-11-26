package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AccountCreatedPage {

    private final WebDriver driver;

    private final By accountCreatedMessage = By.xpath("//h2[@data-qa='account-created']");
    private final By continueButton = By.xpath("//a[@data-qa='continue-button']");

    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    public void assertAccountCreatedMsgVisible() {
        Assert.assertTrue(
                driver.findElement(accountCreatedMessage).isDisplayed(),
                "Account Created message is NOT visible"
        );
    }
}
