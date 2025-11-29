package test_cases.shrouk.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PaymentPage {
    WebDriver driver ;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;}

    By Nameoncard = By.name("name_on_card");
    By Card_number = By.name("card_number");
    By Cvc = By.name("cvc");
    By Expiry_month= By.name("expiry_month");
    By Expiry_year= By.name("expiry_year");
    By Submit = By.id("submit");
    By Continuetwo =By.cssSelector("a[data-qa='continue-button']");
    By Continuetwoclick=By.cssSelector("a[data-qa='continue-button']");
        public void Payment(String nameoncard ,String card_number,String cvc,String expiry_month,String expiry_year )
        {
            driver.findElement(Nameoncard).sendKeys(nameoncard );
            driver.findElement(Card_number).sendKeys(card_number);
            driver.findElement(Cvc).sendKeys(cvc);
            driver.findElement(Expiry_month).sendKeys( expiry_month);
            driver.findElement(Expiry_year).sendKeys(expiry_year);
            driver.findElement(Submit).click();
        }
    public void  Continuetwoclick()
    {
        driver.findElement(Continuetwoclick).click();
    }
        //Assertion
public void assertContinuetwo()
{
    Assert.assertTrue(driver.findElement(Continuetwo).isDisplayed());

}}
