package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSignupPage {
    WebDriver driver;
    public LoginSignupPage(WebDriver driver) {
        this.driver = driver;
    }
    //Locators
    By signUpmsg = By.xpath("//h2[text()='New User Signup!'] ");
    By loginmsg = By.xpath("//h2[text()='Login to your account'] ");
    By emailInput = By.xpath("//input[@data-qa='signup-email']");
    By nameInput = By.xpath("//input[@data-qa='signup-name']");
    By signUpButton = By.xpath("//button[@data-qa='signup-button']");
    By loginEmailInput = By.xpath("//input[@data-qa='login-email']");
    By loginPasswordInput = By.xpath("//input[@data-qa='login-password']");
    By loginButton = By.xpath("//button[@data-qa='login-button']");
    By emadilIsExistingError = By.xpath("//p[text()='Email Address already exist!']");
    By wrongEmailOrPasswordError = By.xpath("//p[text()='Your email or password is incorrect!']");

    //ACTION
    public void enterNameAndEmail(String name, String email) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
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

    //ASSERTION
    public void assertURL() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login", "Login-Signup page URL is incorrect");
    }
    public void assertSignUpMessageVisible() {
        Assert.assertTrue(driver.findElement(signUpmsg).isDisplayed(), "SignUp message is NOT visible");
    }
    public void assertLoginMessageVisible() {
        Assert.assertTrue(driver.findElement(loginmsg).isDisplayed(), "Login message is NOT visible");
    }

    public void assertTitle() {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Automation Exercise - Signup / Login", "Page title is incorrect");
    }
    public void assertNameAndEmailInputVisible() {
        Assert.assertTrue(driver.findElement(nameInput).isDisplayed() && driver.findElement(emailInput).isDisplayed(),
                "Name and Email input fields are NOT visible");
    }
    public void assertSignUpButtonVisible() {
        Assert.assertTrue(driver.findElement(signUpButton).isDisplayed(), "SignUp button is NOT visible");
    }
    public void assertLoginEmailAndPasswordInputVisible() {
        Assert.assertTrue(driver.findElement(loginEmailInput).isDisplayed() && driver.findElement(loginPasswordInput).isDisplayed(),
                "Login Email and Password input fields are NOT visible");
    }
    public void assertLoginButtonVisible() {
        Assert.assertTrue(driver.findElement(loginButton).isDisplayed(), "Login button is NOT visible");
    }
    public void assertUrlBeTheSameWrongEmail( ) {
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login", "URL is not the expected login URL");
    }
    public void assertEmailExistingErrorVisible() {
        Assert.assertTrue(driver.findElement(emadilIsExistingError).isDisplayed(), "Email already exist error is NOT visible");
    }
    public void assertWrongEmailOrPasswordErrorVisible() {
        Assert.assertTrue(driver.findElement(wrongEmailOrPasswordError).isDisplayed(), "Wrong email or password error is NOT visible");
    }
}
