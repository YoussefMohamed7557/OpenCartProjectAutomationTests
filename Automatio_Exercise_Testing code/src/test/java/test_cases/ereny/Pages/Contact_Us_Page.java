package test_cases.ereny.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Contact_Us_Page {

    WebDriver driver;

    public Contact_Us_Page(WebDriver driver) {
        this.driver = driver;
    }
    //Locators
    
    By ContactUs_Button =By.xpath("//a[@href=\"/contact_us\"]");
    By Name_textBox =By.xpath("//input[@data-qa='name']");
    By Email_textBox =By.xpath("//input[@data-qa='email']");
    By Subject_textBox =By.xpath("//input[@data-qa='subject']");
    By Message_textBox = By.id("message");
    By Upload_Button = By.name("upload_file");
    By Submit_Button =By.xpath("//input[@data-qa='submit-button']");
    By HomePageIcon = By.xpath("//a[@class='btn btn-success']");
    By DisplayedMessage = By.xpath("//h2[text()='Get In Touch']");
    By VerifySuccessMessage = By.xpath("//div[contains(@class,'alert-success')]");

    //Actions

    public void Navigate(){

        driver.get("https://automationexercise.com/");
    }

   public void ClickOnContactUs_Button(){

        driver.findElement(ContactUs_Button).click();
    }
    public void Contact_Us(String Name,String Email,String Subject,String Message,String File){

        driver.findElement(Name_textBox).sendKeys(Name);
        driver.findElement(Email_textBox).sendKeys(Email);
        driver.findElement(Subject_textBox).sendKeys(Subject);
        driver.findElement(Message_textBox).sendKeys(Message);
        driver.findElement(Upload_Button).sendKeys(File);
    }
    public void ClickOnHomePageIcon(){

        driver.findElement(HomePageIcon).click();
    }
    public void AssertDisplayedMessage(){

        Assert.assertTrue(driver.findElement(DisplayedMessage).isDisplayed());
    }
    public void AssertSuccessMessage(){

        Assert.assertTrue(driver.findElement(VerifySuccessMessage).isDisplayed());
    }
    public void ClickOnSubmit_Button(){

        driver.findElement(Submit_Button).click();
    }
    public void AssertURL(){

        Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/");
    }
}
