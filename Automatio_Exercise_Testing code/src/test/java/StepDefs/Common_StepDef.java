package StepDefs;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class Common_StepDef {

    @Given("the browser is open")
    public void browser_is_open() {
        WebDriver driver = Hooks.driver;
        if (driver == null) {
            throw new IllegalStateException("Hooks.driver is null — the @Before hook in Hooks may have failed. Check console for earlier errors.");
        }
        // shared precondition only — pages are constructed inside specific StepDefs
    }
}
