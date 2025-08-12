package com.qa.automationexercise.listeners;

import com.qa.automationexercise.factory.DriverFactory;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestAllureListener  implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    /**
     * This method is invoked when a test case fails.
     * It captures a screenshot and attaches it to the Allure report.
     *
     * @param iTestResult The result of the test case that just ran.
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * This method is invoked when a test case fails.
     * It saves a text log message to the Allure report.
     * @param message
     * @return
     */
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Override
    public  void onStart(ITestContext result) {
        System.out.println("Test started: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext result) {
        System.out.println("Test finished: " + result.getName());

    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + getTestMethodName(result) + "started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + getTestMethodName(result) + " passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + getTestMethodName(result) + " failed");
        Object currentClass = result.getInstance();
      if (DriverFactory.getDriver() instanceof WebDriver) {
          System.out.println("Taking screenshot for failed test: " + getTestMethodName(result));
            saveScreenshotPNG(DriverFactory.getDriver());
      }
      // Save a text log message for the failed test
        saveTextLog("Test failed: " + getTestMethodName(result) + " failed with exception: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + getTestMethodName(result) + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test failed but within success percentage: " + getTestMethodName(result) + " failed but within success percentage");
    }

}
