package test_cases.omar.test_base;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import test_cases.omar.pages.HomePage;
import test_cases.omar.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class View_Category_Products extends TestBasic {
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that categories are visible on left side bar
            4. Click on 'Women' category
            5. Click on any category link under 'Women' category, for example: Dress
            6. Verify that category page is displayed and confirm text 'WOMEN - DRESS PRODUCTS'
            7. On left side bar, click on any sub-category link of 'Men' category
            8. Verify that user is navigated to that category page""")

 @Test
    public void viewCategoryProducts() {
        verifyThatHomePageIsVisibleSuccessfully();
        verifyThatCategoriesAreVisibleOnLeftSideBar();
        verifyThatCategoryPageIsDisplayedAndConfirmTextWomenDressProducts();
        verifyThatUserIsNavigatedToThatCategoryPage();
    }

    @Story("User can see home page")
    public void registerUser() {
        verifyThatHomePageIsVisibleSuccessfully();
    }
    @Step("Verify that categories are visible on left side bar")
    private void verifyThatCategoriesAreVisibleOnLeftSideBar() {
        boolean categoriesAreVisible = new HomePage(getDriver())
                .getCategories()
                .isDisplayed();
        Assert.assertTrue(categoriesAreVisible, "Verify that categories are visible on left side bar");
    }

    @Step("Verify that category page is displayed and confirm text 'WOMEN - DRESS PRODUCTS'")
    private void verifyThatCategoryPageIsDisplayedAndConfirmTextWomenDressProducts() {
        String titleTextCenter = new HomePage(getDriver())
                .womenCategoryClick()
                .dressCategoryClick()
                .getTitleTextCenter()
                .getText();
        Assert.assertEquals(titleTextCenter, "WOMEN - DRESS PRODUCTS", "Verify that category page is displayed and confirm text 'WOMEN - DRESS PRODUCTS'");
    }

    @Step("Verify that user is navigated to that category page")
    private void verifyThatUserIsNavigatedToThatCategoryPage() {
        String titleTextCenter = new ProductsPage(getDriver())
                .menCategoryClick()
                .tShirtsCategoryClick()
                .getTitleTextCenter()
                .getText();
        Assert.assertEquals(titleTextCenter, "MEN - TSHIRTS PRODUCTS", "Verify that user is navigated to that category page");
    }
}
