package test_cases.youssef.pages;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test_cases.youssef.test_utilities.JSONReader;
import test_cases.youssef.test_utilities.SeleniumHelper;

import java.io.IOException;
import java.time.Duration;


public class PaymentPage {

    @FindBy(css = "input[data-qa='name-on-card']")
    private WebElement nameOnCardInput;

    @FindBy(css = "input[data-qa='card-number']")
    private WebElement cardNumberInput;

    @FindBy(css = "input[data-qa='cvc']")
    private WebElement cvcInput;

    @FindBy(css = "input[data-qa='expiry-month']")
    private WebElement expirationMonthInput;

    @FindBy(css = "input[data-qa='expiry-year']")
    private WebElement expirationYearInput;

    @FindBy(css = "button[data-qa='pay-button']")
    private WebElement payAndConfirmOrderButton;

    @FindBy(xpath = "//div[contains(@id, 'success_message')]/div") //correct xpath but unable to locate an element
    private WebElement alertSuccess;

    @FindBy(css = "div[class='col-sm-9 col-sm-offset-1'] p")
    private WebElement successMessage;

    @FindBy(css = "a[class='btn btn-default check_out']")
    private WebElement downloadInvoiceButton;

    @FindBy(css = "a[data-qa='continue-button']")
    private WebElement continueButton;

    private WebDriver driver;

    public PaymentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public PaymentPage fillPaymentDetails() throws IOException, ParseException {
        nameOnCardInput.sendKeys(JSONReader.paymentDetails("nameOnCard"));
        cardNumberInput.sendKeys(JSONReader.paymentDetails("cardNumber"));
        cvcInput.sendKeys(JSONReader.paymentDetails("cvc"));
        expirationMonthInput.sendKeys(JSONReader.paymentDetails("expirationMonth"));
        expirationYearInput.sendKeys(JSONReader.paymentDetails("expirationYear"));
        payAndConfirmOrderButton.click();
        return this;
    }

    public WebElement getSuccessMessage() {
        return successMessage;
    }
//
//    public PaymentPage downloadInvoiceButtonClick() {
//        downloadInvoiceButton.click();
//        return this;
//    }
public PaymentPage downloadInvoiceButtonClick() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    // 1. Wait for the button to be clickable
    wait.until(ExpectedConditions.elementToBeClickable(downloadInvoiceButton));

    // 2. Scroll the button into view (avoid element covered by iframe)
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", downloadInvoiceButton);

    // 3. Try a normal click
    try {
        downloadInvoiceButton.click();
    } catch (ElementClickInterceptedException e) {
        // 4. Fallback: force click using JS if something still covers the button
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", downloadInvoiceButton);
    }

    return this;
}

    public HomePage continueButtonClick() {
        SeleniumHelper.waitForElementToBeClickable(driver, continueButton);
        continueButton.click();
        return new HomePage(driver);
    }
}
