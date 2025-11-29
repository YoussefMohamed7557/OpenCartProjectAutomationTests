package test_cases.youssef.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test_cases.youssef.test_utilities.SeleniumHelper;

public class HomePage extends TestBasic {

    @FindBy(css = "div[class='item active'] img[alt='demo website for practice']")
    private WebElement girlImgResponsive;

    @FindBy(css = "a[href='/login']")
    private WebElement signupLoginButton;

    @FindBy(css = "a[href='/products']")
    private WebElement productsButton;
    @FindBy(css = "a[href='/view_cart']")
    private WebElement cartButton;

    @FindBy(css = "div[class='recommended_items'] h2")
    private WebElement recommendedItems;

    @FindBy(css = "div[id='recommended-item-carousel'] a[class='btn btn-default add-to-cart']")
    private WebElement blueTopAddToCartButton;

    @FindBy(css = "div[class='modal-content'] a[href='/view_cart']")
    private WebElement viewCartButton;

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement homePageIsVisible() {
        return girlImgResponsive;
    }

    public LoginSignupPage signupLoginClick() {
        signupLoginButton.click();
        return new LoginSignupPage(driver);
    }
    public ProductsPage productsButtonClick() {
        productsButton.click();
        return new ProductsPage(driver);
    }

    public CartPage cartButtonClick() {
        cartButton.click();
        return new CartPage(driver);
    }


    public WebElement getRecommendedItems() {
        return recommendedItems;
    }

    public HomePage blueTopAddToCartButtonClick() {
        SeleniumHelper.waitForElementToBeClickable(driver, blueTopAddToCartButton);
        blueTopAddToCartButton.click();
        return this;
    }

    public CartPage viewCartButtonClick() {
        SeleniumHelper.waitForElementToBeVisible(driver, viewCartButton);
        viewCartButton.click();
        return new CartPage(driver);
    }

}


