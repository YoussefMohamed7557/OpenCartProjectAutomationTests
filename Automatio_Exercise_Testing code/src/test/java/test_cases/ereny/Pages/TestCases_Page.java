package test_cases.ereny.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TestCases_Page {

    WebDriver driver;

    public TestCases_Page(WebDriver driver) {
        this.driver = driver;
    }

    By TextCase_Button = By.cssSelector("a[href='/test_cases']");

    public void Navigate(){
        driver.get("https://automationexercise.com/");
    }

    public void ClickOnTextCase_Button(){
        driver.findElement(TextCase_Button).click();
    }
    public void AssertURL(){
       Assert.assertEquals(driver.getCurrentUrl() , "https://automationexercise.com/test_cases");
    }
}



