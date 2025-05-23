package com.qa.automationexercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.automationexercise.base.BaseTest;


public class LoginPageTest extends BaseTest{

	@Test
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginTitle();
		Assert.assertEquals(actualTitle, "Automation Exercise");
	}
	
	@Test
	public void loginPageURLTest() {
		String actualURl = loginPage.getLoginPageURL();
		Assert.assertTrue(actualURl.contains("https://automationexercise.com/login"));
	}
	
	@Test
	public void logoExistTest() {
		Assert.assertTrue(loginPage.isLogoExist());
	}
	
	@Test(priority = Integer.MAX_VALUE)
	public void loginTest() {
		String actualAccountPageTitle = loginPage.doLogin("mashaedu@gmail.com", "02281989El=");
		Assert.assertEquals(actualAccountPageTitle, "Automation Exercise");
		
	}
}
