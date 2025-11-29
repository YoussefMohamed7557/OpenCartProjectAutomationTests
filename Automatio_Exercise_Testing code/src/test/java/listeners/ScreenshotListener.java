package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import test_cases.abanoub.TestBase;

import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

public class ScreenshotListener implements ITestListener {

    private static final String DEFAULT_DIR = "target/screenshots";

    @Override
    public void onTestFailure(ITestResult result) {
        Object instance = result.getInstance();
        if (!(instance instanceof TestBase)) return;
        TestBase base = (TestBase) instance;
        if (base.getDriver() == null) return;

        String testClass = result.getTestClass().getName();
        String method = result.getMethod().getMethodName();
        String timestamp = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS")
                .withZone(ZoneId.systemDefault())
                .format(Instant.now());
        String dir = (base.getScreenshotsDir() != null && !base.getScreenshotsDir().trim().isEmpty())
                ? base.getScreenshotsDir()
                : DEFAULT_DIR;

        String fileBaseName = testClass + "." + method + "_" + timestamp;

        try {
            // 1) Save screenshot to filesystem
            Files.createDirectories(Paths.get(dir));
            File src = ((TakesScreenshot) base.getDriver()).getScreenshotAs(OutputType.FILE);
            Path pngDest = Paths.get(dir, fileBaseName + ".png");
            Files.copy(src.toPath(), pngDest);

            // Screenshot saved and attached to Allure
            // (No console printing to keep logs clean)

            // 2) Attach screenshot to Allure report (مهم جداً)
            byte[] bytes = ((TakesScreenshot) base.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(fileBaseName, "image/png", new ByteArrayInputStream(bytes), ".png");

        } catch (IOException e) {
            System.err.println("Failed saving screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
