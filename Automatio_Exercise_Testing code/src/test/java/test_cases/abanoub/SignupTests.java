package test_cases.abanoub;

import org.testng.annotations.Test;
import test_cases.abanoub.Pages.*;
import test_cases.abanoub.Pages.LoginSignupPage;

import java.time.Duration;

// ---------- Allure Annotations ----------
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Description;

@Epic("Authentication")
@Feature("User Signup")
public class SignupTests extends TestBase {

    @Story("Navigate to signup/login page")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that navigation from home page to signup/login page works and both sections are visible.")
    @Test
    public void TC02_verifyNavigationToSignupPage() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.assertOnLoginOrSignupPage();
        loginSignupPage.assertLoginSignupTitleIsCorrect();

        loginSignupPage.assertSignUpMessageVisible();
        loginSignupPage.assertSignupInputsVisible();
        loginSignupPage.assertLoginMessageVisible();
        loginSignupPage.assertLoginInputsVisible();
    }

    @Story("Register new user successfully")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validate that a new user can sign up with unique valid data, complete account creation form, and be logged in.")
    @Test
    public void TC03_registerNewUserSuccessfully() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        AccountCreationPage accountCreationPage = new AccountCreationPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@example.com";
        loginSignupPage.enterName("TestUser0010");
        loginSignupPage.enterSignupEmail(uniqueEmail);
        loginSignupPage.clickSignUpButton();

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
        accountCreationPage.selectCountry("India");
        accountCreationPage.enterState("TestState");
        accountCreationPage.enterCity("TestCity");
        accountCreationPage.enterZipCode("12345");
        accountCreationPage.enterMobileNumber("1234567890");
        accountCreationPage.clickCreateAccountButton();

        accountCreatedPage.assertAccountCreatedMessageVisible();
        accountCreatedPage.clickContinueButton();

        homePage.assertUserLoggedIn();
    }

    @Story("Signup with invalid email format")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that user cannot sign up with invalid email format and remains on the signup/login page.")
    @Test
    public void TC04_registerUserWithInvalidEmailFormat() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterName("TestUser");
        loginSignupPage.enterSignupEmail("invalidemailformat");
        loginSignupPage.clickSignUpButton();

        loginSignupPage.assertOnLoginOrSignupPage();
        loginSignupPage.assertSignUpMessageVisible();
    }

    @Story("Signup with existing email")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that the application shows an 'email already exists' error when signing up with an existing email.")
    @Test
    public void TC05_registerUserWithExistingEmail() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterName("TestUser");
        loginSignupPage.enterSignupEmail("testuser0001@gmail.com");
        loginSignupPage.clickSignUpButton();

        loginSignupPage.assertEmailAlreadyExistsErrorVisible();
    }

    @Story("Signup with missing email field")
    @Severity(SeverityLevel.MINOR)
    @Description("Ensure that when the email field is empty during signup, the user stays on the signup/login page.")
    @Test
    public void TC10_signupWithMissingRequiredEmailField() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterName("emptyuser");
        loginSignupPage.enterSignupEmail("");
        loginSignupPage.clickSignUpButton();

        loginSignupPage.assertOnLoginOrSignupPage();
    }

    @Story("Signup with weak password (BUG)")
    @Severity(SeverityLevel.NORMAL)
    @Description("BUG scenario: Validate behavior when the user completes account creation with a weak password and check if weak password error appears.")
    @Test
    public void TC11_signupWithWeakPassword() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        AccountCreationPage accountCreationPage = new AccountCreationPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        String weakEmail = "weakpass" + System.currentTimeMillis() + "@example.com";
        loginSignupPage.enterName("WeakPassUser");
        loginSignupPage.enterSignupEmail(weakEmail);
        loginSignupPage.clickSignUpButton();

        accountCreationPage.selectTitleMr();
        accountCreationPage.enterPassword("123");
        accountCreationPage.selectDay("1");
        accountCreationPage.selectMonth("January");
        accountCreationPage.selectYear("2000");
        accountCreationPage.enterFirstName("Weak");
        accountCreationPage.enterLastName("Pass");
        accountCreationPage.enterCompany("TestCo");
        accountCreationPage.enterAddress1("Addr1");
        accountCreationPage.selectCountry("India");
        accountCreationPage.enterState("State");
        accountCreationPage.enterCity("City");
        accountCreationPage.enterZipCode("00000");
        accountCreationPage.enterMobileNumber("0000000000");
        accountCreationPage.clickCreateAccountButton();

        accountCreationPage.assertWeakPasswordErrorVisible();
    }

    @Story("Signup with missing name field")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that signup with an empty name field keeps the user on the signup/login page.")
    @Test
    public void TC18_signupWithMissingNameField() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        String email = "noname" + System.currentTimeMillis() + "@example.com";
        loginSignupPage.enterName("");
        loginSignupPage.enterSignupEmail(email);
        loginSignupPage.clickSignUpButton();

        loginSignupPage.assertOnLoginOrSignupPage();
    }

    @Story("Signup without password on account creation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that the account is not created if the user submits the account creation form without a password.")
    @Test
    public void TC19_signupWithoutPasswordOnAccountCreation() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        AccountCreationPage accountCreationPage = new AccountCreationPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        String email = "nopass" + System.currentTimeMillis() + "@example.com";
        loginSignupPage.enterName("NoPassUser");
        loginSignupPage.enterSignupEmail(email);
        loginSignupPage.clickSignUpButton();

        accountCreationPage.selectTitleMr();
        accountCreationPage.selectDay("5");
        accountCreationPage.selectMonth("May");
        accountCreationPage.selectYear("1995");
        accountCreationPage.enterFirstName("NoPass");
        accountCreationPage.enterLastName("User");
        accountCreationPage.enterCompany("TestCo");
        accountCreationPage.enterAddress1("Address 1");
        accountCreationPage.selectCountry("India");
        accountCreationPage.enterState("State");
        accountCreationPage.enterCity("City");
        accountCreationPage.enterZipCode("11111");
        accountCreationPage.enterMobileNumber("01000000000");
        accountCreationPage.clickCreateAccountButton();

        accountCreatedPage.assertAccountNotCreatedInSource();
        loginSignupPage.assertStayedOnSignupOrLoginAfterInvalidData();
    }

    @Story("Signup with max length data")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the system accepts maximum length values in signup and account creation fields and successfully creates an account.")
    @Test
    public void TC20_signupWithMaxLengthData() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        AccountCreationPage accountCreationPage = new AccountCreationPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        String longName = "UserWithVeryLongName_UserWithVeryLongName_1234567890";
        String longPassword = "Pass_" + "1234567890".repeat(3);
        String uniqueEmail = "longdata" + System.currentTimeMillis() + "@example.com";

        loginSignupPage.enterName(longName);
        loginSignupPage.enterSignupEmail(uniqueEmail);
        loginSignupPage.clickSignUpButton();

        accountCreationPage.selectTitleMr();
        accountCreationPage.enterPassword(longPassword);
        accountCreationPage.selectDay("15");
        accountCreationPage.selectMonth("June");
        accountCreationPage.selectYear("1985");
        accountCreationPage.checkNewsletter();
        accountCreationPage.checkOffers();
        accountCreationPage.enterFirstName(longName);
        accountCreationPage.enterLastName("LastNameWithLongValue_1234567890");
        accountCreationPage.enterCompany("VeryLongCompanyName_1234567890");
        accountCreationPage.enterAddress1("Address line 1 - very very long address 1234567890");
        accountCreationPage.enterAddress2("Address line 2 - extra description 1234567890");
        accountCreationPage.selectCountry("India");
        accountCreationPage.enterState("LongStateName_1234567890");
        accountCreationPage.enterCity("LongCityName_1234567890");
        accountCreationPage.enterZipCode("123456");
        accountCreationPage.enterMobileNumber("01234567890");
        accountCreationPage.clickCreateAccountButton();

        accountCreatedPage.assertAccountCreatedMessageVisible();
        accountCreatedPage.clickContinueButton();
        homePage.assertUserLoggedIn();
    }

    @Story("Signup with special characters in name (BUG)")
    @Severity(SeverityLevel.MINOR)
    @Description("BUG scenario: Verify that signing up with special characters in the name is allowed and that the displayed username is sanitized.")
    @Test
    public void TC21_signupWithSpecialCharactersInName() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        AccountCreationPage accountCreationPage = new AccountCreationPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        String specialName = "@@@ User ### !!!";
        String uniqueEmail = "specialname" + System.currentTimeMillis() + "@example.com";

        loginSignupPage.enterName(specialName);
        loginSignupPage.enterSignupEmail(uniqueEmail);
        loginSignupPage.clickSignUpButton();

        accountCreationPage.selectTitleMr();
        accountCreationPage.enterPassword("1122334455");
        accountCreationPage.selectDay("20");
        accountCreationPage.selectMonth("July");
        accountCreationPage.selectYear("1992");
        accountCreationPage.enterFirstName(specialName);
        accountCreationPage.enterLastName("Test");
        accountCreationPage.enterCompany("SpecialNameCo");
        accountCreationPage.enterAddress1("Some Address");
        accountCreationPage.selectCountry("India");
        accountCreationPage.enterState("State");
        accountCreationPage.enterCity("City");
        accountCreationPage.enterZipCode("22222");
        accountCreationPage.enterMobileNumber("01011111111");
        accountCreationPage.clickCreateAccountButton();

        accountCreatedPage.assertAccountCreatedMessageVisible();
        accountCreatedPage.clickContinueButton();

        homePage.assertLoggedInUsernameSanitized();
    }

    @Story("Multiple clicks on Create Account button")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that multiple clicks on the Create Account button do not crash the system and that either the account is created or no weak password error appears.")
    @Test
    public void TC30_multipleClicksOnCreateAccountButton() {
        HomePage homePage = new HomePage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        LoginSignupPage loginSignupPage = new LoginSignupPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));
        AccountCreationPage accountCreationPage = new AccountCreationPage(getDriver(), Duration.ofSeconds(defaultTimeoutSeconds));

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        String uniqueEmail = "multiclick" + System.currentTimeMillis() + "@example.com";
        loginSignupPage.enterName("MultiClickUser");
        loginSignupPage.enterSignupEmail(uniqueEmail);
        loginSignupPage.clickSignUpButton();

        accountCreationPage.selectTitleMr();
        accountCreationPage.enterPassword("1122334455");
        accountCreationPage.selectDay("10");
        accountCreationPage.selectMonth("May");
        accountCreationPage.selectYear("1990");
        accountCreationPage.enterFirstName("Multi");
        accountCreationPage.enterLastName("Click");
        accountCreationPage.enterCompany("TestCo");
        accountCreationPage.enterAddress1("123 Test St");
        accountCreationPage.selectCountry("India");
        accountCreationPage.enterState("State");
        accountCreationPage.enterCity("City");
        accountCreationPage.enterZipCode("99999");
        accountCreationPage.enterMobileNumber("01000000001");

        accountCreationPage.clickCreateAccountButton();

        accountCreationPage.assertAfterMultipleClickAccountCreatedOrNoWeakPassword();
    }
}
