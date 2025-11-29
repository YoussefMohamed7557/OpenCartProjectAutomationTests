package test_cases.abanoub.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitAndClick(By locator) {
        WebElement element = waitForClickable(locator);
        try {
            element.click();
            waitForPageLoad();
        } catch (ElementClickInterceptedException e) {
            // try scroll -> click -> JS fallback
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                wait.until(ExpectedConditions.elementToBeClickable(element));
                try {
                    element.click();
                } catch (Exception clickException) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                }
                waitForPageLoad();
            } catch (Exception ex) {
                try {
                    ((JavascriptExecutor) driver).executeScript(
                            "try{document.querySelectorAll(\"iframe[id^='aswift'], iframe[src*='doubleclick']\").forEach(i=>i.style.display='none');}catch(e){}"
                    );
                } catch (Exception ignore) {}
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                waitForPageLoad();
            }
        }
    }

    protected void waitAndSendKeys(By locator, String text) {
        WebElement element = waitForVisible(locator);
        try { element.clear(); } catch (Exception ignore) {}
        element.sendKeys(text);
    }

    protected boolean isVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected String safeGetText(By locator) {
        try {
            return waitForVisible(locator).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    protected void clickUsingJS(By locator) {
        WebElement element = waitForVisible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        waitForPageLoad();
    }

    protected String getPageSource() {
        try {
            return driver.getPageSource();
        } catch (Exception e) {
            return "";
        }
    }

    protected void waitForPageLoad() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(15)).until(webDriver -> {
                Object readyState = ((JavascriptExecutor) webDriver).executeScript("return document.readyState");
                return readyState != null && readyState.toString().equals("complete");
            });
        } catch (Exception ignore) {
            // best-effort; don't fail tests because of this
        }
    }
}
