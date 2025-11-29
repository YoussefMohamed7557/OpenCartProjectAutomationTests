package test_cases.omar.pages;

import test_cases.omar.utils.JSONReader;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LoginSignupPage  {

    @FindBy(css = "div[class='login-form'] h2")
    private WebElement loginToYourAccount;

    @FindBy(css = "input[data-qa='login-email']")
    private WebElement loginEmailInput;

    @FindBy(css = "input[data-qa='login-password']")
    private WebElement loginPasswordInput;

    @FindBy(css = "button[data-qa='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "/html/body/section/div/div/div[1]/div/form/p")
    private WebElement errorLogin;

    @FindBy(css = "div[class='signup-form'] h2")
    private WebElement newUserSignup;

    @FindBy(css = "input[data-qa='signup-name']")
    private WebElement signupNameInput;

    @FindBy(css = "input[data-qa='signup-email']")
    private WebElement signupEmailInput;

    @FindBy(css = "button[data-qa='signup-button']")
    private WebElement signupButton;

    @FindBy(xpath = "//section/div/div/div[3]/div/form/p")
    private WebElement emailAddressAlreadyExist;

    private WebDriver driver;

    public LoginSignupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ✅ ميثود واضحة للّوجين الصحيح
    public LoggedHomePage enterRightCredentials(String email, String password) {
        loginEmailInput.clear();
        loginEmailInput.sendKeys(email);

        loginPasswordInput.clear();
        loginPasswordInput.sendKeys(password);

        loginButton.click();

        return new LoggedHomePage(driver);
    }

    public WebElement getNewUserSignup() {
        return newUserSignup;
    }

    private void fillSignup(String name, String email) {
        signupNameInput.sendKeys(name);
        signupEmailInput.sendKeys(email);
        signupButton.click();
    }

    public EnterAccountInformationPage fillCorrectSignup(String name, String email) {
        fillSignup(name, email);
        return new EnterAccountInformationPage(driver);
    }

    public LoginSignupPage fillIncorrectSignup() throws IOException, ParseException {
        fillSignup(JSONReader.existingUser("name"), JSONReader.existingUser("email"));
        return this;
    }

    public WebElement getLoginToYourAccount() {
        return loginToYourAccount;
    }

    private void fillLogin(String email, String password) {
        loginEmailInput.clear();
        loginEmailInput.sendKeys(email);
        loginPasswordInput.clear();
        loginPasswordInput.sendKeys(password);
        loginButton.click();
    }

    // تقدر تستخدم دي في التستات بدل Enter_right_credentials لو حابب
    public LoggedHomePage fillCorrectLogin(String email, String password) {
        fillLogin(email, password);
        return new LoggedHomePage(driver);
    }

    public LoginSignupPage fillIncorrectLogin(String email, String password) {
        fillLogin(email, password);
        return this;
    }

    public WebElement getErrorLogin() {
        return errorLogin;
    }

    public WebElement getEmailAddressAlreadyExist() {
        return emailAddressAlreadyExist;
    }
}