package com.qa.automationexercise.pages;

import java.lang.annotation.ElementType;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.automationexercise.utils.ElementUtils;

public class TestCasesPage {
	
	private WebDriver driver;
	private ElementUtils eleUtil;

	private By testCasesText = By.xpath("//*[@class='title text-center']/*[contains(text(), 'Test Cases')]");
	private By headers = By.cssSelector("ul.navbar-nav > li");
	private By testCasesHyperLinks = By.xpath("//*[@class='panel-title']/a')]");
	
	
	public TestCasesPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}
	
	public String getTestCasesHeader(String expectedText) {
		List<WebElement> headerElmements = eleUtil.getElements(headers);
		for (WebElement e : headerElmements) {
			String text = e.getText().trim();
			if (text.equals(expectedText)) {
				return e.getAttribute("style");
				
			}
		}
		return null;
		
	}
	public String getTestCasesTestField() {
		return eleUtil.getElementText(testCasesText);
	}
	
	public List<WebElement> getAllTestCasesLink() {
		return eleUtil.getElements(testCasesHyperLinks);
	}
	
	public ProductPage navigateToProductPage(String productPage) {
		List<WebElement> headerElements = eleUtil.getElements(headers);
		eleUtil.clickElementBy(headerElements, productPage);
		return new ProductPage(driver);
	}
}
