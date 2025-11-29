package test_cases.shrouk.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartPage {

    WebDriver driver ;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // LOCATORS
    By subscribe_email = By.id("susbscribe_email");
    By subscribe_submit = By.id("subscribe");
    By successSubscribe_msg = By.id("success-subscribe");
    By proccedtocheckout= By.cssSelector("a.btn.btn-default.check_out");
    By registerLogin = By.xpath("//u[normalize-space()='Register / Login']");

    // ACTIONS
    public void navigateToCartPage()
    {
        driver.get("https://automationexercise.com/view_cart");
    }

    public void enterSubscribeEmail(String email)
    {
        driver.findElement(subscribe_email).sendKeys(email);
        driver.findElement(subscribe_submit).click();
    }
    public void proceedToCheckoutButton()
    {
        driver.findElement(proccedtocheckout).click();
    }
    public void Signupandloginbuttom()
    {
        driver.findElement(registerLogin).click();
    }
    // ASSERTIONS
    public void assertSuccessSubscription()
    {
        Assert.assertTrue(driver.findElement(successSubscribe_msg).isDisplayed());
    }

    public void assertInvalidEmailErrorMsg()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String msg = (String) js.executeScript("return arguments[0].validationMessage;", driver.findElement(subscribe_email));
        Assert.assertEquals(msg , "Please include an '@' in the email address. 'shrouk.com' is missing an '@'.");
    }
    public void assertemptyEmailErrorMsg(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
    String msg = (String) js.executeScript("return arguments[0].validationMessage;", driver.findElement(subscribe_email));
        Assert.assertEquals(msg, "Please fill out this field.");}
}
