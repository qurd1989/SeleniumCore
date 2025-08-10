package com.qa.automationexercise.listeners;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.automationexercise.exceptions.FrameworkException;
import com.qa.automationexercise.factory.DriverFactory;
import org.testng.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

public class ExtentReportListener implements ITestListener {

    private static final String REPORT_FILE_NAME = "ExtentReport.html";
    private static final String OUTPUT_FOLDER = "ExtentReports/";

    private static ExtentReports extent = init();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    private static ExtentReports extentReports;

    private static ExtentReports init() {

        Path path = Paths.get(OUTPUT_FOLDER);
        // Create the directory if it does not exist
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (Exception e) {
                throw new FrameworkException("Failed to create output directory: " + OUTPUT_FOLDER);
            }
        }
        extentReports  = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(OUTPUT_FOLDER + REPORT_FILE_NAME);
        spark.config().setReportName("Automation Exercise Test Report");

        extentReports.attachReporter(spark);
        extentReports.setSystemInfo("Application Name", "Automation Exercise");
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("User Name", "QA Team");
        extentReports.setSystemInfo("Browser", "Chrome");
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("Build#", "1.1");
        extentReports.setSystemInfo("Version", "1.0");
        extentReports.setSystemInfo("Env name", System.getProperty("env"));

        return extentReports;
    }

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Extent Report is starting...");
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println("Extent Report is finishing...");
        if (extent != null) {
            extent.flush();
            test.remove();
        }
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String  methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        System.out.println("Test started: " + methodName + " in class: " + className);
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        extentTest.assignCategory(result.getTestContext().getSuite().getName(), result.getMethod().getDescription());
        extentTest.assignCategory(className);
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }
    public synchronized void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        System.out.println("Test passed: " + methodName);
        test.get().pass("Test passed");
        test.get().pass(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot(methodName), methodName).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getMethod().getMethodName());
        String methodName = result.getMethod().getMethodName();
        test.get().fail("Test failed: " + methodName);
        test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot(methodName), methodName).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getMethod().getMethodName());
        String methodName = result.getMethod().getMethodName();
        test.get().skip(result.getThrowable());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test failed but within success percentage: " + result.getMethod().getMethodName());
    }
    /**
     * This method converts milliseconds to a Date object.
     * It is used to set the start and end time of the test in the report.
     *
     * @param startMillis The start time in milliseconds.
     * @return A Date object representing the start time.
     */
    private Date getTime(long startMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startMillis);
        return calendar.getTime();
    }
}
