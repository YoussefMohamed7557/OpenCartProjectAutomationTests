package test_cases.abanoub;
import Pages.AccountCreatedPage;
import Pages.AccountCreationPage;
import Pages.HomePage;
import Pages.LoginSignupPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
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
        //Navigate to home page
        homePage.navigateToHomePage();
        // URL check
        homePage.assertURL();
        // Title check
        homePage.assertTitle();
        // Login link visible
        homePage.assertLoginLinkVisible();
        // Features section visible
        homePage.assertFeaturedItemsVisible();
        // Logo visible
        homePage.assertHomePageLogoVisible();
        // Signup link visible
        homePage.assertSignUpLinkVisible();

    }

    @Test
    //TC02 – Verify Navigation to Signup Page
    public void verifyNavigationToSignupPage() {
        //Navigate to home page
        homePage.navigateToHomePage();

        // Click on 'Signup / Login' link
        homePage.clickLoginLink();
        // Verify URL
        loginSignupPage.assertURL();
        // Verify 'New User Signup!' is visible
        loginSignupPage.assertSignUpMessageVisible();
        // Verify 'Login to your account' is visible
        loginSignupPage.assertLoginMessageVisible();
        // Verify signup name and E-mail input field is visible
        loginSignupPage.assertNameAndEmailInputVisible();
        // Verify 'Signup' button is visible
        loginSignupPage.assertSignUpButtonVisible();
        //Verify login email and password input field is visible
        loginSignupPage.assertLoginEmailAndPasswordInputVisible();
        //Verify 'Login' button is visible
        loginSignupPage.assertLoginButtonVisible();
        // Verify title
        loginSignupPage.assertTitle();


    }

    @Test
    //TC03 – Register New User Successfully (Positive Flow)
    public void registerNewUserSuccessfully() {
        //Navigate to home page
        homePage.navigateToHomePage();

        // Click on 'Signup / Login' link
        homePage.clickLoginLink();
        // Enter name and email address
        loginSignupPage.enterNameAndEmail("TestUser0010", "abanoub02@gmail.com");

        // Submit signup Button
        loginSignupPage.clickSignUpButton();

        // Fill details: Title, Name, Email, Password, Date of birth, First name,
        // Last name, Company, Address, Country, State, City, Zipcode, Mobile Number
        accountCreationPage.selectTitleMr();
        accountCreationPage.enterPassword("1122334455");
        accountCreationPage.selectDay("10");
        accountCreationPage.selectMonth("May");
        accountCreationPage.selectYear("1990");
        accountCreationPage.checkNewsletter();
        accountCreationPage.checkOffers();
        accountCreationPage.enterFirstName("Test");
        accountCreationPage.enterLastName("User");
        accountCreationPage.enterCompany("TestCompany");
        accountCreationPage.enterAddress1("123 Test St");
        accountCreationPage.enterAddress2("Apt 4B");
        accountCreationPage.selectCountry("Egypt");
        accountCreationPage.enterState("TestState");
        accountCreationPage.enterCity("TestCity");
        accountCreationPage.enterZipCode("12345");
        accountCreationPage.enterMobileNumber("1234567890");
        // Click 'Create Account' button
        accountCreationPage.clickCreateAccountButton();
        // Verify 'ACCOUNT CREATED!' is visible
        accountCreatedPage.assertAccountCreatedMsgVisible();
        // Click 'Continue' button
        accountCreatedPage.clickContinueButton();
        // Verify 'Logged in as username' is visible
        homePage.assertLoggedInAsUserVisible();


    }

    @Test
    //TC04 – Register User With Invalid Email Format (Negative Flow)
    public void registerUserWithInvalidEmailFormat() {
        //Navigate to home page
        homePage.navigateToHomePage();

        // Click on 'Signup / Login' link
        homePage.clickLoginLink();

        // Enter name and invalid email address
        loginSignupPage.enterNameAndEmail("TestUser", "invalidemailformat");
        // Click 'Signup' button
        loginSignupPage.clickSignUpButton();
        // Verify error message 'Invalid email address.' is visible
        loginSignupPage.assertUrlBeTheSameWrongEmail();


    }

    @Test
    //TC05 – Register User With Existing Email (Negative Flow)
    public void registerUserWithExistingEmail() {
        //Navigate to home page
        homePage.navigateToHomePage();

        // Click on 'Signup / Login' link
        homePage.clickLoginLink();
        // Enter name and existing email address
        loginSignupPage.enterNameAndEmail("TestUser", "testuser0001@gmail.com");
        // Click 'Signup' button
        loginSignupPage.clickSignUpButton();
        // Verify error message 'Email Address already exist!' is visible
        loginSignupPage.assertEmailExistingErrorVisible();


    }

    @Test
    //TC06 – Login With Valid Credentials
    public void loginWithValidCredentials() {
        //Navigate to home page
        homePage.navigateToHomePage();

        // Click on 'Signup / Login' link
        homePage.clickLoginLink();

        // Enter valid email address and password
        loginSignupPage.enterLoginEmailAndPassword("1122334455@gmail.com", "1122334455");
        // Click 'Login' button
        loginSignupPage.clickLoginButton();
        // Verify 'Logged in as username' is visible
        homePage.assertLoggedInAsUserVisible();


    }

    @Test
    // TC07 – Login With Incorrect Credentials (Negative Flow)
    public void loginWithIncorrectCredentials() {
        //Navigate to home page
        homePage.navigateToHomePage();

        // Click on 'Signup / Login' link
        homePage.clickLoginLink();

        // Enter invalid email address and password
        loginSignupPage.enterLoginEmailAndPassword("1122334455@gmail.com", "wrongpassword");
        // Click 'Login' button
        loginSignupPage.clickLoginButton();
        // Verify error message 'Your email or password is incorrect!' is visible
        loginSignupPage.assertWrongEmailOrPasswordErrorVisible();

    }

    @Test
    //TC08 – Logout User Successfully
    public void logoutUserSuccessfully() {
        //Navigate to home page
        homePage.navigateToHomePage();
        // Click on 'Signup / Login' link
        homePage.clickLoginLink();

        // Enter valid email address and password
        loginSignupPage.enterLoginEmailAndPassword("1122334455@gmail.com", "1122334455");
        // Click 'Login' button
        loginSignupPage.clickLoginButton();
        // Verify 'Logout' button is visible
        homePage.assertLogoutLinkVisible();
        // Click 'Logout' button
        homePage.clickLogoutButton();
        // Verify user is navigated to login page
        homePage.assertHomePageLoadedAfterLogout();
    }

    @Test
    //TC9 – Delete User Account Successfully (Post-Login Cleanup)
    public void deleteUserAccountSuccessfully() {
        //Navigate to home page
        homePage.navigateToHomePage();
        // Click on 'Signup / Login' link
        homePage.clickLoginLink();

        // Enter valid email address and password
        loginSignupPage.enterLoginEmailAndPassword("abanoub01@gmail.com", "1122334455");
        // Click 'Login' button
        loginSignupPage.clickLoginButton();
        // Click 'Delete Account' button
        homePage.clickDeleteAccountButton();
        // Verify 'ACCOUNT DELETED!' is visible
        homePage.assertAccountDeletedMessageVisible();
        // Click 'Continue' button
        homePage.clickContinueButton();
    }
}