package com.qa.automationexercise.listeners;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.automationexercise.exceptions.FrameworkException;
import org.testng.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExtentReportListener implements ITestListener {

    private static final String REPORT_FILE_NAME = "ExtentReport.html";
    private static final String OUTPUT_FOLDER = "test-output/ExtentReports/";

    private static ExtentReports extent = init();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
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
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(OUTPUT_FOLDER + REPORT_FILE_NAME);
        spark.config().setReportName("Automation Exercise Test Report");

        assert extentReports != null;
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

        System.out.println("Test started: " + result.getName());

    }
}