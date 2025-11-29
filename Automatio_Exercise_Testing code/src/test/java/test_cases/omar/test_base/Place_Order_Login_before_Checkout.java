package test_cases.omar.test_base;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import test_cases.omar.pages.*;
import test_cases.omar.utils.JSONReader;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;


public class Place_Order_Login_before_Checkout extends TestBasic {
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click 'Signup / Login' button
            5. Fill email, password and click 'Login' button
            6. Verify 'Logged in as username' at top
            7. Add products to cart
            8. Click 'Cart' button
            9. Verify that cart page is displayed
            10. Click Proceed To Checkout
            11. Verify Address Details and Review Your Order
            12. Enter description in comment text area and click 'Place Order'
            13. Enter payment details: Name on Card, Card Number, CVC, Expiration date
            14. Click 'Pay and Confirm Order' button
            15. Verify success message 'Congratulations! Your order has been confirmed!'""")


    @Test(description = "Test Case 16: Place Order: Login before Checkout")

    public void placeOrderLoginBeforeCheckout() throws IOException, ParseException {


        verifyThatHomePageIsVisibleSuccessfully();

        new HomePage(getDriver())
                .signupLoginClick()
                .fillCorrectLogin(JSONReader.existingUser("email"), JSONReader.existingUser("password"));
        verifyLoggedInAsUsernameAtTop();
        verifyThatCartPageIsDisplayed();
        new CartPage(getDriver()).proceedToCheckoutButtonClick();
        verifyAddressDetailsAndReviewYourOrder();
       verifySuccessMessageCongratulationsYourOrderHasBeenConfirmed();
    }
    @Step("Verify that cart page is displayed")
    public static void verifyThatCartPageIsDisplayed() {
        String shoppingCartText = new ProductsPage(getDriver())
                .addProductsToCart()
                .getShoppingCart()
                .getText();
        Assert.assertEquals(shoppingCartText, "Shopping Cart", "Verify that cart page is displayed");
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

    public static void verifyAddressDetails() throws IOException, ParseException {
        List<String> addressDelivery = new HomePage(getDriver())
                .cartButtonClick()
                .proceedToCheckoutLoggedButtonClick()
                .getAddressDelivery();
        List<String> addressInvoice = new CheckoutPage(getDriver()).getAddressInvoice();

        Assert.assertEquals(addressDelivery.get(0), "YOUR DELIVERY ADDRESS", "Verify Address Details");
        Assert.assertEquals(addressInvoice.get(0), "YOUR BILLING ADDRESS", "Verify Address Details");

        for (int i = 1; i < 8; i++) {
            Assert.assertEquals(addressDelivery.get(i), addressInvoice.get(i), "Verify Address Details");
        }

        String no1 = "Mr. " + JSONReader.accountDetails("firstName") + " " + JSONReader.accountDetails("lastName");
        String no2 = JSONReader.accountDetails("company");
        String no3 = JSONReader.accountDetails("address1");
        String no4 = JSONReader.accountDetails("address2");
        String no5 = JSONReader.accountDetails("city") + " " + JSONReader.accountDetails("state") + " " + JSONReader.accountDetails("zipcode");
        String no6 = JSONReader.accountDetails("country");
        String no7 = JSONReader.accountDetails("mobileNumber");

        Assert.assertEquals(addressDelivery.get(1), no1, "Verify Address Details");
        Assert.assertEquals(addressDelivery.get(2), no2, "Verify Address Details");
        Assert.assertEquals(addressDelivery.get(3), no3, "Verify Address Details");
        Assert.assertEquals(addressDelivery.get(4), no4, "Verify Address Details");
        Assert.assertEquals(addressDelivery.get(5), no5, "Verify Address Details");
        Assert.assertEquals(addressDelivery.get(6), no6, "Verify Address Details");
        Assert.assertEquals(addressDelivery.get(7), no7, "Verify Address Details");
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
    @Step("Verify 'Logged in as username' at top")
    private void verifyLoggedInAsUsernameAtTop() throws IOException, ParseException {
        String username = new LoggedHomePage(getDriver())
                .getUsername()
                .getText();
        Assert.assertEquals(username, JSONReader.existingUser("name"), "Verify 'Logged in as username' at top");
    }
}