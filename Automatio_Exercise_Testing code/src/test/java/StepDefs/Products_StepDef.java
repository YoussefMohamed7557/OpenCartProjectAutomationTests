package StepDefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.testng.Assert;
import test_cases.ereny.Pages.Products_Pages;

public class Products_StepDef {

    private Products_Pages productsPage;

    @Given("the user is on the home page for products")
    public void theUserIsOnTheHomePageForProducts() {
        productsPage = new Products_Pages(Hooks.getDriver());
        productsPage.Navigate();
    }

    @When("the user clicks on the Products link")
    public void theUserClicksOnTheProductsLink() {
        productsPage.ClickOnProducts_Button();
    }

    @Then("the Products page URL should be correct")
    public void theProductsPageURLShouldBeCorrect() {
        productsPage.AssertURL();
    }

    @Given("the user is on the Products page")
    public void theUserIsOnTheProductsPage() {
        productsPage = new Products_Pages(Hooks.getDriver());
        productsPage.Navigate();
        productsPage.ClickOnProducts_Button();
        productsPage.AssertURL();
    }

    @When("the user clicks on the View Product button for the first product")
    public void theUserClicksOnTheViewProductButtonForTheFirstProduct() {
        WebDriver driver = Hooks.getDriver();

        WebElement firstProductLink =
                driver.findElement(By.cssSelector("a[href='/product_details/1']"));

        JavascriptExecutor js = (JavascriptExecutor) driver;


        js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -150);", firstProductLink);


        js.executeScript("arguments[0].click();", firstProductLink);
    }

    @Then("the product information section should be displayed")
    public void theProductInformationSectionShouldBeDisplayed() {
        WebDriver driver = Hooks.getDriver();

        WebElement productInfo =
                driver.findElement(By.xpath("//div[@class='product-information']"));

        Assert.assertTrue(productInfo.isDisplayed(),
                "Product information section should be visible");
    }
}
