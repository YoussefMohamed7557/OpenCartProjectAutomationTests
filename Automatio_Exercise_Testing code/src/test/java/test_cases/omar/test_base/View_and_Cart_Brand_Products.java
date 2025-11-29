package test_cases.omar.test_base;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import test_cases.omar.pages.HomePage;
import test_cases.omar.pages.ProductsPage;
import test_cases.omar.utils.JSONReader;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;


public class View_and_Cart_Brand_Products extends TestBasic {
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Click on 'Products' button
            4. Verify that Brands are visible on left side bar
            5. Click on any brand name
            6. Verify that user is navigated to brand page and brand products are displayed
            7. On left side bar, click on any other brand link
            8. Verify that user is navigated to that brand page and can see products""")

    @Test(description = "Test Case 19: View Cart Brand Products")
    public void viewCartBrandProducts() throws IOException, ParseException {
        verifyThatBrandsAreVisibleOnLeftSideBar();
        verifyThatUserIsNavigatedToBrandPageAndBrandProductsAreDisplayed();
        verifyThatUserIsNavigatedToThatBrandPageAndCanSeeProducts();
    }

    @Step("Verify that Brands are visible on left side bar")
    private void verifyThatBrandsAreVisibleOnLeftSideBar() {
        boolean brandsIsDisplayed = new HomePage(getDriver())
                .productsButtonClick()
                .getBrands()
                .isDisplayed();
        Assert.assertTrue(brandsIsDisplayed, "Verify that Brands are visible on left side bar");
    }

    @Step("Verify that user is navigated to brand page and brand products are displayed")
    private void verifyThatUserIsNavigatedToBrandPageAndBrandProductsAreDisplayed() throws IOException, ParseException {
        String titleTextCenter = new ProductsPage(getDriver())
                .poloBrandClick()
                .getTitleTextCenter()
                .getText();
        Assert.assertEquals(titleTextCenter, "BRAND - POLO PRODUCTS", "Verify that user is navigated to brand page");

        List<String> productsNames = new ProductsPage(getDriver()).getProductsSearchNames();
        for (int i = 0; i < productsNames.size(); i++) {
            Assert.assertEquals(productsNames.get(i), JSONReader.poloBrandProducts(String.valueOf(i)), "Verify that brand products are displayed");
        }
    }

    @Step("Verify that user is navigated to that brand page and can see products")
    private void verifyThatUserIsNavigatedToThatBrandPageAndCanSeeProducts() throws IOException, ParseException {
        String titleTextCenter = new ProductsPage(getDriver())
                .madameBrandClick()
                .getTitleTextCenter()
                .getText();
        Assert.assertEquals(titleTextCenter, "BRAND - MADAME PRODUCTS", "Verify that user is navigated to that brand page");

        List<String> productsNames = new ProductsPage(getDriver()).getProductsSearchNames();
        for (int i = 0; i < productsNames.size(); i++) {
            Assert.assertEquals(productsNames.get(i), JSONReader.madameBrandProducts(String.valueOf(i)), "Verify that can see products");
        }
    }
}
