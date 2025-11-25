package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AccountCreatedPage {
    WebDriver driver;

    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
    }
    //Locators
    By accountCreatedMessage = By.xpath("//h2[@data-qa='account-created']");
    By continueButton = By.xpath("//a[@data-qa='continue-button']");

    //ACTION
    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    //ASSERTION
    public void assertAccountCreatedMsgVisible() {
        Assert.assertTrue(driver.findElement(accountCreatedMessage).isDisplayed(), "Account Created message is NOT visible");
    }
}
