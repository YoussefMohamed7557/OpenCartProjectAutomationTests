package test_cases.youssef.test_cases;

import io.qameta.allure.*;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import test_cases.youssef.pages.*;
import test_cases.youssef.test_utilities.JSONReader;
import test_cases.youssef.test_utilities.Utils;

import java.io.IOException;
import java.util.List;

import static test_cases.youssef.test_cases.DownloadInvoiceAfterPurchaseOrder.verifyLoggedInAsUsernameAtTop;
import static test_cases.youssef.test_cases.DownloadInvoiceAfterPurchaseOrder.verifyThatCartPageIsDisplayed;

@Epic("Regression Tests")
@Feature("Verify")
public class VerifyAddressDetailsInCheckOutPageYoussef extends TestBasic {

    String name = "name" + Utils.generateCurrentDateAndTime();
    String email = "email" + Utils.generateCurrentDateAndTime() + "@o2.pl";

    @Test(description = "Test Case 23: Verify address details in checkout page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Verify address details in checkout page")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click 'Signup / Login' button
            5. Fill all details in Signup and create account
            6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
            7. Verify ' Logged in as username' at top
            8. Add products to cart
            9. Click 'Cart' button
            10. Verify that cart page is displayed
            11. Click Proceed To Checkout
            12. Verify that the delivery address and the billing address is same address filled at the time registration of account
            13. Click 'Delete Account' button
            14. Verify 'ACCOUNT DELETED!' and click 'Continue' button""")
    public void verifyAddressDetailsInCheckoutPage() throws IOException, ParseException {
        verifyThatHomePageIsVisibleSuccessfully();
        verifyAccountCreatedAndClickContinueButton(name, email);
        verifyLoggedInAsUsernameAtTop(name);
        verifyThatCartPageIsDisplayed();
        verifyThatTheDeliveryAddressAndTheBillingAddressIsSameAddressFilledAtTheTimeRegistrationOfAccount();
        verifyThatAccountDeletedIsVisibleAndClickContinueButton();
    }
    @Step("Verify that home page is visible successfully")
    public static void verifyThatHomePageIsVisibleSuccessfully() {
        boolean homePageVisible = new HomePage(getDriver())
                .homePageIsVisible()
                .isDisplayed();
        Assert.assertTrue(homePageVisible, "Verify that home page is visible successfully");
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
    @Step("Verify that the delivery address and the billing address is same address filled at the time registration of account")
    private void verifyThatTheDeliveryAddressAndTheBillingAddressIsSameAddressFilledAtTheTimeRegistrationOfAccount() throws IOException, ParseException {
        verifyAddressDetails();
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
    @Step("Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button")
    public static void verifyThatAccountDeletedIsVisibleAndClickContinueButton() {
        String accountDeletedText = new LoggedHomePage(getDriver())
                .deleteAccountButtonClick()
                .getAccountDeleted()
                .getText();
        Assert.assertEquals(accountDeletedText, "ACCOUNT DELETED!", "Verify that 'ACCOUNT DELETED!' is visible");
        new AccountDeletedPage(getDriver()).continueButtonClick();
    }
}
