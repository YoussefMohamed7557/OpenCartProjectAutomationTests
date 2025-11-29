package test_cases.youssef.pages;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test_cases.youssef.test_utilities.JSONReader;

import java.io.IOException;

public class ProductDetailPage extends TestBasic {

    @FindBy(css = "a[href='#reviews']")
    private WebElement writeYourReview;

    @FindBy(id = "name")
    private WebElement yourNameInput;

    @FindBy(id = "email")
    private WebElement emailAddress;

    @FindBy(id = "review")
    private WebElement addReviewHere;

    @FindBy(id = "button-review")
    private WebElement submitButton;

    @FindBy(css = "div[class='alert-success alert'] span")
    private WebElement successMessage;

    private WebDriver driver;

    public ProductDetailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public WebElement getWriteYourReview() {
        return writeYourReview;
    }

    public ProductDetailPage fillReview() throws IOException, ParseException {
        yourNameInput.sendKeys(JSONReader.existingUser("name"));
        emailAddress.sendKeys(JSONReader.existingUser("email"));
        addReviewHere.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Sed viverra, elit quis interdum feugiat, mi urna aliquam est, at venenatis quam odio et nisl." +
                " In at massa sit amet dui hendrerit mattis ac sit amet erat.");
        submitButton.click();
        return this;
    }

    public WebElement getSuccessMessage() {
        return successMessage;
    }

}
