package test_cases.abanoub;

import Pages.AccountCreatedPage;
import Pages.AccountCreationPage;
import Pages.HomePage;
import Pages.LoginSignupPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;

public class AllTests {
    WebDriver driver;
    AccountCreatedPage accountCreatedPage;
    AccountCreationPage accountCreationPage;
    HomePage homePage;
    LoginSignupPage loginSignupPage;

    @BeforeTest
    public void preconditions() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to("https://automationexercise.com/");
        accountCreatedPage = new AccountCreatedPage(driver);
        accountCreationPage = new AccountCreationPage(driver);
        homePage = new HomePage(driver);
        loginSignupPage = new LoginSignupPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    //TC01 – Verify Home Page Loads Successfully
    public void verifyHomePageLoadsSuccessfully() {


        // URL check
        homePage.assertURL();
        // Title check
        homePage.assertTitle();

        // Login link visible
        Assert.assertTrue(
                driver.findElement(By.cssSelector("a[href='/login']")).isDisplayed(),
                "Login link should be visible on the home page"
        );

        // Features section visible
        Assert.assertTrue(
                driver.findElement(By.cssSelector(".features_items")).isDisplayed(),
                "FEATURES ITEMS section should be visible on the home page"
        );

    }

    @Test
    //TC02 – Verify Navigation to Signup Page
    public void verifyNavigationToSignupPage() {


        // Click on 'Signup / Login' link
        driver.findElement(By.cssSelector("a[href='/login']")).click();

        // Verify URL
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login", "Navigated to Signup/Login page");

        // Verify 'New User Signup!' is visible
        Assert.assertTrue(
                driver.findElement(By.xpath("//h2[text()='New User Signup!']")).isDisplayed(),
                "'New User Signup!' should be visible on the signup/login page"

        );
        // Verify 'Login to your account' is visible
        Assert.assertTrue(
                driver.findElement(By.xpath("//h2[text()='Login to your account']")).isDisplayed(),
                "'Login to your account' should be visible on the signup/login page"
        );
        // Verify signup name input field is visible
        Assert.assertTrue(
                driver.findElement(By.cssSelector("input[data-qa='signup-email']")).isDisplayed(),
                "Signup email input field should be visible"
        );


    }

