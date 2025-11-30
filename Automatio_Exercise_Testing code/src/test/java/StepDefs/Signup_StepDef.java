package StepDefs;


import io.cucumber.java.en.*;
import test_cases.abanoub.Pages.AccountCreatedPage;
import test_cases.abanoub.Pages.AccountCreationPage;
import test_cases.abanoub.Pages.HomePage;
import test_cases.abanoub.Pages.LoginSignupPage;

import java.time.Duration;

public class Signup_StepDef {

    private Duration getTimeout() {
        int seconds = Hooks.getDefaultTimeoutSeconds();
        return Duration.ofSeconds(seconds > 0 ? seconds : 10);
    }

    private String generateEmail(String prefix) {
        return prefix + System.currentTimeMillis() + "@example.com";
    }

    @Then("the signup and login sections should be visible")
    public void theSignupAndLoginSectionsShouldBeVisible() {
        LoginSignupPage loginPage = new LoginSignupPage(Hooks.getDriver(), getTimeout());
        loginPage.assertSignUpMessageVisible();
        loginPage.assertSignupInputsVisible();
        loginPage.assertLoginMessageVisible();
        loginPage.assertLoginInputsVisible();
    }

    @When("the user signs up with a unique valid name and email")
    public void theUserSignsUpWithAUniqueValidNameAndEmail() {
        LoginSignupPage loginPage = new LoginSignupPage(Hooks.getDriver(), getTimeout());
        String uniqueEmail = generateEmail("testuser");
        loginPage.enterName("TestUser0010");
        loginPage.enterSignupEmail(uniqueEmail);
        loginPage.clickSignUpButton();
    }

    @When("the user completes the account creation form with valid data")
    public void theUserCompletesTheAccountCreationFormWithValidData() {
        AccountCreationPage createPage = new AccountCreationPage(Hooks.getDriver(), getTimeout());

        createPage.selectTitleMr();
        createPage.enterPassword("1122334455");
        createPage.selectDay("10");
        createPage.selectMonth("May");
        createPage.selectYear("1990");
        createPage.checkNewsletter();
        createPage.checkOffers();
        createPage.enterFirstName("Test");
        createPage.enterLastName("User");
        createPage.enterCompany("TestCompany");
        createPage.enterAddress1("123 Test St");
        createPage.enterAddress2("Apt 4B");
        createPage.selectCountry("India");
        createPage.enterState("TestState");
        createPage.enterCity("TestCity");
        createPage.enterZipCode("12345");
        createPage.enterMobileNumber("1234567890");
        createPage.clickCreateAccountButton();
    }

    @Then("the account should be created successfully")
    public void theAccountShouldBeCreatedSuccessfully() {
        AccountCreatedPage createdPage = new AccountCreatedPage(Hooks.getDriver(), getTimeout());
        createdPage.assertAccountCreatedMessageVisible();
        createdPage.clickContinueButton();
    }

