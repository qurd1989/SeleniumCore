package com.qa.automationexercise.tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.automationexercise.base.BaseTest;
import com.qa.automationexercise.constants.AppConstants;
import com.qa.automationexercise.pages.AccountPage;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		accPage = new AccountPage(driver);
	}
	@Test
	public void accountPageTitleTest() {
		String accTile = accPage.getAccountPageTitle();
		Assert.assertEquals(accTile, AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void isLoggedInAsTest() {
		Assert.assertTrue(accPage.isloggedInAsDisplayed());
	}
	
	@Test
	public void isLogoIsDisplayedTest() {
		Assert.assertTrue(accPage.isLogoIsDisplayed());
	}
	@Test
	public void isTestCasesLinkClickableTest() {
		Assert.assertTrue(accPage.isTestCasesLinkClickable());
	}
	
	@Test
	public void getTotalAccountPageHeadersTest() {
		Assert.assertEquals(accPage.getTotalAccountPageHeaders(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	
	@Test
	public void getAccountHeadersTest() {
		List<String> headerList = accPage.getAccountHeaders();
		Assert.assertEquals(headerList, AppConstants.ACTUAL_ACC_PAGE_HEADERS);
	}
}
