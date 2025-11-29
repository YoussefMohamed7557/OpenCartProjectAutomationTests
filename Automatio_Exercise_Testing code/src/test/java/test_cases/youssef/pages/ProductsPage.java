package test_cases.youssef.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test_cases.youssef.test_utilities.SeleniumHelper;

import java.time.Duration;
import java.util.List;

public class ProductsPage extends TestBasic {
    @FindBy(css = ".title.text-center")
    private WebElement titleTextCenter;

    @FindBy(css = "a[href='/product_details/1']")
    private WebElement viewProductOfFirstProductButton;

    @FindBy(id = "search_product")
    private WebElement searchProductInput;

    @FindBy(id = "submit_search")
    private WebElement submitSearchInput;

    @FindBy(xpath = "//div[contains(@class, 'productinfo text-center')]//p")
    private List<WebElement> searchResultsNames;

    @FindBy(css = "a[data-product-id='1']")
    private WebElement addToCartButton1;

    @FindBy(css = "a[data-product-id='2']")
    private WebElement addToCartButton2;

    @FindBy(css = "button[data-dismiss='modal']")
    private WebElement continueShoppingButton;

    @FindBy(css = "a[href='/view_cart'] u")
    private WebElement viewCartButton;


    private WebDriver driver;

    public ProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getTitleTextCenter() {
        return titleTextCenter;
    }

    public ProductDetailPage viewProductOfFirstProductButtonClick() {
        WebElement product = driver.findElement(By.cssSelector("a[href='/product_details/1']"));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", product);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(product));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", product);
        return new ProductDetailPage(driver);
    }

    public CartPage addProductsToCart() {
        SeleniumHelper.waitForElementToBeClickable(driver, addToCartButton1);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement addToCartBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("a[data-product-id='1']")
                )
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", addToCartBtn);

        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));

        addToCartBtn.click();
       // addToCartButton1.click();
        SeleniumHelper.waitForElementToBeClickable(driver, continueShoppingButton);
        continueShoppingButton.click();
        SeleniumHelper.waitForElementToBeClickable(driver, addToCartButton2);
        addToCartButton2.click();
        SeleniumHelper.waitForElementToBeClickable(driver, viewCartButton);
        viewCartButton.click();
        return new CartPage(driver);
    }


}
