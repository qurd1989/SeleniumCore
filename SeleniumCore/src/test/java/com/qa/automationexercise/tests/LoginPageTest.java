package com.qa.automationexercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.automationexercise.base.BaseTest;
import com.qa.automationexercise.constants.AppConstants;


public class LoginPageTest extends BaseTest{

	@Test
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void loginPageURLTest() {
		String actualURl = loginPage.getLoginPageURL();
		System.out.println(actualURl);
		Assert.assertTrue(actualURl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test
	public void logoExistTest() {
		Assert.assertTrue(loginPage.isLogoExist());
	}
	
	@Test(priority = Integer.MAX_VALUE)
	public void loginTest() throws InterruptedException {
		String actualAccountPageTitle = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		boolean isValidTitle = actualAccountPageTitle.equals(AppConstants.LOGIN_PAGE_TITLE) ||
				actualAccountPageTitle.equals(AppConstants.ACCOUNT_PAGE_TITLE);
		Assert.assertTrue(isValidTitle, "Unexpected page title: " + actualAccountPageTitle);
		
	}
}
 