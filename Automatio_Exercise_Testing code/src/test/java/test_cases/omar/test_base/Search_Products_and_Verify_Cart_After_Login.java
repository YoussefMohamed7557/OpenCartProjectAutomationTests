package test_cases.omar.test_base;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import test_cases.omar.pages.CartPage;
import test_cases.omar.pages.HomePage;
import test_cases.omar.pages.ProductsPage;
import test_cases.omar.utils.JSONReader;
import test_cases.omar.utils.PropertiesLoader;
import org.jetbrains.annotations.NotNull;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;


public class Search_Products_and_Verify_Cart_After_Login extends TestBasic {



    static String search;

    static {
        try {
            search = PropertiesLoader.loadProperty("search.product.input");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Click on 'Products' button
            4. Verify user is navigated to ALL PRODUCTS page successfully
            5. Enter product name in search input and click search button
            6. Verify 'SEARCHED PRODUCTS' is visible
            7. Verify all the products related to search are visible
            8. Add those products to cart
            9. Click 'Cart' button and verify that products are visible in cart
            10. Click 'Signup / Login' button and submit login details
            11. Again, go to Cart page
            12. Verify that those products are visible in cart after login as well
            13. Remove all products that have been added
            14. Verify 'Cart is empty! Click here to buy products.' is visible""")

    @Test
    public void searchProductsAndVerifyCartAfterLogin() throws IOException, ParseException, InterruptedException {
        verifyUserIsNavigatedToAllProductsPageSuccessfully();
        verifySearchedProductsIsVisible();
        List<String> productsNames = verifyAllTheProductsRelatedToSearchAreVisible();
        new ProductsPage(getDriver()).addAllProducts();
        clickCartButtonAndVerifyThatProductsAreVisibleInCart(productsNames);
        new HomePage(getDriver())
                .signupLoginClick()
                .fillCorrectLogin(JSONReader.existingUser("email"), JSONReader.existingUser("password"));
        verifyThatThoseProductsAreVisibleInCartAfterLoginAsWell(productsNames);
        verifyThatCartIsEmpty();
    }

    @Step("Verify all the products related to search are visible")
    public static List<String> verifyAllTheProductsRelatedToSearchAreVisible() {
        List<String> productsNames = new ProductsPage(getDriver()).getProductsSearchNames();

        for (int i = 0; i < productsNames.size(); i++) {
            Assert.assertTrue(productsNames.get(i).toLowerCase().contains(search.toLowerCase()));
            System.out.println(i + ". " + productsNames.get(i) + " - contain: " + search);
        }
        return productsNames;
    }

    @Step("Verify 'SEARCHED PRODUCTS' is visible")
    public static void verifySearchedProductsIsVisible() {
        String searchedProductsText = new ProductsPage(getDriver())
                .fillSearchProductInput(search)
                .getTitleTextCenter()
                .getText();
        Assert.assertEquals(searchedProductsText, "SEARCHED PRODUCTS", "Verify 'SEARCHED PRODUCTS' is visible");
    }
    @Step("Verify user is navigated to ALL PRODUCTS page successfully")
    public static void verifyUserIsNavigatedToAllProductsPageSuccessfully() {
        String allProductsText = new HomePage(getDriver())
                .productsButtonClick()
                .getTitleTextCenter()
                .getText();
        Assert.assertEquals(allProductsText, "ALL PRODUCTS", "Verify user is navigated to ALL PRODUCTS page successfully");
    }

    @Step("Click 'Cart' button and verify that products are visible in cart")
    private void clickCartButtonAndVerifyThatProductsAreVisibleInCart(@NotNull List<String> productsNames) {
        List<String> productsNamesAdded = new HomePage(getDriver())
                .cartButtonClick()
                .getProductsNames();
        for (int i = 0; i < productsNames.size(); i++) {
            Assert.assertEquals(productsNames.get(i), productsNamesAdded.get(i), "Verify that products are visible in cart");
            System.out.println("Search: " + productsNames.get(i) + " = Added: " + productsNamesAdded.get(i));
        }
    }

    @Step("Verify that those products are visible in cart after login as well")
    private void verifyThatThoseProductsAreVisibleInCartAfterLoginAsWell(@NotNull List<String> productsNames) {
        clickCartButtonAndVerifyThatProductsAreVisibleInCart(productsNames);
    }

    @Step("Verify 'Cart is empty! Click here to buy products.' is visible")
    private void verifyThatCartIsEmpty() throws InterruptedException {
        String emptyCartText = new CartPage(getDriver())
                .deleteAllAddedProducts()
                .getEmptyCartSpan()
                .getText();
        Assert.assertEquals(emptyCartText, "Cart is empty! Click here to buy products.", "Verify 'Cart is empty! Click here to buy products.' is visible");
   }
}