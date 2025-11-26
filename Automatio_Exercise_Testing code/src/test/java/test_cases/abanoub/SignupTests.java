package test_cases.abanoub;

import Pages.AccountCreationPage;
import Pages.AccountCreatedPage;
import Pages.HomePage;
import Pages.LoginSignupPage;
import org.testng.annotations.Test;

public class SignupTests extends TestBase {

    @Test
    public void TC02_verifyNavigationToSignupPage() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.assertURL();
        loginSignupPage.assertTitle();

        loginSignupPage.assertSignUpMessageVisible();
        loginSignupPage.assertNameAndEmailInputVisible();
        loginSignupPage.assertSignUpButtonVisible();

        loginSignupPage.assertLoginMessageVisible();
        loginSignupPage.assertLoginEmailAndPasswordInputVisible();
        loginSignupPage.assertLoginButtonVisible();
    }

    @Test
    public void TC03_registerNewUserSuccessfully() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        AccountCreationPage accountCreationPage = new AccountCreationPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@example.com";
        loginSignupPage.enterNameAndEmail("TestUser0010", uniqueEmail);
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

        accountCreatedPage.assertAccountCreatedMsgVisible();
        accountCreatedPage.clickContinueButton();
        homePage.assertLoggedInAsUserVisible();
    }

    @Test
    public void TC04_registerUserWithInvalidEmailFormat() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterNameAndEmail("TestUser", "invalidemailformat");
        loginSignupPage.clickSignUpButton();

        loginSignupPage.assertUrlBeTheSameWrongEmail();
        loginSignupPage.assertSignUpMessageVisible();
        loginSignupPage.assertNameAndEmailInputVisible();
        loginSignupPage.assertSignUpButtonVisible();
    }

    @Test
    public void TC05_registerUserWithExistingEmail() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterNameAndEmail("TestUser", "testuser0001@gmail.com");
        loginSignupPage.clickSignUpButton();

        loginSignupPage.assertUrlBeTheSameWrongEmail();
        loginSignupPage.assertEmailExistingErrorVisible();
        loginSignupPage.assertSignUpMessageVisible();
        loginSignupPage.assertNameAndEmailInputVisible();
        loginSignupPage.assertSignUpButtonVisible();
    }

    @Test
    public void TC10_signupWithMissingRequiredFields() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        loginSignupPage.enterNameAndEmail("emptyuser", "");
        loginSignupPage.clickSignUpButton();

        loginSignupPage.assertUrlBeTheSameWrongEmail();
        loginSignupPage.assertSignUpMessageVisible();
        loginSignupPage.assertNameAndEmailInputVisible();
        loginSignupPage.assertSignUpButtonVisible();
    }

    @Test
    public void TC11_signupWithWeakPassword() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        AccountCreationPage accountCreationPage = new AccountCreationPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        String weakEmail = "weakpass" + System.currentTimeMillis() + "@example.com";
        loginSignupPage.enterNameAndEmail("WeakPassUser", weakEmail);
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
}
