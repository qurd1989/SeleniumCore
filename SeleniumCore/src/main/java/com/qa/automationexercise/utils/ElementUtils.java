package com.qa.automationexercise.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import com.qa.automationexercise.factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.automationexercise.exceptions.FrameworkException;

public class ElementUtils {
	private WebDriver driver;
	private Actions act;
	private WaitUtils waitUtil;
	private JavaScriptUtil jsUtil;

	
	public ElementUtils(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
		waitUtil = new WaitUtils(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public void doClick(By locator) { 
		getElement(locator).click();
	}
	public void doClick(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	/**
	 * Clicks on an element and highlights it if eleHighlight is set to true.
	 * @param element the By locator of the element to click
	 */
	private void checkElementHighlight(WebElement element) {
		if (Boolean.parseBoolean(DriverFactory.isEleHighlight)){
			jsUtil.flash(element);
		}
	}
	public WebElement getElement(By locator) {
		WebElement element = waitUtil.waitForVisibility(locator, 10);
		checkElementHighlight(element);
	   return element;
    }
	
	public  void doSendKeys(By locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
	}
	
	public  void doSendKeys(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}
	
	public void doSendKeys(By locator, CharSequence... value) {

		getElement(locator).sendKeys(value);
	}
	
	/**
	 * Waits until the element located by the given locator becomes visible and returns it.
	 *
	 * @param locator the By locator of the element to wait for
	 * @param timeoutSeconds the maximum time to wait in seconds
	 * @return the visible WebElement
	 */
	public WebElement waitElementVisible(By locator, int timeoutSeconds) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitElement(By locator, int n, String s) {
	
	}

	public String getElementText(By locator) {
		String eleText = getElement(locator).getText();
		if (!eleText.isEmpty()) {
			return eleText;
		}else {
			System.out.println("Element test is null: " + eleText);
		}
		return null;
	}
	 
	public boolean isElementDisplayed(By locator) {
		
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element is not displayed : " + locator);
			return false;
		}
	}
	
	public boolean isElementEnabled(By locator) {
		try {
			return getElement(locator).isEnabled();
		} catch (Exception e) {
			System.out.println("Elemen is not enabled : " + locator);
			return false;
		}
	}
	
	public String doElementGetAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}
	
	public List<WebElement> getElements(By locator) { 
		return driver.findElements(locator);
	}
	
	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}
	
	public List<String> getElementsTextList(By locator) {
		List<WebElement> elelist = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		
		for(WebElement e : elelist) {
			String eleText = e.getText();
			if (eleText.length() != 0) {
				eleTextList.add(eleText);
			}
		}
		return eleTextList;
	}
	/**
	 * clicks on an element from a list of elements matching the target text
	 * @param elements List of WebElements to search
	 * @param targetText to match
	 */
	public void clickElementBy(List<WebElement> elements, String targetText) {
		
		for(WebElement e : elements) {
			String eleText = e.getText().trim();
			if (eleText.equalsIgnoreCase(targetText)) {
				e.click();
				return;
			}
		}
		throw new NoSuchElementException("No such element with text! " + targetText);
	}
	
	public void printElementList(By locator) { 
		List<String> eleTextList = getElementsTextList(locator);
		for(String e : eleTextList) {
			System.out.println(e);
		}
	}
	
