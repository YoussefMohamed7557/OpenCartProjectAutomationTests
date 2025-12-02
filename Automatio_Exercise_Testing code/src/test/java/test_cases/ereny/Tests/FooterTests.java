package test_cases.ereny.Tests;

import test_cases.ereny.Pages.Footer_Page;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class FooterTests extends TestBase{


    Footer_Page footerPage;

    @Description("Test Case 10: Verify Subscription in home page")
    @Test
    public void TC10_ScrollDownToFooter() {
        footerPage = new Footer_Page(driver);

        footerPage.Navigate();
        WebElement footer = driver.findElement(By.xpath("//div[@class='footer-widget']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", footer);
    }
    @Test
    public void TC12_VerifyHomePage(){
        footerPage = new Footer_Page(driver);
        footerPage.Navigate();
        footerPage.AssertToHomePage();
    }
    @Test
    public void TC13_VerifyTextSUBSCRIPTION() {
        footerPage = new Footer_Page(driver);

        footerPage.Navigate();
        WebElement footer = driver.findElement(By.xpath("//div[@class='footer-widget']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", footer);
        footerPage.AssertDisplayText();
    }
    @Test
    public void TC14_SUBSCRIPTIONForm(){
        footerPage = new Footer_Page(driver);
        footerPage.Navigate();

        WebElement footer = driver.findElement(By.xpath("//div[@class='footer-widget']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", footer);
        //driver.findElement(By.id("susbscribe_email")).sendKeys("ereny@gmail.com");
        //footerPage.EnterEmail("ereny@gmail.com");
        footerPage.FooterPage("ereny@gmail.com");
        //driver.findElement(By.id("subscribe")).click();
        footerPage.ClickOnSubscribe_Button();

    }
    @Test
    public void TC15_VerifySuccessMessage(){
        footerPage = new Footer_Page(driver);
        footerPage.Navigate();
        WebElement footer = driver.findElement(By.xpath("//div[@class='footer-widget']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", footer);
        footerPage.FooterPage("ereny@gmail.com");
        footerPage.ClickOnSubscribe_Button();
        footerPage.AssertSuccessMessage();
    }
}