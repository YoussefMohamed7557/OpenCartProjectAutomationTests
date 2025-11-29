package test_cases.omar.test_base;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import test_cases.omar.pages.CartPage;
import test_cases.omar.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Remove_Products_From_Cart extends TestBasic {
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Add products to cart
            5. Click 'Cart' button
            6. Verify that cart page is displayed
            7. Click 'X' button corresponding to particular product
            8. Verify that product is removed from the cart""")

@Test
    public void removeProductsFromCart() {
        verifyThatHomePageIsVisibleSuccessfully();
        verifyThatCartPageIsDisplayed();
        verifyThatProductIsRemovedFromTheCart();
    }

    @Story("User can see home page")
    public void registerUser() {
        verifyThatHomePageIsVisibleSuccessfully();
    }
    @Step("Verify that cart page is displayed")
    public static void verifyThatCartPageIsDisplayed() {
        String shoppingCartText = new ProductsPage(getDriver())
                .addProductsToCart()
                .getShoppingCart()
                .getText();
        Assert.assertEquals(shoppingCartText, "Shopping Cart", "Verify that cart page is displayed");
    }
    @Step("Verify that product is removed from the cart")
    private void verifyThatProductIsRemovedFromTheCart() {
        String emptyCartText = new CartPage(getDriver())
                .xButtonClick()
                .getEmptyCartSpan()
                .getText();
        Assert.assertEquals(emptyCartText, "Cart is empty! Click here to buy products.", "Verify that product is removed from the cart");
    }
}
