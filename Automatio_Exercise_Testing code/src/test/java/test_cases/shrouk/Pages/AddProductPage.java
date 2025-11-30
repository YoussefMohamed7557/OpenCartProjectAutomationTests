package test_cases.shrouk.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AddProductPage {
    WebDriver driver ;

    public AddProductPage (WebDriver driver) {
        this.driver = driver;
    }
    //locators
    By addproductone= By.xpath("//a[@data-product-id=\"1\"]");
    By continueshopping = By.xpath("//button[text()='Continue Shopping']");
    By addproducttWo = By.xpath("//a[@data-product-id=\"2\"]");
    By viewcart = By.cssSelector("a[href='/view_cart']");
    By proccedtocheckout= By.cssSelector("a.btn.btn-default.check_out");
    By deleteproduct=By.cssSelector("a.cart_quantity_delete[data-product-id='1']");

    //Actions
    public void navigateToProductPage()
    {
        driver.get("https://automationexercise.com/products");
    }
    public void AddToCart()
    {
        driver.findElement(addproductone).click();
        driver.findElement(continueshopping).click();
        driver.findElement(addproducttWo).click();
    }

    public void ViewCart()
    {
        driver.findElement(viewcart ).click();

    }
    public void DeleteProduct()
    {
        driver.findElement(deleteproduct).click();

    }

    //Assertion
public void AssertProccedtoCheckoutButtom()
{
    Assert.assertTrue(driver.findElement(proccedtocheckout).isDisplayed());

}



}
