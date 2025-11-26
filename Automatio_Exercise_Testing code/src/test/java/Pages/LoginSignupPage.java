package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSignupPage {

    private final WebDriver driver;

    // Locators
    private final By signUpMessage = By.xpath("//h2[text()='New User Signup!']");
    private final By loginMessage = By.xpath("//h2[text()='Login to your account']");
    private final By signupEmailInput = By.xpath("//input[@data-qa='signup-email']");
    private final By signupNameInput = By.xpath("//input[@data-qa='signup-name']");
    private final By signUpButton = By.xpath("//button[@data-qa='signup-button']");
    private final By loginEmailInput = By.xpath("//input[@data-qa='login-email']");
    private final By loginPasswordInput = By.xpath("//input[@data-qa='login-password']");
    private final By loginButton = By.xpath("//button[@data-qa='login-button']");
    private final By emailIsExistingError = By.xpath("//p[text()='Email Address already exist!']");
    private final By wrongEmailOrPasswordError = By.xpath("//p[text()='Your email or password is incorrect!']");

    public LoginSignupPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions

    public void enterNameAndEmail(String name, String email) {
        driver.findElement(signupNameInput).sendKeys(name);
        driver.findElement(signupEmailInput).sendKeys(email);
    }

    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    public void enterLoginEmailAndPassword(String email, String password) {
        driver.findElement(loginEmailInput).sendKeys(email);
        driver.findElement(loginPasswordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Assertions

    public void assertURL() {
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://automationexercise.com/login",
                "Login-Signup page URL is incorrect"
        );
    }

    public void assertSignUpMessageVisible() {
        Assert.assertTrue(
                driver.findElement(signUpMessage).isDisplayed(),
                "SignUp message is NOT visible"
        );
    }

    public void assertLoginMessageVisible() {
        Assert.assertTrue(
                driver.findElement(loginMessage).isDisplayed(),
                "Login message is NOT visible"
        );
    }

    public void assertTitle() {
        Assert.assertEquals(
                driver.getTitle(),
                "Automation Exercise - Signup / Login",
                "Page title is incorrect"
        );
    }

    public void assertNameAndEmailInputVisible() {
        Assert.assertTrue(
                driver.findElement(signupNameInput).isDisplayed()
                        && driver.findElement(signupEmailInput).isDisplayed(),
                "Name and Email input fields are NOT visible"
        );
    }

    public void assertSignUpButtonVisible() {
        Assert.assertTrue(
                driver.findElement(signUpButton).isDisplayed(),
                "SignUp button is NOT visible"
        );
    }

    public void assertLoginEmailAndPasswordInputVisible() {
        Assert.assertTrue(
                driver.findElement(loginEmailInput).isDisplayed()
                        && driver.findElement(loginPasswordInput).isDisplayed(),
                "Login Email and Password input fields are NOT visible"
        );
    }

    public void assertLoginButtonVisible() {
        Assert.assertTrue(
                driver.findElement(loginButton).isDisplayed(),
                "Login button is NOT visible"
        );
    }

    public void assertUrlBeTheSameWrongEmail() {
        String currentUrl = driver.getCurrentUrl();

        boolean onLoginOrSignup =
                currentUrl.equals("https://automationexercise.com/login") ||
                        currentUrl.equals("https://automationexercise.com/signup");

        Assert.assertTrue(
                onLoginOrSignup,
                "Unexpected URL after invalid signup/login attempt. Actual: " + currentUrl
        );
    }


    public void assertEmailExistingErrorVisible() {
        Assert.assertTrue(
                driver.findElement(emailIsExistingError).isDisplayed(),
                "Email already exist error is NOT visible"
        );
    }

    public void assertWrongEmailOrPasswordErrorVisible() {
        Assert.assertTrue(
                driver.findElement(wrongEmailOrPasswordError).isDisplayed(),
                "Wrong email or password error is NOT visible"
        );
    }
}
