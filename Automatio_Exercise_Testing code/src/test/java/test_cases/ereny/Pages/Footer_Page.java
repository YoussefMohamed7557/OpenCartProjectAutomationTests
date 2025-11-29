package test_cases.ereny.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Footer_Page {
    WebDriver driver;

    public Footer_Page(WebDriver driver) {
        this.driver = driver;
    }

    By Footer = By.xpath("//div[@class='footer-widget']");
    By Email_TextBox = By.id("susbscribe_email");
    By Subscribe_Button = By.id("subscribe");
    By VerifyDisplayText = By.xpath("//h2[contains(text(),'Subscription')]");
    By VerifySuccessMessage = By.xpath("//div[contains(@class,'alert-success')]");

    public void Navigate(){
        driver.get("https://automationexercise.com/");
    }
    public void AssertToHomePage(){
        Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/");
    }
    public void FooterPage(String Email){

        driver.findElement(Email_TextBox).sendKeys(Email);
    }
    public void ClickOnSubscribe_Button(){

        driver.findElement(Subscribe_Button).click();
    }
    public void AssertDisplayText(){
        Assert.assertTrue(driver.findElement(VerifyDisplayText).getText().contains("SUBSCRIPTION"));
    }
    public void AssertSuccessMessage(){
        Assert.assertTrue(driver.findElement(VerifySuccessMessage).getText().contains("You have been successfully subscribed!"));
    }

}
