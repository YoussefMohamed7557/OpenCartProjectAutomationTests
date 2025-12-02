package StepDefs;

import io.cucumber.java.en.*;
import test_cases.ereny.Pages.TestCases_Page;

public class TestCases_StepDef {

    private TestCases_Page testCasesPage;

    @Given("the user is on the home page for test cases")
    public void theUserIsOnTheHomePageForTestCases() {
        testCasesPage = new TestCases_Page(Hooks.getDriver());
        testCasesPage.Navigate();
    }

    @When("the user clicks on the Test Cases link")
    public void theUserClicksOnTheTestCasesLink() {
        testCasesPage.ClickOnTextCase_Button();
    }

    @Then("the Test Cases page URL should be correct")
    public void theTestCasesPageURLShouldBeCorrect() {
        testCasesPage.AssertURL();  //
    }
}