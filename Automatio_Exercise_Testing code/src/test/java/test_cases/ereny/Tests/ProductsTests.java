package test_cases.ereny.Tests;

import test_cases.ereny.Pages.Products_Pages;
import jdk.jfr.Description;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProductsTests extends TestBase{


    Products_Pages productsPages;


    @Description("Test Case 8: Verify All Products and product detail page")
    @Test
    public void TC05_verifyNavigateToProductsPage() {
        productsPages = new Products_Pages(driver);
        productsPages.Navigate();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        productsPages.ClickOnProducts_Button();
        productsPages.AssertURL();
        productsPages.AssertProductInformation();
    }
    @Test
    public void TC06_verifyViewProductButton() {
        productsPages = new Products_Pages(driver);
        productsPages.Navigate();
        productsPages.ClickOnProducts_Button();
        productsPages.ClickOnViewProduct_Button();
    }

    @Test
    public void TC07_DisplayProducts(){
        productsPages = new Products_Pages(driver);
        productsPages.Navigate();
        productsPages.ClickOnProducts_Button();
        productsPages.ClickOnViewProduct_Button();
        productsPages.AssertProductInformation();
    }
}