    @Test
    //TC03 – Register New User Successfully (Positive Flow)
    public void registerNewUserSuccessfully() {


        // Click on 'Signup / Login' link
        driver.findElement(By.cssSelector("a[href='/login']")).click();

        // Enter name and email address
        driver.findElement(By.cssSelector("[name=\"name\"]")).sendKeys("TestUser");
        driver.findElement(By.cssSelector("[data-qa=\"signup-email\"]")).sendKeys("testuser0010@gmail.com");
        // Click 'Signup' button
        driver.findElement(By.cssSelector("[data-qa=\"signup-button\"]")).click();
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("password")).sendKeys("1122334455");
        driver.findElement(By.id("days")).sendKeys("10");
        driver.findElement(By.id("months")).sendKeys("May");
        driver.findElement(By.id("years")).sendKeys("1990");
        driver.findElement(By.id("first_name")).sendKeys("Test");
        driver.findElement(By.id("last_name")).sendKeys("User");
        driver.findElement(By.id("company")).sendKeys("TestCompany");
        driver.findElement(By.id("address1")).sendKeys("123 Test St");
        driver.findElement(By.id("address2")).sendKeys("Apt 4B");
        driver.findElement(By.id("country")).sendKeys("Egypt");
        driver.findElement(By.id("state")).sendKeys("TestState");
        driver.findElement(By.id("city")).sendKeys("TestCity");
        driver.findElement(By.id("zipcode")).sendKeys("12345");
        driver.findElement(By.id("mobile_number")).sendKeys("+201234567890");
        // Click 'Create Account' button
        driver.findElement(By.cssSelector("[data-qa=\"create-account\"]")).click();
        // Verify 'ACCOUNT CREATED!' is visible
        Assert.assertTrue(
                driver.findElement(By.xpath("//b[text()='Account Created!']")).isDisplayed(),
                "'ACCOUNT CREATED!' should be visible after account creation"
        );
        // Click 'Continue' button
        driver.findElement(By.cssSelector("[data-qa=\"continue-button\"]")).click();


    }

    @Test
    //TC04 – Register User With Invalid Email Format (Negative Flow)
    public void registerUserWithInvalidEmailFormat() {


        // Click on 'Signup / Login' link
        driver.findElement(By.cssSelector("a[href='/login']")).click();

        // Enter name and invalid email address
        driver.findElement(By.cssSelector("[name=\"name\"]")).sendKeys("TestUser");
        driver.findElement(By.cssSelector("[data-qa=\"signup-email\"]")).sendKeys("invalidemailformat");
        // Click 'Signup' button
        driver.findElement(By.cssSelector("[data-qa=\"signup-button\"]")).click();
        // Verify error message 'Invalid email address.' is visible
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login", "Should remain on the signup/login page after invalid email submission");

        ;


    }

    @Test
    //TC05 – Register User With Existing Email (Negative Flow)
    public void registerUserWithExistingEmail() {


        // Click on 'Signup / Login' link
        driver.findElement(By.cssSelector("a[href='/login']")).click();

        // Enter name and existing email address
        driver.findElement(By.cssSelector("[name=\"name\"]")).sendKeys("TestUser");
        driver.findElement(By.cssSelector("[data-qa=\"signup-email\"]")).sendKeys("testuser0001@gmail.com");
        // Click 'Signup' button
        driver.findElement(By.cssSelector("[data-qa=\"signup-button\"]")).click();
        // Verify error message 'Email Address already exist!' is visible
        Assert.assertTrue(
                driver.findElement(By.xpath("//*[text()='Email Address already exist!']")).isDisplayed(),
                "'Email Address already exist!' error message should be visible"
        );


    }

    @Test
    //TC06 – Login With Valid Credentials
    public void loginWithValidCredentials() {


        // Click on 'Signup / Login' link
        driver.findElement(By.cssSelector("a[href='/login']")).click();

        // Enter valid email address and password
        driver.findElement(By.cssSelector("[data-qa=\"login-email\"]")).sendKeys("1122334455@gmail.com");
        driver.findElement(By.cssSelector("[data-qa=\"login-password\"]")).sendKeys("1122334455");
        // Click 'Login' button
        driver.findElement(By.cssSelector("[data-qa=\"login-button\"]")).click();

        // Verify 'Logged in as username' is visible
        String username = "1122334455";
        Assert.assertTrue(
                driver.findElement(By.xpath("//*[contains(text(),'Logged in as')]")).getText().contains(username),
                "Correct username should appear in 'Logged in as'"
        );


    }

    @Test
    // TC07 – Login With Incorrect Credentials (Negative Flow)
    public void loginWithIncorrectCredentials() {


        // Click on 'Signup / Login' link
        driver.findElement(By.cssSelector("a[href='/login']")).click();

        // Enter invalid email address and password
        driver.findElement(By.cssSelector("[data-qa=\"login-email\"]")).sendKeys("1122334455@gmail.com");
        driver.findElement(By.cssSelector("[data-qa=\"login-password\"]")).sendKeys("wrongpassword");
        // Click 'Login' button
        driver.findElement(By.cssSelector("[data-qa=\"login-button\"]")).click();
        // Verify error message 'Your email or password is incorrect!' is visible
        Assert.assertTrue(
                driver.findElement(By.xpath("//*[text()='Your email or password is incorrect!']")).isDisplayed(),
                "'Your email or password is incorrect!' error message should be visible"
        );

    }

    @Test
    //TC08 – Logout User Successfully
    public void logoutUserSuccessfully() {

        // Click on 'Signup / Login' link
        driver.findElement(By.cssSelector("a[href='/login']")).click();

        // Enter valid email address and password
        driver.findElement(By.cssSelector("[data-qa=\"login-email\"]")).sendKeys("1122334455@gmail.com");
        driver.findElement(By.cssSelector("[data-qa=\"login-password\"]")).sendKeys("1122334455");
        // Click 'Login' button
        driver.findElement(By.cssSelector("[data-qa=\"login-button\"]")).click();
        // Click 'Logout' button
        driver.findElement(By.cssSelector("a[href='/logout']")).click();
        // Verify user is navigated to login page
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login", "User should be navigated to login page after logout");
    }

    @Test
    //TC9 – Delete User Account Successfully (Post-Login Cleanup)
    public void deleteUserAccountSuccessfully() {

        // Click on 'Signup / Login' link
        driver.findElement(By.cssSelector("a[href='/login']")).click();

        // Enter valid email address and password
        driver.findElement(By.cssSelector("[data-qa=\"login-email\"]")).sendKeys("testuser0010@gmail.com ");
        driver.findElement(By.cssSelector("[data-qa=\"login-password\"]")).sendKeys("1122334455");
        // Click 'Login' button
        driver.findElement(By.cssSelector("[data-qa=\"login-button\"]")).click();
        // Click 'Delete Account' button
        driver.findElement(By.cssSelector("a[href='/delete_account']")).click();
        // Verify 'ACCOUNT DELETED!' is visible
        Assert.assertTrue(
                driver.findElement(By.xpath("//b[text()='Account Deleted!']")).isDisplayed(),
                "'ACCOUNT DELETED!' should be visible after account deletion"
        );
        // Click 'Continue' button
        driver.findElement(By.cssSelector("[data-qa=\"continue-button\"]")).click();
    }
}