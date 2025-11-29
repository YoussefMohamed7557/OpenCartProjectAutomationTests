package test_cases.shrouk.TestCases;

import test_cases.shrouk.Pages.AddProductPage;
import org.testng.annotations.Test;

public class AddProductTests extends TestBase {
    AddProductPage addProductPage;

    @Test
    public void test4() {
        // //testcase12-1
        //Add two product and verify the cart
        addProductPage = new AddProductPage(driver);
        addProductPage.navigateToProductPage();
        addProductPage.AddToCart();
        addProductPage.ViewCart();
        addProductPage.AssertProccedtoCheckoutButtom();

    }

    @Test
    public void test5() {
        // testcase12-2
        //delete products from the cart
        addProductPage = new AddProductPage(driver);
        addProductPage.navigateToProductPage();
        addProductPage.AddToCart();
        addProductPage.ViewCart();
        addProductPage.DeleteProduct();
    }


}
