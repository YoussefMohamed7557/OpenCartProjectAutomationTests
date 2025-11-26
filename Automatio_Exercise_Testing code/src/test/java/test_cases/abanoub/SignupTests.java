package test_cases.abanoub;

import Pages.AccountCreationPage;
import Pages.AccountCreatedPage;
import Pages.HomePage;
import Pages.LoginSignupPage;
import org.openqa.selenium.By;
import org.testng.Assert;
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
    public void TC10_signupWithMissingRequiredEmailField() {

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

    @Test
    public void TC18_signupWithMissingNameField() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        String email = "noname" + System.currentTimeMillis() + "@example.com";
        loginSignupPage.enterNameAndEmail("", email);
        loginSignupPage.clickSignUpButton();

        loginSignupPage.assertUrlBeTheSameWrongEmail();
        loginSignupPage.assertSignUpMessageVisible();
        loginSignupPage.assertNameAndEmailInputVisible();
        loginSignupPage.assertSignUpButtonVisible();
    }

    @Test
    public void TC19_signupWithoutPasswordOnAccountCreation() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        AccountCreationPage accountCreationPage = new AccountCreationPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        String email = "nopass" + System.currentTimeMillis() + "@example.com";
        loginSignupPage.enterNameAndEmail("NoPassUser", email);
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

        boolean accountCreatedVisible =
                !driver.findElements(By.xpath("//h2[@data-qa='account-created']")).isEmpty();

        Assert.assertFalse(
                accountCreatedVisible,
                "Account should NOT be created when password is missing"
        );
    }

    @Test
    public void TC20_signupWithMaxLengthData() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        AccountCreationPage accountCreationPage = new AccountCreationPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        String longName = "UserWithVeryLongName_UserWithVeryLongName_1234567890";
        String longPassword = "Pass_" + "1234567890".repeat(3);
        String uniqueEmail = "longdata" + System.currentTimeMillis() + "@example.com";

        loginSignupPage.enterNameAndEmail(longName, uniqueEmail);
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

        accountCreatedPage.assertAccountCreatedMsgVisible();
        accountCreatedPage.clickContinueButton();
        homePage.assertLoggedInAsUserVisible();
    }

    @Test
    public void TC21_signupWithSpecialCharactersInName() {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        AccountCreationPage accountCreationPage = new AccountCreationPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        String specialName = "@@@ User ### !!!";
        String uniqueEmail = "specialname" + System.currentTimeMillis() + "@example.com";

        loginSignupPage.enterNameAndEmail(specialName, uniqueEmail);
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

        accountCreatedPage.assertAccountCreatedMsgVisible();
        accountCreatedPage.clickContinueButton();
        homePage.assertLoggedInAsUserVisible();
    }
}
