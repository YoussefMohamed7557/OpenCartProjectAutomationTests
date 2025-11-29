package test_cases.shrouk.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class EndToEndPage {
    WebDriver driver ;
    public EndToEndPage(WebDriver driver) {
        this.driver = driver;
    }
    // LOCATORS



    By Delete =By.cssSelector("a[href='/delete_account']");
    By Continuethree =By.cssSelector("a[data-qa='continue-button']");
    By Continuethreeclick=By.cssSelector("a[data-qa='continue-button']");

    // ACTIONS

    public void  Deletebutton()
    {
        driver.findElement(Delete).click();
    }
    public void  Continuethreeclick()
    {
        driver.findElement(Continuethreeclick).click();
    }


    // ASSERTIONS

    public void assertContinuethree()
    {
        Assert.assertTrue(driver.findElement(Continuethree).isDisplayed());

    }






}
