package test_cases.abanoub;

import org.testng.annotations.Test;
import Pages.*;
import Pages.LoginSignupPage;

import java.time.Duration;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SignupTests extends TestBase {

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
