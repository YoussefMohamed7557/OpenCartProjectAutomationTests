package test_cases.abanoub.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import org.testng.Assert;

public class AccountCreationPage extends BasePage {

    // ---------------------------
    // Locators
    // ---------------------------
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

    // ---------------------------
    // Constructor
    // ---------------------------
    public AccountCreationPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

    // ---------------------------
    // Actions
    // ---------------------------
    public void selectTitleMr() { waitAndClick(titleMrRadio); }
    public void selectTitleMrs() { waitAndClick(titleMrsRadio); }
    public void enterPassword(String password) { waitAndSendKeys(passwordInput, password); }

    public void selectDay(String day) {
        WebElement el = waitForVisible(daysDropdown);
        Select select = new Select(el);
        select.selectByVisibleText(day);
    }

    public void selectMonth(String month) {
        WebElement el = waitForVisible(monthsDropdown);
        Select select = new Select(el);
        select.selectByVisibleText(month);
    }

    public void selectYear(String year) {
        WebElement el = waitForVisible(yearsDropdown);
        Select select = new Select(el);
        select.selectByVisibleText(year);
    }

    public void checkNewsletter() { waitAndClick(newsletterCheckbox); }
    public void checkOffers() { waitAndClick(offersCheckbox); }
    public void enterFirstName(String firstName) { waitAndSendKeys(firstNameInput, firstName); }
    public void enterLastName(String lastName) { waitAndSendKeys(lastNameInput, lastName); }
    public void enterCompany(String company) { waitAndSendKeys(companyInput, company); }
    public void enterAddress1(String address1) { waitAndSendKeys(address1Input, address1); }
    public void enterAddress2(String address2) { waitAndSendKeys(address2Input, address2); }

    public void selectCountry(String country) {
        WebElement el = waitForVisible(countryDropdown);
        Select select = new Select(el);
        select.selectByVisibleText(country);
    }

    public void enterState(String state) { waitAndSendKeys(stateInput, state); }
    public void enterCity(String city) { waitAndSendKeys(cityInput, city); }
    public void enterZipCode(String zipCode) { waitAndSendKeys(zipCodeInput, zipCode); }
    public void enterMobileNumber(String mobileNumber) { waitAndSendKeys(mobileNumberInput, mobileNumber); }
    public void clickCreateAccountButton() { waitAndClick(createAccountButton); }

    // ---------------------------
    // State/Getters
    // ---------------------------
    public boolean isWeakPasswordErrorVisible() { return isVisible(weakPasswordError); }

    private boolean isAccountCreatedMarkerVisibleInSource() {
        return getPageSource().contains("account-created");
    }

    // ---------------------------
    // Assertions
    // ---------------------------
    public void assertWeakPasswordErrorVisible() {
        Assert.assertTrue(isWeakPasswordErrorVisible(), "Weak password error should be visible");
    }

    public void assertAfterMultipleClickAccountCreatedOrNoWeakPassword() {
        boolean createdVisible = isAccountCreatedMarkerVisibleInSource();
        boolean weakPasswordVisible = isWeakPasswordErrorVisible();
        Assert.assertTrue(createdVisible || !weakPasswordVisible,
                "Expected either account created or no weak password error after multiple clicks");
    }
}
