package test_cases.ereny.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Products_Pages {

    WebDriver driver;

    public Products_Pages(WebDriver driver) {
        this.driver = driver;
    }

    By Products_Button = By.cssSelector("a[href='/products']");
    By ViewProduct_Button = By.cssSelector("a[href='/product_details/1']");
    By DisplayProductInformation = By.xpath("//div[@class='product-information']");

    public void Navigate(){
        driver.get("https://automationexercise.com/");
    }
    public void ClickOnProducts_Button(){
        driver.findElement(Products_Button).click();
    }
    public void ClickOnViewProduct_Button(){
    }
    public void AssertProductInformation(){
    }
    public void ProductsPages(){

        driver.findElement(ViewProduct_Button).click();
        Assert.assertTrue(driver.findElement(DisplayProductInformation).isDisplayed());
    }
    public void AssertURL(){
        Assert.assertEquals(driver.getCurrentUrl() , "https://automationexercise.com/products");
    }
}
