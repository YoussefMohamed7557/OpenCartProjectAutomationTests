package test_cases.ereny.Tests;

import test_cases.ereny.Pages.Contact_Us_Page;
import jdk.jfr.Description;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Contact_UsTests extends TestBase{


    Contact_Us_Page contactUsPage;

    @Description("Test Case 6: Contact Us Form")
    @Test
    public void TC01_verifyNavigateToContact_UsPageSuccessfully() {

        contactUsPage= new Contact_Us_Page(driver);

        contactUsPage.Navigate();
        contactUsPage.ClickOnContactUs_Button();
        //Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Get In Touch']")).isDisplayed());
        contactUsPage.AssertDisplayedMessage();
    }

    @Test
    public void TC02_FillContactUsForm(){

        contactUsPage= new Contact_Us_Page(driver);
        contactUsPage.Navigate();
        contactUsPage.ClickOnContactUs_Button();
        contactUsPage.Contact_Us("Ereny","ereny@gmail.com","Ereny Yacoub","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","C:\\Users\\ereny\\OneDrive\\Pictures\\pexels-toni-cuenca-585752.jpg");
        //driver.findElement(By.xpath("//input[@data-qa='submit-button']")).click();
        contactUsPage.ClickOnSubmit_Button();
    }

    @Test
    public void TC03_VerifySuccessMessage() {

        contactUsPage = new Contact_Us_Page(driver);

        contactUsPage.Navigate();
        contactUsPage.ClickOnContactUs_Button();

        contactUsPage.Contact_Us(
                "Ereny",
                "ereny@gmail.com",
                "Ereny Yacoub",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "C:\\Users\\ereny\\OneDrive\\Pictures\\pexels-toni-cuenca-585752.jpg"
        );

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-qa='submit-button']")));

        contactUsPage.ClickOnSubmit_Button();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        alert.accept();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'alert-success')]")
                ));

        contactUsPage.AssertSuccessMessage();
    }
     @Test
    public void TC04_verifyNavigateToHomePageSuccessfully() {

        contactUsPage = new Contact_Us_Page(driver);
        contactUsPage.Navigate();
        contactUsPage.ClickOnContactUs_Button();

        contactUsPage.Contact_Us(
                "Ereny",
                "ereny@gmail.com",
                "Ereny Yacoub",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "C:\\Users\\ereny\\OneDrive\\Pictures\\pexels-toni-cuenca-585752.jpg"
        );

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-qa='submit-button']")));

        contactUsPage.ClickOnSubmit_Button();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btn btn-success']")));
        contactUsPage.ClickOnHomePageIcon();

        wait.until(ExpectedConditions.urlToBe("https://automationexercise.com/"));
        contactUsPage.AssertURL();
    }
}

