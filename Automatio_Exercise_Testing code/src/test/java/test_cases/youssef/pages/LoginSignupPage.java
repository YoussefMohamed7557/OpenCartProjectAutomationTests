package test_cases.youssef.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginSignupPage extends TestBasic {

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
        PageFactory.initElements(driver, this);
        this.driver = driver;
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

}
