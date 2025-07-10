package com.qa.automationexercise.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility class for managing explicit waits in Selenium WebDriver-based automation.
 * <p>
 * This class centralizes wait logic such as waiting for element visibility, clickability,
 * and presence in the DOM. By abstracting these wait operations, it helps reduce
 * repetitive code, minimize flakiness, and improve test stability and maintainability.
 * </p>
 * <p>
 * The {@code WaitUtil} class is intended to be used across different components of the test
 * automation framework. It might be used more extensively in the future as the framework evolves
 * and requires more advanced or configurable wait strategies (e.g., fluent waits, dynamic timeouts).
 * </p>
 * <p>
 * This structure also allows for better optimization and reusability of wait-related logic.
 * </p>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>{@code
 * WaitUtil waitUtil = new WaitUtil(driver);
 * WebElement searchBox = waitUtil.waitForVisibility(By.id("searchBox"), 10);
 * }</pre>
 *
 * @author Elmar
 * @since 1.0
 * 
 */
public class WaitUtils {

	  private WebDriver driver;

	    public WaitUtils(WebDriver driver) {
	        this.driver = driver;
	    }

	    /**
	     * Waits for the visibility of the element located by the given locator.
	     */
	    public WebElement waitForVisibility(By locator, int timeoutSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }

	    /**
	     * Waits for an element to be clickable.
	     */
	    public WebElement waitForElementToBeClickable(By locator, int timeoutSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	        return wait.until(ExpectedConditions.elementToBeClickable(locator));
	    }

	    /**
	     * Waits for presence of an element (without checking visibility).
	     */
	    public WebElement waitForPresence(By locator, int timeoutSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	    }
}
