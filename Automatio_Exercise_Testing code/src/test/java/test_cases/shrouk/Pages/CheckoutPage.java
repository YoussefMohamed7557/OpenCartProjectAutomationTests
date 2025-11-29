package test_cases.shrouk.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    WebDriver driver ;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    By placeOrder = By.xpath("//a[@href=\"/payment\"]");


    public void clickPlaceOrder()
    {
        driver.findElement(placeOrder).click();
    }

}
