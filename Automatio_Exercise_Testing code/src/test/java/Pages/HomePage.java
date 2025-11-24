package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators


    //ACTION


    //ASSERTION
    public void assertURL() {
        if (driver.getCurrentUrl().equals("https://automationexercise.com/")) {
            System.out.println("Home page loaded successfully");
        } else {
            System.out.println("Home page did not load successfully");
        }
    }
    public void assertTitle() {
        String actualTitle = driver.getTitle();
        if (actualTitle.equals("Automation Exercise")) {
            System.out.println("Page title is correct");
        } else {
            System.out.println("Page title is incorrect");
        }
    }
}

//


//
//// Title check
//String actualTitle = driver.getTitle();
//        Assert.assertEquals(actualTitle, "Automation Exercise", "Page title should be correct");
//
//// Login link visible
//        Assert.assertTrue(
//        driver.findElement(By.cssSelector("a[href='/login']")).isDisplayed(),
//                "Login link should be visible on the home page"
//                        );
//
//                        // Features section visible
//                        Assert.assertTrue(
//        driver.findElement(By.cssSelector(".features_items")).isDisplayed(),
//                "FEATURES ITEMS section should be visible on the home page"
//                        );


