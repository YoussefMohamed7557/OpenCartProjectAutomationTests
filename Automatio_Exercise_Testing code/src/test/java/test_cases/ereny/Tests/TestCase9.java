package test_cases.ereny.Tests;

import test_cases.ereny.Pages.Searched_Products_Page;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class TestCase9 extends TestBase{


    Searched_Products_Page searchedProductsPage;

    @Description("Test Case 9: Search Product")

    @Test
    public void TC08_verifyProductsPageWithSearchButton(){

        searchedProductsPage = new Searched_Products_Page(driver);
        searchedProductsPage.Navigate();
        searchedProductsPage.ClickOnProducts_Button();
        searchedProductsPage.SearchedProductPage("Blue Top");
        searchedProductsPage.ClickOnSubmit_Button();
    }
    @Test
    public void TC09_DisplayFeatures_items(){
        searchedProductsPage = new Searched_Products_Page(driver);
        searchedProductsPage.Navigate();
        searchedProductsPage.ClickOnProducts_Button();
        searchedProductsPage.SearchedProductPage("Blue Top");
        searchedProductsPage.ClickOnSubmit_Button();
        searchedProductsPage.AssertFeatureItem();
    }
}