package test_cases.abanoub.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.testng.Assert;

public class LoginSignupPage extends BasePage {

    // ---------------------------
    // Locators
    // ---------------------------
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
    private final By genericError = By.cssSelector(".error, .alert, p[style*='color']");

    // ---------------------------
    // Constructor
    // ---------------------------
    public LoginSignupPage(WebDriver driver, Duration timeout) {
        super(driver, timeout);
    }

    // ---------------------------
    // Actions
    // ---------------------------
    public void enterName(String name) { waitAndSendKeys(nameInput, name); }
    public void enterSignupEmail(String email) { waitAndSendKeys(emailInput, email); }
    public void clickSignUpButton() { waitAndClick(signUpButton); }
    public void enterLoginEmail(String email) { waitAndSendKeys(loginEmailInput, email); }
    public void enterLoginPassword(String password) { waitAndSendKeys(loginPasswordInput, password); }
    public void clickLoginButton() { waitAndClick(loginButton); }

    public void pressEnterOnPassword() {
        waitForVisible(loginPasswordInput).sendKeys(Keys.ENTER);
        waitForPageLoad();
    }

    public void signup(String name, String email) {
        enterName(name);
        enterSignupEmail(email);
        clickSignUpButton();
    }

    public void login(String email, String password) {
        enterLoginEmail(email);
        enterLoginPassword(password);
        clickLoginButton();
    }

    // ---------------------------
    // State/Getters
    // ---------------------------
    public boolean isSignUpMessageVisible() { return isVisible(signUpMessage); }
    public boolean isLoginMessageVisible() { return isVisible(loginMessage); }
    public boolean areSignupInputsVisible() { return isVisible(nameInput) && isVisible(emailInput); }
    public boolean areLoginInputsVisible() { return isVisible(loginEmailInput) && isVisible(loginPasswordInput); }
    public boolean isEmailExistingErrorVisible() { return isVisible(emailIsExistingError); }
    public boolean isWrongEmailOrPasswordErrorVisible() { return isVisible(wrongEmailOrPasswordError); }

    public String getVisibleErrorMessageText() {
        String text = safeGetText(genericError);
        if (text.isEmpty()) {
            if (isEmailExistingErrorVisible()) return safeGetText(emailIsExistingError);
            if (isWrongEmailOrPasswordErrorVisible()) return safeGetText(wrongEmailOrPasswordError);
            return "";
        }
        return text;
    }

    public boolean isOnLoginOrSignupPage() {
        String current = driver.getCurrentUrl();
        return current.contains("/login") || current.contains("/signup");
    }

    public String getTitle() { return driver.getTitle(); }

    // ---------------------------
    // Assertions
    // ---------------------------
    public void assertOnLoginOrSignupPage() {
        String current = driver.getCurrentUrl();
        Assert.assertTrue(isOnLoginOrSignupPage(), "Expected to be on login/signup page, but URL was: " + current);
    }

    public void assertLoginSignupTitleIsCorrect() {
        Assert.assertEquals(getTitle(), "Automation Exercise - Signup / Login", "Page title mismatch for Signup/Login page");
    }

    public void assertWrongCredentialsErrorVisible() {
        Assert.assertTrue(isWrongEmailOrPasswordErrorVisible(), "Wrong credentials error should be visible");
    }

    public void assertEmailAlreadyExistsErrorVisible() {
        Assert.assertTrue(isEmailExistingErrorVisible(), "Email already exists error should be visible");
    }

    public void assertSignupInputsVisible() {
        Assert.assertTrue(areSignupInputsVisible(), "Signup inputs should be visible");
    }

    public void assertLoginInputsVisible() {
        Assert.assertTrue(areLoginInputsVisible(), "Login inputs should be visible");
    }

    public void assertLoginMessageVisible() {
        Assert.assertTrue(isLoginMessageVisible(), "Login message should be visible");
    }

    public void assertSignUpMessageVisible() {
        Assert.assertTrue(isSignUpMessageVisible(), "Sign up message should be visible");
    }

    public void assertStayedOnSignupOrLoginAfterInvalidData() {
        String current = driver.getCurrentUrl();
        Assert.assertTrue(isOnLoginOrSignupPage(), "User should remain on signup/login page after invalid data, but URL was: " + current);
    }
}
