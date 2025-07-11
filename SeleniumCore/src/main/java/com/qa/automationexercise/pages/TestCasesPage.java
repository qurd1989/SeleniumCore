package com.qa.automationexercise.pages;

import java.lang.annotation.ElementType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.automationexercise.utils.ElementUtils;

public class TestCasesPage {
	
	private WebDriver driver;
	private ElementUtils eleUtil;

	private By testCasesText = By.xpath("//*[@class='title text-center']/*[contains(text(), 'Test Cases')]");
	private By headers = By.cssSelector("ul.navbar-nav > li >a");
	private By testCasesHyperLinks = By.xpath("//*[@class='panel-title']/a/u");
	private By testCaseHeaderText = By.xpath("//*[contains(text(), 'Click on the scenario for detailed Test Steps:')]");
	private By testCasesDetailText = By.xpath("//*[starts-with(text(), 'Below is the list of test Cases for you to practice the Automation')]");

	
	private Map<String, String> testCasesMap;
	
	public TestCasesPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}
	
	public String  getTestCasesTabActiveStatus(String expectedText) {
		List<WebElement> headerElmements = eleUtil.getElements(headers);
		for (WebElement e : headerElmements) {
			String text = e.getText().trim();
			if (text.equals(expectedText)) {
				return e.getAttribute("style");
			}
		}
		return null;	
	}
	
	public String getTestCasesFieldText() {
		return eleUtil.getElementText(testCasesText);
	}
	
	public List<WebElement> getAllTestCasesLink() {
		return eleUtil.getElements(testCasesHyperLinks);
	}
	
	public String getTestCaseHeaderText() {
		return eleUtil.getElementText(testCaseHeaderText);
	}
	
	public ProductPage navigateToProductPage(String productPage) {
		List<WebElement> headerElements = eleUtil.getElements(headers);
		eleUtil.clickElementBy(headerElements, productPage);
		return new ProductPage(driver);
	}
	
	private void getTestCasesDetails() {
		List<WebElement> headerElmements = eleUtil.getElements(testCasesHyperLinks);
		testCasesMap = new HashMap<String, String>();
		for (WebElement e : headerElmements) {
			String text = e.getText().trim();
			String testCaseData [] = text.split(":");
			
			String testCaseKey = testCaseData[0].trim();
			String testCaseValue = testCaseData[1].trim();
			System.out.println(testCaseKey + " : "+ testCaseValue);
			testCasesMap.put(testCaseKey, testCaseValue);
			}
	}
	
	private String getTestCasesDetailText() {
		return eleUtil.getElementText(testCasesDetailText);
		
	}
	public Map<String, String> getTestCasesData() {
		testCasesMap = new HashMap<String, String>();
		testCasesMap.put("Test Cases Details text", getTestCasesDetailText());
		getTestCasesDetails();
		System.out.println("Test Cases full Text" + testCasesMap);
		return testCasesMap;
	}
}
