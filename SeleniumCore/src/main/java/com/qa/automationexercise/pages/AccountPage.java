package com.qa.automationexercise.pages;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.automationexercise.constants.AppConstants;
import com.qa.automationexercise.utils.ElementUtils;

public class AccountPage {
	private WebDriver driver;
	private ElementUtils elementUtils;
	
	private By loggedInAs = By.xpath("//*[contains(text(), 'Logged in as')]");
	private By logo = By.xpath("//img[@src='/static/images/home/logo.png']");
	private By testCaseLink = By.xpath("//*[@class='btn btn-success' and text()='Test Cases']");
	private By apisListForPraciteLink = By.xpath("//*[@class='btn btn-success' and text()='APIs list for practice']");
	private By headers = By.cssSelector("ul.navbar-nav > li");
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
	}
	
	public String getAccountPageTitle() {
 		String title = elementUtils.waitForTitleContainsAndReturns(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIME_OUT);
 		return title;
 	}
	
	public boolean isloggedInAsDisplayed() {
		return elementUtils.isElementDisplayed(loggedInAs);
	}
	
	public boolean isLogoIsDisplayed() {
		return elementUtils.isElementClickable(logo, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
	}
	
	public int getTotalAccountPageHeaders(){
		return elementUtils.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		
	}
	public List<String> getAccountHeaders(){
		List<WebElement> headersList= elementUtils.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> headrsValueList  = new ArrayList<String>();
		for (WebElement e : headersList) {
			String header = e.getText().trim();
			headrsValueList.add(header);
		}
		return headrsValueList;
	}
	public boolean  isTestCasesLinkClickable() {
		return elementUtils.isElementClickable(testCaseLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
	}
	public TestCasesPage navigateToTestCasesPage() {
		elementUtils.doClick(testCaseLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		return new TestCasesPage(driver);
	}

	public ProductPage navigateToProductPage() {
		//Will need to work on this methods
		return null;
	}
}
