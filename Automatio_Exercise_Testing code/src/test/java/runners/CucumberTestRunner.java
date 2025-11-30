package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"StepDefs"},
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        },
        monochrome = true,
        tags = "not @bug"
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
