package com.qa.automationexercise.utils;

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
}
