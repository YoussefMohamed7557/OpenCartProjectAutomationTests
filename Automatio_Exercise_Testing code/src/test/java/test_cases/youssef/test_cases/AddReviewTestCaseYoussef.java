package test_cases.youssef.test_cases;
import io.qameta.allure.*;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import test_cases.youssef.pages.HomePage;
import test_cases.youssef.pages.ProductDetailPage;
import test_cases.youssef.pages.ProductsPage;
import test_cases.youssef.pages.TestBasic;

import java.io.IOException;


@Epic("Regression Tests")
@Feature("Products")
public class AddReviewTestCaseYoussef extends TestBasic {

    @Test(description = "Test Case 21: Add review on product")
    @Severity(SeverityLevel.MINOR)
    @Story("Add review on product")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Click on 'Products' button
            4. Verify user is navigated to ALL PRODUCTS page successfully
            5. Click on 'View Product' button
            6. Verify 'Write Your Review' is visible
            7. Enter name, email and review
            8. Click 'Submit' button
            9. Verify success message 'Thank you for your review.'""")
    public void addReviewOnProduct() throws IOException, ParseException {
        verifyUserIsNavigatedToAllProductsPageSuccessfully();
        verifyWriteYourReviewIsVisible();
        verifySuccessMessageThankYouForYourReview();
    }

    @Step("Verify user is navigated to ALL PRODUCTS page successfully")
    public static void verifyUserIsNavigatedToAllProductsPageSuccessfully() {
        String allProductsText = new HomePage(getDriver())
                .productsButtonClick()
                .getTitleTextCenter()
                .getText();
        Assert.assertEquals(allProductsText, "ALL PRODUCTS", "Verify user is navigated to ALL PRODUCTS page successfully");
    }
    @Step("Verify 'Write Your Review' is visible")
    private void verifyWriteYourReviewIsVisible() {
        String writeYourReviewText = new ProductsPage(getDriver())
                .viewProductOfFirstProductButtonClick()
                .getWriteYourReview()
                .getText();
        Assert.assertEquals(writeYourReviewText, "WRITE YOUR REVIEW", "Verify 'Write Your Review' is visible");
    }

    @Step("Verify success message 'Thank you for your review.'")
    private void verifySuccessMessageThankYouForYourReview() throws IOException, ParseException {
        String successMessageText = new ProductDetailPage(getDriver())
                .fillReview()
                .getSuccessMessage()
                .getText();
        Assert.assertEquals(successMessageText, "Thank you for your review.", "Verify success message 'Thank you for your review.'");
    }
}
