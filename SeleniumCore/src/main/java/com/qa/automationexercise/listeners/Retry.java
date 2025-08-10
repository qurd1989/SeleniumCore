package com.qa.automationexercise.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITest;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 3; // Set the maximum number of retries
    @Override
    public boolean retry(ITestResult result) {
    if (!result.isSuccess()) { // Check if the test failed
        if (retryCount < maxRetryCount) { // Check if we have retries left
            retryCount++; // Increment the retry count
            result.setStatus(ITestResult.FAILURE); // Set the status to failure
            return true; // Indicate that the test should be retried
        }else {
            result.setStatus(ITestResult.FAILURE); // Set the status to failure after max retries
        }
    } else {
        result.setStatus(ITestResult.SUCCESS); // If the test passed, set the status to success
    }
        return false;
    }
}