	//this function is used for search function of the application
	public boolean doSreach(By searchedField, String searchkey, By suggestions, String matchedValue) throws InterruptedException {
		boolean flag = false; 
		doSendKeys(searchedField, searchkey);
		Thread.sleep(3000);
		List<WebElement> suggList = getElements(suggestions);
		int suggListSize = suggList.size();
		System.out.println("Total number of  suggetions: " + suggListSize);
		
		if (suggListSize == 0) { 
			System.out.println("No suggestions found....");
			throw new FrameworkException("No Suggestions FOUND!");
			
		}
		for (WebElement webElement : suggList) {
			String text = webElement.getText();
			System.out.println(text);
			if (text.contains(matchedValue)) {
				webElement.click();
				flag = true;
				break;
			}
		}
		
		if (flag) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public boolean isElementPresent(By locator) {
		if (getElementsCount(locator) == 1) {
			return true;
		}else { 
			return false;
		}
	}
	
	public boolean isElementPresent(By locator, int expectedElementCount) {
		if (getElementsCount(locator) == expectedElementCount ) {
			return true;
		}else { 
			return false;
		}
	}
	
	//****************** Select Drop Down Utils***********************
	
	private Select getSelect(By locator) {
		return new Select(getElement(locator));
	}
	
	public void selectDropdownValueByVisibleText(By locator, String visibleText) {
		
		getSelect(locator).selectByVisibleText(visibleText);
		
	}
	
	public void selectDropdownValueByIndex(By locator, int index) {
		getSelect(locator).selectByIndex(index);
		
	}
	
	public void selectDropdownValueByValue(By locator, String value) {
		getSelect(locator).selectByValue(value);
	}
	
	public int selectDropDownOptionsCount(By locator) {
		return getSelect(locator).getOptions().size();
	}
	
	public void getDropDownOptionsTextList(By locator, String value) {
		List<WebElement> optionList = getSelect(locator).getOptions();
		SelectDropdown(optionList, value);
	}
	
	public boolean waitForTitleContains(String fractionTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			return wait.until(ExpectedConditions.titleContains(fractionTitle));
		} catch (TimeoutException e) {
			System.out.println("title is not matched! ");
			return false;
		}
	}
	public String waitForTitleContainsAndReturns(String fractionTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.titleContains(fractionTitle));
			return driver.getTitle();
		} catch (TimeoutException e) {
			System.out.println("title is not matched!");
			return "-1";
		}
	}
	
	public String getPageURLContains(String fractionURL, int timeout) {
		if (waitForURLContains(fractionURL, timeout)) {
			return driver.getCurrentUrl();
		}else { 
			return "-1";
		}
	}

	private boolean waitForURLContains(String fractionURL, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			return wait.until(ExpectedConditions.urlContains(fractionURL));//true
		} catch (TimeoutException e) {
			System.out.println("URL is not matched! ");
			return false;
		}
	}

	/**
	 * An Expectation for checking that an element is present on the DOM of a page and visible on the page
	 * as well. Visibility means that the element is not only displays but also has a height and width that
	 * is greater than 0.
	 * Default polling time/interval time = 500ms 
	 * @param locator  is used to find the element
	 * @param timeOut is the maximum time to wait for the element to be visible
	 */
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		checkElementHighlight(element);
		return element;
	}
	
	public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public WebElement waitForElementVisible(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}


	/**
	 * Waits for an element to be visible using fluent wait features.
	 *
	 * @param locator the By locator of the element to wait for
	 * @param timeOut the maximum time to wait in seconds
	 * @param pollingTime the polling interval in milliseconds
	 * @return the visible WebElement
	 */
	public WebElement waitForElementVisibleWithFluentFeatures(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class)
				.ignoring(TimeoutException.class)
				.ignoring(ElementNotInteractableException.class)
				.withMessage("Element is not visible");
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * @param locator is used to select value from dropdown without using the Select class
	 * 
	 */
	public void selectDropDownValue(By locator, String value) {
		List<WebElement> optionList = getElements(locator);
		SelectDropdown(optionList, value);
	} 
	
	private void SelectDropdown(List<WebElement> optionList, String value) {
		System.out.println("total number of options: " + optionList.size());
		for (WebElement e : optionList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}

	}
	
	/**
	 * It verifies that if element is clickable or not 
	 * @param locator
	 * @param timeOut
	 */
	public boolean isElementClickable(By locator, int timeOut) {
		try {
			WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			return true;
		} catch (TimeoutException e) {
			System.out.println("Element not clickable! " + locator);
			return false;
		}
	}
	
	/**
	 * Switches the WebDriver context to a window with the specified URL.
	 *
	 * @param driver       the instance of WebDriver
	 * @param expectedURL  the exact URL of the window to switch to
	 * @throws RuntimeException if no window with the specified URL is found
	 */
	public void switchToWindowByURL(WebDriver driver, String expectedURL) {
	    Set<String> windowHandles = driver.getWindowHandles();

	    for (String handle : windowHandles) {
	        driver.switchTo().window(handle);
	        String currentURL = driver.getCurrentUrl();
	        if (currentURL.equalsIgnoreCase(expectedURL)) {
	            System.out.println("Switched to window with URL: " + currentURL);
	            return;
	        }
	    }

	    throw new RuntimeException("Window with URL '" + expectedURL + "' not found.");
	}
	/**
	 * Switches the WebDriver context to a new window that is not the original one.
	 *
	 * @param driver the instance of WebDriver
	 * @throws RuntimeException if no new window is found
	 */
	public static void switchToNewWindow(WebDriver driver) {
	    String originalWindow = driver.getWindowHandle();
	    Set<String> allWindows = driver.getWindowHandles();

	    for (String windowHandle : allWindows) {
	        if (!windowHandle.equals(originalWindow)) {
	            driver.switchTo().window(windowHandle);
	            System.out.println("Switched to new window: " + driver.getTitle());
	            return;
	        }
	    }

	    throw new RuntimeException("No new window found to switch.");
	}

	/**
	 * Switches the WebDriver context to a window with the specified title.
	 *
	 * @param driver        the instance of WebDriver
	 * @param expectedTitle the exact title of the window to switch to
	 * @throws RuntimeException if no window with the specified title is found
	 */
	public static void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
	    Set<String> windowHandles = driver.getWindowHandles();

	    for (String handle : windowHandles) {
	        driver.switchTo().window(handle);
	        String currentTitle = driver.getTitle();
	        if (currentTitle.equalsIgnoreCase(expectedTitle)) {
	            System.out.println("Switched to window: " + currentTitle);
	            return;
	        }
	    }

	    throw new RuntimeException("Window with title '" + expectedTitle + "' not found.");
	}
	/**
	 * Closes all browser windows except the original (parent) window and
	 * switches back to the parent window.
	 *
	 * @param driver the instance of WebDriver
	 */
	public static void closeAllOtherWindows(WebDriver driver) {
	    String parent = driver.getWindowHandle();
	    Set<String> allWindows = driver.getWindowHandles();

	    for (String handle : allWindows) {
	        if (!handle.equals(parent)) {
	            driver.switchTo().window(handle);
	            driver.close();
	        }
	    }

	    driver.switchTo().window(parent);
	}

}
