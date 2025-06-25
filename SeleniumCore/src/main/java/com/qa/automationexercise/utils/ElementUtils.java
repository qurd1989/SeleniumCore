package com.qa.automationexercise.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.qa.automationexercise.exceptions.FrameworkException;

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
	
	public List<String > getElementsTextList(By locator) {
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
	
	public void selectDropdownValueByVisibleText(By locator, String visibleText) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
		
	}
	
	public void selectDropdownValueByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
		
	}
	
	public void selectDropdownValueByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	public int selectDropDownOptionsCount(By locator) {
		Select select = new Select(getElement(locator));
		return select.getOptions().size();
	}

	
}
