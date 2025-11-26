package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class AccountCreationPage {

    private final WebDriver driver;

    // Locators
    private final By titleMrRadio = By.id("id_gender1");
    private final By titleMrsRadio = By.id("id_gender2");
    private final By passwordInput = By.id("password");
    private final By daysDropdown = By.id("days");
    private final By monthsDropdown = By.id("months");
    private final By yearsDropdown = By.id("years");
    private final By newsletterCheckbox = By.id("newsletter");
    private final By offersCheckbox = By.id("optin");
    private final By firstNameInput = By.id("first_name");
    private final By lastNameInput = By.id("last_name");
    private final By companyInput = By.id("company");
    private final By address1Input = By.id("address1");
    private final By address2Input = By.id("address2");
    private final By countryDropdown = By.id("country");
    private final By stateInput = By.id("state");
    private final By cityInput = By.id("city");
    private final By zipCodeInput = By.id("zipcode");
    private final By mobileNumberInput = By.id("mobile_number");
    private final By createAccountButton = By.xpath("//button[text()='Create Account']");
    private final By weakPasswordError = By.xpath("//p[text()='Weak Password']");

    public AccountCreationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions

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
        new Select(driver.findElement(daysDropdown)).selectByVisibleText(day);
    }

    public void selectMonth(String month) {
        new Select(driver.findElement(monthsDropdown)).selectByVisibleText(month);
    }

    public void selectYear(String year) {
        new Select(driver.findElement(yearsDropdown)).selectByVisibleText(year);
    }

    public void checkNewsletter() {
        if (!driver.findElement(newsletterCheckbox).isSelected()) {
            driver.findElement(newsletterCheckbox).click();
        }
    }

    public void checkOffers() {
        if (!driver.findElement(offersCheckbox).isSelected()) {
            driver.findElement(offersCheckbox).click();
        }
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
        new Select(driver.findElement(countryDropdown)).selectByVisibleText(country);
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

    // Assertions

    public void assertWeakPasswordErrorVisible() {
        Assert.assertTrue(
                driver.findElement(weakPasswordError).isDisplayed(),
                "Weak Password error message is NOT visible"
        );
    }
}