    @Then("the user should be logged in after signup")
    public void theUserShouldBeLoggedInAfterSignup() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        homePage.assertUserLoggedIn();
    }

    @When("the user signs up with name {string} and email {string}")
    public void theUserSignsUpWithNameAndEmail(String name, String email) {
        LoginSignupPage loginPage = new LoginSignupPage(Hooks.getDriver(), getTimeout());
        loginPage.enterName(name);
        loginPage.enterSignupEmail(email);
        loginPage.clickSignUpButton();
    }

    @Then("the user should stay on the signup or login page")
    public void theUserShouldStayOnTheSignupOrLoginPage() {
        LoginSignupPage loginPage = new LoginSignupPage(Hooks.getDriver(), getTimeout());
        loginPage.assertStayedOnSignupOrLoginAfterInvalidData();
    }

    @Then("an email already exists error should be visible")
    public void anEmailAlreadyExistsErrorShouldBeVisible() {
        LoginSignupPage loginPage = new LoginSignupPage(Hooks.getDriver(), getTimeout());
        loginPage.assertEmailAlreadyExistsErrorVisible();
    }

    @When("the user completes the account creation form with a weak password")
    public void theUserCompletesTheAccountCreationFormWithAWeakPassword() {
        AccountCreationPage createPage = new AccountCreationPage(Hooks.getDriver(), getTimeout());

        createPage.selectTitleMr();
        createPage.enterPassword("123");
        createPage.selectDay("1");
        createPage.selectMonth("January");
        createPage.selectYear("2000");
        createPage.enterFirstName("Weak");
        createPage.enterLastName("Pass");
        createPage.enterCompany("TestCo");
        createPage.enterAddress1("Addr1");
        createPage.selectCountry("India");
        createPage.enterState("State");
        createPage.enterCity("City");
        createPage.enterZipCode("00000");
        createPage.enterMobileNumber("0000000000");
        createPage.clickCreateAccountButton();
    }

    @Then("a weak password error should be visible")
    public void aWeakPasswordErrorShouldBeVisible() {
        AccountCreationPage createPage = new AccountCreationPage(Hooks.getDriver(), getTimeout());
        createPage.assertWeakPasswordErrorVisible();
    }

    @When("the user signs up with name {string} and email generated dynamically")
    public void theUserSignsUpWithNameAndEmailGeneratedDynamically(String name) {
        LoginSignupPage loginPage = new LoginSignupPage(Hooks.getDriver(), getTimeout());
        String email = generateEmail("dynamic");
        loginPage.enterName(name);
        loginPage.enterSignupEmail(email);
        loginPage.clickSignUpButton();
    }

    @When("the user completes the account creation form without password")
    public void theUserCompletesTheAccountCreationFormWithoutPassword() {
        AccountCreationPage createPage = new AccountCreationPage(Hooks.getDriver(), getTimeout());

        createPage.selectTitleMr();
        // no password
        createPage.selectDay("5");
        createPage.selectMonth("May");
        createPage.selectYear("1995");
        createPage.enterFirstName("NoPass");
        createPage.enterLastName("User");
        createPage.enterCompany("TestCo");
        createPage.enterAddress1("Address 1");
        createPage.selectCountry("India");
        createPage.enterState("State");
        createPage.enterCity("City");
        createPage.enterZipCode("11111");
        createPage.enterMobileNumber("01000000000");
        createPage.clickCreateAccountButton();
    }

    @Then("the account should not be created")
    public void theAccountShouldNotBeCreated() {
        AccountCreatedPage createdPage = new AccountCreatedPage(Hooks.getDriver(), getTimeout());
        createdPage.assertAccountNotCreatedInSource();
    }

    @When("the user signs up with max length valid data")
    public void theUserSignsUpWithMaxLengthValidData() {
        LoginSignupPage loginPage = new LoginSignupPage(Hooks.getDriver(), getTimeout());
        AccountCreationPage createPage = new AccountCreationPage(Hooks.getDriver(), getTimeout());

        String longName = "UserWithVeryLongName_UserWithVeryLongName_1234567890";
        String longPassword = "Pass_" + "1234567890".repeat(3);
        String uniqueEmail = generateEmail("longdata");

        loginPage.enterName(longName);
        loginPage.enterSignupEmail(uniqueEmail);
        loginPage.clickSignUpButton();

        createPage.selectTitleMr();
        createPage.enterPassword(longPassword);
        createPage.selectDay("15");
        createPage.selectMonth("June");
        createPage.selectYear("1985");
        createPage.checkNewsletter();
        createPage.checkOffers();
        createPage.enterFirstName(longName);
        createPage.enterLastName("LastNameWithLongValue_1234567890");
        createPage.enterCompany("VeryLongCompanyName_1234567890");
        createPage.enterAddress1("Address line 1 - very very long address 1234567890");
        createPage.enterAddress2("Address line 2 - extra description 1234567890");
        createPage.selectCountry("India");
        createPage.enterState("LongStateName_1234567890");
        createPage.enterCity("LongCityName_1234567890");
        createPage.enterZipCode("123456");
        createPage.enterMobileNumber("01234567890");
        createPage.clickCreateAccountButton();
    }

    @When("the user signs up with special characters in name")
    public void theUserSignsUpWithSpecialCharactersInName() {
        LoginSignupPage loginPage = new LoginSignupPage(Hooks.getDriver(), getTimeout());
        AccountCreationPage createPage = new AccountCreationPage(Hooks.getDriver(), getTimeout());

        String specialName = "@@@ User ### !!!";
        String uniqueEmail = generateEmail("specialname");

        loginPage.enterName(specialName);
        loginPage.enterSignupEmail(uniqueEmail);
        loginPage.clickSignUpButton();

        createPage.selectTitleMr();
        createPage.enterPassword("1122334455");
        createPage.selectDay("20");
        createPage.selectMonth("July");
        createPage.selectYear("1992");
        createPage.enterFirstName(specialName);
        createPage.enterLastName("Test");
        createPage.enterCompany("SpecialNameCo");
        createPage.enterAddress1("Some Address");
        createPage.selectCountry("India");
        createPage.enterState("State");
        createPage.enterCity("City");
        createPage.enterZipCode("22222");
        createPage.enterMobileNumber("01011111111");
        createPage.clickCreateAccountButton();
    }

    @Then("the logged in username should be sanitized")
    public void theLoggedInUsernameShouldBeSanitized() {
        HomePage homePage = new HomePage(Hooks.getDriver(), getTimeout());
        homePage.assertLoggedInUsernameSanitized();
    }

    @When("the user fills the account creation form and clicks create account multiple times")
    public void theUserFillsTheAccountCreationFormAndClicksCreateAccountMultipleTimes() {
        AccountCreationPage createPage = new AccountCreationPage(Hooks.getDriver(), getTimeout());

        createPage.selectTitleMr();
        createPage.enterPassword("1122334455");
        createPage.selectDay("10");
        createPage.selectMonth("May");
        createPage.selectYear("1990");
        createPage.enterFirstName("Multi");
        createPage.enterLastName("Click");
        createPage.enterCompany("TestCo");
        createPage.enterAddress1("123 Test St");
        createPage.selectCountry("India");
        createPage.enterState("State");
        createPage.enterCity("City");
        createPage.enterZipCode("99999");
        createPage.enterMobileNumber("01000000001");

        createPage.clickCreateAccountButton();
    }

    @Then("the account should be created or no weak password error should be visible")
    public void theAccountShouldBeCreatedOrNoWeakPasswordErrorShouldBeVisible() {
        AccountCreationPage createPage = new AccountCreationPage(Hooks.getDriver(), getTimeout());
        createPage.assertAfterMultipleClickAccountCreatedOrNoWeakPassword();
    }
}
