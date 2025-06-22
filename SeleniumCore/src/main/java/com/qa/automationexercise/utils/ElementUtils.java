package com.qa.automationexercise.utils;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ElementUtils {
	private WebDriver driver;
	private Actions act;
	
	public ElementUtils(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
	}
	
	public void doClick(By locator) { 
		getElement(locator).click();
	}
	public void doClick(By locator, int timeOut) {
		
	}
	public WebElement getElement(By locator) {
 		return driver.findElement(locator);	
	}
	public  void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	
	public void waitElement(By locator, int n) {
		
	}
	public void waitElement(By locator, int n, String s) {
	
	}
	public String getElementText(By locator) {
		String eleText = getElement(locator).getText();
		if (eleText != null) {
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
	
	public void getElementsTextList(By locator) {
		List<WebElement> elelist = getElements(locator);
		
	}
}
