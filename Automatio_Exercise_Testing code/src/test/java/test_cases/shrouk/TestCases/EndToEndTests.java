package test_cases.shrouk.TestCases;

import test_cases.shrouk.Pages.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
public class EndToEndTests extends TestBase {
    EndToEndTests endToEndTests;
    @Test
    public void test6() {
        //testcase14-1
        AddProductPage addProductPage = new AddProductPage(driver);
        addProductPage.navigateToProductPage();
        addProductPage.AddToCart();
        addProductPage.ViewCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckoutButton();
        cartPage.Signupandloginbuttom();

        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        loginSignupPage.enterNameAndEmail("shrouk" , "shrouk12345678@gmail.com");
        loginSignupPage.clickSignUpButton();

        AccountCreationPage accountCreationPage = new AccountCreationPage(driver);
        accountCreationPage.selectTitleMrs();
        accountCreationPage.fillMandatoryAddressInfo("shrouk" , "abdo" , "testing" , "Cairo" , "India" , "cairo" , "cairo" , "1111" , "12345678");
        accountCreationPage.enterPassword("1234");
        accountCreationPage.clickCreateAccountButton();

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        accountCreatedPage.clickContinueButton();


        cartPage.navigateToCartPage();
        cartPage.proceedToCheckoutButton();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickPlaceOrder();



        // Paymant Page
        PaymentPage paymentpage = new PaymentPage(driver);
        paymentpage.Payment("shrouk","123","132","12","2026");
        paymentpage.assertContinuetwo();
        paymentpage.Continuetwoclick();
        //Home Page

        driver.findElement(By.cssSelector("a[href='/delete_account']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("a[data-qa='continue-button']")).isDisplayed());
        driver.findElement(By.cssSelector("a[data-qa='continue-button']")).click();
    }

    @Test
    public void test7() {
        //14-2
        AddProductPage addProductPage = new AddProductPage(driver);
        addProductPage.navigateToProductPage();
        addProductPage.AddToCart();
        addProductPage.ViewCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckoutButton();
        cartPage.Signupandloginbuttom();
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        loginSignupPage.enterLoginEmailAndPassword("shrouk12345678@gmail.com","12345678");
        loginSignupPage.clickLoginButton();

        cartPage.navigateToCartPage();
        cartPage.proceedToCheckoutButton();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickPlaceOrder();



        // Paymant Page
        PaymentPage paymentpage = new PaymentPage(driver);
        paymentpage.Payment("shrouk","123","132","12","2026");
        paymentpage.assertContinuetwo();
        //Home Page
        driver.findElement(By.cssSelector("a[data-qa='continue-button']")).click();

        driver.findElement(By.cssSelector("a[href='/delete_account']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("a[data-qa='continue-button']")).isDisplayed());
        driver.findElement(By.cssSelector("a[data-qa='continue-button']")).click();
    }
}