package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreationPage {
    WebDriver driver;
    public AccountCreationPage(WebDriver driver) {
        this.driver = driver;
    }
    //Locators
    By titleMrRadio = By.id("id_gender1");
    By titleMrsRadio = By.id("id_gender2");
    By passwordInput = By.id("password");
    By daysDropdown = By.id("days");
    By monthsDropdown = By.id("months");
    By yearsDropdown = By.id("years");
    By newsletterCheckbox = By.id("newsletter");
    By offersCheckbox = By.id("optin");
    By firstNameInput = By.id("first_name");
    By lastNameInput = By.id("last_name");
    By companyInput = By.id("company");
    By address1Input = By.id("address1");
    By address2Input = By.id("address2");
    By countryDropdown = By.id("country");
    By stateInput = By.id("state");
    By cityInput = By.id("city");
    By zipCodeInput = By.id("zipcode");
    By mobileNumberInput = By.id("mobile_number");
    By createAccountButton = By.xpath("//button[text()='Create Account']");
    By weakPasswordError = By.xpath("//p[text()='Weak Password']");




    //ACTION
    public void selectTitleMr() {
        driver.findElement(titleMrRadio).click();
    }
    public void selectTitleMrs() {
        driver.findElement(titleMrsRadio).click();
    }
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }
    public void selectDay(String day) {
        driver.findElement(daysDropdown).sendKeys(day);
    }
    public void selectMonth(String month) {
        driver.findElement(monthsDropdown).sendKeys(month);
    }
    public void selectYear(String year) {
        driver.findElement(yearsDropdown).sendKeys(year);
    }
    public void checkNewsletter() {
        driver.findElement(newsletterCheckbox).click();
    }
    public void checkOffers() {
        driver.findElement(offersCheckbox).click();
    }
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }
    public void enterCompany(String company) {
        driver.findElement(companyInput).sendKeys(company);
    }
    public void enterAddress1(String address1) {
        driver.findElement(address1Input).sendKeys(address1);
    }
    public void enterAddress2(String address2) {
        driver.findElement(address2Input).sendKeys(address2);
    }
    public void selectCountry(String country) {
        driver.findElement(countryDropdown).sendKeys(country);
    }
    public void enterState(String state) {
        driver.findElement(stateInput).sendKeys(state);
    }
    public void enterCity(String city) {
        driver.findElement(cityInput).sendKeys(city);
    }
    public void enterZipCode(String zipCode) {
        driver.findElement(zipCodeInput).sendKeys(zipCode);
    }
    public void enterMobileNumber(String mobileNumber) {
        driver.findElement(mobileNumberInput).sendKeys(mobileNumber);
    }
public void clickCreateAccountButton() {
        driver.findElement(createAccountButton).click();
    }

    //ASSERTION
    public void assertWeakPasswordErrorVisible() {
        if (driver.findElement(weakPasswordError).isDisplayed()) {
            System.out.println("Weak Password error message is visible");
        } else {
            System.out.println("Weak Password error message is not visible");
        }
    }


    }


