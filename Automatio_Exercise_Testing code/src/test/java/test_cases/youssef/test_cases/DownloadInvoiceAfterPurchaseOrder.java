package test_cases.youssef.test_cases;

import io.qameta.allure.*;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import test_cases.youssef.pages.*;
import test_cases.youssef.test_utilities.Utils;
import test_cases.youssef.test_utilities.VerifyDownload;

import java.io.IOException;
import java.util.List;

import static test_cases.youssef.test_cases.VerifyAddressDetailsInCheckOutPageYoussef.verifyAddressDetails;
import static test_cases.youssef.test_cases.VerifyAddressDetailsInCheckOutPageYoussef.verifyThatAccountDeletedIsVisibleAndClickContinueButton;

@Epic("Regression Tests")
@Feature("Invoice")
public class DownloadInvoiceAfterPurchaseOrder extends TestBasic {

    String name = "name" + Utils.generateCurrentDateAndTime();
    String email = "email" + Utils.generateCurrentDateAndTime() + "@o2.pl";

    @Test(description = "Test Case 24: Download Invoice after purchase order")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Download Invoice after purchase order")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Add products to cart
            5. Click 'Cart' button
            6. Verify that cart page is displayed
            7. Click Proceed To Checkout
            8. Click 'Register / Login' button
            9. Fill all details in Signup and create account
            10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
            11. Verify ' Logged in as username' at top
            12. Click 'Cart' button
            13. Click 'Proceed To Checkout' button
            14. Verify Address Details and Review Your Order
            15. Enter description in comment text area and click 'Place Order'
            16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
            17. Click 'Pay and Confirm Order' button
            18. Verify success message 'Congratulations! Your order has been confirmed!'
            19. Click 'Download Invoice' button and verify invoice is downloaded successfully
            20. Click 'Continue' button
            21. Click 'Delete Account' button
            22. Verify 'ACCOUNT DELETED!' and click 'Continue' button""")
    public void downloadInvoiceAfterPurchaseOrder() throws IOException, ParseException {
        verifyThatHomePageIsVisibleSuccessfully();
        verifyThatCartPageIsDisplayed();
        verifyAccountCreatedAndClickContinueButton(name, email);
        verifyLoggedInAsUsernameAtTop(name);
        verifyAddressDetailsAndReviewYourOrder();
        verifySuccessMessageCongratulationsYourOrderHasBeenConfirmed();
        clickDownloadInvoiceButtonAndVerifyInvoiceIsDownloadedSuccessfully();
        new PaymentPage(getDriver()).continueButtonClick();
        verifyThatAccountDeletedIsVisibleAndClickContinueButton();
    }
    @Step("Verify that home page is visible successfully")
    public static void verifyThatHomePageIsVisibleSuccessfully() {
        boolean homePageVisible = new HomePage(getDriver())
                .homePageIsVisible()
                .isDisplayed();
        Assert.assertTrue(homePageVisible, "Verify that home page is visible successfully");
    }
    @Step("Verify that cart page is displayed")
    public static void verifyThatCartPageIsDisplayed() {
        String shoppingCartText = new ProductsPage(getDriver())
                .addProductsToCart()
                .getShoppingCart()
                .getText();
        Assert.assertEquals(shoppingCartText, "Shopping Cart", "Verify that cart page is displayed");
    }
    @Step("Verify 'ACCOUNT CREATED!' and click 'Continue' button")
    public static void verifyAccountCreatedAndClickContinueButton(String name, String email) throws IOException, ParseException {
        String accountCreatedText = new HomePage(getDriver())
                .signupLoginClick()
                .fillCorrectSignup(name, email)
                .fillAccountDetails()
                .getAccountCreated()
                .getText();
        Assert.assertEquals(accountCreatedText, "ACCOUNT CREATED!", "Verify 'ACCOUNT CREATED!'");
        new AccountCreatedPage(getDriver()).continueButtonClick();
    }
    @Step("Verify ' Logged in as username' at top")
    public static void verifyLoggedInAsUsernameAtTop(String name) {
        String username = new LoggedHomePage(getDriver())
                .getUsername()
                .getText();
        Assert.assertEquals(username, name, "Verify ' Logged in as username' at top");
    }
    @Step("Verify Address Details and Review Your Order")
    public static void verifyAddressDetailsAndReviewYourOrder() throws IOException, ParseException {
        verifyAddressDetails();

        List<String> productNames = new CartPage(getDriver()).getProductsNames();
        List<String> prices = new CartPage(getDriver()).getPrices();
        List<String> quantity = new CartPage(getDriver()).getQuantity();
        List<String> totalPrices = new CartPage(getDriver()).getTotalPrices();
        String totalAmount = new CheckoutPage(getDriver()).getTotalAmount().getText();

        for (int i = 0; i < 2; i++) {
            Assert.assertEquals(totalPrices.get(i), prices.get(i), "Verify Review Your Order1");
            Assert.assertEquals(quantity.get(i), "1", "Verify Review Your Order2");
        }

        Assert.assertEquals(productNames.get(0), "Blue Top", "Verify Review Your Order3");
        Assert.assertEquals(productNames.get(1), "Men Tshirt", "Verify Review Your Order4");
        Assert.assertEquals(totalAmount, "Rs. 900", "Verify Review Your Order5");
    }
    @Step("Verify success message 'Congratulations! Your order has been confirmed!'")
    public static void verifySuccessMessageCongratulationsYourOrderHasBeenConfirmed() throws IOException, ParseException {
        String alertSuccessText = new CheckoutPage(getDriver())
                .enterComment("Sed fringilla aliquet turpis, ut vestibulum orci vulputate sit amet.")
                .fillPaymentDetails()
                .getSuccessMessage()
                .getText();
        Assert.assertEquals(alertSuccessText, "Congratulations! Your order has been confirmed!", "Verify success message 'Congratulations! Your order has been confirmed!'");
    }
    @Step("Click 'Download Invoice' button and verify invoice is downloaded successfully")
    private void clickDownloadInvoiceButtonAndVerifyInvoiceIsDownloadedSuccessfully() throws IOException {
        new PaymentPage(getDriver()).downloadInvoiceButtonClick();
        boolean isFileDownloaded = VerifyDownload.isFileDownloaded("invoice", "txt", 5000);
        Assert.assertTrue(isFileDownloaded, "Verify invoice is downloaded successfully.");
    }
}
