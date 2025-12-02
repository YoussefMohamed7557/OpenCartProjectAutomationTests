package test_cases.ereny.Tests;

import test_cases.ereny.Pages.TestCases_Page;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class TestCaseTests extends TestBase{


    TestCases_Page testCasesPage;

    @Description("Test Case 7: Verify Test Cases Page")
    @Test
    public void TC05_verifyNavigateToTestCasesPage(){
        testCasesPage = new TestCases_Page(driver);
        testCasesPage.Navigate();
        testCasesPage.ClickOnTextCase_Button();
        testCasesPage.AssertURL();

    }

}
