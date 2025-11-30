package test_cases.shrouk.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSignupPage {

    private final WebDriver driver;

    private final By signUpMessage = By.xpath("//h2[text()='New User Signup!']");
    private final By loginMessage = By.xpath("//h2[text()='Login to your account']");
    private final By emailInput = By.xpath("//input[@data-qa='signup-email']");
    private final By nameInput = By.xpath("//input[@data-qa='signup-name']");
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
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
    }

    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    public void enterLoginEmailAndPassword(String email, String password) {
        driver.findElement(loginEmailInput).clear();
        driver.findElement(loginEmailInput).sendKeys(email);
        driver.findElement(loginPasswordInput).clear();
        driver.findElement(loginPasswordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void login(String email, String password) {
        enterLoginEmailAndPassword(email, password);
        clickLoginButton();
    }

    public void signup(String name, String email) {
        enterNameAndEmail(name, email);
        clickSignUpButton();
    }

    // Assertions

    public void assertURL() {
        String currentUrl = driver.getCurrentUrl();
        boolean onLoginOrSignup =
                currentUrl.equals("https://automationexercise.com/login") ||
                        currentUrl.equals("https://automationexercise.com/signup");

        Assert.assertTrue(
                onLoginOrSignup,
                "Unexpected URL for Login/Signup page. Actual: " + currentUrl
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
        String actualTitle = driver.getTitle();
        Assert.assertEquals(
                actualTitle,
                "Automation Exercise - Signup / Login",
                "Page title is incorrect"
        );
    }

    public void assertNameAndEmailInputVisible() {
        Assert.assertTrue(
                driver.findElement(nameInput).isDisplayed() &&
                        driver.findElement(emailInput).isDisplayed(),
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
                driver.findElement(loginEmailInput).isDisplayed() &&
                        driver.findElement(loginPasswordInput).isDisplayed(),
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
                "Unexpected URL after invalid login/signup attempt. Actual: " + currentUrl
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
