package test_cases.ereny.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Searched_Products_Page {

    WebDriver driver;

    public Searched_Products_Page(WebDriver driver) {
        this.driver = driver;
    }

    By Products_Button = By.cssSelector("a[href='/products']");
    By Search_TextBox = By.id("search_product");
    By Submit_Button = By.id("submit_search");
    By DisplayFeatureItem = By.className("features_items");

    public void Navigate(){
        driver.get("https://automationexercise.com/");
    }
    public void ClickOnProducts_Button(){
        driver.findElement(Products_Button).click();
    }
    public void ClickOnSubmit_Button(){
        driver.findElement(Submit_Button).click();
    }
    public void AssertFeatureItem(){
        Assert.assertTrue(driver.findElement(DisplayFeatureItem).isDisplayed());
    }
    public void SearchedProductPage(String ProductName){

        driver.findElement(Search_TextBox).sendKeys(ProductName);
    }
}
