package test_cases.youssef.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoggedHomePage {

    @FindBy(xpath = "//*[@id='header']/div/div/div/div[2]/div/ul/li[10]/a/b")
    private WebElement username;

    @FindBy(xpath = "//header/div/div/div/div[2]/div/ul/li[5]/a")
    private WebElement deleteAccountButton;


    private WebDriver driver;

    public LoggedHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getUsername() {
        return username;
    }

    public AccountDeletedPage deleteAccountButtonClick() {
        deleteAccountButton.click();
        return new AccountDeletedPage(driver);
    }

}
