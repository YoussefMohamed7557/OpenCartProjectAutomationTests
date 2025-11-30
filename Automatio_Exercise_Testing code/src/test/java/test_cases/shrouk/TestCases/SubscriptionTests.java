package test_cases.shrouk.TestCases;

import test_cases.shrouk.Pages.CartPage;
import org.testng.annotations.Test;

public class SubscriptionTests extends TestBase {
    CartPage cartPage ;
    //testcase11-1
    //positive: subscribe with valid email (cart)

    @Test
    public void test1()
    {
        cartPage = new CartPage(driver);
        cartPage.navigateToCartPage();
        cartPage.enterSubscribeEmail("Shrouk@shrouk.com");
        cartPage.assertSuccessSubscription();
    }


    @Test
    public void test2() {
        //testcase11-2
        // negative: subscribe with invalid email format (cart)
        cartPage = new CartPage(driver);
        cartPage.navigateToCartPage();
        cartPage.enterSubscribeEmail("shrouk.com");
        cartPage.assertInvalidEmailErrorMsg();
    }


    @Test
    public void test3() {
        //testcase11-3
        //subscribe with empty email format (cart)
        cartPage = new CartPage(driver);
        cartPage.navigateToCartPage();
        cartPage.enterSubscribeEmail("");
        cartPage.assertemptyEmailErrorMsg();

    }




}





